package com.boutique.dao;

import java.util.List;

import com.boutique.common.model.StatusMessage;
import com.boutique.model.Invoice;
import com.boutique.model.Report;
import com.boutique.model.ReportCriteria;

public interface InvoiceDao {
	
	StatusMessage saveInvoice(Invoice invoice);

	List<Report> getInvoiceReport(ReportCriteria reportCriteria);
	
}
