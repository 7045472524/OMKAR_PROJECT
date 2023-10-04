package com.nimap.app.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue
    private int cid;
    
    @Column(unique=true)
    private String name;
   
    private Long registerDate;
    private Long updateDate;
   
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Product> product;
    
    public Category(String name, Long registerDate, Long updateDate, List<Product> product) {
		this.name = name;
		this.registerDate = registerDate;
		this.updateDate = updateDate;
		this.product = product;
	}
	
	

	public Category(int cid, String name, Long registerDate, Long updateDate, List<Product> product) {
		this.cid = cid;
		this.name = name;
		this.registerDate = registerDate;
		this.updateDate = updateDate;
		this.product = product;
	}
	
	




	public Category() {
		super();
		// TODO Auto-generated constructor stub
	}






	public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Long registerDate) {
        this.registerDate = registerDate;
    }

    public Long getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Long updateDate) {
        this.updateDate = updateDate;
    }

    public List<Product> getProduct() {
        return product;
    }

    public void setProduct(List<Product> product) {
        this.product = product;
    }
}
