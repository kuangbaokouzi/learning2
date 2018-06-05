package com.entor.dao;

import com.entor.po.Product;

import java.util.Collection;
import java.util.List;

public interface ProductDao {
    List<Product> findAllProducts();
}
