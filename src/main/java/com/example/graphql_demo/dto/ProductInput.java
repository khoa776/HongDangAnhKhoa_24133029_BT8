package com.example.graphql_demo.dto;

import lombok.Data;

@Data
public class ProductInput {
    private String title;
    private Integer quantity;
    private String description;
    private Double price;
    private Long categoryId;
    private Long userId;
}