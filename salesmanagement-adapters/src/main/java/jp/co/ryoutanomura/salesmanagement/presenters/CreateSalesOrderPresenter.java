package jp.co.ryoutanomura.salesmanagement.presenters;

import javax.inject.Named;
import jp.co.ryoutanomura.salesmanagement.usecases.salesorder.CreateSalesOrderUsecaseResponse;
import jp.co.ryoutanomura.salesmanagement.viewmodels.SalesOrderViewModel;

@Named
public class CreateSalesOrderPresenter {

  public SalesOrderViewModel exec(final CreateSalesOrderUsecaseResponse response) {
    return SalesOrderViewModel.builder().build();
  }
}
