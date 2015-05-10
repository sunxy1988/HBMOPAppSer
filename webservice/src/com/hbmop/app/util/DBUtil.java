package com.hbmop.app.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DBUtil {
	//计算两个集合的差集
		public static List diff(List ls, List ls2) {   
	        List list = new ArrayList(Arrays.asList(new Object[ls.size()]));   
	        Collections.copy(list, ls);   
	        list.removeAll(ls2);   
	        return list;   
	    }	
	/**
	 * 计算地图上两点之间的距离
	 * @param longitude
	 * @param latitude
	 * @param long2
	 * @param lat2
	 * @return
	 */
	public Double Distance(double longitude, double latitude, double long2,  
			double lat2) {  
	    double a, b, R;  
	    R = 6371; // 地球半径  
	    latitude = latitude * Math.PI / 180.0;  
	    lat2 = lat2 * Math.PI / 180.0;  
	    a = latitude - lat2;  
	    b = (longitude - long2) * Math.PI / 180.0;  
	    double d;  
	    double sa2, sb2;  
	    sa2 = Math.sin(a / 2.0);  
	    sb2 = Math.sin(b / 2.0);  
	    d = 2 * R * Math.asin(
	    		Math.sqrt(sa2 * sa2 + Math.cos(latitude)  
	                    * Math.cos(lat2) * sb2 * sb2));  
	    return d;
	} 
	
	//处理以,隔开的字符串，为‘,'
	public static String dealStr(String str){
		if(str.lastIndexOf(";")== (str.length()-1)){
			str = str.substring(0, str.length()-1);
		}
		String re = "'"+str+"'";
		re=re.replaceAll(";", "','");
		return re;
	}
	
	
	public static void main(String[] args) {
		String string = dealStr("武汉;荆州;黄石;鄂州;孝感;荆门;天门;"
				+ "襄阳;宜昌;咸宁;随州;潜江;恩施;黄冈;江汉;十堰;");
		System.out.println(string);
	}
}
