package com.koscielniak.projekt.controller;


import com.koscielniak.projekt.dto.Product;
import com.koscielniak.projekt.services.DataService;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@RestController
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class ProductController {

    private final DataService dataService;

    public ProductController(DataService dataService) {
        this.dataService = dataService;
    }

    @GetMapping("/productsAll")
    public List<Product> getAllProducts() {
        return dataService.getProducts();

    }

    @GetMapping("/products")
    public List<Product> getProducts(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String category,
            @RequestParam(required = false, name = "_sort") String sort,
            @RequestParam(required = false, name = "_order") String order
    ) {
        List<Product> list = new java.util.ArrayList<>(dataService.getProducts().stream()
                .filter(product -> product.name.contains(name) && product.category.contains(category))
                .toList());
        //Malejace
        if (Objects.equals(sort, "price")){
            list.stream()
                    .sorted((a, b) -> a.getPrice().compareToIgnoreCase(b.getPrice()))
                    .toList();
        }
        if (Objects.equals(sort, "name")){
            list.stream()
                    .sorted((a, b) -> a.getName().compareToIgnoreCase(b.getName()))
                    .toList();
        }

        if (Objects.equals(order, "DESC")) {
            Collections.reverse(list);
        }

        return list;
    }

    @GetMapping("/product/{id}")
    public Product getOneProduct(@PathVariable String id) {
        return dataService.getProducts().stream()
                .filter(p -> p.id.equals(id))
                .findFirst()
                .orElse(null);
    }

}
