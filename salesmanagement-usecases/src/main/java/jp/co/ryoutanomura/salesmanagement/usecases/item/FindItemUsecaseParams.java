package jp.co.ryoutanomura.salesmanagement.usecases.item;

import java.util.UUID;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FindItemUsecaseParams {

  private UUID id;
}
