package com.nimap.app.controller;

import com.nimap.app.bean.ProductResponse;
import com.nimap.app.exception.CustomErrorCode;
import com.nimap.app.exception.CustomErrorMessage;
import com.nimap.app.exception.ServiceException;
import com.nimap.app.model.Product;
import com.nimap.app.service.ProductCategoryService;
import com.nimap.app.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    private static final Logger LOG = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    ProductService service;

    @Autowired
    ProductCategoryService productCategoryService;

    
    
    
    /**
     * creating single resource (single object)
     * @param productReq
     * @return
     */
    @PostMapping("/createProduct")
    public ProductResponse createProduct(@RequestBody Product productReq) {
        return service.addProduct(productReq);
    }

   
    
    /**
     *  creating list of resources (multiple objects)
     * @param productReqList
     * @return
     */
    @PostMapping("/createProducts")
    public List<ProductResponse> createProduct(@RequestBody List<Product> productReqList) {
        return service.addProducts(productReqList);
    }
    
    
    /**
     * retrieving / reading resource or resources by id
     * @param id
     * @return
     */
    @GetMapping("/getProductById/{id}")
    public ProductResponse getProductById(@PathVariable int id) {
        return service.findProduct(id);
    }

    
    
    
    /**
     * retrieving / reading resource  or resources by name
     * @param name
     * @return
     */
    @GetMapping("/getProductByName/{name}")
    public List<ProductResponse> getProductByName(@PathVariable String name) {
        return service.getProductByName(name);
    }
    
    
    
    /**
     * retrieving / reading list of resources
     * @return
     */
    @GetMapping("/getAllProducts")
    public List<ProductResponse> getAllProducts() {
        return service.showProduct();
    }

    

    
    /**
     * updating resource
     * @param productReq
     * @return
     */
    @PutMapping("/updateProduct")
    public ResponseEntity<ProductResponse> updateProduct(@RequestBody Product productReq) {
        try{
            if(null==productReq){
                throw new ServiceException(HttpStatus.BAD_REQUEST, CustomErrorCode.INVALID_REQUEST, CustomErrorMessage.INVALID_REQUEST);
            }

            ProductResponse response = service.updateProduct(productReq);

            return new ResponseEntity<ProductResponse>(response, HttpStatus.OK);

        } catch (
        ServiceException e) {
            LOG.error("Error: ", e);
            throw e;
        } catch (Exception e) {
            LOG.error("Error: ", e);
            throw new ServiceException(HttpStatus.INTERNAL_SERVER_ERROR, CustomErrorCode.OTHER_EXCEPTION, CustomErrorMessage.OTHER_EXCEPTION);
        }
    }

    
    
    /**
     * deleting resource or resources by id
     * @param id
     * @return
     */
    @DeleteMapping("/deleteProduct/{id}") 
    public String deleteByID(@PathVariable int id) {
        return service.deleteProduct(id);
    }


  
    
    /**
     *retrieving / reading resource or resources by id 
     * @param id
     * @return
     */
    @GetMapping("/getProductCategoryById/{id}")
    public ProductResponse getProductCategoryById(@PathVariable int id) {
        return productCategoryService.findProductCategory(id);
    }
}
