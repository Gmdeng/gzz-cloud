package com.gzz.cloud.order.service.impl;

import com.gzz.cloud.order.domain.Order;
import com.gzz.cloud.order.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Service
public class OrderService implements IOrderService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Object makeOrder(Order o) {
        Object obj = restTemplate.getForObject("http://member-service/api/v1/member/getInfo/" + UUID.randomUUID().toString(), Object.class);

        System.out.println(obj);
        return obj;
    }
}
