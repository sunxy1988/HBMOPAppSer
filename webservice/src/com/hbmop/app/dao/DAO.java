package com.hbmop.app.dao;


import java.io.Serializable;
import java.sql.ResultSet;
import java.util.LinkedHashMap;
import java.util.List;
public interface DAO<T> {
	/**
	 * 保存
	 * @param t 实体	 */
	public Integer save(T entity);
	
	public void batchSave(List<T> lists);
	
	public void saveOrUpdate(T entity);
	/**
	 * 更新
	 * @param t 实体	 */
	public void update(T entity);
	/**
	 * 更新
	 * @param 条件
	 * @param 条件值
	 */
	public void update(String hql, Object[] values);
	/**
	 * 删除
	 * 
	 * @param id 实体的id
	 */
	
	public void delete(Serializable id);
	public void delete(T entity);
	public void delete(Serializable...ids);
	/**
	 * 加载
	 * 
	 * @param id 实体id值
	 * @return 
	 */
	public T loadById(Serializable id);
	//public List<T> findByModuleId(String hql, Serializable);
	public T getById(Serializable id);
	public List<T> findAll() ;
	public int getCount();
	public List<T> findAll(int start, int limit);
	public List<T> find(String hql, Object[] values);
	public List<T> findByPropertyList(String[] paramNames,String[] paramValues);
	

	/******************************** *****************************/
	
	
	
	

	
	
	/******************************** *****************************/
	
	
	
	/**
	 * 获取list里面的记录
	 * 
	 * @param firstResult 第一个整型结果值
	 * @param maxResult   最大的结果值
	 * @param where       条件
	 * @param params     参数
	 * @param orderby     根据里面的KEY值进行升序或降序排序
						    LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
							orderby.put("email", "asc");
							orderby.put("password", "desc");
							
	 * @return            返回的结果集 QueryResult<T>
	 */
	
	
	public List<T> getRecords(int firstResult, int maxResult, String where, Object[] params, LinkedHashMap<String, String> orderby);
	
	/**
	 * 获取list里面的记录
	 * @param firstResult
	 * @param maxResult
	 * @param orderby
	 * @return
	 */
	public List<T> getRecords(int firstResult, int maxResult,  LinkedHashMap<String, String> orderby);

	/**
	 * 获取list里面的记录
	 * @param firstResult
	 * @param maxResult
	 * @param where
	 * @param params
	 * @return
	 */
	public List<T> getRecords(int firstResult, int maxResult, String where, Object[] params);
	
	/**
	 *获取list里面的记录
	 * @param firstResult
	 * @param maxResult
	 * @return
	 */
	public List<T> getRecords(int firstResult, int maxResult);

	/**
	 * 获取list里面的记录
	 * @return
	 */
	public List<T> getRecords();

	@SuppressWarnings("unchecked")
	ResultSet execute(String hql);
	
}
