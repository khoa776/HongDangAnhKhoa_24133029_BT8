package com.example.graphql_demo.repository;

import com.example.graphql_demo.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    // Hiển thị sản phẩm theo thứ tự giá từ thấp đến cao
    List<Product> findAllByOrderByPriceAsc();

    // Lấy tất cả product theo categoryId
    List<Product> findByCategoryId(Long categoryId);
}