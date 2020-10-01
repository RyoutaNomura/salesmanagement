package jp.co.ryoutanomura.salesmanagement.app.controllers;

import java.util.UUID;
import javax.inject.Inject;
import jp.co.ryoutanomura.salesmanagement.adapters.SalesOrderAdapter;
import jp.co.ryoutanomura.salesmanagement.viewmodels.SalesOrderViewModel;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/salesorder")
@Scope("request")
@ComponentScan("jp.co.ryoutanomura.salesmanagement")
public class SalesOrderController {

  @Inject
  private SalesOrderAdapter adapter;

  @RequestMapping(value = "/{salesOrderId}", method = RequestMethod.GET)
  @ResponseBody
  SalesOrderViewModel find(@PathVariable("salesOrderId") final UUID id) {
    return this.adapter.find(id);
  }
}
