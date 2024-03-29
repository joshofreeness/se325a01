package com.joshofreeness.ordertracking.persistence;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.joshofreeness.ordertracking.domain.Customer;



@Repository("customerDao")
@Transactional
public class CustomerDaoImpl implements CustomerDao{
	
	private final Logger log = Logger.getLogger(CustomerDaoImpl.class);
	private SessionFactory sessionFactory;

	@Resource
	public void setSessionFactory(SessionFactory s){
		sessionFactory = s;
	}
	
	
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	public List<Customer> findAll() {
		List<Customer> result = sessionFactory.getCurrentSession().createQuery("from Customer c").list();
		return result;
	}
	@Transactional
	public void delete(Customer customer) {
		sessionFactory.getCurrentSession().delete(customer);
		log.info("Customer deleted with Name: " + customer.getFirstName()+ " "+ customer.getLastName());
		
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Customer findById(Long id) {
		List<Customer> result = sessionFactory.getCurrentSession().createQuery("from Customer as c where c.id = :id").setParameter("id", id).list();
		if (result.size() < 1){
			return null;
		} 
		return result.get(0);
	}
	@Transactional
	public Customer save(Customer customer) {
		sessionFactory.getCurrentSession().saveOrUpdate(customer);
		log.info("Customer saved with name: " + customer.getFirstName()+ " "+ customer.getLastName());
		return customer;
	}
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

}
