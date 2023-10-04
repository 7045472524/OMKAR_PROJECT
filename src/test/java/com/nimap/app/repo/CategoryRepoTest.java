package com.nimap.app.repo;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.List;
import java.util.ArrayList;

import org.assertj.core.api.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import com.nimap.app.model.Category;
import com.nimap.app.model.Product;


@SpringBootTest
public class CategoryRepoTest {
    
	 @Autowired
	 private CategoryRepo categoryRepo;
	 
	  @Test
	  void isCategoryExitsById() {
		  System.out.println("ïnside isCategoryExitsById");
		  
		  long currentmillis = System.currentTimeMillis();
		  
		  List<Product> productList = new ArrayList<>();
		  Product product = new Product();
		  product.setName("pulsar"+currentmillis);
		  product.setPrice(90000.00);
		  product.setQuantity(2);
		  product.setRegisterDate(1696276780503L);
		  product.setUpdateDate(1696276891652L);
		  productList.add(product);
		  
	        Category category = new Category("bike"+currentmillis,1696276780503L,1696276891652L, productList);
			  System.out.println("ïnside isCategoryExitsById bR cid: "+category.getCid());
				  
			  categoryRepo.save(category);
			  
			  System.out.println("ïnside isCategoryExitsById aR cid: "+category.getCid());
			  
			  Category categoryInDB = categoryRepo.findById(category.getCid()).orElse(new Category());
			  System.out.println("ïnside isCategoryExitsById--->actualResult: "+categoryInDB.getCid());
			  
	         assertThat(categoryInDB.getCid()>0).isTrue();
			  System.out.println("ïnside isCategoryExitsById end");
	    }
	  
	  
	  @AfterEach
	  void tearDown() {
	        System.out.println("tearing down");
	        categoryRepo.deleteAll();
	    }

	    @BeforeEach
	    void setUp() {
	        System.out.println("setting up");
	    }
	    

}
