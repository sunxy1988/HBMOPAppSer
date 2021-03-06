package com.hbmop.app.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.persistence.Entity;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;



@SuppressWarnings("unchecked")
@Transactional
public abstract class DAOSupport<T> extends HibernateDaoSupport implements DAO<T> {
	@Resource(name="sessionFactory")
	public void setBaseDaoSessionFactory(SessionFactory sessionFactory) {
		System.out.println("setBaseDaoSessionFactory sessionFactory");
		super.setSessionFactory(sessionFactory);
	}
	/* 父类中泛型的实际类型 即DAOSupport<T>中的T的实际类型,包含了包名 */
	protected Class<T> entityClass = getSuperClassGenricType();
	/*
	 * T代表的类名，不含包名
	 */
	protected String entityName = getEntityName(entityClass);

	/**
	 * 保存实体
	 * @param entity需要保存的实体
	 */
	public Integer save(T entity) {
//		getHibernateTemplate().persist(entity);
		getHibernateTemplate().save(entity);
		return 0;
	}

/*	public void batchSave(List<T> lists) {
		Session session=null;
		Transaction tc=null;
		try{
			
			session=getSessionFactory().openSession();  
			tc=session.beginTransaction();
			for(T t:lists){
				session.save(t);
			}
			tc.commit();
		}
		catch(Exception e){
			e.printStackTrace();
			if(tc!=null)
				tc.rollback();
		}
		finally{
			session.close();
		}

	}*/
	
	/**
	 * 重写之前的batchSave方法
	 * @author hukai
	 */
	public void batchSave(List<T> lists) {
		Session session = this.getSessionFactory().getCurrentSession();
		int cicleCount = 0;
		for(T t : lists){
			session.save(t);
			cicleCount ++;
			if(cicleCount % 20 == 0){
				session.flush();
				session.clear();
			}
		}
	}

	public void saveOrUpdate(T entity) {
		getHibernateTemplate().saveOrUpdate(entity);
	}

	/**
	 * 根据主键删除实体
	 * @param id主键
	 */
	public void delete(Serializable id) {
		
		T entity = loadById(id);
		getHibernateTemplate().delete(entity);
	}
	
	/**
	 * 根据主键批量删除实体
	 * @param ids主键
	 */
	public void delete(Serializable...ids){
		for(Serializable id:ids){
			delete(id);
		}
	}
	
	/**
	 * 删除实体
	 * @param entity实体
	 */
	public void delete(T entity){
		getHibernateTemplate().delete(entity);
	}
		
	/**
	 * 更新实体
	 * @param entity需要更新的实体
	 */
	public void update(T entity) {
		getHibernateTemplate().update(entity);
	}
	
	
	
	/**
	 * 根据hql语句更新
	 */
	public void update(String hql, Object[] values){
		getHibernateTemplate().bulkUpdate(hql, values);
	}
	/**
	 * 根据主键查找实体
	 * @param id主键
	 * @return返回查找到的实体
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public T loadById(Serializable id) {
		T entity = (T) getHibernateTemplate().get(entityClass, id);
		return entity;
	}
	
	public T getById(Serializable id) {
		T entity = (T) getHibernateTemplate().get(entityClass, id);
		return entity;
	}
	/**
	 * 根据hql语句查询实体
	 * @param hql
	 * @param values
	 * @return
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public List<T> find(String hql, Object[] values){
		List<T> entities = getHibernateTemplate().find(hql, values);
		return entities;
	}
	public List<T> findByPropertyList(String[] paramNames,String[] paramValues){
		String hql="from "+entityName+" o where";
		for(int i=0;i<paramNames.length-1;i++){
			hql+="  o."+paramNames[i]+"=? and";
		}
		hql+=" o."+paramNames[paramNames.length-1]+"=?";
		return getHibernateTemplate().find(hql, paramValues);
	}
	/**
	 * 根据条件查询实体
	 * @param propertyName属性名称
	 * @param value属性值
	 * @return
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public List<T> findByProperty(String propertyName, Object value){
		String hql="from "+entityName+" o where o."+propertyName+" =:propertyName";
		Session session=super.getSession();
		List<T> entities=session.createQuery(hql).setParameter("propertyName", value).list();
		super.releaseSession(session);
		
		return entities;
	}
 
	
	
	/**
	 * 查找到所有实体对象
	 * @return返回查找到的所有实体对象
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public List<T> findAll() {
		return getHibernateTemplate().find("from " + entityName + " ");
	}
	/**
	 * 查找到所有实体对象
	 * @return返回查找到的所有实体对象
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public List<T> findAll(int start, int limit) {
		
		return getHibernateTemplate().find("from " + entityName + " ").subList(start, start + limit);
		
	}

	/**
	 * 获得实体的在数据库中的记录总数
	 * @return记录总数
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public int getCount(){
		String hql="select count(*) from "+entityName;
		Session session=super.getSession();
		Integer count=new Integer(((Long)session.createQuery(hql).uniqueResult()).intValue());
		super.releaseSession(session);
		
		return count;
	}
	
	
	
	/****************************************分析*******************************************/


	

	
	
	/**
	 * 分页
	 * @param firstResult
	 * @param maxResult
	 * @param orderby
	 * @return
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public List<T> getRecords(int firstResult, int maxResult,
			LinkedHashMap<String, String> orderby) {
		return getRecords(firstResult, maxResult, null, null, orderby);
	}

	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public List<T> getRecords(int firstResult, int maxResult,
			String where, Object[] params) {
		return getRecords(firstResult, maxResult, where, params, null);
	}

	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public List<T> getRecords(int firstResult, int maxResult) {
		return getRecords(firstResult, maxResult, null, null, null);
	}

	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public List<T> getRecords() {
		return getRecords(-1, -1, null, null, null);
	}

	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public List<T> getRecords(int firstResult, int maxResult,
			String where, Object[] params, LinkedHashMap<String, String> orderby) {

		// where 语句，省略where 形式为：o.username=?1 and o.password=?2
		String wheresql = where != null && !"".equals(where.trim()) ? " where "
				+ where : "";
		// 创建查询对象
		Session session=super.getSession();
		Query query = session.createQuery(
				"select o from " + entityName + " o " + wheresql
						+ buildOrderBy(orderby));
		// 强制约定如果firstResust == -1,或者maxResult == -1 则返回所有的记录集
		if (firstResult != -1 && maxResult != -1)
			query.setFirstResult(firstResult).setMaxResults(maxResult);
		// 设置where语句中占位符?中的参数
		setQueryParameter(query, params);
		List<T> qr = new ArrayList<T>();
		// 设置QueryResult中的结果集
		qr=query.list();
		super.releaseSession(session);
		return qr;
	}

	/**
	 * 构建orderBy 语句
	 * 
	 * @param orderBy
	 *            排序属性为asc/desc, Key为属性(name,email),Value为asc/desc
	 *            构建完毕后的形式：order by o.name desc , o.email asc
	 * @return 返回构建完成后的orderby语句 形式为 order by o.name desc , o.email asc
	 */
	private static String buildOrderBy(LinkedHashMap<String, String> orderBy) {
		StringBuilder sb = new StringBuilder();
		if (orderBy != null && !orderBy.isEmpty()) {
			sb.append(" order by ");
			for (Map.Entry<String, String> entry : orderBy.entrySet()) {
				sb.append("o.").append(entry.getKey()).append(" ").append(
						entry.getValue()).append(',');
			}
			sb.deleteCharAt(sb.length() - 1);
		}
		return sb.toString();
	}

	/**
	 * 设置where 语句中占位符?的参数 占位符？后面的序号从1开始。形式如：o.username=?1 and o.password=?2
	 * 
	 * @param query
	 * @param params
	 *            where 语句中占位符?的参数数组
	 */
	protected static void setQueryParameter(Query query, Object[] params) {
		if (params != null) {
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i, params[i]);
			}
		}
	}

	/**
	 * @return 返回泛型T的实际类型
	 */
	protected Class<T> getSuperClassGenricType() {
		Type type = this.getClass().getGenericSuperclass();// 返回当前实体的泛型父类
		Type[] params = ((ParameterizedType) type).getActualTypeArguments();
		return (Class<T>) params[0];
	}

	/**
	 * 获得实体在数据库中对应的表名。 如果在annotation中改变了表名，则返回annotation中的表名， 否则返回类的简单名，即默认的表名
	 */
	private static <E> String getEntityName(Class<E> entityClass) {
		String entityName = entityClass.getSimpleName();
		Entity entity = entityClass.getAnnotation(Entity.class);
		if (entity.name() != null && !"".equals(""))
			entityName = entity.name();
		return entityName;
	}
	public ResultSet execute(final String hql){
		//this.getSession();
//		T entity = (T)
		return (ResultSet) getHibernateTemplate().execute(new HibernateCallback() {			
			@SuppressWarnings("deprecation")
			@Override
			public ResultSet doInHibernate(Session session) throws HibernateException,SQLException {
				Statement st=session.connection().createStatement();
				ResultSet rs=st.executeQuery(hql);
				return rs; 
			}
		});
	}

}
