package com.boutique.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;

import com.boutique.dao.util.HibernateUtil;
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

	
}
