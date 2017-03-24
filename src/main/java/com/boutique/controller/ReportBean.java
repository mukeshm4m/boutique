package com.boutique.controller;

import java.util.ArrayList;
import java.util.List;

import com.boutique.model.Report;
import com.boutique.model.ReportCriteria;

public class ReportBean extends ReportController {

	private ReportCriteria reportCriteria = new ReportCriteria();
	private List<Report> reports = new ArrayList<Report>();
	
	/**
	 * @return the reports
	 */
	public List<Report> getReports() {
		return reports;
	}

	/**
	 * @param reports the reports to set
	 */
	public void setReports(List<Report> reports) {
		this.reports = reports;
	}

	/**
	 * @return the reportCriteria
	 */
	public ReportCriteria getReportCriteria() {
		return reportCriteria;
	}

	/**
	 * @param reportCriteria the reportCriteria to set
	 */
	public void setReportCriteria(ReportCriteria reportCriteria) {
		this.reportCriteria = reportCriteria;
	}

	public void loadData() {
		this.reportCriteria.setCategoryId(this.getSelectedProductCategoryId());
		this.reports = getInvoiceDao().getInvoiceReport(this.reportCriteria);
	}

	public void clear() {
		this.getValidationErrors().clear();
		this.setSelectedStoreId(-1);
		this.setSelectedProductCategoryId(-1);
		this.getProducts().clear();
		this.reportCriteria = new ReportCriteria();
	}
}
