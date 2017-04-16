package com.boutique.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.boutique.common.model.StatusMessage;
import com.boutique.common.util.Util;
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

			Criteria criteria = session.createCriteria(Cashier.class).add(Restrictions.eq("username", username)).add(Restrictions.eq("password", password)).add(Restrictions.eq("active", true));;

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
			criteria.add(Restrictions.eq("active", true));

			stores = criteria.list();
			
			session.close();

		} catch (Exception e) {
			System.err.println(e);
		}

		return stores;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Cashier> getAllCashier(Integer storeId) {

		List<Cashier> cashiers = null;

		try {

			Session session = getSession();

			Criteria criteria = session.createCriteria(Cashier.class);
			criteria.add(Restrictions.eq("active", true));
			
			if(storeId != null) {
				criteria.createCriteria("store").add(Restrictions.eq("id", storeId));
			}

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
			cashier.setActive(false);
			session.saveOrUpdate(cashier);
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
			
			store.setActive(false);
			session.saveOrUpdate(store);
			
			tx.commit();

			session.close();
			
			List<Cashier> cashiers = getAllCashier(store.getId());
			for (Cashier cashier : cashiers) {
				deleteCashier(cashier);
			}

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
	public Store getStoreByName(String name, Integer storeId) {

		Store store = null;

		try {

			Session session = getSession();

			Criteria criteria = session.createCriteria(Store.class);
			criteria.add(Restrictions.eq("name", name));
			
			if(!Util.isNullOrZero(storeId)) {
				criteria.add(Restrictions.ne("id", storeId));
			}

			store = (Store) criteria.uniqueResult();
			
			session.close();

		} catch (Exception e) {
			System.err.println(e);
		}

		return store;
	}
	
	@Override
	public Cashier getCashierByUsername(String username, Integer cashierId) {

		Cashier cashier = null;

		try {

			Session session = getSession();

			Criteria criteria = session.createCriteria(Cashier.class);
			criteria.add(Restrictions.eq("username", username));
			
			if(!Util.isNullOrZero(cashierId)) {
				criteria.add(Restrictions.ne("id", cashierId));
			}

			cashier = (Cashier) criteria.uniqueResult();
			
			session.close();

		} catch (Exception e) {
			System.err.println(e);
		}

		return cashier;
	}
}
