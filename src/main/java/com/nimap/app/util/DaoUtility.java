package com.nimap.app.util;

import com.nimap.app.bean.ProductResponse;
import com.nimap.app.model.Category;
import com.nimap.app.model.Product;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class DaoUtility {

    public ProductResponse getProductResponse(Product product){
        ProductResponse response = new ProductResponse();
        BeanUtils.copyProperties(product, response);

        Category category = product.getCategory();
        
        
        if(category!=null) {
        response.setCid(category.getCid());
        }
        return response;
    }
}
