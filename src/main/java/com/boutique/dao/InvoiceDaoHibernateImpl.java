package com.boutique.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;

import com.boutique.common.model.StatusMessage;
import com.boutique.common.util.DateUtil;
import com.boutique.common.util.Util;
import com.boutique.dao.util.HibernateUtil;
import com.boutique.model.Invoice;
import com.boutique.model.Report;
import com.boutique.model.ReportCriteria;

public class InvoiceDaoHibernateImpl implements InvoiceDao {
	
	private Session getSession() {
		return HibernateUtil.getSessionFactory().openSession();
	}

	@Override
	public StatusMessage saveInvoice(Invoice invoice) {
		
		StatusMessage statusMessage = new StatusMessage();
		
		Session session = null;
		Transaction tx = null;

		try {

			session = getSession();
			
			tx = session.beginTransaction();
			session.saveOrUpdate(invoice);
			tx.commit();
			
			statusMessage.setStatusMessage(StatusMessage.SUCCESS);
			statusMessage.getObjects().put("InvoiceId", invoice.getId());
			
			session.close();

		} catch (Exception e) {
			statusMessage.setStatusMessage(StatusMessage.FAILURE);
			
			if(tx != null) {
				tx.rollback();
			}
			
			if(session != null) {
				session.close();
			}
			
			System.err.println(e);
		}
		
		return statusMessage;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Report> getInvoiceReport(ReportCriteria reportCriteria) {

		List<Report> reports = null;

		try {

			Session session = getSession();

			String queryString = getReportQuery(reportCriteria);

			Query query = session.createSQLQuery(queryString)
					.addScalar("id", StandardBasicTypes.INTEGER)
					.addScalar("invoiceNo", StandardBasicTypes.STRING)
					.addScalar("paymentReferenceNo", StandardBasicTypes.STRING)
					.addScalar("paymentDateTime", StandardBasicTypes.DATE)
					.addScalar("clientName", StandardBasicTypes.STRING)
					.addScalar("productCategoryName", StandardBasicTypes.STRING)
					.addScalar("productName", StandardBasicTypes.STRING)
					.addScalar("unitPrice", StandardBasicTypes.DOUBLE)
					.addScalar("quantity", StandardBasicTypes.INTEGER)
					.addScalar("cashierName", StandardBasicTypes.STRING)
					.addScalar("storeName", StandardBasicTypes.STRING)
					.addScalar("totalAmount", StandardBasicTypes.DOUBLE)
					.setResultTransformer(Transformers.aliasToBean(Report.class));

			reports = query.list();

			session.close();

		} catch (Exception e) {
			System.err.println(e);
		}

		return reports;
	}
	
	private String getReportQuery(ReportCriteria reportCriteria) {
		StringBuilder query = new StringBuilder("SELECT i.Id AS id, i.InvoiceNo AS invoiceNo, i.PaymentReferenceNo AS paymentReferenceNo, i.PaymentDateTime AS paymentDateTime, i.ClientName AS clientName, pc.Name AS productCategoryName, p.Name AS productName, p.Price AS unitPrice, ip.Quantity AS quantity, c.Name AS cashierName, s.Name AS storeName, (p.Price * ip.Quantity) AS totalAmount");
		query.append(" FROM BOUTIQUE.Invoice i INNER JOIN BOUTIQUE.InvoiceProduct ip ON (i.Id = ip.InvoiceId)");
		query.append(" INNER JOIN BOUTIQUE.Product p ON(ip.ProductId = p.Id)");
		query.append(" INNER JOIN BOUTIQUE.ProductCategory pc ON (p.ProductCategoryId = pc.Id)");
		query.append(" INNER JOIN BOUTIQUE.Cashier c ON (i.CashierId = c.Id)");
		query.append(" INNER JOIN BOUTIQUE.Store s ON (c.StoreId = s.Id)");
		
		
		StringBuilder whereClause = new StringBuilder(" WHERE");
		
		String and = " ";
		
		if(reportCriteria.getCategoryId() > 0) {
			whereClause.append(and).append(" pc.Id = " + reportCriteria.getCategoryId());
			and = " AND";
		}
		
		if(reportCriteria.getProductId() > 0) {
			whereClause.append(and).append(" p.Id = " + reportCriteria.getProductId());
			and = " AND";
		}
		
		if(reportCriteria.getStoreId() > 0) {
			whereClause.append(and).append(" s.Id = " + reportCriteria.getStoreId());
			and = " AND";
		}
		
		if(!Util.isNullOrZero(reportCriteria.getId())) {
			whereClause.append(and).append(" i.Id  = " + reportCriteria.getId());
			and = " AND";
		}
		
		if(Util.isNotNullAndEmpty(reportCriteria.getClientName())) {
			whereClause.append(and).append(" i.ClientName  = '" + reportCriteria.getClientName() + "'");
			and = " AND";
		}
		
		if(reportCriteria.getFromDate() != null) {
			// for mysql
			//whereClause.append(and).append(" i.PaymentDateTime  >= '" + DateUtil.formatDateOnlyPatternYearMonthDay(reportCriteria.getFromDate()) + " 00:00:00'");
			// for oracle
			whereClause.append(and).append(" to_char(i.PaymentDateTime, 'yyyy-mm-dd hh24:mi:ss')  >= '" + DateUtil.formatDateOnlyPatternYearMonthDay(reportCriteria.getFromDate()) + " 00:00:00'");
			and = " AND";
		}
		
		if(reportCriteria.getToDate() != null) {
			// for mysql
			//whereClause.append(and).append(" i.PaymentDateTime  <= '" + DateUtil.formatDateOnlyPatternYearMonthDay(reportCriteria.getToDate()) + " 23:59:59'");
			// for oracle
			whereClause.append(and).append(" to_char(i.PaymentDateTime, 'yyyy-mm-dd hh24:mi:ss')  <= '" + DateUtil.formatDateOnlyPatternYearMonthDay(reportCriteria.getToDate()) + " 23:59:59'");
		}
		
		if(!whereClause.toString().trim().equals("WHERE")) {
			query.append(whereClause.toString());
		}
		
		query.append(" ORDER BY i.Id");
		
		return query.toString();
	}
	

}
