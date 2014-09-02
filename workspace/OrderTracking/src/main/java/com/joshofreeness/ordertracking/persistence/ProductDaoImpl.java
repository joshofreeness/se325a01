package com.joshofreeness.ordertracking.persistence;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.joshofreeness.ordertracking.domain.Product;

@Repository("productDao")
@Transactional
public class ProductDaoImpl implements ProductDao{
	
	private final Logger log = Logger.getLogger(ProductDaoImpl.class);
	private SessionFactory sessionFactory;

	@Override
	public List<Product> findAll() {
		List<Product> result = sessionFactory.getCurrentSession().createQuery("from Product as p").list();
		return result;
	}

	@Override
	public void delete(Product product) {
		sessionFactory.getCurrentSession().delete(product);
		log.info("Product deleted with Name: " + product.getName());
		
	}

	@Override
	public List<Product> findAllWithDetail() {
		//TODO: Edit the query so that it joins tables with orders
		List<Product> result = sessionFactory.getCurrentSession().getNamedQuery("from Product as p").list();
		return result;
	}

	@Override
	public Product findById(Long id) {
		Product result = (Product) sessionFactory.getCurrentSession().getNamedQuery("from Product as p where p.id = :id").setParameter("id", id);
		return result;
	}

	@Override
	public Product save(Product product) {
		sessionFactory.getCurrentSession().saveOrUpdate(product);
		log.info("Product saved with name: " + product.getName());
		return product;
	}

}
