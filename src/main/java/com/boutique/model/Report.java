package com.boutique.model;

import java.io.Serializable;
import java.util.Date;

public class Report implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String invoiceNo;
	private String slipNo;
	private String paymentReferenceNo;
	private String depositorName;
	private String clientName;
	private String invoiceType;
	private String currency;
	private String cashierName;
	private String storeName;
	private Date paymentDateTime;
	private Date paymentDateOnly;
	private String productCategoryName;
	private String productName;
	private Integer quantity;
	private Double unitPrice;
	private Double totalAmount;
	
	public Report() {
		
	}
	
	public Report(Invoice invoice, InvoiceProduct invoiceProduct) {
		this.id = invoice.getId();
		this.invoiceNo = invoice.getInvoiceNo();
		this.slipNo = invoice.getSlipNo();
		this.paymentReferenceNo = invoice.getPaymentReferenceNo();
		this.depositorName = invoice.getDepositorName();
		this.clientName = invoice.getClientName();
		this.paymentDateTime = invoice.getPaymentDateTime();
		this.cashierName = invoice.getCashier().getName();
		this.storeName = invoice.getCashier().getStore().getName();
		this.currency = invoice.getCurrency();
		
		this.quantity = invoiceProduct.getQuantity();
		this.productName = invoiceProduct.getProduct().getName();
		this.unitPrice = invoiceProduct.getProduct().getPrice();
		this.productCategoryName = invoiceProduct.getProduct().getProductCategory().getName();
		this.totalAmount = this.quantity * this.unitPrice;
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
	 * @return the productCategoryName
	 */
	public String getProductCategoryName() {
		return productCategoryName;
	}

	/**
	 * @param productCategoryName
	 *            the productCategoryName to set
	 */
	public void setProductCategoryName(String productCategoryName) {
		this.productCategoryName = productCategoryName;
	}

	/**
	 * @return the productName
	 */
	public String getProductName() {
		return productName;
	}

	/**
	 * @param productName
	 *            the productName to set
	 */
	public void setProductName(String productName) {
		this.productName = productName;
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
	 * @return the cashierName
	 */
	public String getCashierName() {
		return cashierName;
	}

	/**
	 * @param cashierName
	 *            the cashierName to set
	 */
	public void setCashierName(String cashierName) {
		this.cashierName = cashierName;
	}

	/**
	 * @return the storeName
	 */
	public String getStoreName() {
		return storeName;
	}

	/**
	 * @param storeName
	 *            the storeName to set
	 */
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	/**
	 * @return the quantity
	 */
	public Integer getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity
	 *            the quantity to set
	 */
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	/**
	 * @return the unitPrice
	 */
	public Double getUnitPrice() {
		return unitPrice;
	}

	/**
	 * @param unitPrice
	 *            the unitPrice to set
	 */
	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
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
	 * @return the totalAmount
	 */
	public Double getTotalAmount() {
		return totalAmount;
	}

	/**
	 * @param totalAmount the totalAmount to set
	 */
	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	/**
	 * @return the invoiceType
	 */
	public String getInvoiceType() {
		return invoiceType;
	}

	/**
	 * @param invoiceType the invoiceType to set
	 */
	public void setInvoiceType(String invoiceType) {
		this.invoiceType = invoiceType;
	}

	/**
	 * @return the currency
	 */
	public String getCurrency() {
		return currency;
	}

	/**
	 * @param currency the currency to set
	 */
	public void setCurrency(String currency) {
		this.currency = currency;
	}

	/**
	 * @return the paymentDateOnly
	 */
	public Date getPaymentDateOnly() {
		return paymentDateOnly;
	}

	/**
	 * @param paymentDateOnly the paymentDateOnly to set
	 */
	public void setPaymentDateOnly(Date paymentDateOnly) {
		this.paymentDateOnly = paymentDateOnly;
	}
}
