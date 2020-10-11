package jp.co.ryoutanomura.salesmanagement.repositories;

import java.util.Optional;
import java.util.UUID;
import jp.co.ryoutanomura.salesmanagement.entities.Customer;

public interface CustomerRepository {

  Optional<Customer> find(UUID id);
}
