package com.hbmop.app.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;

import com.demo.T;
import com.hbmop.app.model.CompleteTaskHistory;
import com.hbmop.app.model.IndoorSite;
import com.hbmop.app.model.User;
import com.hbmop.app.monitor.Task_to_do_list;
import com.hbmop.app.util.DBUtil;
import com.hbmop.app.vo.toDataManagement;

public class IndoorSiteDAO extends DAOSupport<IndoorSite> {

	
	public List<IndoorSite> findIndoorSite() {
		// TODO Auto-generated method stub
		String hql="from IndoorSite";
		Object[] values={};
		return this.find(hql, values);
	}

	
	public IndoorSite findIndoorSite(String id) {
		// TODO Auto-generated method stub

		String hql="from IndoorSite i where i.flow_id2 = '"+id+"'";

		//Object[] values={id};
		List<IndoorSite> list = new ArrayList<>();
		
		list = this.getHibernateTemplate().find(hql);
		IndoorSite site = null;
		if(list.size()>0){
			site = list.get(0);
		}
		return site;
	}
	
	public IndoorSite findIndoorSiteById(String id) {
		// TODO Auto-generated method stub
		String hql="from IndoorSite i where i.flow_id2 = '"+id+"'";
		//Object[] values={id};
		List<IndoorSite> list = new ArrayList<>();
		
		list = this.getHibernateTemplate().find(hql);
		IndoorSite site = null;
		if(list.size()>0){
			site = list.get(0);
		}
		return site;
	}
	
	public String findIndoorSite2(String id) {
		//System.out.print("id:"+id+"\t");
		String str = "";
		String sql = "SELECT involved_property_code FROM indoor_site WHERE flow_id = '"+id+"'";
		Session session = super.getSession();
		List<String> list = session.createSQLQuery(sql).list();
		if(list.size() > 0)
			str = list.get(0);
		//System.out.println("str:"+str);
		super.releaseSession(session);
		return str;
	}
	
	public List<IndoorSite> findIndoorSiteByCityAndNetWorkAndName(String city ,String netWorkType,String name) {
		// TODO Auto-generated method stub
		String hql="from IndoorSite where city = ? and network_type = ? and indoor_site_name = ?";
		Object[] values={city,netWorkType,name};
		List<IndoorSite> list = find(hql, values);
		
		return list;
	}
	
	public List<IndoorSite> findIndoorSiteByCity(String city) {
		// TODO Auto-generated method stub
		String hql="from IndoorSite where city = ?";
		Object[] values={city};
		List<IndoorSite> list = find(hql, values);
		
		return list;
	}
	
	public IndoorSite findIndoorSiteByName(String indoorSiteName) {
		// TODO Auto-generated method stub
		String hql="from IndoorSite where indoor_site_name = ?";
		Object[] values={indoorSiteName};
		List<IndoorSite> list = find(hql, values);
		IndoorSite site = null;
		if(list.size()>0){
			site = list.get(0);
		}
		return site;
	}
	
	public IndoorSite findIndoorSiteLike(String id) {
		// TODO Auto-generated method stub
		String hql="from IndoorSite where flow_id like '%"+id+"%'";
		Object[] values={};
		List<IndoorSite> list = find(hql, values);
		IndoorSite site = new IndoorSite();
		if(list.size()>0){
			site = list.get(0);
		}
		return site;
	}
	
	public void deleteIndoorSite(String indoor_site_code) {
		Session session = super.getSessionFactory().getCurrentSession();
		String sql = "delete from indoor_site where indoor_site_code = '"+indoor_site_code+"'";
		session.createSQLQuery(sql).executeUpdate();
		//this.delete(id);
	}

	
	public List<IndoorSite> findIndoorSites(String indoor_site_code) {
		// TODO Auto-generated method stub
		String hql="from IndoorSite where indoor_site_code=?";
		Object[] values={indoor_site_code};
		return this.find(hql, values);
	}
	
	public List<IndoorSite> findIndoorSiteProperty(String involved_property_code) {
		// TODO Auto-generated method stub
		String hql="from IndoorSite where involved_property_code=? and state=0";
		Object[] values={involved_property_code};
		return this.find(hql, values);
	}
	
	
	public List<IndoorSite> findIndoorSiteAllProperty(String involved_property_code) {
		// TODO Auto-generated method stub
		String hql="from IndoorSite where involved_property_code=?";
		Object[] values={involved_property_code};
		return this.find(hql, values);
	}
	
	public String findIndoorSite_code() {
		// TODO Auto-generated method stub
		String hql="from IndoorSite where ROWNUN=1  order by indoor_site_code desc ";
		Object[] values={};
		if(this.find(hql, values).size()==0){
			return "1";
		}
		String a=find(hql, values).get(0).getIndoor_site_code();
		return a;
	}
	
	public String findIndoorSite_code(String mode){
		
		String hql="from IndoorSite "+"where indoor_site_code like '%"+mode+"%'"+"and ROWNUN=1  order by indoor_site_code desc ";
		Object[] values={};
		if(this.find(hql, values).size()==0){
			return "1";
		}
		String a=find(hql, values).get(0).getIndoor_site_code();
		return a;
	}
	public String findMaxCodeByModeAndCity(String mode,String city){
		String hql="select indoor_site_code from indoor_site where city='"+city+"' and indoor_site_code like'"+mode+"%' and state != 2 and ROWNUN=1  order by indoor_site_code desc ";
		Session session = super.getSession();
		String a = (String) session.createSQLQuery(hql).addScalar("indoor_site_code").uniqueResult();
		super.releaseSession(session);
		if(a==null || a.equals("")){
			return "-1";
		}
		return a;
	}
	
	public List<IndoorSite> findIndoorSiteList(String indoor_site_code,String indoor_site_name,String city,String region,String network_type,String state) {
		// TODO Auto-generated method stub
		StringBuffer string=new StringBuffer();
		string.append("from IndoorSite where state=0 or state=5");
		if(indoor_site_code!=null && !indoor_site_code.equals("")){
			string.append(" and indoor_site_code like '%"+indoor_site_code+"%'");
		}
		if(indoor_site_name!=null && !indoor_site_name.equals("")){
			string.append(" and indoor_site_name like '%"+indoor_site_name+"%'");
		}
		if(city != null && !city.equals("")){
			string.append(" and city like '%"+city+"%'");
		}
		if(region!=null && !region.equals("")){
			String[] cities = region.split(",");
			
			for(int a = 0;a <cities.length;a++){
				if(a == 0){
					string.append(" and (region like '%"+cities[a]+"%'");
				}else{
					string.append(" or region like '%"+cities[a]+"%'");
				}
				if(a == cities.length-1){
					string.append(")");
				}
				
			}
		}
		if(network_type!=null && !network_type.equals("")){
			string.append(" and network_type like '%"+network_type+"%'");
		}
		
		String hql=string.toString();
		Object[] values=null;
		return this.find(hql, values);
	}
	/*
	 * input 室分站点编号  ，名称，地市，区县，工期，网络制式，状态值，起始条数，一次条目容量
	 * output 条件查询出List
	 */
	
	public List<IndoorSite> findIndoorSiteListPage(String indoor_site_code,String indoor_site_name,String city,String region,String project_time,String network_type,String state,int start,int size) {
		// TODO Auto-generated method stub
		StringBuffer string=new StringBuffer();
		string.append("from IndoorSite where 0 = 0");
		if(indoor_site_code!=null && !indoor_site_code.equals("")){
			string.append(" and indoor_site_code like '%"+indoor_site_code+"%'");
		}
		if(indoor_site_name!=null && !indoor_site_name.equals("")){
			string.append(" and indoor_site_name like '%"+indoor_site_name+"%'");
		}
		if(city != null && !city.equals("")){
			string.append(" and city ='"+city+"'");
		}
		if(region!=null && !region.equals("")){
			String[] cities = region.split(",");
			
			for(int a = 0;a <cities.length;a++){
				if(a == 0){
					string.append(" and (region like '%"+cities[a]+"%'");
				}else{
					string.append(" or region like '%"+cities[a]+"%'");
				}
				if(a == cities.length-1){
					string.append(")");
				}
				
			}
		}
		if(project_time != null && !project_time.equals("")){
			string.append(" and project_time like '%" +project_time+"%'");
		}
		if(network_type!=null && !network_type.equals("")){
			string.append(" and network_type like '%"+network_type+"%'");
		}
		if(state != null && ! state.equals("")){
			
			string.append(" and state in ('0','5') ");
		}
		String hql=string.toString();
		Session session = super.getSession();
		Query query = session.createQuery(hql);
		// 强制约定如果firstResust == -1,或者maxResult == -1 则返回所有的记录集
		if (start != -1 && size != -1)
			query.setFirstResult(start).setMaxResults(size);
		// 设置where语句中占位符?中的参数
		setQueryParameter(query, null);
		List<IndoorSite> qr = new ArrayList<IndoorSite>();
		// 设置QueryResult中的结果集
		qr=query.list();
		super.releaseSession(session);
		return qr;
	}
	/*
	 * input 室分站点编号  ，名称，地市，区县，工期，网络制式，状态值，起始条数，一次条目容量
	 * output 条件查询出总条数
	 */
	
	public int countIndoorSitePage(String indoor_site_code,
			String indoor_site_name, String city, String region,
			String project_time, String network_type, String state) {
		// TODO Auto-generated method stub
		StringBuffer string=new StringBuffer();
		string.append("select count(*) num  from indoor_site where 0 = 0");
		if(indoor_site_code!=null && !indoor_site_code.equals("")){
			string.append(" and indoor_site_code like '%"+indoor_site_code+"%'");
		}
		if(indoor_site_name!=null && !indoor_site_name.equals("")){
			string.append(" and indoor_site_name like '%"+indoor_site_name+"%'");
		}
		if(city != null && !city.equals("")){
			string.append(" and city like '%"+city+"%'");
		}
		if(region!=null && !region.equals("")){
			String[] cities = region.split(",");
			
			for(int a = 0;a <cities.length;a++){
				if(a == 0){
					string.append(" and (region like '%"+cities[a]+"%'");
				}else{
					string.append(" or region like '%"+cities[a]+"%'");
				}
				if(a == cities.length-1){
					string.append(")");
				}
				
			}
		}
		if(project_time != null && !project_time.equals("")){
			string.append(" and project_time like '%" +project_time+"%'");
		}
		if(network_type!=null && !network_type.equals("")){
			string.append(" and network_type like '%"+network_type+"%'");
		}
		if(state != null && ! state.equals("")){
			
			string.append(" and state in ('0','5') ");
		}
		Session session = super.getSession();
		String sql=string.toString();
		int num =  (int) session.createSQLQuery(sql).addScalar("num", Hibernate.INTEGER).uniqueResult();;
		super.releaseSession(session);
		return num;
	}
	
	
	
	
	public List<IndoorSite> findAllIndoorSite(){
		String hql = "from IndoorSite";
		return find(hql, null);
	}
	/**
	 * 查询已开通站点发起抽验
	 */
	
	public List<IndoorSite> findIndoorSiteByState(String state){
		String hql = "from IndoorSite where state = ?";
		Object[] values = {state};
		return find(hql, values);
	}
	
	/**
	 * 模糊查询
	 */
	
	public List<IndoorSite> findSiteByLike(String city,String site_code,String site_name,String network_type){
		String hql = "from IndoorSite where indoor_site_code like '%"+site_code+"%' "+
				"and indoor_site_name like '%"+site_name+"%' "+
				"and city like '%"+city+"%' and network_type like '%" +network_type +"%'";
		
		List<IndoorSite> list = find(hql, null);
		
		return list;
	}
	
	public void updateIndoorSiteState(String indoor_site_code,String state) {
		// TODO Auto-generated method stub
		String hql="update from IndoorSite set state=? where indoor_site_code=?";
		Object[] values={state,indoor_site_code};
		this.update(hql, values);
	}
	
	public void updateIndoorSiteState(String indoor_site_code,String state,String flow_id) {
		// TODO Auto-generated method stub
		String hql="update from IndoorSite set state=?,flow_id=? where indoor_site_code=?";
		Object[] values={state,flow_id,indoor_site_code};
		this.update(hql, values);
	}

	//用于整治室分站点flow_id2
	
	public void updateIndoorSiteState2(String indoor_site_code,String state,String flow_id2){
		String hql="update from IndoorSite set state=?,flow_id2=? where indoor_site_code=?";
		Object[] values={state,flow_id2,indoor_site_code};
		this.update(hql, values);
	}

//只用于删除室分站点
	
	public void updateIndoorSiteFlow_id(String indoor_site_code, String flow_id) {
		// TODO Auto-generated method stub
		String hql="update from IndoorSite set flow_id=?,state=4 where indoor_site_code=?";
		Object[] values={flow_id,indoor_site_code};
		this.update(hql, values);
	}
	
	public void updateIndoorSiteIndoorSiteCode(String indoor_site_code1,String state,String indoor_site_code){
		String hql="update from IndoorSite set indoor_site_code=?,state=? where indoor_site_code=?";
		Object[] values={indoor_site_code,state,indoor_site_code1};
		this.update(hql, values);
	}

	/**
	 * 根据制式查询已开通的室分站点
	 */
	
	public List<IndoorSite> findIndoorSiteByNetWork(String network_type){
		String hql = "from IndoorSite where network_type = ? and state = 0";
		Object[] values = {network_type};
		List<IndoorSite> list = find(hql, values);
		
		return list;
	}

	
	public IndoorSite findIndoorSiteByCode(String code) {
		String hql="from IndoorSite where indoor_site_code=?";
		Object[] values={code};
		List<IndoorSite> list =find(hql, values);
		IndoorSite site = new IndoorSite();
		if(list.size()>0){
			site = list.get(0);
		}
		return site;
	}
	/**
	 * 查询所有已完成的室分站点 关联物业点
	 * @return
	 */
	
	public List findAllSiteAndPropertyByState(){
		String hql = "from IndoorSite o1,Property o2 where o1.involved_property_code=o2.property_code and o1.state=0 and o2.state=0";
		List list = find(hql, null);
		return list;
	}
	/**
	 * 查询总条数
	 * @return
	 */
	
	public int getCount(){
		String hql="select count(*) from IndoorSite o1,Property o2 where o1.involved_property_code=o2.property_code and o1.state=0 and o2.state=0";
		Session session=super.getSession();
		Integer count=new Integer(((Long)session.createQuery(hql).uniqueResult()).intValue());
		super.releaseSession(session);
		
		return count;
	}
	
	/**
	 * 室分资料管理查询所室分站点 关联物业并分页
	 * @return
	 */
	
	public List findAllSiteAndPropertyByState( int start,int size){
		Session session=super.getSession();
		Query query =session.createQuery(
				"from IndoorSite o1,Property o2 where o1.involved_property_code=o2.property_code and (o1.state=0 or o1.state=1) and (o2.state=0 or o2.state=1)"
						);
		super.releaseSession(session);
		// 强制约定如果firstResust == -1,或者maxResult == -1 则返回所有的记录集
		if (start != -1 && size != -1)
			query.setFirstResult(start).setMaxResults(size);
		// 设置where语句中占位符?中的参数
		setQueryParameter(query, null);
		List<T> qr = new ArrayList<T>();
		// 设置QueryResult中的结果集
		qr=query.list();
		super.releaseSession(session);
		return qr;
	}
	
	//资料查询
	
	public List<toDataManagement> findByKeytypeAndNetworktypePage(String city,String region,String keytype,String keyword,String networktype ,int start,int  size){
		String cityhql = "select i.indoor_site_code code,i.indoor_site_name name ,i.network_type,i.city,i.region,p.name involved_property_name,\r\n" + 
				"case when i.state='5' then '批量上传'\r\n" + 
				"     when i.state='3' or i.state='0' then '新建流程'\r\n" + 
				"		 when i.state='01' or i.state='51' then '整改流程'\r\n" + 
				"		 when i.state='02' or i.state='52' then '省网优集中整治'\r\n" + 
				"		 when i.state='03' or i.state='53' then '地市自行整治'\r\n" + 
				"		 when i.state='010' or i.state='510' then '工期置换'\r\n" + 
				"		 when i.state='07' or i.state='57' then '工期置换'\r\n" + 
				"		 when i.state='6'  then '删除流程'\r\n" + 
				"end processName,\r\n" + 
				"case when i.state in('0','5') then '完成'\r\n" + 
				"		 else if(t.ACTIVITY_NAME_ is null, '三方会审',t.ACTIVITY_NAME_)\r\n" + 
				"end processlink\r\n" + 
				"from indoor_site i\r\n" + 
				"LEFT JOIN property p on i.involved_property_code=p.property_code\r\n" + 
				"LEFT JOIN jbpm4_task t on i.flow_id2=t.EXECUTION_ID_\r\n" + 
				"where i.state !='1'";
		String cq = "";
		if(city != null && !city.equals("")){
			String[] citys = city.split(",");
			for (int i = 0; i < citys.length; i++) {
				if(i == 0){
					cq =" and (i.city ='"+citys[i]+"'";
				}else {
					cq += " or i. city = '"+citys[i]+"'";
				}
				if(i == citys.length-1){
					cq += ")";
				}
			}
		}
		if(region != null && !region.equals("")){
			String[] regions = region.split(",");
			for (int i = 0; i < regions.length; i++) {
				if(i == 0){
					cq +=" and ( i.region ='"+regions[i]+"'";
				}else {
					cq += "or i. region = '"+regions[i]+"'";
				}
				if(i == regions.length-1){
					cq += ")";
				}
			}
		}
		cityhql += cq;
		
		if(keyword!=null&&!keyword.trim().equals("")){
			if(keytype.equals(new String("所有字段"))){
				cityhql +=	" and (i.indoor_site_name like '%"+keyword+"%' or i.indoor_site_code like '%"+keyword+"%'"+ "or o.name like '%"+keyword+"%')";
			}else if(keytype.equals(new String("室分站点名称"))){
				cityhql +=	"  and i.indoor_site_name like '%"+keyword+"%'";
			}else if(keytype.equals(new String("室分站点编号"))){
				cityhql +=	" and i.indoor_site_code like '%"+keyword+"%'";
			}else if(keytype.equals(new String("物业点名称"))){
				cityhql +=	" and p.name like '%"+keyword+"%'";
			}
		}
		if(!networktype.equals(new String("ALL"))){
			cityhql += " and i.network_type = '"+networktype+"'";
		}
		cityhql+=" limit "+start+","+size;
		Session session = super.getSession();
		List<toDataManagement> qr = new ArrayList<toDataManagement>();
		qr=session.createSQLQuery(cityhql).addScalar("code").addScalar("name").addScalar("city")
				.addScalar("region").addScalar("processName").addScalar("processlink")
				.addScalar("network_type").addScalar("involved_property_name")
				.setResultTransformer(Transformers.aliasToBean(toDataManagement.class)).list();
				super.releaseSession(session);
		return qr;
	}
	//
	
	public List findByKeytypeAndNetworktype(String city,String region,String keytype,String keyword,String networktype){
		String cityhql = " SELECT i.* FROM 	indoor_site i LEFT JOIN property o ON i.involved_property_code = o.property_code where "
				+ "i.state != 1 AND i.state IS NOT NULL ";
		String cq = "";
		if(city != null && !city.equals("")){
			String[] citys = city.split(",");
			for (int i = 0; i < citys.length; i++) {
				if(i == 0){
					cq =" and (i.city ='"+citys[i]+"'";
				}else {
					cq += " or i. city = '"+citys[i]+"'";
				}
				if(i == citys.length-1){
					cq += ")";
				}
			}
		}
		if(region != null && !region.equals("")){
			String[] regions = region.split(",");
			for (int i = 0; i < regions.length; i++) {
				if(i == 0){
					cq +=" and ( i.region ='"+regions[i]+"'";
				}else {
					cq += "or i. region = '"+regions[i]+"'";
				}
				if(i == regions.length-1){
					cq += ")";
				}
			}
		}
		cityhql += cq;
		
		if(keyword!=null&&!keyword.trim().equals("")){
//			if(keytype.equals(new String("所有字段"))){
//				cityhql +=	" and (i.indoor_site_name like '%"+keyword+"%' or i.indoor_site_code like '%"+keyword+"%'"+ "or o.name like '%"+keyword+"%')";
//			}else 
				if(keytype.equals(new String("室分站点名称"))){
				cityhql +=	"  and i.indoor_site_name like '%"+keyword+"%'";
			}else if(keytype.equals(new String("室分站点编号"))){
				cityhql +=	" and i.indoor_site_code like '%"+keyword+"%'";
			}else if(keytype.equals(new String("物业点名称"))){
				cityhql +=	" and o.name like '%"+keyword+"%'";
			}
		}
		if(!networktype.equals(new String("ALL"))){
			cityhql += " and i.network_type = '"+networktype+"'";
		}
		Session session = super.getSession();
		List list = session.createSQLQuery(cityhql).addEntity("i",IndoorSite.class).list();
				super.releaseSession(session);
				return list;
	}

	/**
	 * 查询新建室分站点、室分站点变更.....待办
	 */
	@SuppressWarnings("unchecked")
	
	public List<Task_to_do_list> findTodoIndoorSiteByRole(User user,
			String todoName,String keytype,String keyword, int pageNow, int pageSize) {
		List<Task_to_do_list> list = new ArrayList<Task_to_do_list>();
		Session session = super.getSession();
		
		String city = null;
		if(user.getRole_id() == 3 || user.getRole_id() == 4 || user.getRole_id() == 5
				|| user.getRole_id() == 6|| user.getRole_id() == 7|| user.getRole_id() == 8
				|| user.getRole_id() == 10 || user.getRole_id() == 11)
		{
			city = user.getRule_region();
			city = DBUtil.dealStr(city);
		}else{
			city = "'"+user.getCity()+"'";
		}
		//@wu 10.23 查询地市人员在‘提交竣工资料','上传单验报告','上传验收报告’的待办任务同时赋给
		//室分小组的的待办任务，其可对‘提交竣工资料','上传单验报告','上传验收报告’进行随时驳回
		String sql = "";
		if(user.getRole_id()==10 && todoName.equals("newSite")){
			
			sql="SELECT ta.DBID_ task_id,pro.indoor_site_name name, pro.network_type site_netWork, " + 
					" pro.processInitiator task_initiator,ta.CREATE_ time, " + 
					" ta.NAME_ process_status,ta.DESCR_ applicant_reason,pro.isDateReplace isDateReplace " + 
					" FROM jbpm4_task ta " + 
					" LEFT JOIN indoor_site pro ON pro.flow_id = ta.EXECUTION_ID_ " + 
					" WHERE NAME_ in ('上传单验报告','上传验收报告','完成') AND pro.city IN ("+city+") and ta.EXECUTION_ID_ like 'newSite.%' ";
		}else{
			sql = "select ta.DBID_ task_id,pro.indoor_site_name name,pro.network_type site_netWork, " + 
					" pro.processInitiator task_initiator ,ta.CREATE_ time ,ta.NAME_ process_status, " + 
					" ta.DESCR_ applicant_reason, pro.isDateReplace isDateReplace from jbpm4_participation p  " + 
					" LEFT JOIN jbpm4_task ta on p.TASK_=ta.DBID_  " + 
					" LEFT JOIN indoor_site pro on pro.flow_id2 =ta.EXECUTION_ID_   " + 
					" where p.GROUPID_=" +user.getRole_id()
					+" and pro.city in ("+city+")  and ta.EXECUTION_ID_ like '"+todoName+"%' ";
		}
		if (keytype != null && keytype.equals("proname")) {
			sql += " and pro.indoor_site_name like '%" + keyword + "%'";
		} else if (keytype != null && keytype.equals("initiator")) {
			sql += " and pro.processInitiator like '%" + keyword + "%'";
		} else if (keytype != null && keytype.equals("state")) {
			sql += " and ta.NAME_ like '%" + keyword + "%'";
		}
		sql += " limit " + pageNow + "," + pageSize;
		list = session.createSQLQuery(sql).addScalar("task_id",Hibernate.STRING).addScalar("name").addScalar("task_initiator")
		.addScalar("time",Hibernate.STRING).addScalar("process_status").addScalar("applicant_reason")
		.addScalar("site_netWork").addScalar("isDateReplace")
		.setResultTransformer(Transformers.aliasToBean(Task_to_do_list.class)).list();
		super.releaseSession(session);
		return list;
	}
	/**
	 * @authour 吴兵 根据查询条件获得新建室分站点、室分站点整改待办总条数
	 * @Date:2014年12月30日
	 * @return
	 */
	
	public int getTotalNum(User user,String todoName,String keytype,String keyword){
		Session session = super.getSession();
		String city = null;
		if (user.getRole_id() == 3 || user.getRole_id() == 4|| user.getRole_id() == 5
				|| user.getRole_id() == 6 || user.getRole_id() == 7
				|| user.getRole_id() == 8 || user.getRole_id() == 10 || user.getRole_id() == 11) {
			city = user.getRule_region();
			city = DBUtil.dealStr(city);
		} else {
			city = "'" + user.getCity() + "'";
		}
		// @wu 10.23 查询地市人员在‘提交竣工资料','上传单验报告','上传验收报告’的待办任务同时赋给
		// 室分小组的的待办任务，其可对‘提交竣工资料','上传单验报告','上传验收报告’进行随时驳回
		String sql = "";
		if (user.getRole_id() == 10) {
			 sql="SELECT count(*) num " + 
					" FROM jbpm4_task ta " + 
					" LEFT JOIN indoor_site pro ON pro.flow_id = ta.EXECUTION_ID_ " + 
					" WHERE NAME_ in ('上传单验报告','上传验收报告','完成') AND pro.city IN ("+city+") and ta.EXECUTION_ID_ like 'newSite.%' ";
		}else{
			sql = "select count(*) num " +
					" from jbpm4_participation p  " + 
					" LEFT JOIN jbpm4_task ta on p.TASK_=ta.DBID_  " + 
					" LEFT JOIN indoor_site pro on pro.flow_id2 =ta.EXECUTION_ID_   " + 
					" where p.GROUPID_=" +user.getRole_id()
					+" and pro.city in ("+city+")  and ta.EXECUTION_ID_ like '"+todoName+"%' ";

		}
		if (keytype != null && keytype.equals("proname")) {
			sql += " and pro.indoor_site_name like '%" + keyword + "%'";
		} else if (keytype != null && keytype.equals("initiator")) {
			sql += " and pro.processInitiator like '%" + keyword + "%'";
		} else if (keytype != null && keytype.equals("state")) {
			sql += " and ta.NAME_ like '%" + keyword + "%'";
		}
		int num = (int) session.createSQLQuery(sql).addScalar("num",Hibernate.INTEGER).uniqueResult();
		super.releaseSession(session);
		return num;
	}
	
	
	public IndoorSite findIndoorSiteBy(String id) {
		// TODO Auto-generated method stub
		String hql="from IndoorSite i where i.indoor_site_code = '"+id+"'";
		//Object[] values={id};
		List<IndoorSite> list = new ArrayList();
		
		list = this.getHibernateTemplate().find(hql);
		IndoorSite site = null;
		if(list.size()>0){
			site = list.get(0);
		}
		return site;
	}

	@SuppressWarnings("unchecked")
	
	public List<String> getHistoryTaskId(String pro_id, String starttime) {
		// TODO Auto-generated method stub
		List<String> list = new ArrayList<>();
		Session session = super.getSession();
		//@wu 10.23 添加TYPE_!='decision'条件去除流程历史HTASK_为null的情况
		String sql_query="select HTASK_ from jbpm4_hist_actinst where HPROCI_="+pro_id+" and TYPE_!='decision' and END_<='"+starttime+"' order by START_ ASC";
		list = session.createSQLQuery(sql_query).addScalar("HTASK_",Hibernate.STRING).list();
		return list;
	}
	@SuppressWarnings("unchecked")
	
	public List<CompleteTaskHistory> findSiteWaitTodo(String user,String flow,int pageNow, int pageSize,String keytype,String keyword){
		List<CompleteTaskHistory> list = new ArrayList<>();
		Session session = super.getSession();
		String str = "";
		if(keyword == null || "".equals(keyword)){
			
		}else if("siteName".equals(keytype)){
			str = " and i.indoor_site_name like '%"+keyword+"%' ";
		}else if("initiator".equals(keytype)){
			str = " and i.processInitiator like '%"+keyword+"%' ";
		}else if("state".equals(keytype)){
			str = " and task.ACTIVITY_NAME_ like '%"+keyword+"%' ";
		}
		String sql = "";
		if(flow.equals("newSite")){//新建室分站点没有end节点 用此方法
			sql ="SELECT i.indoor_site_name construction_name,i.flow_id2 executionId,i.city city, " + 
					" i.indoor_site_code construction_code,"
					+ "IF(task.ACTIVITY_NAME_='完成' || task.ACTIVITY_NAME_ IS NULL,0,IFNULL(ht.revokeFlag,0)) revokeFlag, " + 
					" i.network_type construction_netType,IFNULL(task.ACTIVITY_NAME_,'完成') name, "
					+" i.processInitiator flowInitiator, " + 
					" CASE " + 
					" WHEN task.ACTIVITY_NAME_ IS NULL then hp.END_ " + 
					" WHEN task.ACTIVITY_NAME_='完成' THEN task.CREATE_ " + 
					" end as completionTime, " + 
					" IF(task.ACTIVITY_NAME_='完成' || task.ACTIVITY_NAME_ IS NULL,'流程已结束','正在执行中') comment " + 
					" FROM (SELECT  EXECUTION_ FROM jbpm4_hist_task WHERE  " + 
					" EXECUTION_ LIKE '"+flow+"%' AND ASSIGNEE_ = '"+user+"' GROUP BY EXECUTION_ " + 
					" ) flow LEFT JOIN indoor_site i ON flow.EXECUTION_ = i.flow_id2  " + 
					" LEFT JOIN jbpm4_task task ON task.EXECUTION_ID_ = i.flow_id2  "
					+ " LEFT JOIN jbpm4_hist_procinst hp ON hp.ID_ = i.flow_id2 " + 
					" LEFT JOIN (SELECT count(STATE_) revokeFlag, EXECUTION_ FROM jbpm4_hist_task WHERE  " + 
					" EXECUTION_ LIKE '"+flow+"%'  GROUP BY EXECUTION_ " + 
					" ) ht ON ht.EXECUTION_ = i.flow_id2  WHERE i.flow_id2 LIKE '"+flow+"%' "+str
					+" ORDER BY i.id desc limit "+pageNow+","+pageSize;
		}else {//室分站点整改、整治、删除用此方法
			sql ="SELECT i.indoor_site_name construction_name,i.flow_id2 executionId,i.city city, " + 
					" i.indoor_site_code construction_code,"
					+ "IF(task.ACTIVITY_NAME_='完成' || task.ACTIVITY_NAME_ IS NULL,0,IFNULL(ht.revokeFlag,0)) revokeFlag, "
					+" i.network_type construction_netType, " + 
					" IFNULL(task.ACTIVITY_NAME_,'流程已结束') name,i.processInitiator flowInitiator, " + 
					" hp.END_ completionTime,   " + 
					" IF(task.ACTIVITY_NAME_ IS NULL,'流程已结束','正在执行中') comment " + 
					" FROM (SELECT  EXECUTION_ FROM jbpm4_hist_task WHERE  " + 
					" EXECUTION_ LIKE '"+flow+"%' AND ASSIGNEE_ = '"+user+"' GROUP BY EXECUTION_ " + 
					" ) flow LEFT JOIN indoor_site i ON flow.EXECUTION_ = i.flow_id2  " + 
					" LEFT JOIN jbpm4_task task ON task.EXECUTION_ID_ = i.flow_id2  "
					+ " LEFT JOIN jbpm4_hist_procinst hp ON hp.ID_ = i.flow_id2 " + 
					" LEFT JOIN (SELECT count(STATE_) revokeFlag, EXECUTION_ FROM jbpm4_hist_task WHERE  " + 
					" EXECUTION_ LIKE '"+flow+"%'  GROUP BY EXECUTION_ " + 
					") ht ON ht.EXECUTION_ = i.flow_id2  WHERE i.flow_id2 LIKE '"+flow+"%' "+str
					+" ORDER BY i.id desc limit "+pageNow+","+pageSize;
		}
		 
		list = session.createSQLQuery(sql).addScalar("executionId").addScalar("revokeFlag",Hibernate.INTEGER).addScalar("name").addScalar("comment")
		.addScalar("flowInitiator").addScalar("construction_name").addScalar("construction_code").addScalar("city")
		.addScalar("construction_netType").addScalar("completionTime",Hibernate.STRING)
		.setResultTransformer(Transformers.aliasToBean(CompleteTaskHistory.class)).list();
		super.releaseSession(session);
		return list;
	}
	
	/**
	 * 获得已办任务总条数
	 */
	
	public int countSiteWaitTodo(String user,String flow,String keytype,String keyword){
		Session session = super.getSession();
		String str = "";
		if(keyword == null || "".equals(keyword)){
			
		}else if("siteName".equals(keytype)){
			str = " and i.indoor_site_name like '%"+keyword+"%' ";
		}else if("initiator".equals(keytype)){
			str = " and i.processInitiator like '%"+keyword+"%' ";
		}else if("state".equals(keytype)){
			str = " and task.ACTIVITY_NAME_ like '%"+keyword+"%' ";
		}
		/*String sql = "select count(*) num "
				+" from complete_task c "
				+" INNER JOIN jbpm4_hist_procinst procinst on c.flow_id=procinst.ID_ "
				+" INNER JOIN jbpm4_hist_var v on v.VARNAME_='starter_id' and v.PROCINSTID_=c.flow_id "
				+" INNER JOIN indoor_site i on c.flow_id=i.flow_id2 "
				+" left JOIN jbpm4_task task  on v.HPROCI_=task.PROCINST_ "
				+" INNER JOIN user u on v.VALUE_=u.account_name "
				+" where  c.user_id='"+user+"' and c.flow_id like '"+flow+"%' "
				+str;*/
		String sql = "select count(*) num from (SELECT count(*) FROM jbpm4_hist_task ht " + 
				" INNER JOIN indoor_site i ON i.flow_id2 = ht.EXECUTION_ "+
				" left join jbpm4_task task on task.EXECUTION_ = i.flow_id2 "
				+" WHERE ht.ASSIGNEE_ = '"+user+"' AND ht.EXECUTION_ like '"+flow+"%' "
				+ str
				+ " GROUP BY ht.EXECUTION_) as t4 ";
		
		int num =  (int) session.createSQLQuery(sql).addScalar("num", Hibernate.INTEGER).uniqueResult();;
		super.releaseSession(session);
		return num;
	}
	
	/**
	 * @authour 吴兵
	 * @Date:2014年9月12日
	 * @return 查询抽验的室分站点总条数
	 */
	
	public int totalNum(String indoor_site_code,String indoor_site_name,User user,String region,String network_type,String state){
		Session session = super.getSession();
		StringBuffer string=new StringBuffer();
		string.append("select count(*) num from indoor_site where state=0");
		if(indoor_site_code!=null && !indoor_site_code.equals("")){
			string.append(" and indoor_site_code like '%"+indoor_site_code+"%'");
		}
		if(indoor_site_name!=null && !indoor_site_name.equals("")){
			string.append(" and indoor_site_name like '%"+indoor_site_name+"%'");
		}
		String city = null;
		if( user.getRole_id() == 4){
			city = user.getRule_region();
			city = DBUtil.dealStr(city);
		}else{
			city = "'"+user.getCity()+"'";
		}
		if(city != null && !city.equals("")){
			string.append(" and city in ("+city+")");
		}
		if(region!=null && !region.equals("")){
			String[] cities = region.split(",");
			
			for(int a = 0;a <cities.length;a++){
				if(a == 0){
					string.append(" and (region = '"+cities[a]+"'");
				}else{
					string.append(" or region = '"+cities[a]+"'");
				}
				if(a == cities.length-1){
					string.append(")");
				}
				
			}
		}
		if(network_type!=null && !network_type.equals("")){
			string.append(" and network_type = '"+network_type+"'");
		}
		String sql=string.toString();
		int num =  (int) session.createSQLQuery(sql).addScalar("num", Hibernate.INTEGER).uniqueResult();;
		super.releaseSession(session);
		return num;
	}
	/**
	 * @wu 室分站点抽验查询分页
	 */
	
	public List<IndoorSite> findTestIndoorSiteListPage(String indoor_site_code,String indoor_site_name,
			User user,String region,String network_type,String state,int start,int size) {
		// TODO Auto-generated method stub
		StringBuffer string=new StringBuffer();
		string.append("from IndoorSite where state=0");
		if(indoor_site_code!=null && !indoor_site_code.equals("")){
			string.append(" and indoor_site_code like '%"+indoor_site_code+"%'");
		}
		if(indoor_site_name!=null && !indoor_site_name.equals("")){
			string.append(" and indoor_site_name like '%"+indoor_site_name+"%'");
		}
		String city = null;
		if( user.getRole_id() == 4){
			city = user.getRule_region();
			city = DBUtil.dealStr(city);
		}else{
			city = "'"+user.getCity()+"'";
		}
		if(city != null && !city.equals("")){
			string.append(" and city in ("+city+")");
		}
		if(region!=null && !region.equals("")){
			String[] cities = region.split(",");
			
			for(int a = 0;a <cities.length;a++){
				if(a == 0){
					string.append(" and (region = '"+cities[a]+"'");
				}else{
					string.append(" or region ='"+cities[a]+"'");
				}
				if(a == cities.length-1){
					string.append(")");
				}
				
			}
		}
		if(network_type!=null && !network_type.equals("")){
			string.append(" and network_type = '"+network_type+"' limit "+start+","+size);
		}
		
		String hql=string.toString();
		Session session = super.getSession();
		Query query = session.createQuery(hql);
		// 强制约定如果firstResust == -1,或者maxResult == -1 则返回所有的记录集
		if (start != -1 && size != -1)
			query.setFirstResult(start).setMaxResults(size);
		// 设置where语句中占位符?中的参数
		setQueryParameter(query, null);
		List<IndoorSite> qr = new ArrayList<IndoorSite>();
		// 设置QueryResult中的结果集
		qr=query.list();
		super.releaseSession(session);
		return qr;
	}
	//查询所有的室分站点编号
		
		public List<String> findIndoorSiteAllCode(Integer state) {
			// TODO Auto-generated method stub
			List<String> list = new ArrayList<>();
			list = this.findIndoorSiteCodeByState(new Integer[]{state});
			return list;
		}

		
		public int countByKeytypeAndNetworktype(String city, String region,
				String keytype, String keyword, String networktype) {
			// TODO Auto-generated method stub
			String cityhql = "SELECT count(*) num  FROM 	indoor_site i,property o where "
					+ "i.state != 1 AND i.state IS NOT NULL and i.involved_property_code=o.property_code ";
			String cq = "";
			if(city != null && !city.equals("")){
				String[] citys = city.split(",");
				for (int i = 0; i < citys.length; i++) {
					if(i == 0){
						cq =" and (i.city ='"+citys[i]+"'";
					}else {
						cq += " or i. city = '"+citys[i]+"'";
					}
					if(i == citys.length-1){
						cq += ")";
					}
				}
			}
			if(region != null && !region.equals("")){
				String[] regions = region.split(",");
				for (int i = 0; i < regions.length; i++) {
					if(i == 0){
						cq +=" and ( i.region ='"+regions[i]+"'";
					}else {
						cq += "or i. region = '"+regions[i]+"'";
					}
					if(i == regions.length-1){
						cq += ")";
					}
				}
			}
			cityhql += cq;
			
			if(keyword!=null&&!keyword.trim().equals("")){
//				if(keytype.equals(new String("所有字段"))){
//					cityhql +=	" and (i.indoor_site_name like '%"+keyword+"%' or i.indoor_site_code like '%"+keyword+"%'"+ "or o.name like '%"+keyword+"%')";
//				}else 
					if(keytype.equals(new String("室分站点名称"))){
					cityhql +=	"  and i.indoor_site_name like '%"+keyword+"%'";
				}else if(keytype.equals(new String("室分站点编号"))){
					cityhql +=	" and i.indoor_site_code like '%"+keyword+"%'";
				}else if(keytype.equals(new String("物业点名称"))){
					cityhql +=	" and o.name like '%"+keyword+"%'";
				}
			}
			if(!networktype.equals(new String("ALL"))){
				cityhql += " and i.network_type = '"+networktype+"'";
			}
			Session session = super.getSession();
			int num =  (int) session.createSQLQuery(cityhql).addScalar("num", Hibernate.INTEGER).uniqueResult();;
			return num;
		}

		
		public List<String> findIndoorSiteCodeByState(Integer[] state) {
			// TODO Auto-generated method stub
			List<String> list = new ArrayList<>();
			Session session = super.getSession();
			String sql_query="select indoor_site_code from indoor_site where 0=0 ";
			String query ="";
			if(state!=null && state.length >0){
				query += " and (";
				for (int i = 0; i < state.length; i++) {
					if(i == 0){
						query =" and (state ="+state[i];
					}else {
						query += " or state ="+state[i];
					}
					if(i == state.length-1){
						query += ")";
					}
				}
			}
			sql_query += query;
			list = session.createSQLQuery(sql_query).addScalar("indoor_site_code",Hibernate.STRING).list();
			return list;
		}


		
		public IndoorSite findIndoorSiteByFlowId2(String id) {
			// TODO Auto-generated method stub
			String hql="from IndoorSite i where i.flow_id2 = '"+id+"'";

			//Object[] values={id};
			List<IndoorSite> list = new ArrayList<>();
			
			list = this.getHibernateTemplate().find(hql);
			IndoorSite site = null;
			if(list.size()>0){
				site = list.get(0);
			}
			return site;
		}

		
		public int countIndoorSiteDateReplace(String indoor_site_code,
				String indoor_site_name, String city, String region,
				String project_time, String merge_type) {
			// TODO Auto-generated method stub
			StringBuffer string=new StringBuffer();
			string.append("select count(*) num  from indoor_site where 0 = 0");
			if(indoor_site_code!=null && !indoor_site_code.equals("")){
				string.append(" and indoor_site_code like '%"+indoor_site_code+"%'");
			}
			if(indoor_site_name!=null && !indoor_site_name.equals("")){
				string.append(" and indoor_site_name like '%"+indoor_site_name+"%'");
			}
			if(city != null && !city.equals("")){
				string.append(" and city like '%"+city+"%'");
			}
			if(region!=null && !region.equals("")){
				String[] cities = region.split(",");
				
				for(int a = 0;a <cities.length;a++){
					if(a == 0){
						string.append(" and (region like '%"+cities[a]+"%'");
					}else{
						string.append(" or region like '%"+cities[a]+"%'");
					}
					if(a == cities.length-1){
						string.append(")");
					}
					
				}
			}
			if(project_time != null && !project_time.equals("")){
				string.append(" and project_time like '%" +project_time+"%'");
			}
			if(merge_type!=null && !merge_type.equals("")){
				string.append(" and network_type like '%"+merge_type+"%'");
			}
			string.append(" and (project_time is not null and project_time != '') "
					+ "and (isDateReplace ='0' or isDateReplace is null) and state = '3' ");
			Session session = super.getSession();
			String sql=string.toString();
			int num =  (int) session.createSQLQuery(sql).addScalar("num", Hibernate.INTEGER).uniqueResult();;
			super.releaseSession(session);
			return num;
		}

		
		public List<IndoorSite> findIndoorSiteListDateReplace(
				String indoor_site_code, String indoor_site_name, String city,
				String region, String project_time, String merge_type,
				int start, int size) {
			// TODO Auto-generated method stub
			StringBuffer string=new StringBuffer();
			string.append("from IndoorSite where 0 = 0");
			if(indoor_site_code!=null && !indoor_site_code.equals("")){
				string.append(" and indoor_site_code like '%"+indoor_site_code+"%'");
			}
			if(indoor_site_name!=null && !indoor_site_name.equals("")){
				string.append(" and indoor_site_name like '%"+indoor_site_name+"%'");
			}
			if(city != null && !city.equals("")){
				string.append(" and city ='"+city+"'");
			}
			if(region!=null && !region.equals("")){
				String[] cities = region.split(",");
				
				for(int a = 0;a <cities.length;a++){
					if(a == 0){
						string.append(" and (region like '%"+cities[a]+"%'");
					}else{
						string.append(" or region like '%"+cities[a]+"%'");
					}
					if(a == cities.length-1){
						string.append(")");
					}
					
				}
			}
			if(project_time != null && !project_time.equals("")){
				string.append(" and project_time like '%" +project_time+"%'");
			}
			if(merge_type!=null && !merge_type.equals("")){
				string.append(" and network_type like '%"+merge_type+"%'");
			}
			string.append(" and (project_time is not null and project_time != '') "
					+ "and (isDateReplace ='0' or isDateReplace is null) and state = '3' ");
			String hql=string.toString();
			Session session = super.getSession();
			Query query = session.createQuery(hql);
			// 强制约定如果firstResust == -1,或者maxResult == -1 则返回所有的记录集
			if (start != -1 && size != -1)
				query.setFirstResult(start).setMaxResults(size);
			// 设置where语句中占位符?中的参数
			setQueryParameter(query, null);
			List<IndoorSite> qr = new ArrayList<IndoorSite>();
			// 设置QueryResult中的结果集
			qr=query.list();
			super.releaseSession(session);
			return qr;
		}

		
		public int getDateReplaceNum(String user, String flow,
				String keytype, String keyword) {
			// TODO Auto-generated method stub
			Session session = super.getSession();
			String str = "";
			if(keyword == null || "".equals(keyword)){
				
			}else if("siteName".equals(keytype)){
				str = " and i.indoor_site_name like '%"+keyword+"%' ";
			}else if("initiator".equals(keytype)){
				str = " and i.processInitiator like '%"+keyword+"%' ";
			}else if("state".equals(keytype)){
				str = " and task.ACTIVITY_NAME_ like '%"+keyword+"%' ";
			}
			String sql = "select count(*) num from (SELECT count(*) FROM jbpm4_hist_task ht " + 
					" INNER JOIN indoor_site i ON i.flow_temp = ht.EXECUTION_ "+
					" left join jbpm4_task task on task.EXECUTION_ = i.flow_temp "
					+" WHERE ht.ASSIGNEE_ = '"+user+"' AND ht.EXECUTION_ like '"+flow+"%' "
					+ str
					+ " GROUP BY ht.EXECUTION_) as t4 ";
			
			int num =  (int) session.createSQLQuery(sql).addScalar("num", Hibernate.INTEGER).uniqueResult();;
			super.releaseSession(session);
			return num;
		}

		
		public List<CompleteTaskHistory> findDateReplaceTodo(String user,
				String flow, int pageNow, int pageSize, String keytype,
				String keyword) {
			// TODO Auto-generated method stub
			List<CompleteTaskHistory> list = new ArrayList<>();
			Session session = super.getSession();
			String str = "";
			if(keyword == null || "".equals(keyword)){
				
			}else if("siteName".equals(keytype)){
				str = " and i.indoor_site_name like '%"+keyword+"%' ";
			}else if("initiator".equals(keytype)){
				str = " and i.processInitiator like '%"+keyword+"%' ";
			}else if("state".equals(keytype)){
				str = " and task.ACTIVITY_NAME_ like '%"+keyword+"%' ";
			}
			String sql = "";
				sql ="SELECT i.indoor_site_name construction_name,i.flow_temp executionId,i.city city, " + 
						" i.indoor_site_code construction_code,"
						+ " IF(task.ACTIVITY_NAME_='完成' || task.ACTIVITY_NAME_ IS NULL,0,IFNULL(ht.revokeFlag,0))  revokeFlag, "
						+" i.network_type construction_netType, " + 
						" IFNULL(task.ACTIVITY_NAME_,'流程已结束') name,i.processInitiator flowInitiator, " + 
						" hp.END_ completionTime,   " + 
						" IF(task.ACTIVITY_NAME_ IS NULL,'流程已结束','正在执行中') comment " + 
						" FROM (SELECT  EXECUTION_ FROM jbpm4_hist_task WHERE  " + 
						" EXECUTION_ LIKE '"+flow+"%' AND ASSIGNEE_ = '"+user+"' GROUP BY EXECUTION_ " + 
						" ) flow LEFT JOIN indoor_site i ON flow.EXECUTION_ = i.flow_temp  " + 
						" LEFT JOIN jbpm4_task task ON task.EXECUTION_ID_ = i.flow_temp  "
						+ " LEFT JOIN jbpm4_hist_procinst hp ON hp.ID_ = i.flow_temp " + 
						" LEFT JOIN (SELECT count(STATE_) revokeFlag, EXECUTION_ FROM jbpm4_hist_task WHERE  " + 
						" EXECUTION_ LIKE '"+flow+"%'  GROUP BY EXECUTION_ " + 
						") ht ON ht.EXECUTION_ = i.flow_temp  WHERE i.flow_temp LIKE '"+flow+"%' "+str
						+" ORDER BY i.id desc limit "+pageNow+","+pageSize;
			 
			list = session.createSQLQuery(sql).addScalar("executionId").addScalar("revokeFlag",Hibernate.INTEGER).addScalar("name").addScalar("comment")
			.addScalar("flowInitiator").addScalar("construction_name").addScalar("construction_code").addScalar("city")
			.addScalar("construction_netType").addScalar("completionTime",Hibernate.STRING)
			.setResultTransformer(Transformers.aliasToBean(CompleteTaskHistory.class)).list();
			super.releaseSession(session);
			return list;
		}

		
		public IndoorSite findDateReplaceSiteById(String id) {
			// TODO Auto-generated method stub
			String hql="from IndoorSite i where i.flow_temp = '"+id+"'";
			//Object[] values={id};
			List<IndoorSite> list = new ArrayList<>();
			
			list = this.getHibernateTemplate().find(hql);
			IndoorSite site = null;
			if(list.size()>0){
				site = list.get(0);
			}
			return site;
		}


		public int findByKeytypeAndNetworkTypeCount(String area,
				String keyWord, String networkType) {
			String cityhql = " select count(*) num from indoor_site o where  o.state IS NOT NULL";
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
			if(networkType!=null && !"".equals(networkType)){
				if(!"网络类型".equals(networkType)){
					cq +=" and network_type ='"+networkType+"'";
				}
			}
			cityhql += cq;
			if(keyWord!=null&&!keyWord.trim().equals("")){
				cityhql += " and(indoor_site_code like '%"+keyWord+"%' or indoor_site_name like '%"+keyWord+"%' or address like '%"+keyWord+"%')";
			}
			Session session = super.getSession();
			int num =  (int) session.createSQLQuery(cityhql).addScalar("num", Hibernate.INTEGER).uniqueResult();;
			return num;
		}


		public List<IndoorSite> findByKeytypeAndNetworkTypePage(String area,
				String keyWord, String networkType, int recordCount,
				int currentPage) {
			String cityhql="from IndoorSite where state IS NOT NULL";
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
			if(networkType!=null && !"".equals(networkType)){
				if(!"网络类型".equals(networkType)){
					cq +=" and network_type ='"+networkType+"'";
				}
			}
			cityhql += cq;
			if(keyWord!=null&&!keyWord.trim().equals("")){
				cityhql += " and(indoor_site_code like '%"+keyWord+"%' or indoor_site_name like '%"+keyWord+"%' or address like '%"+keyWord+"%')";
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


		public List<IndoorSite> findByChildProperty(String childPropertyCode) {
			String hql="from IndoorSite where child_property_code=?";
			Object[] values={childPropertyCode};
			return this.find(hql, values);
		}
}
