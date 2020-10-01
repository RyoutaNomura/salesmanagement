package jp.co.ryoutanomura.salesmanagement.viewmodels;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import jp.co.ryoutanomura.salesmanagement.entities.Customer;
import jp.co.ryoutanomura.salesmanagement.entities.SalesOrder;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SalesOrderViewModel {

  private final UUID id;
  private final String slipNumber;

  private final LocalDate orderDate;
  private final Customer customer;
  private final List<SalesOrderDetailViewModel> details;
  private final ZonedDateTime createdAt;
  private final ZonedDateTime updatedAt;
  private BigDecimal amount;
  private BigDecimal amountWithTax;

  public static SalesOrderViewModel from(final SalesOrder salesOrder) {
    return SalesOrderViewModel.builder()
        .id(salesOrder.getId())
        .amount(salesOrder.getAmount())
        .amountWithTax(salesOrder.getAmountWithTax())
        .customer(salesOrder.getCustomer())
        .orderDate(salesOrder.getOrderDate())
        .details(salesOrder.getDetails().stream()
            .map(SalesOrderDetailViewModel::from)
            .collect(Collectors.toList()))
        .build();
  }

}
