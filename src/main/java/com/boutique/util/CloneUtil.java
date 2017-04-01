package com.boutique.util;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;

import com.boutique.model.Cashier;
import com.boutique.model.InvoiceProduct;
import com.boutique.model.Product;
import com.boutique.model.Stock;
import com.boutique.model.Store;

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
	
	public static Cashier cloneCashier(Cashier cashier) {

		Cashier cloneCashier = new Cashier();

		try {
			BeanUtilsBean.getInstance().getConvertUtils().register(false, false, 0);
			BeanUtils.copyProperties(cloneCashier, cashier);

		} catch (Exception e) {
			System.err.println(e);
		}

		return cloneCashier;
	}
	
	public static Store cloneStore(Store store) {

		Store cloneStore = new Store();

		try {
			BeanUtilsBean.getInstance().getConvertUtils().register(false, false, 0);
			BeanUtils.copyProperties(cloneStore, store);

		} catch (Exception e) {
			System.err.println(e);
		}

		return cloneStore;
	}
	
	public static Stock cloneStock(Stock stock) {

		Stock cloneStock = new Stock();

		try {
			BeanUtilsBean.getInstance().getConvertUtils().register(false, false, 0);
			BeanUtils.copyProperties(cloneStock, stock);

		} catch (Exception e) {
			System.err.println(e);
		}

		return cloneStock;
	}

}
