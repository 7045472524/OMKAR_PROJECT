package com.nimap.app.service;

import com.nimap.app.model.Category;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.util.Assert;

import java.util.List;

@SpringBootTest
public class CategoryPageServiceTest {

    @Autowired
    CategoryPageService categoryPageService;

    @Test
    void getCategories() {
        System.out.println("inside CategoryPageServiceTest-->getCategories()");

        List<Category> listCats = categoryPageService.getCategories();

        System.out.println("inside test-->getCategories()-->listCatsSize: " + listCats.size() + ", listCats: " + listCats);

        //Assert.notEmpty(listCats);
    }

    @Test
    void getCategoriesByPageTest() {
        System.out.println("inside CategoryPageServiceTest-->getCategoriesByPageTest()");

        Page<Category> pages = categoryPageService.getCategoriesByPage(0, 1, "name");

        System.out.println("inside test-->getCategoriesByPageTest()-->totalPages: " + pages.getTotalPages() + ", pages: " + pages);

        //Assert.isTrue(pages.getTotalPages() > 0, "true");
    }
}
