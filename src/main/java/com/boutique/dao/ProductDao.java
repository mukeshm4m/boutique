package com.boutique.dao;

import java.util.List;

import com.boutique.common.model.StatusMessage;
import com.boutique.model.ConversionRate;
import com.boutique.model.Product;
import com.boutique.model.ProductCategory;

public interface ProductDao {
	
	List<ProductCategory> getAllProductCategories();
	
	List<Product> getAllProducts();

	StatusMessage saveProduct(Product product);

	void deleteProduct(Product product);

	StatusMessage saveProductCategory(ProductCategory productCategory);

	StatusMessage saveConversionRate(ConversionRate conversionRate);

	List<ConversionRate> getAllConversionRates();
	
}
