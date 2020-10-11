package jp.co.ryoutanomura.salesmanagement.usecases.customer.impl;

import javax.inject.Inject;
import javax.inject.Named;
import jp.co.ryoutanomura.salesmanagement.entities.Customer;
import jp.co.ryoutanomura.salesmanagement.repositories.CustomerRepository;
import jp.co.ryoutanomura.salesmanagement.usecases.customer.FindCustomerUsecase;
import jp.co.ryoutanomura.salesmanagement.usecases.customer.FindCustomerUsecaseParams;
import jp.co.ryoutanomura.salesmanagement.usecases.customer.FindCustomerUsecaseResponse;
import lombok.val;

@Named
public class FindCustomerUsecaseImpl implements FindCustomerUsecase {

  @Inject
  CustomerRepository repo;

  @Override
  public FindCustomerUsecaseResponse exec(final FindCustomerUsecaseParams params) {
    val entity = this.repo.find(params.getId());
    return FindCustomerUsecaseResponse.builder().Customer(entity.orElse(Customer.builder().build()))
        .build();
  }
}
