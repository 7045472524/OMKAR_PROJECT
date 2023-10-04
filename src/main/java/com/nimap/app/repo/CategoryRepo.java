package com.nimap.app.repo;



import com.nimap.app.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category, Integer> {
	
    Category findByName(String cname);
    
}
