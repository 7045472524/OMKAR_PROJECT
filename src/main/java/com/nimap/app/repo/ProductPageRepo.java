package com.nimap.app.repo;

import com.nimap.app.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProductPageRepo extends  JpaRepository<Product, Integer>, PagingAndSortingRepository<Product, Integer>  {
}