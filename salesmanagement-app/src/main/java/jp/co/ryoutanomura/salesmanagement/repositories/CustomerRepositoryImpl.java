package jp.co.ryoutanomura.salesmanagement.repositories;

import static jp.co.ryoutanomura.salesmanagement.jooq.tables.Customers.CUSTOMERS;

import java.util.Optional;
import java.util.UUID;
import javax.inject.Inject;
import javax.inject.Named;
import jp.co.ryoutanomura.salesmanagement.entities.Customer;
import lombok.AllArgsConstructor;
import lombok.val;
import org.jooq.DSLContext;

@Named
@AllArgsConstructor
public class CustomerRepositoryImpl implements CustomerRepository {

  @Inject
  private final DSLContext ctx;

  @Override
  public Optional<Customer> find(final UUID id) {
    val entity = this.ctx.selectFrom(CUSTOMERS).where(CUSTOMERS.ID.eq(id)).fetchOptional();
    System.out.println(entity);
    return entity.map(c -> Customer.builder().id(c.getId()).name(c.getName()).build());
  }
}
