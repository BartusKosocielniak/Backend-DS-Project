package com.koscielniak.projekt.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Product {
    public String id;
    public String name;
    public String price;
    public String image;
    public String rate;
    public String category;
    public String description;

}