package com.boutique.controller;

import java.util.Date;

import com.boutique.common.controller.AbstractController;
import com.boutique.common.model.StatusMessage;
import com.boutique.common.util.Util;
import com.boutique.model.ConversionRate;
import com.boutique.model.InvoiceProduct;
import com.boutique.model.Product;
import com.boutique.util.CloneUtil;
import com.boutique.util.Constants;
import com.boutique.util.DataUtil;
import com.boutique.util.ReceiptGenerator;
import com.boutique.validation.ValidationUtil;

public class CartController extends AbstractController {

	public void loadProductDetails() {
		CartBean cartBean = getCartBean();

		if (cartBean.getSelectedProductId() > 0) {
			Product product = DataUtil.getProductById(cartBean.getSelectedProductId());
			cartBean.getInvoiceProduct().setProduct(product);
		} else {
			cartBean.getInvoiceProduct().setProduct(new Product());
		}
	}

	public void loadTotalPrice() {
		CartBean cartBean = getCartBean();
		cartBean.getValidationErrors().clear();

		System.out.println(cartBean.getInvoiceProduct().getTotalPrice());
	}

	private Boolean validateNewCartItem() {
		CartBean cartBean = getCartBean();
		cartBean.getValidationErrors().clear();
		
		if(cartBean.getSelectedProductCategoryId() == -1) {
			cartBean.getValidationErrors().addError("ProductCategory", "Select a value");
		}
		
		if(cartBean.getSelectedProductId() == -1) {
			cartBean.getValidationErrors().addError("Product", "Select a value");
		}
		
		if(cartBean.getInvoiceProduct().getQuantity() == null || cartBean.getInvoiceProduct().getQuantity() <= 0) {
			cartBean.getValidationErrors().addError("Quantity", "Enter a valid value");
		}

		return !cartBean.getValidationErrors().getAnyError();
	}

	public void addToCart() {

		if (validateNewCartItem()) {
			CartBean cartBean = getCartBean();

			int index = -1;
			boolean inEditMode = false;

			// replacing edited invoiceProduct
			for (InvoiceProduct invoiceProduct : cartBean.getInvoice().getInvoiceProducts()) {
				index++;
				if (Boolean.TRUE.equals(invoiceProduct.getEditable())) {
					inEditMode = true;
					break;
				}
			}
			
			cartBean.getInvoiceProduct().setTotalAmount(cartBean.getInvoiceProduct().getTotalPrice());

			if (inEditMode) {
				cartBean.getInvoice().getInvoiceProducts().remove(index);
				cartBean.getInvoiceProduct().setEditable(false);
				cartBean.getInvoice().getInvoiceProducts().add(index, cartBean.getInvoiceProduct());
			} else {
				cartBean.getInvoiceProduct().setInvoice(cartBean.getInvoice());
				cartBean.getInvoice().getInvoiceProducts().add(cartBean.getInvoiceProduct());
			}
			
			updateInvoiceTotalAmount();

			cartBean.clear();
		}
	}
	
	public void updateInvoiceTotalAmount() {
		CartBean cartBean = getCartBean();
		
		Double totalAmount = 0.0;
		
		for (InvoiceProduct invoiceProduct : cartBean.getInvoice().getInvoiceProducts()) {
			totalAmount += invoiceProduct.getTotalPrice();
		}
		
		ConversionRate conversionRate = DataUtil.getConversionRateByCurrency(cartBean.getInvoice().getCurrency());
		
		if(Constants.CURRENCY_CDF.equalsIgnoreCase(cartBean.getInvoice().getCurrency()) || Constants.CURRENCY_EURO.equalsIgnoreCase(cartBean.getInvoice().getCurrency())) {
			totalAmount = totalAmount * conversionRate.getRate();
		}
		
		cartBean.getInvoice().setAmount(totalAmount);
	}

	public void editCartItem(InvoiceProduct invoiceProduct) {
		if (invoiceProduct != null) {
			CartBean cartBean = getCartBean();

			for (InvoiceProduct tempInvoiceProduct : cartBean.getInvoice().getInvoiceProducts()) {
				tempInvoiceProduct.setEditable(false);
			}
			
			invoiceProduct.setEditable(true);
			cartBean.setSelectedProductCategoryId(invoiceProduct.getProduct().getProductCategory().getId());
			cartBean.setSelectedProductId(invoiceProduct.getProduct().getId());
			cartBean.loadProductsByCategory();
			
			cartBean.setInvoiceProduct(CloneUtil.cloneInvoiceProduct(invoiceProduct));
		}
	}

	public void deleteCartItem(InvoiceProduct invoiceProduct) {
		if (invoiceProduct != null) {
			getCartBean().getInvoice().getInvoiceProducts().remove(invoiceProduct);
		}
	}
	
	private Boolean validate() {
		CartBean cartBean = getCartBean();
		cartBean.getValidationErrors().clear();
		
		//ValidationUtil.validateTextField(true, "Invoice No", "InvoiceNo", cartBean.getInvoice().getInvoiceNo(), cartBean.getValidationErrors(), 45);
		ValidationUtil.validateTextField(true, "Client Name", "ClientName", cartBean.getInvoice().getClientName(), cartBean.getValidationErrors(), 45);
		ValidationUtil.validateTextField(true, "Depositor Name", "DepositorName", cartBean.getInvoice().getDepositorName(), cartBean.getValidationErrors(), 45);
		ValidationUtil.validateTextField(true, "Slip No", "SlipNo", cartBean.getInvoice().getSlipNo(), cartBean.getValidationErrors(), 45);
		ValidationUtil.validateTextField(true, "Payment Reference No", "PaymentReferenceNo", cartBean.getInvoice().getPaymentReferenceNo(), cartBean.getValidationErrors(), 45);
		
		if(cartBean.getInvoice().getAmount() < 0.0) {
			cartBean.getValidationErrors().addError("TotalAmount", "Total Amount should be greater than zero");
		}
		
		if(cartBean.getInvoice().getPurchaseDateTime() == null) {
			cartBean.getValidationErrors().addError("PurchaseDate", "Purchase Date can not be null");
		}
		
		if(Util.isNullOrEmpty(cartBean.getInvoice().getInvoiceProducts())) {
			cartBean.getValidationErrors().addError("InvoiceProducts", "Add atleast one product in cart");
		}
		
		return !cartBean.getValidationErrors().getAnyError();
	}
	
	public void saveInvoice() {
		if(validate()) {
			CartBean cartBean = getCartBean();
			
			cartBean.getInvoice().setPaymentDateTime(new Date());
			cartBean.getInvoice().setPaymentMode(Constants.PAYMENT_MODE_CASH);
			cartBean.getInvoice().setCashier(getSessionBean().getCashier());
			
			ConversionRate conversionRate = DataUtil.getConversionRateByCurrency(cartBean.getInvoice().getCurrency());
			
			if(Constants.CURRENCY_CDF.equalsIgnoreCase(cartBean.getInvoice().getCurrency()) || Constants.CURRENCY_EURO.equalsIgnoreCase(cartBean.getInvoice().getCurrency())) {
				for(InvoiceProduct invoiceProduct : cartBean.getInvoice().getInvoiceProducts()) {
					invoiceProduct.setTotalAmount(invoiceProduct.getTotalAmount() * conversionRate.getRate());
				}
			}
			
			StatusMessage statusMessage = getInvoiceDao().saveInvoice(cartBean.getInvoice());
			
			if(StatusMessage.SUCCESS.equals(statusMessage.getStatusMessage())) {
				cartBean.setStep(2);
				getStockDao().updateStock(getSessionBean().getCashier().getStore(), cartBean.getInvoice().getInvoiceProducts());
			}
		}
	}
	
	public void printReceipt() {
		CartBean cartBean = getCartBean();
		String receiptName = ReceiptGenerator.generateReceipt(cartBean.getInvoice(), getRealPath());

        String receiptUrl = "/boutique/receipts/" + receiptName;

        redirectToPage(receiptUrl);
	}
}
