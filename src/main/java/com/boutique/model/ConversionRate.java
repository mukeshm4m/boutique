package com.boutique.model;

import java.io.Serializable;

public class ConversionRate implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String currency;
	private Double rate;
	
	public ConversionRate() {
		
	}
	
	public ConversionRate(String currency) {
		this.currency = currency;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the currency
	 */
	public String getCurrency() {
		return currency;
	}

	/**
	 * @param currency
	 *            the currency to set
	 */
	public void setCurrency(String currency) {
		this.currency = currency;
	}

	/**
	 * @return the rate
	 */
	public Double getRate() {
		return rate;
	}

	/**
	 * @param rate
	 *            the rate to set
	 */
	public void setRate(Double rate) {
		this.rate = rate;
	}
}
