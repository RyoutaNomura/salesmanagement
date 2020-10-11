package jp.co.ryoutanomura.salesmanagement.presenters;

import javax.inject.Named;
import jp.co.ryoutanomura.salesmanagement.usecases.salesorder.FindSalesOrderUsecaseResponse;
import jp.co.ryoutanomura.salesmanagement.viewmodels.SalesOrderViewModel;

@Named
public class FindSalesOrderPresenter {

  public SalesOrderViewModel exec(final FindSalesOrderUsecaseResponse response) {
    return SalesOrderViewModel.from(response.getSalesOrder());
  }
}
