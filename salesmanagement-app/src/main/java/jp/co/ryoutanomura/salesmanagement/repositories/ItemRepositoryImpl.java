package jp.co.ryoutanomura.salesmanagement.repositories;

import static jp.co.ryoutanomura.salesmanagement.jooq.tables.Items.ITEMS;

import java.util.Optional;
import java.util.UUID;
import javax.inject.Inject;
import javax.inject.Named;
import jp.co.ryoutanomura.salesmanagement.entities.Item;
import lombok.AllArgsConstructor;
import lombok.val;
import org.jooq.DSLContext;

@Named
@AllArgsConstructor
public class ItemRepositoryImpl implements ItemRepository {

  @Inject
  private final DSLContext ctx;

  @Override
  public Optional<Item> find(final UUID id) {
    val itemOpt = this.ctx.selectFrom(ITEMS).where(ITEMS.ID.eq(id)).fetchOptional();
    return itemOpt.map(i -> Item.builder().id(i.getId()).name(i.getName()).build());
  }
}
