package com.xyuan.ngtest.DAO;

import java.sql.Timestamp;
import org.apache.ibatis.annotations.Param;

public interface ServerDAO {
	public int getMaxRound(@Param("projectname")String proName);
	
	public void updateRunReports(@Param("round")int round,@Param("apitotal")int apitotal,
			@Param("success")int success,@Param("fail")int fail,@Param("notrun")int notrun,@Param("projectname")String projectname);
	
	public void newRunReports(@Param("projectname")String projectname,@Param("suitename")String suitename,@Param("round")int round);
	
	public void newReports(@Param("round")int round,@Param("apitype")String apitype,@Param("apiname")String apiname,
			@Param("message")String message,@Param("starttime")Timestamp starttime,@Param("endtime")Timestamp endtime,
			@Param("exectime")String exectime,@Param("responsecode")String responsecode,@Param("status")String status,
			@Param("testcasename")String testcasename,@Param("projectname")String projectname,@Param("requesturl")String requesturl,
			@Param("requestdata")String requestdata,@Param("responsedata")String responsedata);
	
	public void newLog(@Param("round")int round,@Param("projectname")String projectname,@Param("logInfo")String logInfo);
}
