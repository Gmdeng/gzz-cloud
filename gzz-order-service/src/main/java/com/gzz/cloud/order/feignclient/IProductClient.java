package com.gzz.cloud.order.feignclient;

import com.gzz.cloud.order.feignclient.hystric.ProductClientFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

////使用断路器
// @FeignClient(value = "product-service", fallback = ProductServiceHystric.class)
@FeignClient(value = "product-service", fallbackFactory = ProductClientFallbackFactory.class)
public interface IProductClient {

    @RequestMapping(value = "/api/v1/product/getInfo/{no}", method = RequestMethod.GET)
    public Object getProductInfo(@PathVariable("no") String no);
}
