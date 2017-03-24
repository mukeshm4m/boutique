package com.boutique.dao;

import java.util.List;

import com.boutique.model.Cashier;
import com.boutique.model.Store;

public interface CashierDao {
	
	Cashier login(String username, String password);

	List<Store> getAllStores();
	
}
