package jp.co.ryoutanomura.salesmanagement.usecases.salesorder;

import jp.co.ryoutanomura.salesmanagement.entities.SalesOrder;
import lombok.Data;

@Data
public class CreateSalesOrderUsecaseResponse {

  private final SalesOrder salesOrder;
}
