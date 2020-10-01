package jp.co.ryoutanomura.salesmanagement.repositories;

import java.util.UUID;
import jp.co.ryoutanomura.salesmanagement.entities.Customer;

public class CustomerRepositoryImpl implements CustomerRepository {

  @Override
  public Customer find(final UUID id) {
    return Customer.builder().id(UUID.randomUUID()).name("hoge").build();
  }
}
