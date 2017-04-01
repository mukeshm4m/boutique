package com.boutique.dao;

import java.util.List;

import com.boutique.common.model.StatusMessage;
import com.boutique.model.Cashier;
import com.boutique.model.Store;

public interface CashierDao {
	
	Cashier login(String username, String password);

	List<Store> getAllStores();

	StatusMessage saveStore(Store store);

	StatusMessage saveCashier(Cashier cashier);

	List<Cashier> getAllCashier();

	void deleteCashier(Cashier cashier);

	void deleteStore(Store store);
	
}
