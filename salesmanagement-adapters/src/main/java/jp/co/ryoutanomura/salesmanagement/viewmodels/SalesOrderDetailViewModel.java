package jp.co.ryoutanomura.salesmanagement.viewmodels;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.UUID;
import jp.co.ryoutanomura.salesmanagement.entities.Item;
import jp.co.ryoutanomura.salesmanagement.entities.SalesOrderDetail;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SalesOrderDetailViewModel {

  private final UUID id;

  private final Item item;
  private final BigDecimal quantity;
  private final BigDecimal price;
  private final BigDecimal amount;
  private final BigDecimal amountWithTax;

  private final ZonedDateTime createdAt;
  private final ZonedDateTime updatedAt;

  public static SalesOrderDetailViewModel from(final SalesOrderDetail entity) {
    return SalesOrderDetailViewModel.builder()
        .id(entity.getId())
        .item(entity.getItem())
        .quantity(entity.getQuantity())
        .price(entity.getPrice())
        .amount(entity.getAmount())
        .amountWithTax(entity.getAmountWithTax())
        .build();
  }
}
