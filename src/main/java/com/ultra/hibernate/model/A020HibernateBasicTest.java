package com.ultra.hibernate.model;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.model.naming.ImplicitNamingStrategyJpaCompliantImpl;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.criterion.Expression;
import org.hibernate.jdbc.Work;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class A020HibernateBasicTest extends A010HibernateTest{

	/**
	 * TODO
	 * 对象的状态:
	 * 			瞬时态:未与session实例关联.在数据库中没有与之关联的记录.
	 * 			脱管态:未与session实例关联,在数据库中有与之关联的记录.
	 * 			持久态:与session实例关联,在数据库中有与之关联的记录.
	 * 		处于持久态或者脱管态的对象主键属性不能被修改.
	 */
	
	/**
	 * TODO
	 * 缓存相关
	 */
	
	/**
	 * @Description: 清除session缓存
	 *
	 * @date 2017年4月28日,下午3:01:34
	 * @author fanbaoshen
	 * @version 5.0
	 *
	 */
	@Test
	public void testClear(){
		News new1 = session.get(News.class, 1);
		System.out.println(new1);
		session.clear();
		News new2 = session.get(News.class, 1);
		System.out.println(new2);
	}
	
	/**
	 * @Description: 刷新session缓存中的对象(即从数据库查询最新数据并更新到session缓存)
	 *
	 * @date 2017年4月28日,下午2:55:24
	 * @author fanbaoshen
	 * @version 5.0
	 *
	 */
	@Test
	public void testRefresh(){
		News news = session.get(News.class, 1);
		session.refresh(news);
	}
	/**
	 * 
	 * @Description: flush: 使数据表中的记录和 Session 缓存中的对象的状态保持一致.flush() 方法会可能会发送 SQL
	 *               语句, 但不会提交事务. 
	 *               flush()方法执行时间.
	 *               1. 显示的调用,session.flush();
	 *               2. 在 Transaction 的 commit() 方法中自动调用: 先调用 session 的flush方法,再提交事务. 
	 *               3. 注意:在未提交事务或显式的调用 session.flush() 方法之前,
	 *               1).执行 HQL 或 QBC 查询, 会先进行 flush() 操作,以得到数据表的最新的记录 
	 *               2). 若记录的 ID是由底层数据库使用自增的方式生成的, 则在调用 save() 方法时,
	 *               就会立即发送 INSERT 语句. 因为 save 方法后, 必须保证对象的 ID 是存在的!
	 *
	 * @date 2017年4月28日,下午2:07:04
	 * @author fanbaoshen
	 * @version 5.0
	 *
	 */
	@Test
	public void testFlush() {
		News news = session.get(News.class, 1);
		System.out.println(news);
		news.setTitle("Test");
		// 执行HQL或者QBC查询时会执行flush()方法.HQL、QBC保证查询的数据是最新的
		Criteria criteria = session.createCriteria(News.class).add(Expression.eq("id", 1));
		News news2 = (News) criteria.uniqueResult();
		System.out.println(news2);
	}

	/**
	 * @Description: 同一个session连接,多次查询同一个对象时,只会发送一条sql.原理:
	 *               session查询时会先从session缓存中查找,如果没有,则从数据库查询(即执行sql),
	 *               并加入到session缓存中,如果存在,则直接从session缓存中获取.
	 *
	 * @date 2017年4月28日,上午10:46:27
	 * @author fanbaoshen
	 * @version 5.0
	 *
	 */
	@Test
	public void testSessionCache() {
		News news = session.get(News.class, 1);
		System.out.println(news);
		News news2 = session.get(News.class, 1);
		System.out.println(news2);
	}

	/**
	 * TODO
	 * 配置文件(.hbm.xml)
	 */
	
	/**
	 * @Description: 测试主键生成策略
	 *
	 * @date 2017年5月2日,上午10:57:04
	 * @author fanbaoshen
	 * @version 5.0
	 *
	 * @throws InterruptedException
	 */
	@Test
	public void testIdGenerator() throws InterruptedException{
		News news = new News("AA", "aa", new Date(), null);
		session.save(news);
//		Thread.sleep(5000);
	}
	
	/**
	 * @Description: 在映射文件中配置class标签的dynamic-update属性为true(只修改变化的属性)
	 *
	 * @date 2017年5月2日,上午10:38:01
	 * @author fanbaoshen
	 * @version 5.0
	 *
	 */
	@Test
	public void testDynamicUpdate(){
		News news = session.get(News.class, 2);
		news.setDate(new Date());
	}
	
	/**
	 * @Description: 在映射文件中配置class标签的dynamic-insert属性为true(只插入不为空的属性)
	 *
	 * @date 2017年5月2日,上午10:35:57
	 * @author fanbaoshen
	 * @version 5.0
	 *
	 */
	@Test
	public void testDynamicInsert(){
		News news = new News();
		news.setTitle("AA");
		news.setAuthor("aa");
		session.save(news);
	}
	
	/**
	 * TODO
	 * 获取jdbc原生connection
	 */
	/**
	 * @Description: 获取jdbc原生连接
	 *
	 * @date 2017年5月2日,上午9:50:49
	 * @author fanbaoshen
	 * @version 5.0
	 *
	 */
	@Test
	public void testWork(){
		
		session.doWork(new Work() {
			
			@Override
			public void execute(Connection connection) throws SQLException {
				//获取Connection连接
				System.out.println(connection);
				//调用存储过程...
				//关闭连接(测试使用,使用数据库连接池不用手动关闭连接)
				connection.close();
			}
		});
	}
	
	/**
	 * TODO
	 * session的增删改查基本操作
	 */
	
	/**
	 * @Description: 从数据库中删除对象(删除对象后为提交事务时, 其 OID仍然存在,不能进行update等操作.
	 * 				  在Hibernate.cfg.xml中配置hibernate.use_identifier_rollback为true
	 * 				  可以在删除对象未提交事务前,置其OID为null)
	 * 				  若 OID 在数据表中没有对应的记录, 则抛出异常
	 *
	 * @date 2017年5月2日,上午9:39:20
	 * @author fanbaoshen
	 * @version 5.0
	 *
	 */
	@Test
	public void testDelete(){
		News news = session.get(News.class, 14);
		session.delete(news);
		System.out.println(news);
	}
	
	/**
	 * @Description: 从session中删除对象(删除后,修改不会被提交)
	 *
	 * @date 2017年5月2日,上午9:22:46
	 * @author fanbaoshen
	 * @version 5.0
	 *
	 */
	@Test
	public void testEvict(){
		News news1 = session.get(News.class, 13);
		News news2 = session.get(News.class, 14);
		
		news1.setAuthor("t1");
		news2.setAuthor("t2");
		
		session.evict(news1);
	}
	
	/**
	 * @Description: saveOrUpdate:添加或者更新
	 *				1). 主键属性不存在时,执行save操作.
	 *				2). 主键属性存在时,执行update操作.
	 *				3). 若主键属性存在,但是数据库没有对应的记录,则会抛异常OptimisticLockException
	 *
	 * @date 2017年4月28日,下午5:27:10
	 * @author fanbaoshen
	 * @version 5.0
	 *
	 */
	@Test
	public void testSaveOrUpdate(){
		News news = new News("AA", "aa", new Date(), null);
		news.setId(1);
		session.saveOrUpdate(news);
	}
	/**
	 * @Description: 1). 处于持久态的对象属性变化时,会执行session的flush()方法,会隐式更新数据库.
	 * 				 2). 处于持久态的对象属性未变化,而显示的调用session的update方法,也不会执行sql.
	 * 				 3). 处于脱管态的对象,即使对象未改变,也会执行update操作,并会把该对象转化为持久态.
	 * 				 	  因为session中没有该对象的缓存.在对象对应的.hbm.xml中class标签中设置
	 * 					 select-before-update="true"时,更新之前先查询,未变化,不更新.如果变化,先执行select
	 * 					  再执行update,会执行两条sql,所以一般 不设置该属性,默认false.
	 *				 4). update()处于瞬时态的对象时,会抛异常. 
	 *				 5). update()处于脱管态的对象,如果有相同主键属性的对象处于持久态,会抛异常.
	 *					  因为session缓存中不能有两个相同的 OID 的对象
	 *
	 * @date 2017年4月28日,下午5:37:03
	 * @author fanbaoshen
	 * @version 5.0
	 *
	 */
	@Test
	public void testUpdate(){
		News news = session.get(News.class, 13);
//		news.setAuthor("Test");
		transaction.commit();
		session.close();
		//处于持久态或者脱管态的对象主键属性不能被修改.
//		news.setId(1);
		
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		News news2 = session.get(News.class, 13);
		session.update(news2);
	}
	
	/**
	 * @Description: save和persist的区别
	 * 				1). 添加对象时如果主键属性有值,save会忽略该属性,persist会抛异常
	 * 				2). save、persist后修改对象的主键属性会抛异常org.hibernate.HibernateException
	 *
	 * @date 2017年4月28日,下午4:10:13
	 * @author fanbaoshen
	 * @version 5.0
	 *
	 */
	@Test
	public void testPersist(){
		News news = new News();
		news.setTitle("AA");
		news.setAuthor("aa");
		news.setDate(new Date());
		
		System.out.println(news);
		session.persist(news);
		System.out.println(news);
		
//		news.setId(1003);
	}
	@Test
	public void testSave() {
		News news = new News();
		news.setTitle("BB");
		news.setAuthor("bb");
		news.setDate(new Date());
//		news.setId(1001);
		System.out.println(news);
		session.save(news);
		System.out.println(news);
		
//		news.setId(1002);
	}

	/**
	 * @Description: Load和get区别
	 * 				1). load返回的一个代理对象,get返回真正的对象.
	 * 				2). load只有在使用对象属性时才会真正加载对象,get会立即加载对象.
	 * 				3). 在使用对象属性时,load会先从session中获取,如果不存在,在从数据库获取,
	 * 					当使用对象属性时,如果session已经关闭了,会抛异常LazyInitializationException.
	 * 					get获取的对象和session的状态没有关系.
	 * 				4). 当获取的对象不存在时:get会返回null,load会抛出异常ObjectNotFoundException
	 *
	 * @date 2017年4月28日,下午3:42:25
	 * @author fanbaoshen
	 * @version 5.0
	 *
	 */
	@Test
	public void testLoad(){
		News news = session.load(News.class, 1);
		System.out.println(news.getClass());//com.ultra.hibernate.model.News_$$_jvst50d_0
//		session.close();
		System.out.println(news);
//		查询不存在
//		News notExistNews = session.load(News.class, -1);
//		System.out.println(notExistNews);
	}
	
	@Test
	public void testGet() {
		News news = session.get(News.class, 1);
		System.out.println(news.getClass());//com.ultra.hibernate.model.News
//		session.close();
		System.out.println(news);
//		查询不存在
//		News notExistNews = session.get(News.class, -1);
//		System.out.println(notExistNews);
	}

}
