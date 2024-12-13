package com.example.demo.controller;

import com.example.demo.entities.Product;
import com.example.demo.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping("/save")
    public Product save(@RequestBody Product product) {
        return productService.save(product);
    }

    @GetMapping("/find-By-Id")
    public Product findById(@RequestParam Long id) {
        return productService.findById(id);
    }

    @DeleteMapping("/delete-all")
    public void deleteAll() {
        productService.deleteAll();
    }

    @DeleteMapping("/delete-by-id")
    public void deleteById(@RequestParam Long id) {
        productService.deleteById(id);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestBody Product product) {
        productService.delete(product);
    }

    @GetMapping("find-By-Name")
    public Product findByName(@RequestParam String name) {
        return productService.findByName(name);
    }

    @PatchMapping("/update")
    public Product update(@RequestBody Product product) {
        return productService.update(product);
    }
}
