package com.boutique.controller;

import java.util.Date;
import java.util.List;

import com.boutique.common.controller.AbstractController;
import com.boutique.common.util.ApplicationProperties;
import com.boutique.common.util.DateUtil;
import com.boutique.model.Cashier;
import com.boutique.model.Invoice;
import com.boutique.util.Constants;
import com.boutique.util.PDFGenerator;

public class ReportController extends AbstractController {

	private Boolean validate() {
		ReportBean reportBean = getReportBean();
		reportBean.getValidationErrors().clear();

		return !reportBean.getValidationErrors().getAnyError();
	}

	public void search() {
		if (validate()) {
			getReportBean().loadData();
		}
	}

	public void resetFilters() {
		ReportBean reportBean = getReportBean();
		reportBean.clear();
		reportBean.loadData();
	}

	public void showReport(Integer reportType) {
		ReportBean reportBean = getReportBean();
		reportBean.clear();
		reportBean.setReportType(reportType);
		reportBean.loadData();
	}
	
	public void generateIntegrationFiles() {

		try {
			
			Cashier cashier = getSessionBean().getCashier();
			
			List<Invoice> b2bInvoices = getInvoiceDao().getInvoicesByTypeAndDate(Constants.INVOICE_TYPE_B2B,
					DateUtil.getDateOnlyFromDateAndTime(new Date()), cashier.getStore().getName());
			
			PDFGenerator.generateIntegerationFile(b2bInvoices, ApplicationProperties.getPropertyByName("integerationFileDir"), Constants.INVOICE_TYPE_B2B, cashier);
			
			List<Invoice> distributorInvoices = getInvoiceDao().getInvoicesByTypeAndDate(Constants.INVOICE_TYPE_DISTRIBUTOR,
					DateUtil.getDateOnlyFromDateAndTime(new Date()), cashier.getStore().getName());
			
			PDFGenerator.generateIntegerationFile(distributorInvoices, ApplicationProperties.getPropertyByName("integerationFileDir"), Constants.INVOICE_TYPE_DISTRIBUTOR, cashier);
			
			List<Invoice> customerInvoices = getInvoiceDao().getInvoicesByTypeAndDate(Constants.INVOICE_TYPE_CUSTOMER,
					DateUtil.getDateOnlyFromDateAndTime(new Date()), cashier.getStore().getName());
			
			PDFGenerator.generateIntegerationFile(customerInvoices, ApplicationProperties.getPropertyByName("integerationFileDir"), Constants.INVOICE_TYPE_CUSTOMER, cashier);
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}

	}
}
