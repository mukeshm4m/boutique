package com.boutique.model;

import java.io.Serializable;

public class InvoiceProduct implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private Integer quantity;
	private Double totalPrice;
	private Product product;
	private Invoice invoice;
	private Boolean editable = false;

	public InvoiceProduct() {

	}

	public InvoiceProduct(boolean initialize) {

		if (Boolean.TRUE.equals(initialize)) {
			this.product = new Product();
			this.invoice = new Invoice();
			this.quantity = 0;
			this.totalPrice = 0.0;
		}
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
	 * @return the product
	 */
	public Product getProduct() {
		return product;
	}

	/**
	 * @param product
	 *            the product to set
	 */
	public void setProduct(Product product) {
		this.product = product;
	}

	/**
	 * @return the invoice
	 */
	public Invoice getInvoice() {
		return invoice;
	}

	/**
	 * @param invoice
	 *            the invoice to set
	 */
	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

	/**
	 * @return the totalPrice
	 */
	public Double getTotalPrice() {
		totalPrice = 0.0;
		if (product.getPrice() != null) {
			totalPrice = quantity * product.getPrice();
		}
		return totalPrice;
	}

	/**
	 * @return the editable
	 */
	public Boolean getEditable() {
		return editable;
	}

	/**
	 * @param editable the editable to set
	 */
	public void setEditable(Boolean editable) {
		this.editable = editable;
	}
}
