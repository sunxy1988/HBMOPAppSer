package com.hbmop.app.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;

import com.hbmop.app.model.CompleteTaskHistory;
import com.hbmop.app.model.Property;
import com.hbmop.app.model.User;
import com.hbmop.app.monitor.Task_to_do_list;
import com.hbmop.app.util.DBUtil;
import com.hbmop.app.vo.ToDoCount;
import com.hbmop.app.vo.toDataManagement;


public class PropertyDAO extends DAOSupport<Property>{

	
	
	
	public List<Property> findAllProperty(){
		String hql="from Property";
		return this.find(hql, null);
	}
	
	public List<Property> findPropertyByCity(String city){
		String hql="from Property where city = ?";
		Object[] value={city};
		return this.find(hql, value);
	}
	
	public List<Property> findPropertyList(String state) {
		// TODO Auto-generated method stub
		String hql="from Property where state=?";
		Object[] values={state};
		return this.find(hql, values);
	}
	
	public List<Property> findnoPropertyList(String state) {
		// TODO Auto-generated method stub
		String hql="from Property where state != ?";
		Object[] values={state};
		return this.find(hql, values);
	}

	
	public Property findPropertyInformation(String property_code) {
		// TODO Auto-generated method stub
		String hql="from Property where property_code=?";
		Object[] values={property_code};
		List<Property> properties = find(hql, values);
		Property p = null;
		if(properties.size()>0){
			p = properties.get(0);
		}
		return p;
	}


	
	public String findProperty_code() {
		// TODO Auto-generated method stub
		String hql="from Property where ROWNUN=1  order by property_code desc ";
		Object[] values={};
		if(this.find(hql, values).size()==0){
			return "1";
		}
		return this.find(hql, values).get(0).getProperty_code();
	}
	public String findMaxCodeByCity(String city){
		String hql="from Property where city='"+city+"'  and state != 2 and ROWNUN=1  order by property_code"+" desc";
		Object[] values={};
		if(this.find(hql, values).size()==0){
			return "-1";
		}
		return this.find(hql, values).get(0).getProperty_code();
	}

	

	
	/**
	 * 物业点资料管理的查询总数
	 */
	public int findByKeytypeAndCovertypeCount(String area ,String keyword,String covertype){
		String cityhql = " select count(*) num from property o where o.state != 2 AND o.state IS NOT NULL";
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
			cityhql += " and (property_code like '%"+keyword+"%' or name like '%"+keyword+"%' or address like '%"+keyword+"%')";
		}
		Session session = super.getSession();
		int num =  (int) session.createSQLQuery(cityhql).addScalar("num", Hibernate.INTEGER).uniqueResult();;
		return num;
	}
	/**
	 * 物业点资料管理的查询分页
	 */
	public List<Property> findByKeytypeAndCovertypePage(String area ,String keyword,String covertype,int recordCount, int currentPage){
		int size = recordCount/10;//总条数/每页显示的条数=总页数 
        int mod = recordCount % 10;//最后一页的条数 
		String cityhql="from Property where state != 2 AND state IS NOT NULL";
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
			cityhql += " and(property_code like '%"+keyword+"%' or name like '%"+keyword+"%' or address like '%"+keyword+"%')";
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

	

	
	public List<Property> findByCity(String city) {
		// TODO Auto-generated method stub
		String hql="from Property where city = ?";
		
		Object[] values={city};
		return this.find(hql, values);
	}
	
	
	
	/**
	 * @authour 吴兵 查询物业点的所有编号
	 * @Date:2014年12月5日
	 * @return
	 */
	
	public List<String> findPropertyCodes(){
		String sql = "select property_code from property ";
		Session session = super.getSession();
		List<String> codes = session.createSQLQuery(sql).addScalar("property_code").list();
		return codes;
	}

}
