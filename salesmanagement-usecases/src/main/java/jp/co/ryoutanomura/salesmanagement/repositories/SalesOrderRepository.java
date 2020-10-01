package jp.co.ryoutanomura.salesmanagement.repositories;

import java.util.Optional;
import java.util.UUID;
import jp.co.ryoutanomura.salesmanagement.entities.SalesOrder;

public interface SalesOrderRepository {

  Optional<SalesOrder> find(UUID id);

  void save(SalesOrder salesOrder);
}
