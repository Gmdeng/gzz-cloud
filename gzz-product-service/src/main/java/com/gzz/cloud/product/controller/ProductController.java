package com.gzz.cloud.product.controller;

import com.gzz.cloud.product.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 会员信息
 */
@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

    @Value("${env.name}")
    String envname;

    @Autowired
    private IProductService productService;

    @RequestMapping("/getInfo/{no}")
    public Object getProductInfo(@PathVariable("no") String no) {
        return productService.getProduct(no);
    }

    @RequestMapping("/getList")
    public Object getProductList() {
        return productService.getProductList();
    }

    @RequestMapping("/getConfig")
    public Object getConfig() {
        return envname;
    }
}
