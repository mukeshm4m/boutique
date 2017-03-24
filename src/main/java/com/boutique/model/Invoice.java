package com.boutique.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.boutique.util.Constants;

public class Invoice implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String invoiceNo;
	private String slipNo;
	private String paymentReferenceNo;
	private String paymentMode;
	private String invoiceType;
	private String depositorName;
	private String clientName;
	private String currency;
	private Double amount;
	private Date paymentDateTime;
	private Date purchaseDateTime;
	private Cashier cashier;
	private List<InvoiceProduct> invoiceProducts = new ArrayList<InvoiceProduct>();

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
	 * @return the invoiceNo
	 */
	public String getInvoiceNo() {
		return invoiceNo;
	}

	/**
	 * @param invoiceNo
	 *            the invoiceNo to set
	 */
	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	/**
	 * @return the slipNo
	 */
	public String getSlipNo() {
		return slipNo;
	}

	/**
	 * @param slipNo
	 *            the slipNo to set
	 */
	public void setSlipNo(String slipNo) {
		this.slipNo = slipNo;
	}

	/**
	 * @return the paymentReferenceNo
	 */
	public String getPaymentReferenceNo() {
		return paymentReferenceNo;
	}

	/**
	 * @param paymentReferenceNo
	 *            the paymentReferenceNo to set
	 */
	public void setPaymentReferenceNo(String paymentReferenceNo) {
		this.paymentReferenceNo = paymentReferenceNo;
	}

	/**
	 * @return the paymentMode
	 */
	public String getPaymentMode() {
		return paymentMode;
	}

	/**
	 * @param paymentMode
	 *            the paymentMode to set
	 */
	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	/**
	 * @return the invoiceType
	 */
	public String getInvoiceType() {
		return invoiceType;
	}

	/**
	 * @param invoiceType
	 *            the invoiceType to set
	 */
	public void setInvoiceType(String invoiceType) {
		this.invoiceType = invoiceType;
	}

	/**
	 * @return the depositorName
	 */
	public String getDepositorName() {
		return depositorName;
	}

	/**
	 * @param depositorName
	 *            the depositorName to set
	 */
	public void setDepositorName(String depositorName) {
		this.depositorName = depositorName;
	}

	/**
	 * @return the clientName
	 */
	public String getClientName() {
		return clientName;
	}

	/**
	 * @param clientName
	 *            the clientName to set
	 */
	public void setClientName(String clientName) {
		this.clientName = clientName;
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
	 * @return the amount
	 */
	public Double getAmount() {
		return amount;
	}

	/**
	 * @param amount
	 *            the amount to set
	 */
	public void setAmount(Double amount) {
		this.amount = amount;
	}

	/**
	 * @return the paymentDateTime
	 */
	public Date getPaymentDateTime() {
		return paymentDateTime;
	}

	/**
	 * @param paymentDateTime
	 *            the paymentDateTime to set
	 */
	public void setPaymentDateTime(Date paymentDateTime) {
		this.paymentDateTime = paymentDateTime;
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
	 * @return the invoiceProducts
	 */
	public List<InvoiceProduct> getInvoiceProducts() {
		return invoiceProducts;
	}

	/**
	 * @param invoiceProducts
	 *            the invoiceProducts to set
	 */
	public void setInvoiceProducts(List<InvoiceProduct> invoiceProducts) {
		this.invoiceProducts = invoiceProducts;
	}
	
	public String getClientNameByType() {
		if(Constants.INVOICE_TYPE_CUSTOMER.equalsIgnoreCase(this.invoiceType)) {
			return "Client Name";
		}
		
		if(Constants.INVOICE_TYPE_DISTRIBUTOR.equalsIgnoreCase(this.invoiceType)) {
			return "Distributor Name";
		}
		
		if(Constants.INVOICE_TYPE_B2B.equalsIgnoreCase(this.invoiceType)) {
			return "Business Unit Name";
		}
		
		return "";
	}

	/**
	 * @return the purchaseDateTime
	 */
	public Date getPurchaseDateTime() {
		return purchaseDateTime;
	}

	/**
	 * @param purchaseDateTime the purchaseDateTime to set
	 */
	public void setPurchaseDateTime(Date purchaseDateTime) {
		this.purchaseDateTime = purchaseDateTime;
	}
}
