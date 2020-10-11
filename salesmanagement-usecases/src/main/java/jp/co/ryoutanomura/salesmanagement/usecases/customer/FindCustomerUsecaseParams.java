package jp.co.ryoutanomura.salesmanagement.usecases.customer;

import java.util.UUID;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FindCustomerUsecaseParams {

  private UUID id;
}
