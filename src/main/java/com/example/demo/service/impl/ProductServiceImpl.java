package com.example.demo.service.impl;

import com.example.demo.config.KafkaProducerConfig;
import com.example.demo.entities.Product;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.ProductService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final KafkaProducerConfig kafkaProducerConfig;

    @Override
    public Product save(Product product) {
        try {
            kafkaProducerConfig.sendMessage("Saving a product with name : " + product.getName());
            return productRepository.save(product);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("An error occured while saving product" + e.getMessage());
        }
    }

    @Override
    public Product update(Product product) {
        try {
            kafkaProducerConfig.sendMessage("Updating a product with name : " + product.getName());
            return productRepository.save(product);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("An error occured while updating product" + e.getMessage());
        }
    }

    @Override
    public void delete(Product product) {
        try{
            kafkaProducerConfig.sendMessage("Deleting a product with name : " + product.getName());
            productRepository.delete(product);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("An error occured while deleting product" + e.getMessage());
        }
    }

    @Override
    public void deleteAll() {
        try{
            kafkaProducerConfig.sendMessage("Deleting all products");
            productRepository.deleteAll();
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("An error occured while deleting all products");
        }
    }

    @Override
    public void deleteById(Long id) {
        try{
            kafkaProducerConfig.sendMessage("Deleting a product with id : " + id);
            productRepository.deleteById(id);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("An error occured while deleting product" + e.getMessage());
        }
    }

    @Override
    public List<Product> findAll() {
        try{
            kafkaProducerConfig.sendMessage("Finding all products");
            return productRepository.findAll();
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("An error occured while finding all products");
        }
    }

    @Override
    public Product findById(Long id) {
        try {

            return productRepository.findById(id).orElseThrow(() -> new RuntimeException("An error occured while finding product"));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public Product findByName(String name) {
        try {
            return productRepository.findByName(name);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public List<Product> findByPrice(Double price) {
        try{
            return productRepository.findByPrice(price);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("An error occured while finding all products" + e.getMessage());
        }
    }
}
