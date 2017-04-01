package com.boutique.controller;

import java.util.ArrayList;
import java.util.List;

import com.boutique.model.Report;
import com.boutique.model.ReportCriteria;
import com.boutique.util.Constants;

public class ReportBean extends ReportController {

	private Integer reportType = 1;
	private ReportCriteria reportCriteria = new ReportCriteria();
	private List<Report> reports = new ArrayList<Report>();

	/**
	 * @return the reportType
	 */
	public Integer getReportType() {
		return reportType;
	}

	/**
	 * @param reportType
	 *            the reportType to set
	 */
	public void setReportType(Integer reportType) {
		this.reportType = reportType;
	}

	/**
	 * @return the reports
	 */
	public List<Report> getReports() {
		return reports;
	}

	/**
	 * @param reports
	 *            the reports to set
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
	 * @param reportCriteria
	 *            the reportCriteria to set
	 */
	public void setReportCriteria(ReportCriteria reportCriteria) {
		this.reportCriteria = reportCriteria;
	}

	public void loadData() {
		if (Constants.ROLE_CASHIER.equalsIgnoreCase(getSessionBean().getCashier().getRole())) {
			this.reportCriteria.setStoreId(getSessionBean().getCashier().getStore().getId());
		}

		this.reportCriteria.setCategoryId(this.getSelectedProductCategoryId());

		if (this.reportType.equals(1)) {
			this.reports = getInvoiceDao().getInvoiceReport(this.reportCriteria);
		} else if (this.reportType.equals(2)) {
			this.reports = getInvoiceDao().getInvoiceShortReport(this.reportCriteria);
		}
	}

	public void clear() {
		this.getValidationErrors().clear();
		this.setSelectedStoreId(-1);
		this.setSelectedProductCategoryId(-1);
		this.getProducts().clear();
		this.reportCriteria = new ReportCriteria();
	}
}
