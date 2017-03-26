package com.boutique.util;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;

import com.boutique.model.InvoiceProduct;
import com.boutique.model.Product;

public class CloneUtil {

	public static InvoiceProduct cloneInvoiceProduct(InvoiceProduct invoiceProduct) {

		InvoiceProduct cloneInvoiceProduct = new InvoiceProduct();

		try {
			BeanUtilsBean.getInstance().getConvertUtils().register(false, false, 0);
			BeanUtils.copyProperties(cloneInvoiceProduct, invoiceProduct);
			
			if(invoiceProduct.getId() == null) {
				cloneInvoiceProduct.setId(null);
			}

		} catch (Exception e) {
			System.err.println(e);
		}

		return cloneInvoiceProduct;
	}
	
	public static Product cloneProduct(Product product) {

		Product cloneProduct = new Product();

		try {
			BeanUtilsBean.getInstance().getConvertUtils().register(false, false, 0);
			BeanUtils.copyProperties(cloneProduct, product);

		} catch (Exception e) {
			System.err.println(e);
		}

		return cloneProduct;
	}

}
