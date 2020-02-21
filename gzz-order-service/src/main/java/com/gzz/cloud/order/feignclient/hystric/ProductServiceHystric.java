package com.gzz.cloud.order.feignclient.hystric;

import com.gzz.cloud.order.feignclient.IProductService;
import org.springframework.stereotype.Component;

@Component
public class ProductServiceHystric implements IProductService {
    @Override
    public Object getProductInfo(String no) {
        return "33399988";
    }
}
