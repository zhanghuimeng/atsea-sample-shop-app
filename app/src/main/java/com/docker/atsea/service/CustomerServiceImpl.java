package com.docker.atsea.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.docker.atsea.model.Customer;
import com.docker.atsea.repositories.CustomerRepository;

// Customer数据操作的实现类
// Spring Boot 使用事务非常简单，首先使用注解 @EnableTransactionManagement 开启事务支持后，
// 然后在访问数据库的Service方法上添加注解 @Transactional 便可。
// Service通过Repository访问数据库
// 这些操作好像都比较平凡，主要是JpaRepository和Customer的Model实现的。

@Service("customerService")
@Transactional
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;

	public Customer findById(Long customerId) {
		return customerRepository.findOne(customerId);
	}

	public Customer findByUserName(String name) {
		return customerRepository.findByUserName(name);
	}

	public Customer findByName(String name) {
		return customerRepository.findByName(name);
	}
	
	public Customer createCustomer(Customer customer) {		
		customer = customerRepository.save(customer);
		customerRepository.flush();
		return customer;
	}
	
	public void saveCustomer(Customer customer) {
		customerRepository.save(customer);
	}

	public void updateCustomer(Customer customer) {
		customerRepository.save(customer);
	}

	public void deleteAllCustomers() {
		customerRepository.deleteAll();
	}

	public List<Customer> findAllCustomers() {
		return (List<Customer>) customerRepository.findAll();
	}
	
	public boolean customerExist(Customer customer) {
		System.out.println(customer.getUsername());
		return customerRepository.findByUserName(customer.getUsername()) != null;
	}

	public void deleteCustomerById(Long customerId) {
		customerRepository.delete(customerId);		
	}
}
