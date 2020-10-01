package jp.co.ryoutanomura.salesmanagement.usecases;

import jp.co.ryoutanomura.salesmanagement.entities.SalesOrder;
import lombok.Data;

@Data
public class CreateSalesOrderUsecaseResponse {

  private final SalesOrder salesOrder;
}
