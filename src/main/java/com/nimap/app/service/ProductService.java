package com.nimap.app.service;

import com.nimap.app.bean.ProductResponse;
import com.nimap.app.exception.CustomErrorCode;
import com.nimap.app.exception.CustomErrorMessage;
import com.nimap.app.exception.ServiceException;
import com.nimap.app.model.Product;
import com.nimap.app.repo.ProductRepo;
import com.nimap.app.util.DaoUtility;
import com.nimap.app.util.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepo repo;

    @Autowired
    DaoUtility daoUtility;

    
    
    /**
     * creating single resource.resource means object
     * @param productReq
     * @return
     */
    public ProductResponse addProduct(Product productReq) { 
        productReq.setRegisterDate(System.currentTimeMillis());

        Product product = repo.save(productReq);

        return daoUtility.getProductResponse(product);
    }

    
    
    
    /**
     * creating list of resources (multiple objects)
     * @param productReqList
     * @return
     */
    public List<ProductResponse> addProducts(List<Product> productReqList) {
        long currentmillis = System.currentTimeMillis();
        for(Product product: productReqList){
            product.setRegisterDate(currentmillis);
        }

        List<Product> productList = repo.saveAll(productReqList);

        return getProductResponseList(productList);
    }

    
    
    
    
    /**
     *retrieving / reading resource or resources by id
     * @param id
     * @return
     */
    public ProductResponse findProduct(int id) {
        Product product = repo.findById(id).orElse(new Product());

        return daoUtility.getProductResponse(product);
    }

    
    
    public List<ProductResponse> getProductResponseList(List<Product> productList){
        List<ProductResponse> productRespList = new ArrayList<>();

        for(Product product : productList) {
            ProductResponse response = daoUtility.getProductResponse(product);
            productRespList.add(response);
        }

        return productRespList;
    }

   
    
    /**
     * retrieving / reading resource  or resources by name.it is optional
     * @param name
     * @return
     */
    public List<ProductResponse> getProductByName(String name) {
        List<Product> productList = repo.findByName(name);

        return getProductResponseList(productList);
    }

    
    
    /**
     *retrieving / reading list of resources
     * @return
     */
    public List<ProductResponse> showProduct() {
        List<Product> productList = repo.findAll();

        return getProductResponseList(productList);
    }

    
    
    
    /**
     * updating resource
     * @param productReq
     * @return
     */
    public ProductResponse updateProduct(Product productReq) {
        Product product = repo.findById(productReq.getPid()).orElse(new Product());

        if(null==product || (null!=product && product.getPid()==0)){
            throw new ServiceException(HttpStatus.BAD_REQUEST, CustomErrorCode.INVALID_REQUEST, CustomErrorMessage.INVALID_REQUEST);
        }

        if(!Utility.isEmptyNullStr(productReq.getName())){
            product.setName(productReq.getName());
        }
        if(!Utility.isEmptyNullDouble(productReq.getPrice())) {
            product.setPrice(productReq.getPrice());
        }
        if(!Utility.isEmptyNullInt(productReq.getQuantity())) {
            product.setQuantity(productReq.getQuantity());
        }
        product.setUpdateDate(System.currentTimeMillis());

        Product productEntity = repo.save(product);

        return daoUtility.getProductResponse(productEntity);
    }

    
    /**
     * deleting resource or resources by id
     * @param id
     * @return
     */
    public String deleteProduct(int id) { 
        //delete operation
        repo.deleteById(id);
        return new StringBuilder("DELETED SUCCESSFULLY ").append(id).toString();
    }
}
