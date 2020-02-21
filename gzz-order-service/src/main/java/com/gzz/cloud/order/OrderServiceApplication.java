package com.gzz.cloud.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 *  订单服务
 *
 * @author G-m
 */
//开启hystrix熔断
@EnableHystrix
@EnableEurekaClient
@EnableFeignClients
@SpringBootApplication
public class OrderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderServiceApplication.class, args);
	}

	@Bean
	@LoadBalanced  //注解表明这个restRemplate开启负载均衡的功能。
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
