package com.boutique.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

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
}
