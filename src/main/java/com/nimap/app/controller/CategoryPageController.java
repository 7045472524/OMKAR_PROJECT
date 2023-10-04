package com.nimap.app.controller;

import com.nimap.app.model.Category;
import com.nimap.app.bean.PageResponse;
import com.nimap.app.service.CategoryPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categorypage")
public class CategoryPageController {

    @Autowired
    private CategoryPageService categoryPageService;


    @GetMapping
    public PageResponse<Category> getCategories(
            @RequestParam Integer pageIndex,
            @RequestParam Integer pageSize,
            @RequestParam String sortBy
    ) {
        Page<Category> page = categoryPageService.getCategoriesByPage(pageIndex, pageSize, sortBy);
        return new PageResponse<>(page);
    }
}
