package com.nimap.app.service;

import com.nimap.app.model.Category;
import com.nimap.app.repo.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryPageService {
    @Autowired
    private CategoryRepo categoryRepo;

    public List<Category> getCategories() {
        List<Category> categories = new ArrayList<>();

        Iterable<Category> categoryIterable = categoryRepo.findAll();
        categoryIterable.forEach(categories::add);

        return categories;
    }

    public Page<Category> getCategoriesByPage(Integer pageIndex, Integer pageSize, String field){
        Sort sort = Sort.by(Sort.Direction.ASC, field);
        Pageable pageRequest = PageRequest.of(pageIndex, pageSize, sort);
        return categoryRepo.findAll(pageRequest);
    }

    public Category addCategory(Category category) {
        return categoryRepo.save(category);
    }
}
