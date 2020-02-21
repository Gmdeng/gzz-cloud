package com.gzz.cloud.order.service.impl;

import com.gzz.cloud.order.domain.Order;
import com.gzz.cloud.order.service.IOrderService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Service
public class OrderService implements IOrderService {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod="makeOrderError")
    @Override
    public Object makeOrder(Order o) {
        Object obj = restTemplate.getForObject("http://member-service/api/v1/member/getInfo/" + UUID.randomUUID().toString().replaceAll("-", ""), Object.class);

        System.out.println(obj);
        return obj;
    }

    // 熔断器
    public Object makeOrderError(Order o) {
        return "Hi," + o.getNo() + ",sorry,error!";
    }

}
