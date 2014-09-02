package com.joshofreeness.ordertracking.persistence;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.joshofreeness.ordertracking.domain.Customer;
import com.joshofreeness.ordertracking.domain.Product;

@Repository("customerDao")
@Transactional
public class CustomerDaoImpl implements CustomerDao{
	
	private final Logger log = Logger.getLogger(CustomerDaoImpl.class);
	private SessionFactory sessionFactory;

	
	public List<Customer> findAll() {
		List<Customer> result = sessionFactory.getCurrentSession().createQuery("from Customer as c").list();
		return result;
	}

	public void delete(Customer customer) {
		sessionFactory.getCurrentSession().delete(customer);
		log.info("Customer deleted with Name: " + customer.getFirstName()+ " "+ customer.getLastName());
		
	}

	public List<Customer> findAllWithDetail() {
		//TODO: Edit the query so that it joins tables with orders
		List<Customer> result = sessionFactory.getCurrentSession().getNamedQuery("from Customer as c").list();
		return result;
	}

	public Customer findById(Long id) {
		Customer result = (Customer) sessionFactory.getCurrentSession().getNamedQuery("from Customer as c where c.id = :id").setParameter("id", id);
		return result;
	}

	public Customer save(Customer customer) {
		sessionFactory.getCurrentSession().saveOrUpdate(customer);
		log.info("Customer saved with name: " + customer.getFirstName()+ " "+ customer.getLastName());
		return customer;
	}

}
