package com.boutique.controller;

import javax.servlet.http.HttpSession;

import com.boutique.common.controller.AbstractController;
import com.boutique.model.Cashier;

public class SessionBean extends AbstractController {

	private Cashier cashier;
	private Boolean userLoggedIn = false;

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
	 * @return the userLoggedIn
	 */
	public Boolean getUserLoggedIn() {
		return userLoggedIn;
	}

	/**
	 * @param userLoggedIn
	 *            the userLoggedIn to set
	 */
	public void setUserLoggedIn(Boolean userLoggedIn) {
		this.userLoggedIn = userLoggedIn;
	}
	
	public void loadSessionData(Cashier cashier) {
		
		this.cashier = cashier;
		this.userLoggedIn = true;
	}
}
