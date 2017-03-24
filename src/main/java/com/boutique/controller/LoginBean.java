package com.boutique.controller;

import javax.servlet.http.HttpSession;

import com.boutique.common.controller.AbstractController;
import com.boutique.common.dao.DaoManager;
import com.boutique.dao.CashierDao;
import com.boutique.model.Cashier;
import com.boutique.util.Constants;
import com.boutique.validation.ValidationUtil;

public class LoginBean extends AbstractController {

	private String username;
	private String password;
	
	private CashierDao getCashierDao() {
		return DaoManager.getInstance().getDao(CashierDao.class);
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	private boolean validate() {
		getValidationErrors().clear();
		ValidationUtil.validateTextField(true, "Username", "Username", this.username, getValidationErrors());
		ValidationUtil.validateTextField(true, "Password", "Password", this.password, getValidationErrors());
		
		return !getValidationErrors().getAnyError();
	}
	
	public String login() {
		
		if(validate()) {
			Cashier cashier = getCashierDao().login(this.username, this.password);
		
			if(cashier == null) {
				getValidationErrors().addError("InvalidLogin", "Invalide Username or Password");
			} else {
				HttpSession session = getSession(true);
				session.setAttribute("loginUser", cashier);
				
				getSessionBean().loadSessionData(cashier);
				return Constants.PAGE_SHOPPING_CART;
			}
		}
		
		return Constants.EMPTY_STRING;
	}
	
	public String logout() {
		
		HttpSession session = getSession(false);

		if (session != null) {
			session.invalidate();
		}
		
		return Constants.PAGE_HOME;
	}

}
