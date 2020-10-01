package jp.co.ryoutanomura.salesmanagement.repositories;

import static jp.co.ryoutanomura.salesmanagement.jooq.tables.SalesOrderDetails.SALES_ORDER_DETAILS;
import static jp.co.ryoutanomura.salesmanagement.jooq.tables.SalesOrders.SALES_ORDERS;

import java.util.Optional;
import java.util.UUID;
import javax.inject.Inject;
import javax.inject.Named;
import jp.co.ryoutanomura.salesmanagement.entities.SalesOrder;
import jp.co.ryoutanomura.salesmanagement.entities.SalesOrderDetail;
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
        .selectFrom(SALES_ORDERS)
        .where(SALES_ORDERS.ID.eq(id))
        .fetchOptional();

    return head.map(h -> {
          final SalesOrder headEntity = SalesOrder.builder()
              .id(h.getId())
              .slipNumber(h.getSlipNumber())
              .customer(null)
              .orderDate(h.getOrderDate())
              .amount(h.getAmount())
              .amountWithTax(h.getAmountWithTax())
              .build();

          val lines = this.ctx
              .select()
              .from(SALES_ORDER_DETAILS)
              .where(SALES_ORDER_DETAILS.SALES_ORDER_ID.eq(id))
              .fetch();

          lines.forEach(l -> headEntity.addDetail(SalesOrderDetail.builder()
              .id(l.getValue(SALES_ORDER_DETAILS.ID))
              .item(null)
              .price(l.getValue(SALES_ORDER_DETAILS.PRICE))
              .quantity(l.getValue(SALES_ORDER_DETAILS.QUANTITY))
              .amount(l.getValue(SALES_ORDER_DETAILS.AMOUNT))
              .amountWithTax(l.getValue(SALES_ORDER_DETAILS.AMOUNT_WITH_TAX))
              .salesOrder(headEntity)
              .build()));

          return headEntity;
        }
    );
  }

  @Override
  public void save(final SalesOrder salesOrder) {

  }
}
