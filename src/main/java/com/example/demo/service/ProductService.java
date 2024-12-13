package com.example.demo.service;

import com.example.demo.entities.Product;

import java.util.List;

public interface ProductService {
    Product save(Product product);

    Product update(Product product);

    void delete(Product product);

    void deleteAll();

    void deleteById(Long id);

    List<Product> findAll();

    Product findById(Long id);

    Product findByName(String name);

    List<Product> findByPrice(Double price);
}
