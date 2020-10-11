package jp.co.ryoutanomura.salesmanagement.usecases.salesorder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import jp.co.ryoutanomura.salesmanagement.entities.Customer;
import jp.co.ryoutanomura.salesmanagement.entities.Item;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateSalesOrderUsecaseParams {

  private final Customer customer;
  private final LocalDate orderDate;
  private final List<DetailParams> details;

  @Data
  @Builder
  public static class DetailParams {

    private final Item item;
    private final BigDecimal price;
    private final BigDecimal quantity;
  }
}
