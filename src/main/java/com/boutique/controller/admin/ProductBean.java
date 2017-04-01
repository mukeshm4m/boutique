package com.boutique.controller.admin;

import com.boutique.model.ConversionRate;
import com.boutique.model.Product;
import com.boutique.model.ProductCategory;

public class ProductBean extends ProductController {

	private Product product = new Product();
	private ProductCategory productCategory = new ProductCategory();
	private ConversionRate conversionRateCDF = new ConversionRate();
	private ConversionRate conversionRateEuro = new ConversionRate();

	/**
	 * @return the product
	 */
	public Product getProduct() {
		return product;
	}

	/**
	 * @param product
	 *            the product to set
	 */
	public void setProduct(Product product) {
		this.product = product;
	}

	/**
	 * @return the productCategory
	 */
	public ProductCategory getProductCategory() {
		return productCategory;
	}

	/**
	 * @param productCategory
	 *            the productCategory to set
	 */
	public void setProductCategory(ProductCategory productCategory) {
		this.productCategory = productCategory;
	}

	public void clear() {
		this.getValidationErrors().clear();
		this.setSelectedProductCategoryId(-1);
		this.product = new Product();
	}

	/**
	 * @return the conversionRateCDF
	 */
	public ConversionRate getConversionRateCDF() {
		return conversionRateCDF;
	}

	/**
	 * @param conversionRateCDF the conversionRateCDF to set
	 */
	public void setConversionRateCDF(ConversionRate conversionRateCDF) {
		this.conversionRateCDF = conversionRateCDF;
	}

	/**
	 * @return the conversionRateEuro
	 */
	public ConversionRate getConversionRateEuro() {
		return conversionRateEuro;
	}

	/**
	 * @param conversionRateEuro the conversionRateEuro to set
	 */
	public void setConversionRateEuro(ConversionRate conversionRateEuro) {
		this.conversionRateEuro = conversionRateEuro;
	}
}
