package com.boutique.dao;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;

import com.boutique.common.model.StatusMessage;
import com.boutique.common.util.DateUtil;
import com.boutique.common.util.Util;
import com.boutique.dao.util.HibernateUtil;
import com.boutique.model.Invoice;
import com.boutique.model.Report;
import com.boutique.model.ReportCriteria;
import com.boutique.validation.ValidationUtil;

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
					.addScalar("invoiceType", StandardBasicTypes.STRING)
					.addScalar("currency", StandardBasicTypes.STRING)
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
		StringBuilder query = new StringBuilder("SELECT i.Id AS id, i.InvoiceNo AS invoiceNo, i.PaymentReferenceNo AS paymentReferenceNo, i.PaymentDateTime AS paymentDateTime, i.ClientName AS clientName, i.InvoiceType AS invoiceType, i.Currency AS currency, pc.Name AS productCategoryName, p.Name AS productName, p.Price AS unitPrice, ip.Quantity AS quantity, c.Name AS cashierName, s.Name AS storeName, ip.TotalAmount AS totalAmount");
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
		
		if(Util.isNotNullAndEmpty(reportCriteria.getInvoiceType()) && !"-1".equalsIgnoreCase(reportCriteria.getInvoiceType())) {
			whereClause.append(and).append(" i.InvoiceType  = '" + reportCriteria.getInvoiceType() + "'");
			and = " AND";
		}
		
		if(reportCriteria.getFromDate() != null) {
			whereClause.append(and).append(" to_char(i.PaymentDateTime, 'yyyy-mm-dd hh24:mi:ss')  >= '" + DateUtil.formatDateOnlyPatternYearMonthDay(reportCriteria.getFromDate()) + " 00:00:00'");
			and = " AND";
		}
		
		if(reportCriteria.getToDate() != null) {
			whereClause.append(and).append(" to_char(i.PaymentDateTime, 'yyyy-mm-dd hh24:mi:ss')  <= '" + DateUtil.formatDateOnlyPatternYearMonthDay(reportCriteria.getToDate()) + " 23:59:59'");
		}
		
		if(!whereClause.toString().trim().equals("WHERE")) {
			query.append(whereClause.toString());
		}
		
		query.append(" ORDER BY i.Id");
		
		return query.toString();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Report> getInvoiceShortReport(ReportCriteria reportCriteria) {

		List<Report> reports = null;

		try {

			Session session = getSession();
			
			String queryString = getShortReportQuery(reportCriteria);

			Query query = session.createSQLQuery(queryString)
					.addScalar("storeName", StandardBasicTypes.STRING)
					.addScalar("currency", StandardBasicTypes.STRING)
					.addScalar("paymentDateTime", StandardBasicTypes.DATE)
					//.addScalar("paymentDateOnly", StandardBasicTypes.DATE)
					.addScalar("totalAmount", StandardBasicTypes.DOUBLE)
					.setResultTransformer(Transformers.aliasToBean(Report.class));

			reports = query.list();
			
			session.close();

		} catch (Exception e) {
			System.err.println(e);
		}

		return reports;
	}
	
	private String getShortReportQuery(ReportCriteria reportCriteria) {
		//StringBuilder query = new StringBuilder("SELECT s.Name AS storeName, i.Currency AS currency, i.PaymentDateTime AS paymentDateTime, DATE(i.PaymentDateTime) AS paymentDateOnly, SUM(i.Amount) AS totalAmount");
		StringBuilder query = new StringBuilder("SELECT s.Name AS storeName, i.Currency AS currency, i.PaymentDateTime AS paymentDateTime, SUM(i.Amount) AS totalAmount");
		query.append(" FROM BOUTIQUE.Invoice i INNER JOIN BOUTIQUE.Cashier c ON (i.CashierId = c.Id)");
		query.append(" INNER JOIN BOUTIQUE.Store s ON (c.StoreId = s.Id)");
		
		StringBuilder whereClause = new StringBuilder(" WHERE");
		
		String and = " ";
		
		if(reportCriteria.getStoreId() > 0) {
			whereClause.append(and).append(" s.Id = " + reportCriteria.getStoreId());
			and = " AND";
		}
		
		if(reportCriteria.getFromDate() != null) {
			whereClause.append(and).append(" to_char(i.PaymentDateTime, 'yyyy-mm-dd')  >= '" + DateUtil.formatDateOnlyPatternYearMonthDay(reportCriteria.getFromDate()) + " 00:00:00'");
			and = " AND";
			whereClause.append(and).append(" to_char(i.PaymentDateTime, 'yyyy-mm-dd')  <= '" + DateUtil.formatDateOnlyPatternYearMonthDay(reportCriteria.getFromDate()) + " 00:00:00'");
		}
		
		if(!whereClause.toString().trim().equals("WHERE")) {
			query.append(whereClause.toString());
		}
		
		query.append(" GROUP BY s.Name, i.Currency, i.PaymentDateTime");
		
		query.append(" ORDER BY i.PaymentDateTime DESC");
		
		return query.toString();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Invoice> getInvoicesByTypeAndDate(String type, Date date, String branchName) {
		
		List<Invoice> invoices = null;

		try {
			
			Date maxDate = new Date(date.getTime() + TimeUnit.DAYS.toMillis(1));

			Session session = getSession();

			Criteria criteria = session.createCriteria(Invoice.class)
					.add(Restrictions.eq("invoiceType", type));
			
			Conjunction and = Restrictions.conjunction();
			
		    and.add( Restrictions.ge("paymentDateTime", date) );
		    and.add( Restrictions.lt("paymentDateTime", maxDate) ); 
		    
		    criteria.add(and);
			
			invoices = criteria.list();
			
			if(Boolean.FALSE.equals(ValidationUtil.isNullOrEmpty(branchName))) {
				invoices = invoices.stream()
						.filter(i -> branchName.equals(i.getCashier().getStore().getName()))
						.collect(Collectors.toList());
			}
			
			session.close();

		} catch (Exception e) {
			System.err.println(e);
		}

		return invoices;
	}

}
