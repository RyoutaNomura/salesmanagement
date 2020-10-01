package jp.co.ryoutanomura.salesmanagement.repositories;

import java.util.UUID;
import jp.co.ryoutanomura.salesmanagement.entities.Customer;

public interface CustomerRepository {

  Customer find(UUID id);
}
