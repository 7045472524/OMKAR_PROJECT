package com.nimap.app.repo;

import com.nimap.app.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CategoryPageRepo extends JpaRepository<Category, Integer>, PagingAndSortingRepository<Category, Integer> {
}