package com.demo;

import net.sf.json.JSONObject;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

import com.hbmop.app.webservice.BuildingWebService;



public class T implements Runnable{
	
	private static T t;
	
	private T(){}
	
	public static T getT(){
		if(t==null){
			t = new T();
		}
		return t;
			
	}

	public static void main(String[] args) {
//		bySpring();
//		//byClient();
//		System.out.println(System.currentTimeMillis());
		T myRunnable = new T();         
        Thread runnable = new Thread(myRunnable);  
        runnable.start();  
		
		/*T t = new T();
		int[] i ={1,22,3,4,56,};
		int[] si = t.getNew(i);
		for (int j : si) {
			System.out.println(j);
		}*/
	}
	
	public int[] getNew(int[] ss){
		for(int i=0;i<ss.length;i++){
			for(int j=0;j<i;j++){
				if(ss[i]<ss[j]){
					int index = ss[i];
					ss[i]=ss[j];
					ss[j]=index;
				}
			}
		}
		return ss;
	}
	public void bySpring() {
		
		this.run();
		
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();  
	    //注册WebService接口  
	    factory.setServiceClass(BuildingWebService.class);  
	    //设置WebService地址  
	    factory.setAddress("http://localhost:8080/webservice/BuildingService");       
	    BuildingWebService iHelloWorld = (BuildingWebService)factory.create();  
	    System.out.println("invoke webservice...");  

	   iHelloWorld.findBuilding("武汉", "", "", 1);
//	     String str=object.toString();
//	     System.out.println(str);
	     try {
			Thread.sleep(7000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/*public static void byClient() {
		String addr = "http://119.2.29.122:8012/SHOW/services/zyhx";
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();  
        factory.setAddress(addr);  
        factory.setServiceClass(NetWebServiceImpl.class);  
  
        NetWebServiceImpl net = (NetWebServiceImpl) factory.create();  
        System.out.println(net.saveNet("1", "2", "3"));
	}*/
}
