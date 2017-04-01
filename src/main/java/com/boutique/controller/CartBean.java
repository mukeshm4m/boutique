package com.boutique.controller;

import com.boutique.model.Invoice;
import com.boutique.model.InvoiceProduct;
import com.boutique.model.Product;
import com.boutique.util.Constants;

public class CartBean extends CartController {

	private Integer step = 1;
	
	private InvoiceProduct invoiceProduct = new InvoiceProduct(true);
	private Invoice invoice = new Invoice();
	
	/**
	 * @return the step
	 */
	public Integer getStep() {
		return step;
	}

	/**
	 * @param step the step to set
	 */
	public void setStep(Integer step) {
		this.step = step;
	}

	/**
	 * @return the invoiceProduct
	 */
	public InvoiceProduct getInvoiceProduct() {
		return invoiceProduct;
	}

	/**
	 * @param invoiceProduct the invoiceProduct to set
	 */
	public void setInvoiceProduct(InvoiceProduct invoiceProduct) {
		this.invoiceProduct = invoiceProduct;
	}

	/**
	 * @return the invoice
	 */
	public Invoice getInvoice() {
		return invoice;
	}

	/**
	 * @param invoice the invoice to set
	 */
	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}
	
	 public void init(){
		 this.invoice.setInvoiceType(Constants.INVOICE_TYPE_CUSTOMER);
		 this.invoice.setAmount(0.0);
	 }
	
	public void clear() {
		this.getValidationErrors().clear();
		this.getProducts().clear();
		this.setSelectedProductCategoryId(-1);
		this.setSelectedProductId(-1);
		this.invoiceProduct = new InvoiceProduct(true);
	}
	
	public void loadProductsByCategory() {
		super.loadProductsByCategory();
		this.invoiceProduct.setProduct(new Product());
	}
}
