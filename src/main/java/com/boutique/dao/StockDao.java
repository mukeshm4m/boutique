package com.boutique.dao;

import java.util.List;

import com.boutique.common.model.StatusMessage;
import com.boutique.model.InvoiceProduct;
import com.boutique.model.Stock;
import com.boutique.model.Store;

public interface StockDao {
	
	List<Stock> getStocksByStoreId(Integer storeId);
	
	StatusMessage saveStock(Stock stock);

	void updateStock(Store store, List<InvoiceProduct> invoiceProducts);
}
