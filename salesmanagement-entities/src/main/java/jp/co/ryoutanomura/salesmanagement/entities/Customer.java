package jp.co.ryoutanomura.salesmanagement.entities;

import java.util.UUID;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Customer {

  private final UUID id;
  private final String name;
}
