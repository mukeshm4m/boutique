package com.boutique.controller.admin;

import com.boutique.common.controller.AbstractController;
import com.boutique.model.Stock;
import com.boutique.util.CloneUtil;

public class StockController extends AbstractController {

	private Boolean validate() {
		StockBean stockBean = getStockBean();
		stockBean.getValidationErrors().clear();

		if (stockBean.getStock().getQuantity() < 0) {
			stockBean.getValidationErrors().addError("Quantity", "Enter a valid value");
		}

		return !stockBean.getValidationErrors().getAnyError();
	}

	public void updateStock() {

		if (validate()) {
			StockBean stockBean = getStockBean();

			getStockDao().saveStock(stockBean.getStock());

			stockBean.loadStocks();

			stockBean.clear();
		}
	}

	public void showStockUpdatePopup(Stock stock) {
		if (stock != null) {
			StockBean stockBean = getStockBean();

			stockBean.setStock(CloneUtil.cloneStock(stock));
		}
	}
}
