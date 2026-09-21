package com.example.graphql_demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    // Khi người dùng vào "/" hoặc "/product", trả về file product.html
    @GetMapping({"/", "/product"})
    public String productPage() {
        return "product"; // Đã đổi từ "index" thành "product"
    }

    // Khi người dùng vào "/category", trả về file category.html
    @GetMapping("/category")
    public String categoryPage() {
        return "category";
    }
}