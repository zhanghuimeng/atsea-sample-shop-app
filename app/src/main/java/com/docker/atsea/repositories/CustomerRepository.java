package com.docker.atsea.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.docker.atsea.model.Customer;

/**
 * 首先JPA的全称叫做Java Persistence API，JPA是一个基于O/R映射的标准规范，在这个规范中，JPA只定义标准规则，不提供实现，
 * 使用者则需要按照规范中定义的方式来使用。目前JPA的主要实现有Hibernate、EclipseLink、OpenJPA等，事实上，
 * 由于Hibernate在数据访问解决技术领域的绝对霸主地位，JPA的标准基本是由Hibernate来主导的。
 */

/**
 * 我们的数据访问接口需要继承JpaRepository类
 */

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
	
	Customer findOne(Long customerId);
	
	Customer findByName(String name);
	
	// adding find by username
    // 添加@Query注解，当我调用这个方法的时候使用这个注解中的sql语句进行查询，方法的参数则是注解中的占位符的值。
    // 我们可以在接口中定义查询方法，可以按照属性名来查询，但是方法的命名方式是固定的，比如第一个方法和第二个方法，第一个方法表示根据一个属性查询，
    // 第二个方法表示根据多个属性查询，findBy、And等可以算作是这里的查询关键字了，如果写作其他名称则系统不能识别，类似的关键字还有Like、Or、Is、
    // Equals、Between等，而这里的findBy关键字又可以被find、read、readBy、query、queryBy、get、getBy等来代替。
	@Query("SELECT c FROM Customer c WHERE c.username = :userName")
	Customer findByUserName(@Param("userName") String userName);
}

