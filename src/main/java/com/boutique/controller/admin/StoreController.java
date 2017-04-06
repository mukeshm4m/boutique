package com.boutique.controller.admin;

import com.boutique.common.controller.AbstractController;
import com.boutique.model.Cashier;
import com.boutique.model.Product;
import com.boutique.model.Stock;
import com.boutique.model.Store;
import com.boutique.util.CloneUtil;
import com.boutique.util.Constants;
import com.boutique.util.DataUtil;
import com.boutique.validation.ValidationUtil;

public class StoreController extends AbstractController {

	private Boolean validate() {
		StoreBean storeBean = getStoreBean();
		storeBean.getValidationErrors().clear();

		ValidationUtil.validateTextField(true, "Store Name", "StoreName", storeBean.getStore().getName(), storeBean.getValidationErrors(), 45);

		if (storeBean.getStore().getId() == null && DataUtil.getStoreByName(storeBean.getStore().getName()) != null) {
			storeBean.getValidationErrors().addError("StoreName", "Store with this name already exists");
		}

		return !storeBean.getValidationErrors().getAnyError();
	}

	public void addStore() {
		if (validate()) {
			StoreBean storeBean = getStoreBean();

			boolean isNewStore = storeBean.getStore().getId() == null;

			getCashierDao().saveStore(storeBean.getStore());
			DataUtil.loadStores();

			if (isNewStore) {
				// add all products in new store stock
				for (Product product : getAllProducts()) {
					Stock stock = new Stock(product, storeBean.getStore());
					getStockDao().saveStock(stock);
				}
			}

			storeBean.clear();
		}
	}

	public void reset() {
		StoreBean storeBean = getStoreBean();
		storeBean.clear();
	}

	public void editStore(Store store) {
		if (store != null) {
			StoreBean storeBean = getStoreBean();

			storeBean.setStore(CloneUtil.cloneStore(store));
		}
	}

	public void deleteStore(Store store) {
		if (store != null) {
			StoreBean storeBean = getStoreBean();
			storeBean.clear();

			getCashierDao().deleteStore(store);
			DataUtil.loadStores();
		}
	}

	private Boolean validateCashier() {
		StoreBean storeBean = getStoreBean();
		storeBean.getValidationErrors().clear();

		if (storeBean.getCashier().getRole().equals("-1")) {
			storeBean.getValidationErrors().addError("UserRole", "Select a value");
		}

		if (Constants.ROLE_CASHIER.equals(storeBean.getCashier().getRole()) && storeBean.getStoreId() <= 0) {
			storeBean.getValidationErrors().addError("StoreName", "Select a value");
		}

		ValidationUtil.validateTextField(true, "Name", "Name", storeBean.getCashier().getName(), storeBean.getValidationErrors(), 45);
		ValidationUtil.validateTextField(true, "User Name", "UserName", storeBean.getCashier().getUsername(), storeBean.getValidationErrors(), 45);
		ValidationUtil.validateTextField(true, "Password", "Password", storeBean.getCashier().getPassword(), storeBean.getValidationErrors(), 45);
		
		if (storeBean.getCashier().getId() ==  null && !isUsernameUnique(storeBean.getCashier().getUsername())) {
			storeBean.getValidationErrors().addError("UserName", "Username already exists");
		}

		return !storeBean.getValidationErrors().getAnyError();
	}

	public void addUser() {
		if (validateCashier()) {
			StoreBean storeBean = getStoreBean();

			if (Constants.ROLE_CASHIER.equals(storeBean.getCashier().getRole())) {
				storeBean.getCashier().setStore(DataUtil.getStoreById(storeBean.getStoreId()));
			} else {
				storeBean.getCashier().setStore(null);
			}

			getCashierDao().saveCashier(storeBean.getCashier());

			storeBean.loadData();

			storeBean.clear();
		}
	}

	public void editUser(Cashier cashier) {
		if (cashier != null) {
			StoreBean storeBean = getStoreBean();

			if (Constants.ROLE_CASHIER.equals(cashier.getRole())) {
				storeBean.setStoreId(cashier.getStore().getId());
			} else {
				storeBean.setStoreId(-1);
			}

			storeBean.setCashier(CloneUtil.cloneCashier(cashier));
		}
	}

	public void deleteUser(Cashier cashier) {
		if (cashier != null) {
			StoreBean storeBean = getStoreBean();
			storeBean.clear();

			getCashierDao().deleteCashier(cashier);
			storeBean.loadData();
		}
	}

	private boolean isUsernameUnique(String username) {
		StoreBean storeBean = getStoreBean();

		for (Cashier cashier : storeBean.getCashiers()) {
			if (cashier.getName().equalsIgnoreCase(username)) {
				return false;
			}
		}

		return true;
	}
}
