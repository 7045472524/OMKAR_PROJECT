package com.nimap.app.service;

import com.nimap.app.model.Product;
import com.nimap.app.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductPageService {

    @Autowired
    private ProductRepo productRepo;

    public List<Product> getProducts() {
        List<Product> products = new ArrayList<>();

        Iterable<Product> productIterable = productRepo.findAll();
        productIterable.forEach(products::add);

        return products;
    }

    public Page<Product> getProductsByPage(Integer pageIndex, Integer pageSize, String field){
        Sort sort = Sort.by(Sort.Direction.ASC, field);
        Pageable pageRequest = PageRequest.of(pageIndex, pageSize, sort);
        return productRepo.findAll(pageRequest);
    }

    public Product addProduct(Product product) {
        return productRepo.save(product);
    }
}
