package com.koscielniak.projekt.controller;


import com.koscielniak.projekt.dto.Product;
import com.koscielniak.projekt.services.DataService;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

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
    public Map<String, Object> getProducts(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String category,
            @RequestParam(required = false, name = "_sort") String sort,
            @RequestParam(required = false, name = "_order") String order,
            @RequestParam(required = false, name = "_page") Integer page,
            @RequestParam(required = false, name = "_limit") Integer limit
    ) {
        List<Product> list = dataService.getProducts();
        if (name!=null && !name.isEmpty()) {
            list = list.stream()
                    .filter(product -> product.getName().contains(name))
                    .collect(Collectors.toList());
        }

        if(category !=null && !category.isEmpty()){
           list = list.stream()
                    .filter(product -> product.getCategory().contains(category))
                   .collect(Collectors.toList());
        }
//        //Malejace
        if (Objects.equals(sort, "price")) {
            list = list.stream()
                    .sorted((a, b) -> Integer.compare(Integer.parseInt(a.getPrice()), Integer.parseInt(b.getPrice())))
                    .collect(Collectors.toList());
        }
        if (Objects.equals(sort, "name")) {
            list = list.stream()
                    .sorted((a, b) -> a.getName().compareToIgnoreCase(b.getName()))
                    .collect(Collectors.toList());
        }

        if (Objects.equals(order, "DESC") || Objects.equals(order, "desc")) {
            Collections.reverse(list);
        }

        //todo w trakcie robienia paginacji
        Map<String, Object> response = new HashMap<>();
        response.put("data", list);
        response.put("total", "6767");
        return response;
    }

    @GetMapping("/product/{id}")
    public Product getOneProduct(@PathVariable String id) {
        return dataService.getProducts().stream()
                .filter(p -> p.id.equals(id))
                .findFirst()
                .orElse(null);
    }

    @GetMapping("/categories")
    public List<String> getCategories() {
        return dataService.getCategories();
    }

}
