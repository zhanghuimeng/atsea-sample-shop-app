package com.docker.atsea.service;

import com.docker.atsea.model.Order;

import java.util.List;

// Order相关的数据库操作

public interface OrderService {

	List<Order> findAllOrders();
	
	Order findById(Long orderId);
	
	Order createOrder(Order order);
	
	void saveOrder(Order order);
	
	void updateOrder(Order order);
	
	void deleteOrderById(Long orderId);
	
	void deleteAllItems();

	boolean orderExists(Order order);	
}