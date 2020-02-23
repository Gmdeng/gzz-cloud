package com.gzz.cloud.order.feignclient.hystric;

import com.gzz.cloud.order.feignclient.IProductClient;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class ProductClientFallbackFactory implements FallbackFactory<IProductClient> {
    @Override
    public IProductClient create(Throwable throwable) {
        return new IProductClient() {
            @Override
            public Object getProductInfo(String no){
                // new thread去通知程序员处理。
                new Thread(()->{
                    System.out.println("发送紧急消息。。。。。。");
                }).start();
                return "连接超时,稍后重试";
            }
        };
    }
}
