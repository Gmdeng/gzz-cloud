package com.gzz.cloud.order.controller;

import com.gzz.cloud.order.domain.Order;
import com.gzz.cloud.order.feignclient.IProductService;
import com.gzz.cloud.order.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/order")
public class OrderController {
    @Autowired
    private IOrderService orderService;
    @Autowired
    private IProductService productService;

    @RequestMapping("/buildOrder")
    public Object buildOrder() {
        Order o = new Order();
        o.setNo(UUID.randomUUID().toString());
        o.setAmount(new BigDecimal("88.88"));
        o.setCreateDate(new Date());
        o.setMemberNo("eeeeeeeeeeeeeeeeeee");
        Object ret = orderService.makeOrder(o);
        return ret;
    }

    @RequestMapping("/showOrder")
    public Object showOrder(){
        return productService.getProductInfo("952700000GM");
    }
}
