package com.koscielniak.projekt.controller;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;

@RestController
@CrossOrigin(origins = "*") // Odpowiednik app.use(cors())
public class PromotionController {

    @GetMapping("/promotions")
    public Object getPromotions() throws IOException {
        // Wczytanie pliku JSON z zasobów
        Resource resource = new ClassPathResource("static/data/data.json");

        // Używamy Jacksona (wbudowany w Spring), aby sparsować JSON do obiektu
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(resource.getInputStream(), Object.class);
    }
}