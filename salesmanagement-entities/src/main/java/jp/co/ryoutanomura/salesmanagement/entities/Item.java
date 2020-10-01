package jp.co.ryoutanomura.salesmanagement.entities;

import java.math.BigDecimal;
import java.util.UUID;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Item {

  private final UUID id;
  private final String name;
  private final BigDecimal standardPrice;
}
