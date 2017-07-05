package com.atguigu.spring.tx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("bookShopService")
public class BookShopServiceImpl implements BookShopService {

	@Autowired
	private BookShopDao bookShopDao;
	
	/**
	 * propagation: 可以指定事务的传播行为. 即其他事务方法调用当前事务方法时, 
	 * 当前方法如何来使用事务 .
	 * REQUIRED(默认值): 有事务就可以. 若调用当前方法的方法, 有事务, 则使用那个方法的事务. 若没有事务, 则开启新的事务.
	 * REQUIRES_NEW: 开启一个新的事务. 即使调用当前方法的方法是事务方法, 当前方法也会开启新的事务. 
	 * 
	 * isolation: 指定事务的隔离级别. 取值 READ_COMMITTED 即可. 
	 * 
	 * rollbackFor
	 * rollbackForClassName
	 * noRollbackFor
	 * noRollbackForClassName
	 * 指定事务的回滚属性. 即对哪些异常回滚, 对哪些异常不回滚. 默认只对 RuntimeException 进行回滚. 通常不需要进行设置.
	 * 
	 * timeout: 连接被占用多长时间进行强制回滚. 
	 * 
	 * readOnly: 若事务没有修改操作, 则可以把该属性设置为 true, 可以改善性能. 
	 */
	@Transactional(propagation=Propagation.REQUIRES_NEW,
			isolation=Isolation.READ_COMMITTED,
//			noRollbackFor={AccountException.class}
			timeout=3,
			readOnly=false
			)
	@Override
	public void purchase(String isbn, String username) {
		int price = bookShopDao.findPriceByIsbn(isbn);
		
		//更新数的库存
		bookShopDao.updateBookStock(isbn);
		
		try {
			Thread.sleep(7000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		//更新余额
		bookShopDao.updateAccountBalance(username, price);
	}

}
