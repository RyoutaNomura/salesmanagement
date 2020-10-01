package jp.co.ryoutanomura.salesmanagement.entities;

import com.google.common.collect.Lists;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SalesOrder {

  private final UUID id;
  private final String slipNumber;

  private final LocalDate orderDate;
  private final Customer customer;
  private final List<SalesOrderDetail> details = Lists.newArrayList();
  private final ZonedDateTime createdAt;
  private final ZonedDateTime updatedAt;
  private BigDecimal amount;
  private BigDecimal amountWithTax;

  public static SalesOrderBuilder builder() {
    return new SalesOrderBuilder().id(UUID.randomUUID());
  }

  public SalesOrder addDetail(final SalesOrderDetail detail) {
    this.details.add(detail);
    this.calcAmount();
    return this;
  }

  private void calcAmount() {
    this.amount = this.details.stream().map(SalesOrderDetail::getAmount)
        .reduce(BigDecimal.ZERO, BigDecimal::add);
    this.amountWithTax = this.details.stream().map(SalesOrderDetail::getAmountWithTax)
        .reduce(BigDecimal.ZERO, BigDecimal::add);
  }
}
