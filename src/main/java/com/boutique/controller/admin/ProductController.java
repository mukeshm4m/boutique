package com.boutique.controller.admin;

import com.boutique.common.controller.AbstractController;
import com.boutique.model.ConversionRate;
import com.boutique.model.Product;
import com.boutique.model.ProductCategory;
import com.boutique.model.Stock;
import com.boutique.model.Store;
import com.boutique.util.CloneUtil;
import com.boutique.util.Constants;
import com.boutique.util.DataUtil;
import com.boutique.validation.ValidationUtil;

public class ProductController extends AbstractController {

	private Boolean validate() {
		ProductBean productBean = getProductBean();
		productBean.getValidationErrors().clear();

		ValidationUtil.validateTextField(true, "Product Name", "ProductName", productBean.getProduct().getName(), productBean.getValidationErrors(), 45);

		if (productBean.getSelectedProductCategoryId() == -1) {
			productBean.getValidationErrors().addError("ProductCategory", "Select a value");
		}

		if (productBean.getProduct().getPrice() == null || productBean.getProduct().getPrice() <= 0) {
			productBean.getValidationErrors().addError("Price", "Enter a valid value");
		}
		
		if(productBean.getProduct().getId() == null && DataUtil.getProductByNameAndCategory(productBean.getProduct().getName(), DataUtil.getProductCategoryById(productBean.getSelectedProductCategoryId()).getName()) != null) {
			productBean.getValidationErrors().addError("ProductName", "Product with this name already exists");
		}

		return !productBean.getValidationErrors().getAnyError();
	}

	public void addProduct() {
		if (validate()) {
			ProductBean productBean = getProductBean();

			boolean isNewProduct = productBean.getProduct().getId() == null;

			productBean.getProduct().setProductCategory(DataUtil.getProductCategoryById(productBean.getSelectedProductCategoryId()));

			getProductDao().saveProduct(productBean.getProduct());
			DataUtil.loadProducts();

			if (isNewProduct) {
				// add new product in stock for all branches with zero quantity
				for (Store store : getAllStores()) {
					Stock stock = new Stock(productBean.getProduct(), store);
					getStockDao().saveStock(stock);
				}
			}

			productBean.clear();
		}
	}

	public void reset() {
		ProductBean productBean = getProductBean();
		productBean.clear();
	}

	public void editProduct(Product product) {
		if (product != null) {
			ProductBean productBean = getProductBean();
			productBean.setSelectedProductCategoryId(product.getProductCategory().getId());

			productBean.setProduct(CloneUtil.cloneProduct(product));
		}
	}

	public void deleteProduct(Product product) {
		if (product != null) {
			ProductBean productBean = getProductBean();
			productBean.clear();

			getProductDao().deleteProduct(product);
			DataUtil.loadProducts();
		}
	}

	public void showProductCategoryPopup() {
		ProductBean productBean = getProductBean();
		productBean.getValidationErrors().clear();
		productBean.setProductCategory(new ProductCategory());
	}

	private Boolean validateProductCategory() {
		ProductBean productBean = getProductBean();
		productBean.getValidationErrors().clear();

		ValidationUtil.validateTextField(true, "Category Name", "CategoryName", productBean.getProductCategory().getName(), productBean.getValidationErrors(), 45);
		
		if(DataUtil.getProductCategoryByName(productBean.getProductCategory().getName()) != null) {
			productBean.getValidationErrors().addError("CategoryName", "Category with this name already exists");
		}

		return !productBean.getValidationErrors().getAnyError();
	}

	public void saveProductCategory() {

		if (validateProductCategory()) {
			ProductBean productBean = getProductBean();
			getProductDao().saveProductCategory(productBean.getProductCategory());
			DataUtil.loadProductCategories();
		}
	}

	public void showConversionRatePopup() {
		ProductBean productBean = getProductBean();
		productBean.getValidationErrors().clear();

		ConversionRate conversionRateCDF = DataUtil.getConversionRateByCurrency(Constants.CURRENCY_CDF);
		if (conversionRateCDF == null) {
			conversionRateCDF = new ConversionRate(Constants.CURRENCY_CDF);
		}
		productBean.setConversionRateCDF(conversionRateCDF);

		ConversionRate conversionRateEuro = DataUtil.getConversionRateByCurrency(Constants.CURRENCY_EURO);
		if (conversionRateEuro == null) {
			conversionRateEuro = new ConversionRate(Constants.CURRENCY_EURO);
		}
		productBean.setConversionRateEuro(conversionRateEuro);
	}

	public void saveConversionRate() {
		ProductBean productBean = getProductBean();
		productBean.getValidationErrors().clear();

		if (productBean.getConversionRateCDF().getRate() == null || productBean.getConversionRateCDF().getRate() <= 0) {
			productBean.getValidationErrors().addError("RateCDF", "Enter a valid value");
			return;
		}
		
		if (productBean.getConversionRateEuro().getRate() == null || productBean.getConversionRateEuro().getRate() <= 0) {
			productBean.getValidationErrors().addError("RateEuro", "Enter a valid value");
			return;
		}
		
		Boolean isNewProduct = (productBean.getConversionRateCDF().getId() == null || productBean.getConversionRateEuro().getId() == null) ? true : false; 

		getProductDao().saveConversionRate(productBean.getConversionRateCDF());
		getProductDao().saveConversionRate(productBean.getConversionRateEuro());
		
		if(isNewProduct) {
			DataUtil.loadConversionRates();
		}
	}
}
