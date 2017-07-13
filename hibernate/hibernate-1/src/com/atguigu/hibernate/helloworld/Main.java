package com.atguigu.hibernate.helloworld;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class Main {
	
	public static void main(String[] args) {
		//1. 创建 SessionFactory 对象.
		//SessionFactory: 创建 Session 的工厂
		Configuration configuration = new Configuration().configure();
		ServiceRegistry serviceRegistry = 
				new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
		SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		
		//2. 从 SessionFactory 中获取 Session 对象
		//Session: hibernate 应用程序和数据库的一次会话. 即一个 Connection.
		Session session = sessionFactory.openSession();
		
		//3. 开启事务
		Transaction tx = session.beginTransaction();
		
		//4. 执行保存
		News news = new News();
		news.setAuthor("Jerry");
		news.setTitle("SUN");
		session.save(news);
		
		//5. 提交事务
		tx.commit();
		
		//6. 关闭 Session
		session.close();
		
		//7. 关闭 SessionFactory
		sessionFactory.close();
	}
	
}
