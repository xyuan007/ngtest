package com.xyuan.ngtest.Util;

import java.io.FileInputStream;
import java.util.Properties;

public class ProjectPropUtil 
{
	public static  void main(String[] args) throws Exception{
		System.out.println(getProp("RunMode"));
	}
	
	static private volatile Properties prop = null;
	static private volatile String projectName = null;
	
	public static void setPojectName(String curProject){
		prop = new Properties();
    	FileInputStream fis;
		try {
			fis = new FileInputStream("config\\" + curProject + ".properites");
	    	prop.load(fis);
	    	projectName = curProject;
		} catch (Exception e) {
		}//属性文件流     
	}
	
	//拿配置文件 
    public static String getProp(String key){
    	return prop.getProperty(key);
    }
    
    public static String getProjectName(){
		return projectName;
    }
    
    public static String getHttpProxy(){
    	return getProp("HttpProxy");
    }
    
    public static String getProxyIP(){
    	return getProp("ProxyIP");
    }
    
    public static String getRunMode(){
    	return getProp("RunMode");
    }
    
    public static String getTestFile(){
    	return getProp("TestFile");
    }
    
    public static String getRunClass(){
    	return getProp("RunClass");
    }
    
    public static String getSocketIP(){
    	return getProp("SocketIP");
    }
    
    public static String getFuncClass(){
    	return getProp("FuncClass");
    }
    
    public static String getCharSet(){
    	return getProp("CharSet");
    }
    
    public static String getTokenStr(){
    	return getProp("Token");
    }
    
    
    public static int getTimeOut(){
    	return Integer.parseInt(getProp("TimeOut"));
    }
    
    public static int getWaitTime(){
    	return Integer.parseInt(getProp("WaitTime"));
    }    
    
    public static int getSocketPort(){
    	return Integer.parseInt(getProp("SocketPort"));
    }
    
    public static int getProxyPort(){
    	return Integer.parseInt( getProp("ProxyPort"));
    }

}
