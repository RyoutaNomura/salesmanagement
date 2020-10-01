package jp.co.ryoutanomura.salesmanagement.usecases;

import java.util.UUID;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FindSalesOrderUsecaseParams {

  private final UUID id;
}
