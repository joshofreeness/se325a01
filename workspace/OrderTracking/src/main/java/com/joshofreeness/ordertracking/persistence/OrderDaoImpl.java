package com.joshofreeness.ordertracking.persistence;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.joshofreeness.ordertracking.domain.Order;


@Repository("orderDao")
@Transactional
public class OrderDaoImpl implements OrderDao{
	
	private final Logger log = Logger.getLogger(OrderDaoImpl.class);
	private SessionFactory sessionFactory;
	
	@Resource
	public void setSessionFactory(SessionFactory s){
		sessionFactory = s;
	}


	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly=true)
	public List<Order> findAll() {
		List<Order> result = sessionFactory.getCurrentSession().createQuery("from Order o").list();
		return result;
	}

	@Override
	public void delete(Order order) {
		sessionFactory.getCurrentSession().delete(order);
		log.info("Order deleted with Id: " + order.getId());
		
	}


	@SuppressWarnings("unchecked")
	@Override
	public Order findById(Long id) {
		List<Order> result = sessionFactory.getCurrentSession().createQuery("from Order as o where o.id = :id").setParameter("id", id).list();
		if (result.size() < 1){
			log.info("RETURNED NULL "+ result.size());
			return null;
		} 
		log.info("RETURNED OBJECT");
		return result.get(0);
	}

	@Override
	public Order save(Order order) {
		sessionFactory.getCurrentSession().saveOrUpdate(order);
		log.info("Order saved with Id: " + order.getId());
		return order;
	}
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

}
