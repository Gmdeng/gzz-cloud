package com.gzz.cloud.product.service;

import com.gzz.cloud.product.domain.Product;

import java.util.List;

public interface IProductService {
    Product getProduct(String no);

    List<Product> getProductList();
}
