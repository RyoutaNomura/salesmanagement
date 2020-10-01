package jp.co.ryoutanomura.salesmanagement.adapters;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.inject.Named;
import jp.co.ryoutanomura.salesmanagement.entities.Customer;
import jp.co.ryoutanomura.salesmanagement.entities.Item;
import jp.co.ryoutanomura.salesmanagement.presenters.CreateSalesOrderPresenter;
import jp.co.ryoutanomura.salesmanagement.presenters.FindSalesOrderPresenter;
import jp.co.ryoutanomura.salesmanagement.usecases.CreateSalesOrderUsecase;
import jp.co.ryoutanomura.salesmanagement.usecases.CreateSalesOrderUsecaseParams;
import jp.co.ryoutanomura.salesmanagement.usecases.FindSalesOrderUsecase;
import jp.co.ryoutanomura.salesmanagement.usecases.FindSalesOrderUsecaseParams;
import jp.co.ryoutanomura.salesmanagement.viewmodels.SalesOrderViewModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.val;

@AllArgsConstructor
@Named
public class SalesOrderAdapter {

  @Inject
  private final CreateSalesOrderUsecase createSalesOrderUsecase;
  @Inject
  private final CreateSalesOrderPresenter presenter;
  @Inject
  private final FindSalesOrderUsecase findSalesOrderUsecase;
  @Inject
  private final FindSalesOrderPresenter findSalesOrderPresenter;

  public SalesOrderViewModel find(final UUID id) {
    val res = this.findSalesOrderUsecase.exec(FindSalesOrderUsecaseParams.builder().id(id).build());
    return this.findSalesOrderPresenter.exec(res);
  }

  public SalesOrderViewModel create(final UUID customerId, final LocalDate orderDate,
      final List<SalesOrderControllerParams> details) {

    val customer = Customer.builder().build();// customerRepo.find(customerId);
    val item = Item.builder().build();
    val input = CreateSalesOrderUsecaseParams.builder()
        .customer(customer)
        .orderDate(orderDate)
        .details(details.stream()
            .map(d -> CreateSalesOrderUsecaseParams.DetailParams.builder()
                .item(item)
                .price(d.getPrice())
                .quantity(d.getQuantity())
                .build()
            )
            .collect(Collectors.toList())
        ).build();
    val res = this.createSalesOrderUsecase.exec(input);
    return this.presenter.exec(res);
  }

  @Data
  @Builder
  public static class SalesOrderControllerParams {

    private final UUID itemId;
    private final BigDecimal price;
    private final BigDecimal quantity;
  }
}
