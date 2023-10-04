package com.nimap.app.controller;

import com.nimap.app.bean.PageResponse;
import com.nimap.app.model.Product;
import com.nimap.app.service.ProductPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/productpage")
public class ProductPageController {

    @Autowired
    private ProductPageService productPageService;

    @GetMapping
    public PageResponse<Product> getProducts(
            @RequestParam Integer pageIndex,
            @RequestParam Integer pageSize,
            @RequestParam  String sortBy
    ) {
        Page<Product> page = productPageService.getProductsByPage(pageIndex, pageSize, sortBy);
        return new PageResponse<>(page);
    }
}
