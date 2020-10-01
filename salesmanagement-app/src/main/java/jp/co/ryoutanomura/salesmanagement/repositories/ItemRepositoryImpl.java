package jp.co.ryoutanomura.salesmanagement.repositories;

import java.util.UUID;
import jp.co.ryoutanomura.salesmanagement.entities.Item;

public class ItemRepositoryImpl implements ItemRepository {

  @Override
  public Item find(final UUID id) {
    return Item.builder().id(UUID.randomUUID()).name("hoge").build();
  }
}
