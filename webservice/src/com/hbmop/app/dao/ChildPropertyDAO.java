package com.hbmop.app.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;

import com.hbmop.app.model.ChildProperty;
import com.hbmop.app.model.CompleteTaskHistory;
import com.hbmop.app.model.Property;
import com.hbmop.app.model.User;
import com.hbmop.app.monitor.Task_to_do_list;
import com.hbmop.app.util.DBUtil;

public class ChildPropertyDAO extends DAOSupport<ChildProperty> {

	/**
	 * @authour 吴兵 根据物业点编号查询子物业点
	 * @Date:2014年11月21日
	 * @param property_code
	 * @return
	 */	
	public List<ChildProperty> findChildProperty(String involved_property_code){
		String hql = "from ChildProperty where involved_property_code = ?";
		Object[] values = {involved_property_code};
		List<ChildProperty> list = find(hql, values);
		return list;
	}

	/*
	 * @authour 吴兵 根据子物业点id查询子物业点
	 * @Date:2014年11月21日
	 * @param property_code
	 * @return
	 */
	
	public ChildProperty findChildPropertyById(int child_property_id){
		ChildProperty p = this.getById(child_property_id);
		return p;
	}
	
	
	
	public void saveChildProperty(ChildProperty property) {
		save(property);
	}
	
	
	
	public List<ChildProperty> findAllChildProperty(){
		String hql="from ChildProperty";
		return this.find(hql, null);
	}

	/**
	 * 根据子物业点编号查询子物业点
	 * @author sunxingyang
	 * @Date 2014年12月12日10:06:58
	 */
	
	public ChildProperty findChildPropertyInformation(String child_property_code) {
		String hql="from ChildProperty where child_property_code=?";
		Object[] values={child_property_code};
		List<ChildProperty> properties = find(hql, values);
		ChildProperty p = null;
		if(properties.size()>0){
			p = properties.get(0);
		}
		return p;
	}

	
	public void updateChildProperty(ChildProperty property) {
		this.update(property);
	}

	
	
	
	public String findMaxCodeByCity(String city,String involved_property_code){
		String hql="from ChildProperty where city='"+city+"' and involved_property_code ='"+involved_property_code+"'and ROWNUN=1   order by child_property_code desc ";
		Object[] values={};
		if(this.find(hql, values).size()==0){
			return "-1";
		}
		return this.find(hql, values).get(0).getChild_property_code();
	}


	/**
	 * @authour 吴兵 跟君子物业点编号查询子物业点
	 * @Date:2014年12月5日
	 * @param childPropertyCode
	 * @return
	 */
	
	public ChildProperty findByChildPropertyCode(String childPropertyCode){
		String hql = "from ChildProperty where child_property_code = ?";
		String[] values = {childPropertyCode};
		List<ChildProperty> list = find(hql, values);
		if(list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}

	
	public ChildProperty findChildPropertyByFlow_id(String flow_id) {
		// TODO Auto-generated method stub
		String HQL="from ChildProperty where flow_id =?";
		Object[] value={flow_id};
		List<ChildProperty> list = this.find(HQL, value);
		ChildProperty property = new ChildProperty();
		if(list.size()>0){
			property = list.get(0);
		}
		return property;
	}
	
	public void updateChildProperty_stato(String childProperty_code,String state) {
		// TODO Auto-generated method stub
		String hql="update from ChildProperty set state=? where child_property_code=?";
		Object[] values={state,childProperty_code};
		this.update(hql, values);
	}
	/**
	 * @author sunxingyang 根据物业点查询子物业点
	 */
	
	public List<ChildProperty> findByProperyCode(String propertyCode) {
		String hql = "from ChildProperty where involved_property_code = ?";
		String[] values = {propertyCode};
		List<ChildProperty> list = find(hql, values);
		return list;
	}

	

	
	public void deleteByChildPropertyCode(String child_property_code) {
		ChildProperty childProperty = findByChildPropertyCode(child_property_code);
		delete(childProperty);
		
	}

	/**
	 * 已办任务查询
	 */
	@SuppressWarnings("unchecked")
	
	public List<CompleteTaskHistory> findHavetodoChildProperty(User user,String todoName,int pageNow, 
			int pageSize,String keytype,String keyword){
		List<CompleteTaskHistory> list = new ArrayList<CompleteTaskHistory>();
		Session session = super.getSession();
		String str = "";
		if(keyword == null || "".equals(keyword)){
			
		}else if("propertyName".equals(keytype)){
			str = " and i.name like '%"+keyword+"%' ";
		}else if("initiator".equals(keytype)){
			str = " and i.processInitiator like '%"+keyword+"%' ";
		}else if("state".equals(keytype)){
			str = " and task.ACTIVITY_NAME_ like '%"+keyword+"%' ";
		}
		String sql ="SELECT i.name construction_name,i.flow_id executionId,i.city city, " + 
				" i.child_property_code construction_code,"
				+ "IF(task.ACTIVITY_NAME_='完成' || task.ACTIVITY_NAME_ IS NULL,0,IFNULL(ht.revokeFlag,0))  revokeFlag, " + 
				" IFNULL(task.ACTIVITY_NAME_,'流程已结束') name,i.processInitiator flowInitiator, " + 
				" hp.END_ completionTime,   " + 
				" IF(task.ACTIVITY_NAME_ IS NULL,'流程已结束','正在执行中') comment " + 
				" FROM (SELECT  EXECUTION_ FROM jbpm4_hist_task WHERE  " + 
				" EXECUTION_ LIKE '"+todoName+"%' AND ASSIGNEE_ = '"+user.getAccount_name()+"' GROUP BY EXECUTION_ " + 
				" ) flow LEFT JOIN child_property i ON flow.EXECUTION_ = i.flow_id  " + 
				" LEFT JOIN jbpm4_task task ON task.EXECUTION_ID_ = i.flow_id  "
				+ " LEFT JOIN jbpm4_hist_procinst hp ON hp.ID_ = i.flow_id " + 
				" LEFT JOIN (SELECT count(STATE_) revokeFlag, EXECUTION_ FROM jbpm4_hist_task WHERE  " + 
				" EXECUTION_ LIKE '"+todoName+"%'  GROUP BY EXECUTION_ " + 
				") ht ON ht.EXECUTION_ = i.flow_id  WHERE i.flow_id LIKE '"+todoName+"%' "+str
				+" ORDER BY i.id desc limit "+pageNow+","+pageSize;
	     list = (List<CompleteTaskHistory>)session.createSQLQuery(sql).addScalar("revokeFlag",Hibernate.INTEGER).addScalar("construction_name").addScalar("executionId")
	    		 .addScalar("flowInitiator").addScalar("name").addScalar("completionTime",Hibernate.STRING)
	    		 .addScalar("construction_code").addScalar("comment")
	    		 .setResultTransformer(Transformers.aliasToBean(CompleteTaskHistory.class)).list();
	     super.releaseSession(session);
	     return list;
	}
	/**
	 * 计算已办任务总条数
	 */
	
	public int countHavetodoChildPropert(String user,String flow,String keytype,String keyword){
		Session session = super.getSession();
		String str = "";
		if(keyword == null || "".equals(keyword)){
			
		}else if("propertyName".equals(keytype)){
			str = " and i.name like '%"+keyword+"%' ";
		}else if("initiator".equals(keytype)){
			str = " and i.processInitiator like '%"+keyword+"%' ";
		}else if("state".equals(keytype)){
			str = " and task.ACTIVITY_NAME_ like '%"+keyword+"%' ";
		}
		String sql = "select count(*) num from (SELECT count(*) FROM jbpm4_hist_task ht " + 
				" INNER JOIN child_property i ON i.flow_id = ht.EXECUTION_ "
				+ " left join jbpm4_task task on task.EXECUTION_ = i.flow_id "
				+" WHERE ht.ASSIGNEE_ = '"+user+"' AND ht.EXECUTION_ like '"+flow+"%' "
				+ str
				+ " GROUP BY ht.EXECUTION_) as t4 ";
		
		int num =  (int) session.createSQLQuery(sql).addScalar("num", Hibernate.INTEGER).uniqueResult();;
		super.releaseSession(session);
		return num;
	}
	
	/**
	 * 待办条数及分页
	 * @author sunxingyang
	 * @date 2015年1月8日18:00:16
	 */
	
	public int getTotalNum(User user,String todoName,String keytype,String keyword){
		String city = null;
		if(user.getRole_id() == 3 || user.getRole_id() == 4 
				|| user.getRole_id() == 6|| user.getRole_id() == 7|| user.getRole_id() == 8){
			city = user.getRule_region();
			city = DBUtil.dealStr(city);
		}
			
		else
			city = "'"+user.getCity()+"'";
		
		String sql = "select count(1) num from jbpm4_participation p  " + 
				" LEFT JOIN jbpm4_task ta on p.TASK_=ta.DBID_  " + 
				" LEFT JOIN child_property pro on pro.flow_id =ta.EXECUTION_ID_   " + 
				" where p.GROUPID_=" +user.getRole_id()
				+" and pro.city in ("+city+")  and ta.EXECUTION_ID_ like '"+todoName+"%' ";
				
				if(keytype != null && keytype.equals("proname")){
					sql += " and pro.name like '%" + keyword +"%'";
				}else if(keytype != null && keytype.equals("initiator")){
					sql += " and pro.processInitiator like '%" + keyword +"%'";
				}else if(keytype != null && keytype.equals("state")){
					sql += " and ta.NAME_ like '%" + keyword +"%'";
				}
		Session session = super.getSession();
		int num = (int) session.createSQLQuery(sql).addScalar("num",Hibernate.INTEGER).uniqueResult();
		super.releaseSession(session);
		return num;
	}
	
	
	/**
	 * 待办待办查询
	 */
	@SuppressWarnings("unchecked")
	
	public List<Task_to_do_list> findTodoPropertyByRole(User user,
			String todoName,String keytype,String keyword,int pageNow,int pageSize) {
		List<Task_to_do_list> list = new ArrayList<Task_to_do_list>();
		
		
		String city = null;
		if(user.getRole_id() == 3 || user.getRole_id() == 4 
				|| user.getRole_id() == 6|| user.getRole_id() == 7|| user.getRole_id() == 8){
			city = user.getRule_region();
			city = DBUtil.dealStr(city);
		}
			
		else
			city = "'"+user.getCity()+"'";
		
		String sql = "select ta.DBID_ task_id,pro.name name, " + 
				" pro.processInitiator task_initiator ,ta.CREATE_ time ,ta.NAME_ process_status, " + 
				" ta.DESCR_ applicant_reason from jbpm4_participation p  " + 
				" LEFT JOIN jbpm4_task ta on p.TASK_=ta.DBID_  " + 
				" LEFT JOIN child_property pro on pro.flow_id =ta.EXECUTION_ID_   " + 
				" where p.GROUPID_=" +user.getRole_id()
				+" and pro.city in ("+city+")  and ta.EXECUTION_ID_ like '"+todoName+"%' ";
				if(keytype != null && keytype.equals("proname")){
					sql += " and pro.name like '%" + keyword +"%'";
				}else if(keytype != null && keytype.equals("initiator")){
					sql += " and pro.processInitiator like '%" + keyword +"%'";
				}else if(keytype != null && keytype.equals("state")){
					sql += " and ta.NAME_ like '%" + keyword +"%'";
				}
				//待办排序 sunxingyang 2015年1月19日16:54:51
				sql += " ORDER BY time desc limit "+pageNow+","+pageSize;
		Session session = super.getSession();
		list = session.createSQLQuery(sql).addScalar("task_id",Hibernate.STRING).addScalar("name").addScalar("task_initiator")
		.addScalar("time",Hibernate.STRING).addScalar("process_status").addScalar("applicant_reason")
		.setResultTransformer(Transformers.aliasToBean(Task_to_do_list.class)).list();
		super.releaseSession(session);
		return list;
	}

	/**
	 * 物业点资料管理的查询总数
	 */
	public int findByKeytypeAndCovertypeCount(String area ,String keyword,String covertype){
		String cityhql = " select count(*) num from child_property o where o.state != 2 AND o.state IS NOT NULL";
		String cq = "";
		if(area != null && !area.equals("")){
			
			if(area.contains("-")){
				String[] reg =area.split("-");
				cq += " and (region='"+reg[1]+"')";
			}else if(area.contains(";")){
				
				cq += " and city in('"+area.replace(";", "','")+"')";
				
				
			}else{
				cq += " and (city='"+area+"')";
			}
		
	}
		if(covertype!=null && !"".equals(covertype)){
			if(!"覆盖类型".equals(covertype)){
				cq +=" and cover_type ='"+covertype+"'";
			}
		}
		cityhql += cq;
		if(keyword!=null&&!keyword.trim().equals("")){
			cityhql += " and (child_property_code like '%"+keyword+"%' or name like '%"+keyword+"%' or address like '%"+keyword+"%')";
		}
		Session session = super.getSession();
		int num =  (int) session.createSQLQuery(cityhql).addScalar("num", Hibernate.INTEGER).uniqueResult();;
		return num;
	}
	/**
	 * 物业点资料管理的查询分页
	 */
	public List<ChildProperty> findByKeytypeAndCovertypePage(String area ,String keyword,String covertype,int recordCount, int currentPage){
		int size = recordCount/10;//总条数/每页显示的条数=总页数 
        int mod = recordCount % 10;//最后一页的条数 
		String cityhql="from ChildProperty where state != 2 AND state IS NOT NULL";
		String cq = "";
		if(area != null && !area.equals("")){
			
			if(area.contains("-")){
				String[] reg =area.split("-");
				cq += " and (region='"+reg[1]+"')";
			}else if(area.contains(";")){
				
				cq += " and city in('"+area.replace(";", "','")+"')";
				
				
			}else{
				cq += " and (city='"+area+"')";
			}
		
	}
		if(covertype!=null && !"".equals(covertype)){
			if(!"覆盖类型".equals(covertype)){
				cq +=" and cover_type ='"+covertype+"'";
			}
		}
		cityhql += cq;
		if(keyword!=null&&!keyword.trim().equals("")){
			cityhql += " and(child_property_code like '%"+keyword+"%' or name like '%"+keyword+"%' or address like '%"+keyword+"%')";
		}
		Session session = getSession();  
		 int pageSize = 10;
	    List list = session.createQuery(cityhql)  
	           .setFirstResult(currentPage*pageSize-pageSize)  
	           .setMaxResults(pageSize)  
	           .list();  
	  
	    releaseSession(session);  
	  
	    return list;
	}

	
}
