package jp.co.ryoutanomura.salesmanagement.adapters;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.inject.Named;
import jp.co.ryoutanomura.salesmanagement.presenters.FindSalesOrderPresenter;
import jp.co.ryoutanomura.salesmanagement.usecases.customer.FindCustomerUsecase;
import jp.co.ryoutanomura.salesmanagement.usecases.customer.FindCustomerUsecaseParams;
import jp.co.ryoutanomura.salesmanagement.usecases.item.FindItemUsecase;
import jp.co.ryoutanomura.salesmanagement.usecases.item.FindItemUsecaseParams;
import jp.co.ryoutanomura.salesmanagement.usecases.salesorder.CreateSalesOrderUsecase;
import jp.co.ryoutanomura.salesmanagement.usecases.salesorder.CreateSalesOrderUsecaseParams;
import jp.co.ryoutanomura.salesmanagement.usecases.salesorder.FindSalesOrderUsecase;
import jp.co.ryoutanomura.salesmanagement.usecases.salesorder.FindSalesOrderUsecaseParams;
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
  private final FindSalesOrderUsecase findSalesOrderUsecase;
  @Inject
  private final FindSalesOrderPresenter findSalesOrderPresenter;
  @Inject
  private final FindItemUsecase findItemUsecase;
  @Inject
  private final FindCustomerUsecase findCustomerUsecase;

  public SalesOrderViewModel find(final UUID id) {
    val res = this.findSalesOrderUsecase.exec(FindSalesOrderUsecaseParams.builder().id(id).build());
    return this.findSalesOrderPresenter.exec(res);
  }

  public void create(final UUID customerId, final LocalDate orderDate,
      final List<CreateDetailParam> details) {

    val input = CreateSalesOrderUsecaseParams.builder()
        .customer(this.findCustomerUsecase.exec(FindCustomerUsecaseParams
                .builder()
                .id(customerId)
                .build()
            ).getCustomer()
        )
        .orderDate(orderDate)
        .details(details.stream()
            .map(d -> CreateSalesOrderUsecaseParams.DetailParams.builder()
                .item(this.findItemUsecase.exec(FindItemUsecaseParams
                        .builder()
                        .id(d.getItemId())
                        .build()
                    ).getItem()
                )
                .price(d.getPrice())
                .quantity(d.getQuantity())
                .build()
            )
            .collect(Collectors.toList())
        ).build();

    System.out.println(input);
    this.createSalesOrderUsecase.exec(input);
  }

  @Data
  @Builder
  public static class CreateDetailParam {

    private UUID itemId;
    private BigDecimal price;
    private BigDecimal quantity;
  }
}
