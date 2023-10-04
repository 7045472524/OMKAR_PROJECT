package com.nimap.app.service;

import com.nimap.app.exception.CustomErrorCode;
import com.nimap.app.exception.CustomErrorMessage;
import com.nimap.app.exception.ServiceException;
import com.nimap.app.model.Category;
import com.nimap.app.model.Product;
import com.nimap.app.repo.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    CategoryRepo categoryRepo;
    
    
    
    
    

    /**
     * creating single resource.resource means object
     * @param categoryReq
     * @return
     */
	public Category addCategory(Category categoryReq) { 
        Category category = getCategoryByName(categoryReq.getName().trim());
        if(null!=category && categoryReq.getName().trim().equals(category.getName().trim())){
            throw new ServiceException(HttpStatus.BAD_REQUEST, CustomErrorCode.CATEGORY_EXISTS, CustomErrorMessage.CATEGORY_EXISTS);
        }

        categoryReq.setRegisterDate(System.currentTimeMillis());

        long currentmillis = System.currentTimeMillis();
        for(Product product: categoryReq.getProduct()){
            product.setRegisterDate(currentmillis);
        }

        return categoryRepo.save(categoryReq);
    }

    
	
	
	/**
	 *  creating list of resources (multiple objects)
	 * @param categoryReqListReq
	 * @return
	 */
	public List<Category> addCategories(List<Category> categoryReqListReq){
        long currentmillis = System.currentTimeMillis();
        for(Category category: categoryReqListReq){
            category.setRegisterDate(currentmillis);

            for(Product product: category.getProduct()){
                product.setRegisterDate(currentmillis);
            }
        }

        return categoryRepo.saveAll(categoryReqListReq);
    }


   
	
	
	
	/**
	 * retrieving / reading resource or resources by id
	 * @param id
	 * @return
	 */
	public Category findCategory(int id) {
        return categoryRepo.findById(id).orElse(new Category());
    }

    
	
	/**
	 *retrieving / reading resource  or resources by name.it is optional
	 * @param name
	 * @return
	 */
	public Category getCategoryByName(String name){
        return categoryRepo.findByName(name);
    }

   
	
	/**
     * retrieving / reading list of resources
     * @return
     */
    public List<Category> showCategory(){
        return categoryRepo.findAll();
    }

   
    
    /**
     * update resource
     * @param categoryReq
     * @return
     */
    public Category updateCategory(Category categoryReq) {
        Category category = categoryRepo.findById(categoryReq.getCid()).orElse(new Category());
        if(null==category || (null!=category && category.getCid()==0)){
            throw new ServiceException(HttpStatus.BAD_REQUEST, CustomErrorCode.INVALID_REQUEST, CustomErrorMessage.INVALID_REQUEST);
        }

        Category categoryDB = getCategoryByName(categoryReq.getName().trim());
        if(null!=categoryDB && categoryReq.getCid()!=categoryDB.getCid() && categoryReq.getName().trim().equals(categoryDB.getName().trim())){
            throw new ServiceException(HttpStatus.BAD_REQUEST, CustomErrorCode.CATEGORY_EXISTS, CustomErrorMessage.CATEGORY_EXISTS);
        }

        category.setName(categoryReq.getName());

        long currentmillis = System.currentTimeMillis();
        category.setUpdateDate(currentmillis);

        for(Product product: category.getProduct()){
            product.setUpdateDate(currentmillis);
        }

        return categoryRepo.save(category);
    }

   
    
    
    /**
     * deleting resource or resources by id
     * @param id
     * @return
     */
    public String deleteCategory(Integer id) {
        categoryRepo.deleteById(id);

        return new StringBuilder("DELETED SUCCESSFULLY ").append(id).toString();
    }
}
