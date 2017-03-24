package com.boutique.controller;

import com.boutique.common.controller.AbstractController;

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
}
