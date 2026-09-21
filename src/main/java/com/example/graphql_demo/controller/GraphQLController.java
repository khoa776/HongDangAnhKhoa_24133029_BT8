package com.example.graphql_demo.controller;

import com.example.graphql_demo.dto.CategoryInput;
import com.example.graphql_demo.dto.ProductInput;
import com.example.graphql_demo.entity.Category;
import com.example.graphql_demo.entity.Product;
import com.example.graphql_demo.entity.User;
import com.example.graphql_demo.repository.CategoryRepository;
import com.example.graphql_demo.repository.ProductRepository;
import com.example.graphql_demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class GraphQLController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private UserRepository userRepository;

    // ================= QUERIES =================

    // 1. Hiển thị tất cả product có price từ thấp đến cao
    @QueryMapping
    public List<Product> productsByPriceAsc() {
        return productRepository.findAllByOrderByPriceAsc();
    }

    // 2. Lấy tất cả product của 01 category
    @QueryMapping
    public List<Product> productsByCategoryId(@Argument Long categoryId) {
        return productRepository.findByCategoryId(categoryId);
    }

    @QueryMapping
    public List<Product> products() {
        return productRepository.findAll();
    }

    @QueryMapping
    public Product productById(@Argument Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @QueryMapping
    public List<Category> categories() {
        return categoryRepository.findAll();
    }

    @QueryMapping
    public Category categoryById(@Argument Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    // ================= MUTATIONS (CRUD) =================

    // CRUD Product
    @MutationMapping
    public Product createProduct(@Argument ProductInput input) {
        Product product = new Product();
        product.setTitle(input.getTitle());
        product.setQuantity(input.getQuantity());
        product.setDescription(input.getDescription());
        product.setPrice(input.getPrice());

        if (input.getCategoryId() != null) {
            Category category = categoryRepository.findById(input.getCategoryId()).orElse(null);
            product.setCategory(category);
        }
        if (input.getUserId() != null) {
            User user = userRepository.findById(input.getUserId()).orElse(null);
            product.setUser(user);
        }
        return productRepository.save(product);
    }

    @MutationMapping
    public Product updateProduct(@Argument Long id, @Argument ProductInput input) {
        Product product = productRepository.findById(id).orElse(null);
        if (product != null) {
            product.setTitle(input.getTitle());
            product.setQuantity(input.getQuantity());
            product.setDescription(input.getDescription());
            product.setPrice(input.getPrice());

            if (input.getCategoryId() != null) {
                Category category = categoryRepository.findById(input.getCategoryId()).orElse(null);
                product.setCategory(category);
            }
            if (input.getUserId() != null) {
                User user = userRepository.findById(input.getUserId()).orElse(null);
                product.setUser(user);
            }
            return productRepository.save(product);
        }
        return null;
    }

    @MutationMapping
    public Boolean deleteProduct(@Argument Long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // CRUD Category
    @MutationMapping
    public Category createCategory(@Argument CategoryInput input) {
        Category category = new Category();
        category.setName(input.getName());
        category.setImages(input.getImages());
        return categoryRepository.save(category);
    }

    @MutationMapping
    public Category updateCategory(@Argument Long id, @Argument CategoryInput input) {
        Category category = categoryRepository.findById(id).orElse(null);
        if (category != null) {
            category.setName(input.getName());
            category.setImages(input.getImages());
            return categoryRepository.save(category);
        }
        return null;
    }

    @MutationMapping
    public Boolean deleteCategory(@Argument Long id) {
        if (categoryRepository.existsById(id)) {
            categoryRepository.deleteById(id);
            return true;
        }
        return false;
    }
}