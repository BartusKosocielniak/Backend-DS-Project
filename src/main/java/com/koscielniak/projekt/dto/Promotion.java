package com.koscielniak.projekt.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Promotion {
    public String id;
    public String header;
    public String description;
    public String longDescription;
    public List<String> items;
}