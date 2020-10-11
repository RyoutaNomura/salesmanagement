package jp.co.ryoutanomura.salesmanagement.repositories;

import java.util.Optional;
import java.util.UUID;
import jp.co.ryoutanomura.salesmanagement.entities.Item;

public interface ItemRepository {

  Optional<Item> find(UUID id);
}
