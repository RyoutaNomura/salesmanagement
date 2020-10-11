package jp.co.ryoutanomura.salesmanagement.usecases.item;

import jp.co.ryoutanomura.salesmanagement.entities.Item;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FindItemUsecaseResponse {

  private Item item;
}
