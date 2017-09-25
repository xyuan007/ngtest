package com.xyuan.ngtest.Helper;

import java.io.FileReader;
import java.io.Reader;
import java.sql.Timestamp;
import java.util.Date;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.xyuan.ngtest.DAO.ServerDAO;
import com.xyuan.ngtest.Util.MyLog;
import com.xyuan.ngtest.Util.ProjectPropUtil;

public class DatabaseHelper {
	public static MyLog loger = MyLog.getLoger();
	private static SqlSession session = null;
	private static ServerDAO serverDao = null;
	private static SqlSessionFactory sessionFactory = null;
	
	static{
		String resource = System.getProperty("user.dir") + "\\config\\config.xml";
		Reader is;
		try {
			is = new FileReader(resource);
	        sessionFactory = new SqlSessionFactoryBuilder().build(is);
	        session = sessionFactory.openSession();
	        serverDao = session.getMapper(ServerDAO.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static synchronized void reSession(){
		session.close();
        session = sessionFactory.openSession();
        serverDao = session.getMapper(ServerDAO.class);
	}
	
	public static synchronized int getMaxRound(String proName){
		int res = serverDao.getMaxRound(proName);
		session.commit();
		reSession();
		return res;
	}
	
	public  static synchronized void updateRunReports(int round,int apitotal,int success,int fail,int notrun,String projectname){
		serverDao.updateRunReports(round, apitotal, success, fail, notrun,projectname);
		session.commit();
	}
	
	public static synchronized void newRunReports(String suitename,int round){
		serverDao.newRunReports(ProjectPropUtil.getProjectName(),suitename,round);
		session.commit();
	}
	
	public static synchronized void newReports(int round,String apitype,String apiname,String message,Timestamp starttime,
			Timestamp endtime,String exectime,String responsecode,String status,String testcasename,
			String projectname,String requesturl,String requestdata,String responsedata){
		serverDao.newReports(round, apitype, apiname, message, starttime, endtime, 
				exectime, responsecode, status,testcasename,projectname,
				requesturl,requestdata,responsedata);
		session.commit();
	}
	
	public static synchronized void newLog(int round,String projectName,String logInfo){
		serverDao.newLog(round, projectName, logInfo);
		session.commit();
	}
	
}
