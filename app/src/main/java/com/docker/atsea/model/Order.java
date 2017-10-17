package com.docker.atsea.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

// 实现Order的类
// @Entity标记这是一个实体，需要存在数据库里面
// @Table说明了表名
// @JsonInclude说明了与Java类序列化相关的设置
// @JsonInclude(value=Include.NON_NULL)这个注解的意思是说，在序列化的过程中，如果对象的某个属性的值为空null，
// 则这个属性就不会出现在json中；可能是前端这么要求，也可能是减少数据传输流量的要求；

// @Entity，说明被这个注解修饰的类应该与一张数据库表相对应，表的名称可以由类名推断，当然了，也可以明确配置，
// 只要加上@Table(name = "books")即可。需要特别注意，每个Entity类都应该有一个protected访问级别的无参构造函数，
// 用于给Hibernate提供初始化的入口。

@Entity
@Table(name = "orders")
@JsonInclude(Include.NON_NULL)
public class Order implements Serializable {
	
	private static final long serialVersionUID = 8367647197454666804L;

    /**
     * 自定义主键生成策略，由@GenericGenerator实现。
     hibernate在JPA的基础上进行了扩展，可以用一下方式引入hibernate独有的主键生成策略，就是通过@GenericGenerator加入的。
     http://blog.csdn.net/chenlong220192/article/details/46678461

     @Id and @GeneratedValue：@Id注解修饰的属性应该作为表中的主键处理、
     @GeneratedValue修饰的属性应该由数据库自动生成，而不需要明确指定。
     http://www.jianshu.com/p/1b626a6f550e

     identity：使用SQL Server 和 MySQL 的自增字段，这个方法不能放到 Oracle 中，Oracle 不支持自增字段，
     要设定sequence（MySQL 和 SQL Server 中很常用），等同于JPA中的INDENTITY；
     */
    // 话说……为什么这个不用加column？
	@Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long orderId;

	// 存放创建时的时间戳
    // Temporal：指定存放时间内容的精确度，日期、时间或日期+时间
    // https://stackoverflow.com/questions/25333711/what-is-the-use-of-the-temporal-annotation-in-hibernate
    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    @Column(name = "orderdate" )
    private Date orderDate;
        
    @Column(name = "customerid")
    private Long customerId;
    
    @ElementCollection
    @MapKeyColumn(name="productid")
    @Column(name = "productsordered")
    @CollectionTable(name="orderquantities", joinColumns=@JoinColumn(name="orderid"))
    Map<Integer, Integer> productsOrdered = new HashMap<Integer, Integer>();

    
    public Order(){
		
	}
	
	public Order(Long orderId, Date orderDate, Long productId, Map<Integer, Integer> productsOrdered) {
    	this.orderId = orderId;
    	this.orderDate = orderDate;
    	this.productsOrdered = productsOrdered;
	}

    public Order(Long orderId, Date orderDate, Long productId, Map<Integer, Integer> productsOrdered, Long customerId) { 
    	this.orderId = orderId;
    	this.orderDate = orderDate;
    	this.productsOrdered = productsOrdered;
    	this.customerId = customerId;
    };

	public Long getCustomerId() {
		return customerId;
	}
	
    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
    
    public long getOrderId() {
    	return orderId;
    }
    
    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }
    
    public Date getOrderDate() {
    	return orderDate;
    }
    
    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }
        
    public Map<Integer, Integer> getProductsOrdered() {
    	return productsOrdered;
    }
    
    public void setProductsOrdered(Map<Integer, Integer> productsOrdered) {
    	this.productsOrdered = productsOrdered;
    }
    	
	@Override
	public String toString() {
		return "Order [customerId = " + customerId + 
				      "orderDate= " + orderDate + 
				      "orderId = "+ orderId + 
				      "productsOrdered = " + productsOrdered +
				      "]";
	}
}
