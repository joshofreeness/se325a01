package com.joshofreeness.ordertracking.persistence;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.joshofreeness.ordertracking.domain.Order;
import com.joshofreeness.ordertracking.domain.Product;

@Repository("orderDao")
@Transactional
public class OrderDaoImpl implements OrderDao{
	
	private final Logger log = Logger.getLogger(OrderDaoImpl.class);
	private SessionFactory sessionFactory;
	
	@Resource
	public void setSessionFactory(SessionFactory s){
		sessionFactory = s;
		//TODO: Change this statement
		log.debug("SessionFactory class: " + sessionFactory.getClass().getName());
	}


	@Override
	public List<Order> findAll() {
		List<Order> result = sessionFactory.getCurrentSession().createQuery("from Order_table as o").list();
		return result;
	}

	@Override
	public void delete(Order order) {
		sessionFactory.getCurrentSession().delete(order);
		log.info("Order deleted with Id: " + order.getId());
		
	}

	@Override
	public List<Order> findAllWithDetail() {
		//TODO: Edit the query so that it joins tables with orders
		List<Order> result = sessionFactory.getCurrentSession().getNamedQuery("from Order_table as p").list();
		return result;
	}

	@Override
	public Order findById(Long id) {
		Order result = (Order) sessionFactory.getCurrentSession().getNamedQuery("from Order_table as o where o.id = :id").setParameter("id", id);
		return result;
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
