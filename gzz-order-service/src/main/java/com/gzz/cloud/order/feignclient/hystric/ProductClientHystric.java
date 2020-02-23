package com.gzz.cloud.order.feignclient.hystric;

import com.gzz.cloud.order.feignclient.IProductClient;
import org.springframework.stereotype.Component;

@Component
public class ProductClientHystric implements IProductClient {
    @Override
    public Object getProductInfo(String no) {
        return "调用getProductInfo，断了。。。。"+ no;
    }
}
