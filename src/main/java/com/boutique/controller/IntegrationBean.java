package com.boutique.controller;

import java.util.Date;

public class IntegrationBean extends IntegrationController {

	private Date date;
	private String b2bPdfUrl;
	private String distributorPdfUrl;
	private String customerPdfUrl;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getB2bPdfUrl() {
		return b2bPdfUrl;
	}

	public void setB2bPdfUrl(String b2bPdfUrl) {
		this.b2bPdfUrl = b2bPdfUrl;
	}

	public String getDistributorPdfUrl() {
		return distributorPdfUrl;
	}

	public void setDistributorPdfUrl(String distributorPdfUrl) {
		this.distributorPdfUrl = distributorPdfUrl;
	}

	public String getCustomerPdfUrl() {
		return customerPdfUrl;
	}

	public void setCustomerPdfUrl(String customerPdfUrl) {
		this.customerPdfUrl = customerPdfUrl;
	}
	
	public void clear() {
		this.getValidationErrors().clear();
		this.b2bPdfUrl = "";
		this.customerPdfUrl = "";
		this.distributorPdfUrl = "";
	}

}
