package jp.co.ryoutanomura.salesmanagement.app.controllers;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import javax.inject.Inject;
import jp.co.ryoutanomura.salesmanagement.adapters.SalesOrderAdapter;
import jp.co.ryoutanomura.salesmanagement.adapters.SalesOrderAdapter.CreateDetailParam;
import jp.co.ryoutanomura.salesmanagement.viewmodels.SalesOrderViewModel;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/salesorder")
@Scope("request")
@ComponentScan("jp.co.ryoutanomura.salesmanagement")
@Transactional
public class SalesOrderController {

  @Inject
  private SalesOrderAdapter adapter;

  @GetMapping(value = "/{salesOrderId}")
  @ResponseBody
  SalesOrderViewModel find(@PathVariable("salesOrderId") final UUID id) {
    return this.adapter.find(id);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  void create(@RequestBody final CreateMethodBody body) {
    System.out.println(body);

    this.adapter.create(
        body.getCustomerId(),
        body.getOrderDate(),
        body.details.stream()
            .map(d -> CreateDetailParam
                .builder()
                .itemId(d.getItemId())
                .price(d.getPrice())
                .quantity(d.getQuantity())
                .build())
            .collect(Collectors.toList()));
  }

  @Data
  @NoArgsConstructor
  public static class CreateMethodBody {

    private UUID customerId;
    private LocalDate orderDate;
    private List<CreateMethodBodyDetail> details;

    @Data
    @NoArgsConstructor
    private static class CreateMethodBodyDetail {

      private UUID itemId;
      private BigDecimal price;
      private BigDecimal quantity;
    }
  }
}
