package com.hbmop.app.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;

import com.hbmop.app.model.Building;
import com.hbmop.app.model.CompleteTaskHistory;
import com.hbmop.app.model.User;
import com.hbmop.app.monitor.Task_to_do_list;
import com.hbmop.app.util.DBUtil;
import com.hbmop.app.vo.ToDoCount;

public class BuildingDAO extends DAOSupport<Building> {
	
	/**
	 * 楼宇资料管理的查询总数
	 */
	public int findByKeytypeAndCovertypeCount(String area ,String keyword,String covertype){
		String cityhql = " select count(*) num from building o where o.state != 2 AND o.state IS NOT NULL";
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
			cityhql += " and (building_code like '%"+keyword+"%' or name like '%"+keyword+"%' or address like '%"+keyword+"%')";
		}
		Session session = super.getSession();
		int num =  (int) session.createSQLQuery(cityhql).addScalar("num", Hibernate.INTEGER).uniqueResult();;
		return num;
	}
	/**
	 * 楼宇资料管理的查询分页
	 */
	public List<Building> findByKeytypeAndCovertypePage(String area ,String keyword,String covertype,int recordCount, int currentPage){
		String cityhql="from Building where state != 2 AND state IS NOT NULL";
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
			cityhql += " and(building_code like '%"+keyword+"%' or name like '%"+keyword+"%' or address like '%"+keyword+"%')";
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
	
	
	
	
/****************以下为待用方法**********************/	
	
	
	
	
	public Building findByLatAndLont(String longitude,String latitude){
		double lat = Double.valueOf(latitude);
		double lon = Double.valueOf(longitude);
		String hql = "from Property where longitude = ? and latitude = ?";
		Building building = null;
		Object[] values = {lon,lat};
		if(find(hql, values).size()>0){
			building = find(hql, values).get(0);
		}
		return building;
	}
	
	/**
	 * 模糊查询可以变更的物业点
	 */
	public List<Building> findByLike(String city,String building_code,String name,String cover_type){
		List<Building> list = new ArrayList<Building>();
		StringBuffer str=new StringBuffer();
		str.append("from Building where state = 0 ");
		if(building_code != null&&!building_code.equals("")){
			str.append("and building_code like '%"+building_code+"%'");
		}
		if(name != null&&!name.equals("")){
			str.append("and name like '%"+name+"%'");
		}
		if(cover_type != ""&&!cover_type.equals("")){
			str.append("and cover_type ='"+ cover_type +"'");
		}
		if(city ==null || city.equals("")){
			
		}else{
			String[] cities = city.split(",");			
			for(int a = 0;a <cities.length;a++){
				if(a == 0){
					str.append(" and (region ='"+cities[a]+"'");
				}else{
					str.append(" or region ='"+cities[a]+"'");
				}
				if(a == cities.length-1){
					str.append(")");
				}
				
			}
		}
		Object[] valueObjects = {};
		list = this.find(str.toString(), valueObjects);
		
		return list;
	}
	
	public List<Building> findByLikePage(String city,String building_code,String name,String cover_type,int start ,int size){
		StringBuffer str=new StringBuffer();
		str.append("from Building where state = 0 ");
		if(building_code != null&&!building_code.equals("")){
			str.append("and building_code like '%"+building_code+"%'");
		}
		if(name != null&&!name.equals("")){
			str.append("and name like '%"+name+"%'");
		}
		if(cover_type != ""&&!cover_type.equals("")){
			str.append("and cover_type ='"+ cover_type +"'");
		}
		if(city ==null || city.equals("")){
			
		}else{
			String[] cities = city.split(",");
			for(int a = 0;a <cities.length;a++){
				if(a == 0){
					str.append(" and (region ='"+cities[a]+"'");
				}else{
					str.append(" or region ='"+cities[a]+"'");
				}
				if(a == cities.length-1){
					str.append(")");
				}
				
			}
		}
		Session session = super.getSession();
		Query query = session.createQuery(str.toString());
		// 强制约定如果firstResust == -1,或者maxResult == -1 则返回所有的记录集
		if (start != -1 && size != -1)
			query.setFirstResult(start).setMaxResults(size);
		// 设置where语句中占位符?中的参数
		setQueryParameter(query, null);
		List<Building> qr = new ArrayList<Building>();
		// 设置QueryResult中的结果集
		qr=query.list();
		super.releaseSession(session);
		return qr;
	}
	
	public Building findBuilding(String flow_id) {
		// TODO Auto-generated method stub
		String HQL="from Building where flow_id =?";
		Object[] value={flow_id};
		List<Building> list = this.find(HQL, value);
		Building building = new Building();
		if(list.size()>0){
			building = list.get(0);
		}
		return building;
	}
	public Building findBuildingLikeFlow_id(String flow_id) {
		// TODO Auto-generated method stub
		String HQL="from Building where flow_id like '%"+flow_id+"%'";
		List<Building> list = this.find(HQL, null);
		Building building = null;
		if(list.size()==1){
			building = list.get(0);
		}else if(list.size()>1){
			for (int i = 0; i < list.size(); i++) {
				if(list.get(i).getFlow_id().equals(flow_id)
						||list.get(i).getFlow_id().contains(flow_id+";")){
					building = list.get(i);
				}
			}
		}
		return building;
	}
	public List<Building> findAllBuilding(){
		String hql="from Building";
		return this.find(hql, null);
	}
	public List<Building> findBuildingByCity(String city){
		String hql="from Building where city = ?";
		Object[] value={city};
		return this.find(hql, value);
	}
	public List<Building> findBuildingList(String state) {
		// TODO Auto-generated method stub
		String hql="from Building where state=?";
		Object[] values={state};
		return this.find(hql, values);
	}
	public List<Building> findnoBuildingList(String state) {
		// TODO Auto-generated method stub
		String hql="from Building where state != ?";
		Object[] values={state};
		return this.find(hql, values);
	}

	public Building findBuildingInformation(String building_code) {
		// TODO Auto-generated method stub
		String hql="from Building where building_code=?";
		Object[] values={building_code};
		List<Building> buildings = find(hql, values);
		Building b = null;
		if(buildings.size()>0){
			b = buildings.get(0);
		}
		return b;
	}


	/**
	 * @wu 在新建流程中使用 根据物业点编号删除物业点
	 * @param property_code
	 */
	public void deleteBuildingByCode(String building_code){
		Building building = findBuildingInformation(building_code);
		delete(building);
	}
	
	public void updateBuilding_stateAndFlowId(String code,String state,String flow_id){
		String hql="update from Building set state=? ,flow_id = ? where building_code=?";
		Object[] values={state,flow_id,code};
		this.update(hql, values);
	}
	
	public String findBuilding_code() {
		// TODO Auto-generated method stub
		String hql="from Building where ROWNUN=1 order  by building_code desc ";
		Object[] values={};
		if(this.find(hql, values).size()==0){
			return "1";
		}
		return this.find(hql, values).get(0).getBuilding_code();
	}
	public String findMaxCodeByCity(String city){
		String hql="from Building where city='"+city+"'and ROWNUN=1  order by building_code"+" desc ";
		Object[] values={};
		if(this.find(hql, values).size()==0){
			return "-1";
		}
		return this.find(hql, values).get(0).getBuilding_code();
	}

	public List<Building> fuzzyFindBuilding_name(String building_name) {
		// TODO Auto-generated method stub
		String hql="from Building where state=0 and name like '%"+building_name+"%'";
		return find(hql, null);
	}
	public List<Building> fuzzyFindBuilding_name(String building_name,String city){
		String hql="from Building where state=0 and name like '%"+building_name+"%' and city='"+city+"'";
		return find(hql, null);
	}


	/**
	 * 查询一定范围内的经纬度点
	 */

	public List<Building> findNeighPosition(double longitude,double latitude){
		//先计算查询点的经纬度范围
		double r = 6371;//地球半径千米
		double dis = 0.5;//0.5千米距离
		double dlng =  2*Math.asin(Math.sin(dis/(2*r))/Math.cos(latitude*Math.PI/180));
		dlng = dlng*180/Math.PI;//角度转为弧度
		double dlat = dis/r;
		dlat = dlat*180/Math.PI;		
		double minlat =latitude-dlat;
		double maxlat = latitude+dlat;
		double minlng = longitude -dlng;
		double maxlng = longitude + dlng;
		
		String hql = "from Building where longitude>=? and longitude =<? and latitude>=? latitude=<? and state=0";
		Object[] values = {minlng,maxlng,minlat,maxlat};
		
		List<Building> list = find(hql, values);
		return list;
	}
	/**
	 * 楼宇资料管理的查询(总条数)
	 * @author sunxingyang
	 * @Date 2014年12月1日15:10:29
	 */
	public int findByKeytypeAndCovertype(String city,String region ,String keytype,String keyword,String covertype,String state){
		StringBuffer sql=new StringBuffer();
		if("finish".equals(state)){
			sql.append("select count(1) num from building where state = 0 ");
		}else{
			sql.append("select count(1) num from building where 1=1 ");
		}
		StringBuffer citysql = new StringBuffer();
		if(city != null && !"".equals(city)){
			String[] citys = city.split(",");
			for (int i = 0; i < citys.length; i++) {
				if(i == 0){
					citysql.append(" and (city ='"+citys[i]+"'");
				}else {
					citysql.append(" or city = '"+citys[i]+"'");
				}
				if(i == citys.length-1){
					citysql.append( ")");
				}
			}
		}if(region != null && !"".equals(region)){
			String[] regions = region.split(",");
			for (int i = 0; i < regions.length; i++) {
				if(i == 0){
					citysql.append(" and (region ='"+regions[i]+"'");
				}else {
					citysql.append("or region = '"+regions[i]+"'");
				}
				if(i == regions.length-1){
					citysql.append(")");
				}
			}
		}
		if("所有字段".equals(keytype)){
			sql.append("and (building_code like '%"+keyword+"%' or name like '%"+keyword+"%' or address like '%"+keyword+"%') ");
		}else if("楼宇编号".equals(keytype)){
			sql.append("and building_code like '%"+keyword+"%' ");
		}else if("楼宇名称".equals(keytype)){
			sql.append("and name like '%"+keyword+"%' ");
		}else{
			sql.append("and address like '%"+keyword+"%' ");
		}
		if(!"全部".equals(covertype)){
			sql.append("and cover_type = '"+covertype+"'");
		}
		sql.append(citysql);
		Session session = super.getSession();
		String sqls=sql.toString();
		int num =  (int) session.createSQLQuery(sqls).addScalar("num", Hibernate.INTEGER).uniqueResult();;
		super.releaseSession(session);
		return num;	
	}

	/**
	 * 分页查询楼宇
	 * @author sunxingyang
	 * @date 2015年1月7日18:33:59
	 */
	public List<Building> findByKeytypeAndCovertypePage(String city,String region,
			String keytype, String keyword, String covertype,String state, int start,
			int size) {
		StringBuffer sql=new StringBuffer();
		if("finish".equals(state)){
			sql.append("from Building where state = 0 ");
		}else{
			sql.append("from Building where 1=1 ");
		}
		StringBuffer citysql = new StringBuffer();
		if(city != null && !"".equals(city)){
			String[] citys = city.split(",");
			for (int i = 0; i < citys.length; i++) {
				if(i == 0){
					citysql.append(" and (city ='"+citys[i]+"'");
				}else {
					citysql.append(" or city = '"+citys[i]+"'");
				}
				if(i == citys.length-1){
					citysql.append( ")");
				}
			}
		}if(region != null && !"".equals(region)){
			String[] regions = region.split(",");
			for (int i = 0; i < regions.length; i++) {
				if(i == 0){
					citysql.append(" and (region ='"+regions[i]+"'");
				}else {
					citysql.append("or region = '"+regions[i]+"'");
				}
				if(i == regions.length-1){
					citysql.append(")");
				}
			}
		}
		if("所有字段".equals(keytype)){
			sql.append("and (building_code like '%"+keyword+"%' or name like '%"+keyword+"%' or address like '%"+keyword+"%') ");
		}else if("楼宇编号".equals(keytype)){
			sql.append("and building_code like '%"+keyword+"%' ");
		}else if("楼宇名称".equals(keytype)){
			sql.append("and name like '%"+keyword+"%' ");
		}else{
			sql.append("and address like '%"+keyword+"%' ");
		}
		if(!"全部".equals(covertype)){
			sql.append("and cover_type = '"+covertype+"'");
		}
		sql.append(citysql);
		Session session = super.getSession();
		Query query = session.createQuery(sql.toString());
		// 强制约定如果firstResust == -1,或者maxResult == -1 则返回所有的记录集
		if (start != -1 && size != -1)
			query.setFirstResult(start).setMaxResults(size);
		// 设置where语句中占位符?中的参数
		setQueryParameter(query, null);
		List<Building> qr = new ArrayList<Building>();
		// 设置QueryResult中的结果集
		qr=query.list();
		super.releaseSession(session);
		return qr;
	}

	public List<Building> findByCity(String city) {
		// TODO Auto-generated method stub
		String hql="from Building where city = ?";
		
		Object[] values={city};
		return this.find(hql, values);
	}
	/**
	 * 
	 */
	public List<Task_to_do_list> findTodoBuildingByRole(User user,
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
				" LEFT JOIN building pro on pro.flow_id =ta.EXECUTION_ID_   " + 
				" where p.GROUPID_=" +user.getRole_id()
				+" and pro.city in ("+city+")  and ta.EXECUTION_ID_ like '"+todoName+"%' ";
				if(keytype != null && keytype.equals("bname")){
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
				" LEFT JOIN building b on b.flow_id =ta.EXECUTION_ID_   " + 
				" where p.GROUPID_=" +user.getRole_id()
				+" and b.city in ("+city+")  and ta.EXECUTION_ID_ like '"+todoName+"%' ";
				
				if(keytype != null && keytype.equals("bname")){
					sql += " and b.name like '%" + keyword +"%'";
				}else if(keytype != null && keytype.equals("initiator")){
					sql += " and b.processInitiator like '%" + keyword +"%'";
				}else if(keytype != null && keytype.equals("state")){
					sql += " and ta.NAME_ like '%" + keyword +"%'";
				}
		Session session = super.getSession();
		int num = (int) session.createSQLQuery(sql).addScalar("num",Hibernate.INTEGER).uniqueResult();
		super.releaseSession(session);
		return num;
	}
	public String[] findTodoNum(User user) {
		
		String city = null;
		if(user.getRole_id() == 3 || user.getRole_id() == 4
				|| user.getRole_id() == 6|| user.getRole_id() == 7|| user.getRole_id() == 8)
		{
			city = user.getRule_region();
			city = DBUtil.dealStr(city);
		}else{
			city = "'"+user.getCity()+"'";
		}
		List<ToDoCount> list = new ArrayList<ToDoCount>();
		Session session = super.getSession();
		String sql = "select t.name todoName ,count(1) num from "+
				"(select substring_index(t.EXECUTION_,'.',1) name "+
				"from jbpm4_participation p LEFT JOIN jbpm4_hist_task t on p.TASK_=t.DBID_ "+
				"LEFT JOIN jbpm4_task ta on ta.DBID_=t.DBID_ "+
				"LEFT JOIN jbpm4_hist_var v on ta.PROCINST_= v.HPROCI_ "+
				"left join  jbpm4_hist_detail d on t.DBID_-8= d.HTASK_ "+
				"where p.GROUPID_=" +user.getRole_id()+
				" and v.VALUE_ in (" + city +"))t GROUP BY t.name ";
		//室分小组登录 查看随时驳回待办任务的个数
		if(user.getRole_id()==10){
			sql = "select t.name todoName ,count(1) num from "
					+" (select substring_index(t.EXECUTION_,'.',1) name "
					+" from jbpm4_participation p LEFT JOIN jbpm4_hist_task t on p.TASK_=t.DBID_ "
					+" LEFT JOIN jbpm4_task ta on ta.DBID_=t.DBID_ "
					+" LEFT JOIN jbpm4_hist_var v on ta.PROCINST_= v.HPROCI_ "
					+" left join  jbpm4_hist_detail d on t.DBID_-8= d.HTASK_ "
					+" where NAME_ in ('上传单验报告','上传验收报告','完成') "
					+" and v.VALUE_ in ("+city+")" 
					+" )t GROUP BY t.name";
		}
		list = session.createSQLQuery(sql).addScalar("todoName").addScalar("num",Hibernate.INTEGER)
				.setResultTransformer(Transformers.aliasToBean(ToDoCount.class)).list();
		super.releaseSession(session);
		
		String []con = {"0","0","0","0","0","0","0","0","0","0"};
		for(ToDoCount t : list){
			String todoName = t.getTodoName();
			if(todoName.startsWith("newProperty"))
				con[0] = String.valueOf(t.getNum());
			else if(todoName.startsWith("editProperty"))
				con[1] = String.valueOf(t.getNum());
			else if(todoName.startsWith("newSite"))
				con[2] = String.valueOf(t.getNum());
			else if(todoName.startsWith("editIndoorSite"))
				con[3] = String.valueOf(t.getNum());
			else if(todoName.startsWith("deleteSite"))
				con[4] = String.valueOf(t.getNum());
			else if(todoName.startsWith("testIndoorSite"))
				con[5] = String.valueOf(t.getNum());
			else if(todoName.startsWith("newBuilding")){
				con[6] = String.valueOf(t.getNum());
			}else if(todoName.startsWith("renovationSite")){
				con[7] = String.valueOf(t.getNum());
			}else if(todoName.startsWith("renovationSiteCity")){
				con[8] = String.valueOf(t.getNum());
			}else if(todoName.startsWith("dateReplace")){
				con[9] = String.valueOf(t.getNum());
			}
		}
		return con;
	}
	public List<CompleteTaskHistory> findHavetodoPropertyByUser(User user,String todoName){
		List<CompleteTaskHistory> list = new ArrayList<CompleteTaskHistory>();
		Session session = super.getSession();
		String sql = "select c.flow_id executionId,COUNT(hist.STATE_) revokeFlag revokeFlag,p.name construction_name,''name,procinst.END_ completionTime,'流程已结束' comment,u.`name` flowInitiator, " +
				"p.property_code construction_code "+
				"from complete_task c "+
				"INNER JOIN jbpm4_hist_procinst procinst on c.flow_id=procinst.ID_ " +
				"INNER JOIN jbpm4_hist_var v on v.VARNAME_='starter_id' and v.PROCINSTID_=c.flow_id " +
				"INNER JOIN property p on p.flow_id REGEXP c.flow_id " +
				"INNER JOIN user u on v.VALUE_=u.account_name "
				+ "LEFT JOIN jbpm4_hist_task hist ON c.flow_id = hist.EXECUTION_ "+
				"where procinst.STATE_='ended' and c.user_id='"+user.getAccount_name()+"' and c.flow_id like '"+todoName+"%'  GROUP BY hist.EXECUTION_ " +
				"union "+
				"select c.flow_id executionId,COUNT(hist.STATE_) revokeFlag,p.name construction_name,task.ACTIVITY_NAME_ name,''completionTime, '正在执行中' comment,u.`name` flowInitiator, "+
				"p.property_code construction_code "+
				"from complete_task c "+
				"INNER JOIN jbpm4_hist_var v on v.VARNAME_='starter_id' and v.PROCINSTID_=c.flow_id "+
				"INNER JOIN jbpm4_task task  on v.HPROCI_=task.PROCINST_ "+
				"INNER JOIN property p on p.flow_id REGEXP c.flow_id "
				+ "LEFT JOIN jbpm4_hist_task hist ON c.flow_id = hist.EXECUTION_ "+
				"INNER JOIN user u on v.VALUE_=u.account_name "+
				"where c.user_id='"+user.getAccount_name()+"' and c.flow_id like '"+todoName+"%'  GROUP BY hist.EXECUTION_ ";
	     list = (List<CompleteTaskHistory>)session.createSQLQuery(sql).addScalar("construction_name").addScalar("revokeFlag",Hibernate.INTEGER).addScalar("executionId")
	    		 .addScalar("flowInitiator").addScalar("name").addScalar("completionTime",Hibernate.STRING)
	    		 .addScalar("construction_code").addScalar("comment")
	    		 .setResultTransformer(Transformers.aliasToBean(CompleteTaskHistory.class)).list();
	     super.releaseSession(session);
	     return list;
	}

	/**
	 * @authour 吴兵 根据子物业点编号 查询楼宇信息
	 * @Date:2014年11月26日
	 * @param child_property_code
	 * @return
	 */
	public List<Building> findByChild_property_code(String child_property_code){
		String hql = "from Building where child_property_code = ?";
		Object[] values={child_property_code};
		return find(hql, values);
	}
	/**
	 * @authour 吴兵 根据物业点编号 查询楼宇信息
	 * @Date:2014年11月26日
	 * @param child_property_code
	 * @return
	 */
	public List<Building> findByProperty_code(String property_code){
		String hql = "from Building where involved_property_code = ?";
		Object[] values={property_code};
		return find(hql, values);
	}

	/**
	 * @author sunxingyang 根据物业点编号查询没被子物业点占用的楼宇
	 */
	public List<Building> findByPropertyCode(String involved_property_code) {
		String hql = "from Building where involved_property_code = ? and (child_property_code is null or child_property_code='')";
		Object[] values={involved_property_code};
		return find(hql, values);
	}

	/**
	 * @authour sunxingyang 根据物业点编号 模糊查询楼宇信息
	 * @Date:2015年1月10日10:32:38
	 * @param 
	 * @return
	 */
	public List<Building> findByProperty_code2(String property_code){
		String hql = "from Building where involved_property_code like '%"+property_code+"'";
//		Object[] values={property_code};
		return find(hql, null);
	}

	/**
	 * @author sunxingyang 根据物业点编号模糊查询没被子物业点占用的楼宇
	 */
	public List<Building> findByPropertyCode2(String involved_property_code) {
		String hql = "from Building where involved_property_code like '%"+involved_property_code+"' and (child_property_code is null or child_property_code='')";
//		Object[] values={involved_property_code};
		return find(hql, null);
	}

	public List<Building> findBuilding(){
		String hql = "from Building";
		Object[] values={};
		return find(hql, values);
	}

	/**
	 * 已办总条数
	 */
	public int countBuildingHaveTodo(String user,String flow,String keytype,String keyword) {
		Session session = super.getSession();
		String str = "";
		if(keyword == null || "".equals(keyword)){
			
		}else if("buildingName".equals(keytype)){
			str = " and b.name like '%"+keyword+"%' ";
		}else if("initiator".equals(keytype)){
			str = " and u.name like '%"+keyword+"%' ";
		}else if("state".equals(keytype)){
			str = " and task.ACTIVITY_NAME_ like '%"+keyword+"%' ";
		}
		String sql ="select count(*) num from (SELECT count(*) FROM jbpm4_hist_task ht  \r\n" + 
				"INNER JOIN building b ON b.flow_id = ht.EXECUTION_  \r\n" + 
				"LEFT JOIN jbpm4_hist_var v on v.VARNAME_='starter_id' and v.PROCINSTID_=b.flow_id  \r\n" + 
				"LEFT JOIN user u ON u.account_name = v.VALUE_  \r\n" + 
				"WHERE ASSIGNEE_ = '"+user+"' AND EXECUTION_ like '"+flow+"%' "+str+" GROUP BY EXECUTION_) as t4 ";
		int num =  (int) session.createSQLQuery(sql).addScalar("num", Hibernate.INTEGER).uniqueResult();;
		super.releaseSession(session);
		return num;
	}

	public List<CompleteTaskHistory> findBuildingHaveTodo(String user,String flow,int pageNow, int pageSize,String keytype,String keyword) {
		List<CompleteTaskHistory> list = new ArrayList<>();
		Session session = super.getSession();
		String str = "";
		if(keyword == null || "".equals(keyword)){
			
		}else if("buildingName".equals(keytype)){
			str = " and b.name like '%"+keyword+"%' ";
		}else if("initiator".equals(keytype)){
			str = " and b.processInitiator like '%"+keyword+"%' ";
		}else if("state".equals(keytype)){
			str = " and task.ACTIVITY_NAME_ like '%"+keyword+"%' ";
		}
		
		String sql ="SELECT b.id,b.name construction_name,b.flow_id executionId,b.city city, \r\n" + 
				"b.building_code construction_code,IFNULL(ht.revokeFlag,0) revokeFlag, \r\n" + 
				"IFNULL(task.ACTIVITY_NAME_,'流程已结束') name\r\n" + 
				",b.processInitiator flowInitiator, \r\n" + 
				"hp.END_ completionTime,   \r\n" + 
				"IF(task.ACTIVITY_NAME_ IS NULL,'流程已结束','正在执行中') comment FROM  \r\n" + 
				"(\r\n" + 
				"	SELECT  EXECUTION_ FROM jbpm4_hist_task WHERE  EXECUTION_ LIKE '"+flow+"%' AND ASSIGNEE_ = '"+user+"' GROUP BY EXECUTION_ \r\n" + 
				") flow LEFT JOIN building b ON flow.EXECUTION_ = b.flow_id  \r\n" + 
				"LEFT JOIN jbpm4_task task ON task.EXECUTION_ID_ = b.flow_id\r\n" + 
				"LEFT JOIN jbpm4_hist_procinst hp ON hp.ID_ = b.flow_id\r\n" + 
				"LEFT JOIN (\r\n" + 
				"	SELECT count(STATE_) revokeFlag, EXECUTION_ FROM jbpm4_hist_task WHERE  EXECUTION_ LIKE '"+flow+"%'  GROUP BY EXECUTION_ \r\n" + 
				") ht ON ht.EXECUTION_ = b.flow_id  WHERE b.flow_id LIKE '"+flow+"%' "+str
				+" ORDER BY b.id desc limit "+pageNow+","+pageSize;
		list = session.createSQLQuery(sql).addScalar("executionId").addScalar("revokeFlag",Hibernate.INTEGER).addScalar("name").addScalar("comment")
		.addScalar("flowInitiator").addScalar("construction_name").addScalar("construction_code").addScalar("city")
		.addScalar("completionTime",Hibernate.STRING)
		.setResultTransformer(Transformers.aliasToBean(CompleteTaskHistory.class)).list();
		super.releaseSession(session);
		return list;
	}

	

}
