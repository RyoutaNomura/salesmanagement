package jp.co.ryoutanomura.salesmanagement.usecases.salesorder;

import jp.co.ryoutanomura.salesmanagement.entities.SalesOrder;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FindSalesOrderUsecaseResponse {

  private final SalesOrder salesOrder;
}
