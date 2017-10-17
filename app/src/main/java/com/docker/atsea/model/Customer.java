package com.docker.atsea.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;

// 这个类定义了Customer对象及其数据库格式、访问方法

/**
 *
 javax.persistence
 这个包的作用是持久化的作用，具体的说就是在实体类中进行元数据标签的作用，是ORM框架中用到的。
 ORM框架可以通过这个元数据标签，使得实体类与数据库中的表建立映射关系。
 例如javax.persistence.Column标识实体类中的这个属性对应于数据库中的一个字段等等。

 这个好像不是对于hibernate使用的，而是open jpa使用，open jpa也是一种orm框架和hibernate类似。
 */

/**
 * Hibernate是一个开放源代码的对象关系映射框架，它对JDBC进行了非常轻量级的对象封装，它将POJO与数据库表建立映射关系，
 * 是一个全自动的orm框架，hibernate可以自动生成SQL语句，自动执行，使得Java程序员可以随心所欲的使用对象编程思维来操纵数据库。
 * Hibernate可以应用在任何使用JDBC的场合，既可以在Java的客户端程序使用，也可以在Servlet/JSP的Web应用中使用，
 * 最具革命意义的是，Hibernate可以在应用EJB的J2EE架构中取代CMP，完成数据持久化的重任。
 */

@Entity
@Table(name = "customer")
public class Customer implements Serializable {
	
	private static final long serialVersionUID = -8697455919895226841L;

	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long customerId;
	
	@NotEmpty
    @Column(name = "name", length = 255, nullable = false)
    private String name;
	
	@NotEmpty
	@Column(name = "address", length = 512, nullable = false)
	
	private String address;
	@NotEmpty
	@Column(name = "email", length = 128, nullable = false)
    private String email;
	
	@NotEmpty
	@Column(name = "phone", length = 32, nullable = false)
    private String phone;
	
	@NotEmpty
	@Column(name = "username", length = 255, nullable = false)
    private String username;
	
	@NotEmpty
	@Column(name = "password", length = 255, nullable = false)
    private String password;
	
	@Column(name = "enabled", nullable = false)
	@Type( type = "org.hibernate.type.NumericBooleanType")
	private boolean enabled;
	
	@NotEmpty
	@Column(name = "role", columnDefinition = "varchar(5) DEFAULT 'USER'")
	private String role;
	
	public Customer() {
		
	}
	
	public Customer(Long customerId, String name, String address, String email, String phone,
			String username, String password, Boolean enabled, String role) {
		this.customerId = customerId;
		this.name = name;
		this.address = address;
		this.email = email;
		this.phone = phone;
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.role = role;
	}
	
	public Long getCustomerId() {
    	return customerId;
    }
    
    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
    
    public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
    }
 
    public String getEmail() {
        return email;
    }
 
    public void setEmail(String email) {
        this.email = email;
    }
 
    public String getAddress() {
        return address;
    }
 
    public void setAddress(String address) {
        this.address = address;
    }
 
    public String getPhone() {
        return phone;
    }
 
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public String getUsername() {
        return username;
    }
 
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPassword() {
        return password;
    }
 
    public void setPassword(String password) {
        this.password = password;
    }
       
    public Boolean getEnabled() {
    	return enabled;
    }
    
    public void setEnabled(Boolean enabled) {
    	this.enabled = enabled;
    }
    
    public String getRole() {
    	return role;
    }
    
    public void setRole(String role) {
    	this.role = role;
    }
    
	@Override
	public String toString() {
		return "Customer [customerId=" + customerId 
				          + ", name=" + name 
				          + ", username=" + username
				          + ", address=" + address 
				          + ", email=" + email 
				          + ", phone=" + phone 
				          + ", password=" + password + 
				          "]";
	}
    
}
