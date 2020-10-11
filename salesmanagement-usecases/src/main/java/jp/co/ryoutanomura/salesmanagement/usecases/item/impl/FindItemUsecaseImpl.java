package jp.co.ryoutanomura.salesmanagement.usecases.item.impl;

import javax.inject.Inject;
import javax.inject.Named;
import jp.co.ryoutanomura.salesmanagement.entities.Item;
import jp.co.ryoutanomura.salesmanagement.repositories.ItemRepository;
import jp.co.ryoutanomura.salesmanagement.usecases.item.FindItemUsecase;
import jp.co.ryoutanomura.salesmanagement.usecases.item.FindItemUsecaseParams;
import jp.co.ryoutanomura.salesmanagement.usecases.item.FindItemUsecaseResponse;
import lombok.val;

@Named
public class FindItemUsecaseImpl implements FindItemUsecase {

  @Inject
  ItemRepository repo;

  @Override
  public FindItemUsecaseResponse exec(final FindItemUsecaseParams params) {
    val entity = this.repo.find(params.getId());
    return FindItemUsecaseResponse.builder().item(entity.orElse(Item.builder().build())).build();
  }
}
