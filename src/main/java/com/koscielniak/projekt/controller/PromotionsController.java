package com.koscielniak.projekt.controller;

import com.koscielniak.projekt.dto.Product;
import com.koscielniak.projekt.dto.Promotion;
import com.koscielniak.projekt.services.DataService;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class PromotionsController {

    private final DataService dataService;

    public PromotionsController(DataService dataService) {
        this.dataService = dataService;
    }

    @GetMapping("/promotions")
    public List<Promotion> getAll() {
        return dataService.getPromotions();
    }

    @GetMapping("/promotion/{id}")
    public Promotion getOnePromo(@PathVariable String id) {
        return dataService.getPromotions().stream()
                .filter(p -> p.id.equals(id))
                .findFirst()
                .orElse(null);
    }

    @GetMapping("/products")
    public List<Product> getAllProducts(){
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