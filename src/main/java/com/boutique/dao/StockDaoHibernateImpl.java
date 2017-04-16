package com.boutique.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.boutique.common.model.StatusMessage;
import com.boutique.common.util.Util;
import com.boutique.dao.util.HibernateUtil;
import com.boutique.model.InvoiceProduct;
import com.boutique.model.Stock;
import com.boutique.model.Store;

public class StockDaoHibernateImpl implements StockDao {

	private Session getSession() {
		return HibernateUtil.getSessionFactory().openSession();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Stock> getStocksByStoreId(Integer storeId) {
		List<Stock> stocks = null;
		try {

			Session session = getSession();

			Criteria criteria = session.createCriteria(Stock.class);
			criteria.createCriteria("store").add(Restrictions.eq("id", storeId));
			criteria.createCriteria("product").add(Restrictions.eq("active", true));

			stocks = criteria.list();
			
			if(Util.isNotNullAndEmpty(stocks)) {
				for (Stock stock : stocks) {
					Hibernate.initialize(stock.getProduct());
					Hibernate.initialize(stock.getProduct().getProductCategory());
					Hibernate.initialize(stock.getStore());
				}
			}

			session.close();

		} catch (Exception e) {
			System.err.println(e);
		}

		return stocks;
	}

	@Override
	public StatusMessage saveStock(Stock stock) {
		StatusMessage statusMessage = new StatusMessage();

		Session session = null;
		Transaction tx = null;

		try {

			session = getSession();

			tx = session.beginTransaction();
			session.saveOrUpdate(stock);
			tx.commit();

			statusMessage.setStatusMessage(StatusMessage.SUCCESS);
			statusMessage.getObjects().put("StockId", stock.getId());

			session.close();

		} catch (Exception e) {
			statusMessage.setStatusMessage(StatusMessage.FAILURE);

			if (tx != null) {
				tx.rollback();
			}

			if (session != null) {
				session.close();
			}

			System.err.println(e);
		}

		return statusMessage;
	}

	@Override
	public void updateStock(Store store, List<InvoiceProduct> invoiceProducts) {
		try {

			for (InvoiceProduct invoiceProduct : invoiceProducts) {
				Session session = getSession();

				Criteria criteria = session.createCriteria(Stock.class);
				criteria.add(Restrictions.and(Restrictions.eq("store", store), Restrictions.eq("product", invoiceProduct.getProduct())));

				Stock stock = (Stock) criteria.uniqueResult();

				session.close();

				if (stock != null) {
					if (stock.getQuantity() >= invoiceProduct.getQuantity()) {
						stock.setQuantity(stock.getQuantity() - invoiceProduct.getQuantity());
					} else {
						stock.setQuantity(0);
					}

					saveStock(stock);
				}
			}

		} catch (Exception e) {
			System.err.println(e);
		}
	}
}
