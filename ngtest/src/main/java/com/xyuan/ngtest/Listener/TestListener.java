package com.xyuan.ngtest.Listener;

import java.sql.Timestamp;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.xyuan.ngtest.Helper.DatabaseHelper;
import com.xyuan.ngtest.Helper.PublicDataHelper;
import com.xyuan.ngtest.Util.FuncUtil;
import com.xyuan.ngtest.Util.MyLog;
import com.xyuan.ngtest.Util.ProjectPropUtil;

public class TestListener implements ITestListener  {

	public void onTestStart(ITestResult result) {
	}

	public synchronized void onTestSuccess(ITestResult result) {
		try {
			FuncUtil.newDetailReport(new Timestamp( result.getStartMillis()),new Timestamp( result.getEndMillis()),"success");
			newLog();
		} catch (Exception e) {
		}finally{
			PublicDataHelper.getRound().incSuccess();
		}
	}

	public synchronized void onTestFailure(ITestResult result) {
		try {
			FuncUtil.newDetailReport(new Timestamp( result.getStartMillis()),new Timestamp( result.getEndMillis()),"fail");
			newLog();
		} catch (Exception e) {
		}finally{
			PublicDataHelper.getRound().incFail();
		}
	}

	public void onTestSkipped(ITestResult result) {

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext context) {
		try{
		}
		catch(Exception ex){ex.printStackTrace();}
	}

	public void onFinish(ITestContext context) {
		try{
		}
		catch(Exception ex){ex.printStackTrace();}
	}
	
	private void newLog(){
		int round = PublicDataHelper.getIns(Thread.currentThread().getId()).getCasedata().getRound();
		DatabaseHelper.newLog(round, ProjectPropUtil.getProjectName(), MyLog.getMsg());
	}
}
