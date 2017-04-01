package com.boutique.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.boutique.common.model.StatusMessage;
import com.boutique.dao.util.HibernateUtil;
import com.boutique.model.ConversionRate;
import com.boutique.model.Product;
import com.boutique.model.ProductCategory;

public class ProductDaoHibernateImpl implements ProductDao {
	
	private Session getSession() {
		return HibernateUtil.getSessionFactory().openSession();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ProductCategory> getAllProductCategories() {
		
		List<ProductCategory> productCategories = null;
		try {

			Session session = getSession();

			Criteria criteria = session.createCriteria(ProductCategory.class);

			productCategories = criteria.list();
			
			session.close();

		} catch (Exception e) {
			System.err.println(e);
		}
		
		return productCategories;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> getAllProducts() {
		List<Product> products = null;
		try {

			Session session = getSession();

			Criteria criteria = session.createCriteria(Product.class);

			products = criteria.list();
			
			session.close();

		} catch (Exception e) {
			System.err.println(e);
		}
		
		return products;
	}
	
	@Override
	public StatusMessage saveProduct(Product product) {

		StatusMessage statusMessage = new StatusMessage();

		Session session = null;
		Transaction tx = null;

		try {

			session = getSession();

			tx = session.beginTransaction();
			session.saveOrUpdate(product);
			tx.commit();

			statusMessage.setStatusMessage(StatusMessage.SUCCESS);
			statusMessage.getObjects().put("ProductId", product.getId());

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
	public void deleteProduct(Product product) {

		Session session = null;
		Transaction tx = null;

		try {

			session = getSession();

			tx = session.beginTransaction();
			session.delete(product);
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
	public StatusMessage saveProductCategory(ProductCategory productCategory) {

		StatusMessage statusMessage = new StatusMessage();

		Session session = null;
		Transaction tx = null;

		try {

			session = getSession();

			tx = session.beginTransaction();
			session.saveOrUpdate(productCategory);
			tx.commit();

			statusMessage.setStatusMessage(StatusMessage.SUCCESS);
			statusMessage.getObjects().put("ProductCategoryId", productCategory.getId());

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
	public StatusMessage saveConversionRate(ConversionRate conversionRate) {

		StatusMessage statusMessage = new StatusMessage();

		Session session = null;
		Transaction tx = null;

		try {

			session = getSession();

			tx = session.beginTransaction();
			session.saveOrUpdate(conversionRate);
			tx.commit();

			statusMessage.setStatusMessage(StatusMessage.SUCCESS);
			statusMessage.getObjects().put("ConversionRateId", conversionRate.getId());

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
	public List<ConversionRate> getAllConversionRates() {
		List<ConversionRate> conversionRates = null;
		try {

			Session session = getSession();

			Criteria criteria = session.createCriteria(ConversionRate.class);

			conversionRates = criteria.list();
			
			session.close();

		} catch (Exception e) {
			System.err.println(e);
		}
		
		return conversionRates;
	}
}
