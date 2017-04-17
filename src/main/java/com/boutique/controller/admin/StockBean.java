package com.boutique.controller.admin;

import java.util.ArrayList;
import java.util.List;

import com.boutique.common.util.Util;
import com.boutique.model.Stock;
import com.boutique.model.Store;
import com.boutique.util.DataUtil;

public class StockBean extends StockController {

	private Integer storeId = -1;
	private Store store = new Store();
	private Stock stock = new Stock();
	private List<Stock> stocks = new ArrayList<Stock>();

	/**
	 * @return the storeId
	 */
	public Integer getStoreId() {
		return storeId;
	}

	/**
	 * @param storeId
	 *            the storeId to set
	 */
	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	/**
	 * @return the stock
	 */
	public Stock getStock() {
		return stock;
	}

	/**
	 * @param stock
	 *            the stock to set
	 */
	public void setStock(Stock stock) {
		this.stock = stock;
	}

	/**
	 * @return the stocks
	 */
	public List<Stock> getStocks() {
		return stocks;
	}

	/**
	 * @param stocks
	 *            the stocks to set
	 */
	public void setStocks(List<Stock> stocks) {
		this.stocks = stocks;
	}

	/**
	 * @return the store
	 */
	public Store getStore() {
		return store;
	}

	/**
	 * @param store
	 *            the store to set
	 */
	public void setStore(Store store) {
		this.store = store;
	}

	public void init() {
		if(Util.isNotNullAndEmpty(this.getAllStores())) {
			this.storeId = this.getAllStores().get(0).getId();
		} else {
			this.storeId = -1;
		}
		
		loadStocks();
	}

	public void loadStocks() {
		this.store = DataUtil.getStoreById(this.storeId);
		this.stocks = getStockDao().getStocksByStoreId(this.storeId);
	}

	public void clear() {
		this.getValidationErrors().clear();
		this.stock = new Stock();
	}
}
