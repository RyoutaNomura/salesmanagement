package jp.co.ryoutanomura.salesmanagement.usecases.customer;

import jp.co.ryoutanomura.salesmanagement.entities.Customer;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FindCustomerUsecaseResponse {

  private Customer Customer;
}
