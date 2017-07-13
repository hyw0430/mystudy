package com.atguigu.hibernate.many2many;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class HibernateTest {
	
	private SessionFactory sessionFactory;
	private Session session;
	private Transaction transaction;
	
	@Before
	public void init(){
		Configuration configuration = new Configuration().configure();
		ServiceRegistry serviceRegistry = 
				new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
		sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
	}
	
	@After
	public void destroy(){
		transaction.commit();
		session.close();
		sessionFactory.close();
	}
	
	/**
	 * 1. 多对多的关联关系必须使用中间表!
	 * 2. 使用 set 来映射多对多的关联关系.
	 * <set name="属性名" table="中间表的表名">
	 * 	<key column="当前数据表主键在中间表外键列的列名"/>
	 * 	<many-to-many column="关联的类所对应的数据表的主键在中间表外键列的列名" class="关联类的全类名" />
	 * </set>
	 * 
	 * 所以两边都需要使用 set 进行映射, 那么
	 * 1). table 必须一致. 只能有一张中间表
	 * 2). 外键列必须交叉相同. 
	 * 
	 * 3. 生成的中间表使用两个外键作为联合主键. 所以必须有一端放弃维护关联关系. 即必须一端的 set 的 inverse 设置为 true.
	 * 
	 */
	@Test
	public void testManyToManySave(){
		Item item1 = new Item();
		item1.setItemName("AA");
		
		Item item2 = new Item();
		item2.setItemName("BB");
		
		Category category1 = new Category();
		category1.setCategoryName("AAA");
		
		Category category2 = new Category();
		category2.setCategoryName("BBB");
		
		//设定关联关系
		item1.getCategories().add(category1);
		item1.getCategories().add(category2);
		
		item2.getCategories().add(category1);
		item2.getCategories().add(category2);
		
		category1.getItems().add(item1);
		category1.getItems().add(item2);
		
		category2.getItems().add(item1);
		category2.getItems().add(item2);
		
		//保存:
		session.save(category1);
		session.save(category2);
		
		session.save(item1);
		session.save(item2);
	}
	
}
