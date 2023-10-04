package com.nimap.app.repo;

import java.util.List;

import com.nimap.app.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Product, Integer> {
    List<Product> findByName(String pname);
}
