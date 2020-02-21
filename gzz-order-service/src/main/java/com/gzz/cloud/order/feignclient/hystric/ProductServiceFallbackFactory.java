package com.gzz.cloud.order.feignclient.hystric;

import com.gzz.cloud.order.feignclient.IProductService;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class ProductServiceFallbackFactory implements FallbackFactory<IProductService> {
    @Override
    public IProductService create(Throwable throwable) {
        return new IProductService() {
            @Override
            public Object getProductInfo(String no){
                return "连接超时,稍后重试";
            }
        };
    }
}
