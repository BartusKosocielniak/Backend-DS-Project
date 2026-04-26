package com.koscielniak.projekt.controller;


import com.koscielniak.projekt.dto.Product;
import com.koscielniak.projekt.services.DataService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class ProductController {

    private final DataService dataService;

    public ProductController(DataService dataService) {
        this.dataService = dataService;
    }

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return dataService.getProducts();

    }
    @GetMapping("/product/{id}")
    public Product getOneProduct(@PathVariable String id){
        return dataService.getProducts().stream()
                .filter(p -> p.id.equals(id))
                .findFirst()
                .orElse(null);
    }
}
