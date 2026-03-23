package com.koscielniak.projekt.services;

import com.koscielniak.projekt.dto.Product;
import com.koscielniak.projekt.dto.Promotion;
import jakarta.annotation.PostConstruct;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class DataService {

    //zmienne klasy czyli z czego będzie korzystał kontroler podczas kolejnych żądań get

    private final ObjectMapper mapper = new ObjectMapper();
    private List<Promotion> promotions = new ArrayList<>();
    private List<Product> products = new ArrayList<>();

    //Adnotacja @PostConstruct w Spring Boot służy do oznaczenia metody,
    //która ma zostać wykonana automatycznie zaraz po tym, jak Spring
    // zakończy proces wstrzykiwania zależności (Dependency Injection).

    // To taki drugi konstruktor, który odpala się w momencie, gdy obiekt jest już w pełni gotowy do pracy
    // i ma dostęp do wszystkich swoich komponentów.

    @PostConstruct
    public void init() throws IOException {
        JsonNode root = mapper.readTree(new ClassPathResource("static/data/data.json").getInputStream());
        this.promotions = mapper.convertValue(root.get("promotions"), new TypeReference<List<Promotion>>() {});
        this.products = mapper.convertValue(root.get("products"), new TypeReference<List<Product>>() {});
    }


    public List<Promotion> getPromotions() {
        return promotions;
    }

    public List<Product> getProducts(){
        return products;
    }

}