package com.boutique.controller.admin;

import java.util.ArrayList;
import java.util.List;

import com.boutique.model.Cashier;
import com.boutique.model.Store;

public class StoreBean extends StoreController {

	private Integer storeId = -1;
	private Store store = new Store();
	private Cashier cashier = new Cashier();
	private List<Cashier> cashiers = new ArrayList<Cashier>();

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

	/**
	 * @return the cashier
	 */
	public Cashier getCashier() {
		return cashier;
	}

	/**
	 * @param cashier
	 *            the cashier to set
	 */
	public void setCashier(Cashier cashier) {
		this.cashier = cashier;
	}

	/**
	 * @return the cashiers
	 */
	public List<Cashier> getCashiers() {
		return cashiers;
	}

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
	 * @param cashiers
	 *            the cashiers to set
	 */
	public void setCashiers(List<Cashier> cashiers) {
		this.cashiers = cashiers;
	}

	public void loadData() {
		this.cashiers = getCashierDao().getAllCashier(null);
	}

	public void clear() {
		this.getValidationErrors().clear();
		this.store = new Store();
		this.cashier = new Cashier();
		this.storeId = -1;
	}
}
