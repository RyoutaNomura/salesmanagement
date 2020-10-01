package jp.co.ryoutanomura.salesmanagement.repositories;

import java.util.Optional;
import java.util.UUID;
import javax.inject.Inject;
import javax.inject.Named;
import jp.co.ryoutanomura.salesmanagement.entities.SalesOrder;
import jp.co.ryoutanomura.salesmanagement.entities.SalesOrderDetail;
import jp.co.ryoutanomura.salesmanagement.jooq.tables.SalesOrderDetails;
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
        .selectFrom(SalesOrders.SALES_ORDERS)
        .where(SalesOrders.SALES_ORDERS.ID.eq(id))
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
              .from(SalesOrderDetails.SALES_ORDER_DETAILS)
              .where(SalesOrderDetails.SALES_ORDER_DETAILS.SALES_ORDER_ID.eq(id))
              .fetch();

          lines.forEach(l -> headEntity.addDetail(SalesOrderDetail.builder()
              .id(l.getValue(SalesOrderDetails.SALES_ORDER_DETAILS.ID))
              .item(null)
              .price(l.getValue(SalesOrderDetails.SALES_ORDER_DETAILS.PRICE))
              .quantity(l.getValue(SalesOrderDetails.SALES_ORDER_DETAILS.QUANTITY))
              .amount(l.getValue(SalesOrderDetails.SALES_ORDER_DETAILS.AMOUNT))
              .amountWithTax(l.getValue(SalesOrderDetails.SALES_ORDER_DETAILS.AMOUNT_WITH_TAX))
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
