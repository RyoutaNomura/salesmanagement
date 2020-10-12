package jp.co.ryoutanomura.salesmanagement.repositories;

import static jp.co.ryoutanomura.salesmanagement.jooq.tables.Customers.CUSTOMERS;
import static jp.co.ryoutanomura.salesmanagement.jooq.tables.Items.ITEMS;
import static jp.co.ryoutanomura.salesmanagement.jooq.tables.SalesOrderDetails.SALES_ORDER_DETAILS;
import static jp.co.ryoutanomura.salesmanagement.jooq.tables.SalesOrders.SALES_ORDERS;

import java.util.Optional;
import java.util.UUID;
import javax.inject.Inject;
import javax.inject.Named;
import jp.co.ryoutanomura.salesmanagement.entities.Customer;
import jp.co.ryoutanomura.salesmanagement.entities.Item;
import jp.co.ryoutanomura.salesmanagement.entities.SalesOrder;
import jp.co.ryoutanomura.salesmanagement.entities.SalesOrderDetail;
import jp.co.ryoutanomura.salesmanagement.jooq.tables.SalesOrders;
import lombok.AllArgsConstructor;
import lombok.val;
import org.jooq.DSLContext;

@Named
@AllArgsConstructor
public class SalesOrderRepositoryImpl implements SalesOrderRepository {

  @Inject
  private final DSLContext ctx;

  @Override
  public Optional<SalesOrder> find(final UUID id) {
    val head = this.ctx
        .select()
        .from(SALES_ORDERS)
        .join(CUSTOMERS).on(SALES_ORDERS.CUSTOMER_ID.eq(CUSTOMERS.ID))
        .where(SalesOrders.SALES_ORDERS.ID.eq(id))
        .fetchOptional();

    return head.map(h -> {
          final SalesOrder headEntity = SalesOrder.builder()
              .id(h.getValue(SALES_ORDERS.ID))
              .slipNumber(h.getValue(SALES_ORDERS.SLIP_NUMBER))
              .customer(Customer.builder()
                  .id(h.getValue(CUSTOMERS.ID))
                  .name(h.getValue(CUSTOMERS.NAME))
                  .build())
              .orderDate(h.getValue(SALES_ORDERS.ORDER_DATE))
              .amount(h.getValue(SALES_ORDERS.AMOUNT))
              .amountWithTax(h.getValue(SALES_ORDERS.AMOUNT_WITH_TAX))
              .createdAt(h.getValue(SALES_ORDERS.CREATED_AT).toZonedDateTime())
              .updatedAt(h.getValue(SALES_ORDERS.UPDATED_AT).toZonedDateTime())
              .build();

          val lines = this.ctx
              .select()
              .from(SALES_ORDER_DETAILS)
              .join(ITEMS).on(SALES_ORDER_DETAILS.ITEM_ID.eq(ITEMS.ID))
              .where(SALES_ORDER_DETAILS.SALES_ORDER_ID.eq(id))
              .fetch();

          lines.forEach(l -> headEntity.addDetail(SalesOrderDetail.builder()
              .id(l.getValue(SALES_ORDER_DETAILS.ID))
              .item(Item.builder().id(l.getValue(ITEMS.ID)).name(l.getValue(ITEMS.NAME)).build())
              .price(l.getValue(SALES_ORDER_DETAILS.PRICE))
              .quantity(l.getValue(SALES_ORDER_DETAILS.QUANTITY))
              .amount(l.getValue(SALES_ORDER_DETAILS.AMOUNT))
              .amountWithTax(l.getValue(SALES_ORDER_DETAILS.AMOUNT_WITH_TAX))
              .salesOrder(headEntity)
              .createdAt(l.getValue(SALES_ORDER_DETAILS.CREATED_AT).toZonedDateTime())
              .updatedAt(l.getValue(SALES_ORDER_DETAILS.UPDATED_AT).toZonedDateTime())
              .build()));

          return headEntity;
        }
    );
  }

  @Override
  public void save(final SalesOrder salesOrder) {
    this.ctx.insertInto(SALES_ORDERS)
        .set(SALES_ORDERS.ID, salesOrder.getId())
        .set(SALES_ORDERS.SLIP_NUMBER, salesOrder.getSlipNumber())
        .set(SALES_ORDERS.CUSTOMER_ID, salesOrder.getCustomer().getId())
        .set(SALES_ORDERS.ORDER_DATE, salesOrder.getOrderDate())
        .set(SALES_ORDERS.AMOUNT, salesOrder.getAmount())
        .set(SALES_ORDERS.AMOUNT_WITH_TAX, salesOrder.getAmountWithTax())
        .set(SALES_ORDERS.CREATED_AT, salesOrder.getCreatedAt().toOffsetDateTime())
        .set(SALES_ORDERS.UPDATED_AT, salesOrder.getUpdatedAt().toOffsetDateTime())
        .execute();

    val insert = this.ctx.insertInto(SALES_ORDER_DETAILS,
        SALES_ORDER_DETAILS.ID,
        SALES_ORDER_DETAILS.ITEM_ID,
        SALES_ORDER_DETAILS.QUANTITY,
        SALES_ORDER_DETAILS.PRICE,
        SALES_ORDER_DETAILS.AMOUNT,
        SALES_ORDER_DETAILS.AMOUNT_WITH_TAX,
        SALES_ORDER_DETAILS.SALES_ORDER_ID,
        SALES_ORDER_DETAILS.CREATED_AT,
        SALES_ORDER_DETAILS.UPDATED_AT);

    salesOrder.getDetails().forEach(d -> insert.values(
        d.getId(),
        d.getItem().getId(),
        d.getQuantity(),
        d.getPrice(),
        d.getAmount(),
        d.getAmountWithTax(),
        d.getSalesOrder().getId(),
        d.getCreatedAt().toOffsetDateTime(),
        d.getUpdatedAt().toOffsetDateTime()
    ));
    insert.execute();
  }
}
