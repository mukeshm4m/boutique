package com.boutique.dao;

import java.util.List;

import com.boutique.model.Product;
import com.boutique.model.ProductCategory;

public interface ProductDao {
	
	List<ProductCategory> getAllProductCategories();
	
	List<Product> getAllProducts();
	
}
