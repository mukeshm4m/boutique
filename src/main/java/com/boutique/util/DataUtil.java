package com.boutique.util;

import java.util.ArrayList;
import java.util.List;

import com.boutique.common.dao.DaoManager;
import com.boutique.common.util.Util;
import com.boutique.dao.CashierDao;
import com.boutique.dao.ProductDao;
import com.boutique.model.Product;
import com.boutique.model.ProductCategory;
import com.boutique.model.Store;

public final class DataUtil {

	private static List<ProductCategory> productCategories = new ArrayList<ProductCategory>();
	private static List<Product> products = new ArrayList<Product>();
	private static List<Store> stores = new ArrayList<Store>();

	private DataUtil() {
	}

	static {
		loadData();
	}

	private static ProductDao getProductDao() {
		return DaoManager.getInstance().getDao(ProductDao.class);
	}

	private static CashierDao getCashierDao() {
		return DaoManager.getInstance().getDao(CashierDao.class);
	}

	/**
	 * @return the productCategories
	 */
	public static List<ProductCategory> getProductCategories() {

		if (Util.isNullOrEmpty(productCategories)) {
			loadProductCategories();
		}

		return productCategories;
	}

	/**
	 * @return the products
	 */
	public static List<Product> getProducts() {

		if (Util.isNullOrEmpty(products)) {
			loadProducts();
		}

		return products;
	}

	/**
	 * @return the stores
	 */
	public static List<Store> getStores() {
		if (Util.isNullOrEmpty(stores)) {
			loadStores();
		}

		return stores;
	}

	public static void loadData() {
		loadProductCategories();
		loadProducts();
		loadStores();
	}

	public static void loadProductCategories() {
		productCategories = getProductDao().getAllProductCategories();
	}

	public static void loadProducts() {
		products = getProductDao().getAllProducts();
	}

	public static void loadStores() {
		stores = getCashierDao().getAllStores();
	}

	public static List<Product> getProductsByCategoryId(Integer categoryId) {

		List<Product> filteredProducts = new ArrayList<Product>();

		for (Product product : products) {
			if (product.getProductCategory().getId().equals(categoryId)) {
				filteredProducts.add(product);
			}
		}

		return filteredProducts;
	}

	public static Product getProductById(Integer productId) {

		Product selectedProduct = null;

		for (Product product : products) {
			if (product.getId().equals(productId)) {
				selectedProduct = product;
				break;
			}
		}

		return selectedProduct;
	}
}
