package com.nimap.app.controller;

import com.nimap.app.exception.CustomErrorCode;
import com.nimap.app.exception.CustomErrorMessage;
import com.nimap.app.exception.ServiceException;
import com.nimap.app.model.Category;
import com.nimap.app.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * To determine the category usage
 */
@RestController
@RequestMapping("/category")
public class CategoryController {
    private static final Logger LOG = LoggerFactory.getLogger(CategoryController.class);

    @Autowired
    CategoryService service;

   
    
    
    /**
     * creating single resource (single object)
     * @param categoryReq
     * @return
     */
    @PostMapping("/createcategory")
    public ResponseEntity<Category> createcategory(@RequestBody Category categoryReq) {
        try{
            if(null==categoryReq || (null!=categoryReq && categoryReq.getName().trim().isEmpty())){
                throw new ServiceException(HttpStatus.BAD_REQUEST, CustomErrorCode.INVALID_REQUEST, CustomErrorMessage.INVALID_REQUEST);
            }

            Category category = service.addCategory(categoryReq);

            return new ResponseEntity<Category>(category, HttpStatus.OK);

        } catch (ServiceException e) {
            LOG.error("Error: ", e);
            throw e;
        } catch (Exception e) {
            LOG.error("Error: ", e);
            throw new ServiceException(HttpStatus.INTERNAL_SERVER_ERROR, CustomErrorCode.OTHER_EXCEPTION, CustomErrorMessage.OTHER_EXCEPTION);
        }
    }

    /**
     * creating list of resources (multiple objects)
     * @param categoryReq
     * @return list of categories
     */
    @PostMapping("/createcategories")
    public List<Category> createcategories(@RequestBody List<Category> categoryReq) {
        return service.addCategories(categoryReq);
    }

    
    
    
    
    /**
     * retrieving / reading resource or resources by id
     * @param id
     * @return
     */
    @GetMapping("/getCategoryById/{id}")
    public Category getCategoryById(@PathVariable int id) {
        return service.findCategory(id);
    }
    
    
    
    /**
     * retrieving / reading resource  or resources by name
     * @param name
     * @return
     */
    @GetMapping("/getCategoryByName/{name}")
    public ResponseEntity<Category> getCategoryByName(@PathVariable String name) {
        try{
            if(null!=name && name.trim().isEmpty()){
                throw new ServiceException(HttpStatus.BAD_REQUEST, CustomErrorCode.INVALID_REQUEST, CustomErrorMessage.INVALID_REQUEST);
            }

            Category category = service.getCategoryByName(name);
            return new ResponseEntity<Category>(category, HttpStatus.OK);

        } catch (ServiceException e) {
            LOG.error("Error: ", e);
            throw e;
        } catch (Exception e) {
            LOG.error("Error: ", e);
            throw new ServiceException(HttpStatus.INTERNAL_SERVER_ERROR, CustomErrorCode.OTHER_EXCEPTION, CustomErrorMessage.OTHER_EXCEPTION);
        }
    }
    
    
    
    
    /**
     * retrieving / reading list of resources
     * @return
     */
    @GetMapping("/getAllCategories")
    public List<Category> getAllCategories() {
        return service.showCategory();
    }
    
    
    
    
    
    
    /**
     *updating resource 
     * @param categoryReq
     * @return
     */
    @PutMapping("/updateCategory")
    public ResponseEntity<Category> updateCategory(@RequestBody Category categoryReq) {
        try{
            if(null==categoryReq || (null!=categoryReq && categoryReq.getName().trim().isEmpty())){
                throw new ServiceException(HttpStatus.BAD_REQUEST, CustomErrorCode.INVALID_REQUEST, CustomErrorMessage.INVALID_REQUEST);
            }

            Category category = service.updateCategory(categoryReq);

            return new ResponseEntity<Category>(category, HttpStatus.OK);

        } catch (ServiceException e) {
            LOG.error("Error: ", e);
            throw e;
        } catch (Exception e) {
            LOG.error("Error: ", e);
            throw new ServiceException(HttpStatus.INTERNAL_SERVER_ERROR, CustomErrorCode.OTHER_EXCEPTION, CustomErrorMessage.OTHER_EXCEPTION);
        }
    }


    
    
    /**
     *deleting resource or resources by id 
     * @param id
     * @return
     */
    @DeleteMapping("/deleteCategory/{id}") 
    public String deleteCategory(@PathVariable int id) {
        return service.deleteCategory(id);
    }


   

  

    
}
