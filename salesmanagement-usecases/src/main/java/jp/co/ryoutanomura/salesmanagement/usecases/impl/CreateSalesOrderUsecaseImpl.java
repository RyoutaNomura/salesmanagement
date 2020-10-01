package jp.co.ryoutanomura.salesmanagement.usecases.impl;

import javax.inject.Inject;
import javax.inject.Named;
import jp.co.ryoutanomura.salesmanagement.entities.SalesOrder;
import jp.co.ryoutanomura.salesmanagement.entities.SalesOrderDetail;
import jp.co.ryoutanomura.salesmanagement.repositories.SalesOrderRepository;
import jp.co.ryoutanomura.salesmanagement.usecases.CreateSalesOrderUsecase;
import jp.co.ryoutanomura.salesmanagement.usecases.CreateSalesOrderUsecaseParams;
import jp.co.ryoutanomura.salesmanagement.usecases.CreateSalesOrderUsecaseResponse;
import lombok.AllArgsConstructor;
import lombok.val;

@AllArgsConstructor
@Named
public class CreateSalesOrderUsecaseImpl implements CreateSalesOrderUsecase {

  @Inject
  private final SalesOrderRepository repository;

  @Override
  public CreateSalesOrderUsecaseResponse exec(CreateSalesOrderUsecaseParams params) {
    val salesOrder = SalesOrder.builder()
        .customer(params.getCustomer())
        .orderDate(params.getOrderDate())
        .slipNumber("")
        .build();

    params.getDetails().stream().forEach(d ->
        salesOrder.addDetail(
            SalesOrderDetail.builder()
                .item(d.getItem())
                .price(d.getPrice())
                .quantity(d.getQuantity())
                .salesOrder(salesOrder)
                .build()
        )
    );

    repository.save(salesOrder);

    val response = new CreateSalesOrderUsecaseResponse(salesOrder);
    return response;
  }
}
