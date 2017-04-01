package com.boutique.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.boutique.common.model.StatusMessage;
import com.boutique.dao.util.HibernateUtil;
import com.boutique.model.Cashier;
import com.boutique.model.Store;

public class CashierDaoHibernateImpl implements CashierDao {
	
	private Session getSession() {
		return HibernateUtil.getSessionFactory().openSession();
	}

	@Override
	public Cashier login(String username, String password) {

		Cashier cashier = null;

		try {

			Session session = getSession();

			Criteria criteria = session.createCriteria(Cashier.class).add(Restrictions.eq("username", username)).add(Restrictions.eq("password", password));

			cashier = (Cashier) criteria.uniqueResult();
			
			session.close();

		} catch (Exception e) {
			System.err.println(e);
		}

		return cashier;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Store> getAllStores() {

		List<Store> stores = null;

		try {

			Session session = getSession();

			Criteria criteria = session.createCriteria(Store.class);

			stores = criteria.list();
			
			session.close();

		} catch (Exception e) {
			System.err.println(e);
		}

		return stores;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Cashier> getAllCashier() {

		List<Cashier> cashiers = null;

		try {

			Session session = getSession();

			Criteria criteria = session.createCriteria(Cashier.class);

			cashiers = criteria.list();
			
			session.close();

		} catch (Exception e) {
			System.err.println(e);
		}

		return cashiers;
	}
	
	@Override
	public StatusMessage saveStore(Store store) {

		StatusMessage statusMessage = new StatusMessage();

		Session session = null;
		Transaction tx = null;

		try {

			session = getSession();

			tx = session.beginTransaction();
			session.saveOrUpdate(store);
			tx.commit();

			statusMessage.setStatusMessage(StatusMessage.SUCCESS);
			statusMessage.getObjects().put("StoreId", store.getId());

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
	public StatusMessage saveCashier(Cashier cashier) {

		StatusMessage statusMessage = new StatusMessage();

		Session session = null;
		Transaction tx = null;

		try {

			session = getSession();

			tx = session.beginTransaction();
			session.saveOrUpdate(cashier);
			tx.commit();

			statusMessage.setStatusMessage(StatusMessage.SUCCESS);
			statusMessage.getObjects().put("CashierId", cashier.getId());

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
	public void deleteCashier(Cashier cashier) {

		Session session = null;
		Transaction tx = null;

		try {

			session = getSession();

			tx = session.beginTransaction();
			session.delete(cashier);
			tx.commit();

			session.close();

		} catch (Exception e) {

			if (tx != null) {
				tx.rollback();
			}

			if (session != null) {
				session.close();
			}

			System.err.println(e);
		}
	}
	
	@Override
	public void deleteStore(Store store) {

		Session session = null;
		Transaction tx = null;

		try {

			session = getSession();

			tx = session.beginTransaction();
			session.delete(store);
			tx.commit();

			session.close();

		} catch (Exception e) {

			if (tx != null) {
				tx.rollback();
			}

			if (session != null) {
				session.close();
			}

			System.err.println(e);
		}
	}
}
