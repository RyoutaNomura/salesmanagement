package jp.co.ryoutanomura.salesmanagement.usecases.impl;

import javax.inject.Inject;
import javax.inject.Named;
import jp.co.ryoutanomura.salesmanagement.entities.SalesOrder;
import jp.co.ryoutanomura.salesmanagement.repositories.SalesOrderRepository;
import jp.co.ryoutanomura.salesmanagement.usecases.FindSalesOrderUsecase;
import jp.co.ryoutanomura.salesmanagement.usecases.FindSalesOrderUsecaseParams;
import jp.co.ryoutanomura.salesmanagement.usecases.FindSalesOrderUsecaseResponse;
import lombok.AllArgsConstructor;
import lombok.val;

@AllArgsConstructor
@Named
public class FindSalesOrderUsecaseImpl implements FindSalesOrderUsecase {

  @Inject
  private final SalesOrderRepository repo;

  @Override
  public FindSalesOrderUsecaseResponse exec(FindSalesOrderUsecaseParams params) {
    val entity = repo.find(params.getId());
    return FindSalesOrderUsecaseResponse.builder()
        .salesOrder(entity.orElse(SalesOrder.builder().id(null).build())).build();
  }
}
