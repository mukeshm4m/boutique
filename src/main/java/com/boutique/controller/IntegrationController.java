package com.boutique.controller;

import java.util.List;

import com.boutique.common.controller.AbstractController;
import com.boutique.common.util.ApplicationProperties;
import com.boutique.common.util.DateUtil;
import com.boutique.model.Cashier;
import com.boutique.model.Invoice;
import com.boutique.util.Constants;
import com.boutique.util.PDFGenerator;
import com.boutique.util.ReceiptGenerator;
import com.boutique.validation.ValidationUtil;

public class IntegrationController extends AbstractController {

	public void generateIntegrationFiles() {

		IntegrationBean integrationBean = getIntegrationBean();

		// Clear the bean
		integrationBean.clear();

		if (ValidationUtil.validateDate(Boolean.TRUE, "Date", "Date", integrationBean.getDate(),
				integrationBean.getValidationErrors())) {

			try {

				Cashier cashier = getSessionBean().getCashier();

				List<Invoice> b2bInvoices = getInvoiceDao().getInvoicesByTypeAndDate(Constants.INVOICE_TYPE_B2B,
						DateUtil.getDateOnlyFromDateAndTime(integrationBean.getDate()), cashier.getStore().getName());

				integrationBean.setB2bPdfUrl("/boutique/integrationfiles/" + PDFGenerator.generateIntegerationFile(b2bInvoices,
						getRealPath() + "integrationfiles/", Constants.INVOICE_TYPE_B2B, cashier));

				List<Invoice> distributorInvoices = getInvoiceDao().getInvoicesByTypeAndDate(
						Constants.INVOICE_TYPE_DISTRIBUTOR, DateUtil.getDateOnlyFromDateAndTime(integrationBean.getDate()),
						cashier.getStore().getName());

				integrationBean.setDistributorPdfUrl("/boutique/integrationfiles/" + PDFGenerator.generateIntegerationFile(distributorInvoices,
						getRealPath() + "integrationfiles/", Constants.INVOICE_TYPE_DISTRIBUTOR, cashier));

				List<Invoice> customerInvoices = getInvoiceDao().getInvoicesByTypeAndDate(
						Constants.INVOICE_TYPE_CUSTOMER, DateUtil.getDateOnlyFromDateAndTime(integrationBean.getDate()),
						cashier.getStore().getName());

				integrationBean.setCustomerPdfUrl("/boutique/integrationfiles/" + PDFGenerator.generateIntegerationFile(customerInvoices,
						getRealPath() + "integrationfiles/", Constants.INVOICE_TYPE_CUSTOMER, cashier));

			} catch (Exception e) {

				e.printStackTrace();
			}
		}
	}
}
