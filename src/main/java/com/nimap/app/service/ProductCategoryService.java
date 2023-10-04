package com.nimap.app.service;

import com.nimap.app.bean.CategoryResponse;
import com.nimap.app.bean.ProductResponse;
import com.nimap.app.model.Category;
import com.nimap.app.model.Product;
import com.nimap.app.repo.ProductRepo;
import com.nimap.app.util.DaoUtility;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ProductCategoryService {

    @Autowired
    ProductRepo repo;

    @Autowired
    CategoryService categoryService;

    @Autowired
    DaoUtility daoUtility;

    
    
    /**
     * retrieving / reading resource or resources by id
     * @param id
     * @return
     */
    public ProductResponse findProductCategory(int id) {
        Product product = repo.findById(id).orElse(new Product());

        ProductResponse productResponse =  daoUtility.getProductResponse(product);
        CategoryResponse categoryResponse = new CategoryResponse();

        Category category = categoryService.findCategory(product.getCategory().getCid());
        BeanUtils.copyProperties(category, categoryResponse);

        productResponse.setCategory(categoryResponse);

        return productResponse;
    }
}
