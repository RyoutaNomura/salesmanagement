package jp.co.ryoutanomura.salesmanagement.entities;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.UUID;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SalesOrderDetail {

  private final UUID id;

  private final Item item;
  private final BigDecimal quantity;
  private final BigDecimal price;
  private final BigDecimal amount;
  private final BigDecimal amountWithTax;

  private final SalesOrder salesOrder;
  private final ZonedDateTime createdAt;
  private final ZonedDateTime updatedAt;

  public static SalesOrderDetailBuilder builder() {
    return new SalesOrderDetailBuilder().id(UUID.randomUUID());
  }
}
