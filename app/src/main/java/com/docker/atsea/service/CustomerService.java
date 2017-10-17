package com.docker.atsea.service;

import com.docker.atsea.model.Customer;

import java.util.List;

// 与Customer数据相关的各种服务

public interface CustomerService {

	Customer findById(Long customerId);

	Customer findByUserName(String name);
	
	Customer findByName(String name);
	
	Customer createCustomer(Customer customer);

	void saveCustomer(Customer customer);

	void updateCustomer(Customer customer);

	void deleteCustomerById(Long customerId);

	void deleteAllCustomers();

	List<Customer> findAllCustomers();

	boolean customerExist(Customer customer);
}
