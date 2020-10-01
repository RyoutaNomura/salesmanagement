package jp.co.ryoutanomura.salesmanagement.repositories;

import java.util.UUID;
import jp.co.ryoutanomura.salesmanagement.entities.Item;

public interface ItemRepository {

  Item find(UUID id);
}
