package com.xyuan.ngtest.ps.comp;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import com.xyuan.ngtest.Helper.DatabaseHelper;
import com.xyuan.ngtest.Helper.PublicDataHelper;
import com.xyuan.ngtest.Util.FuncUtil;
//import com.xyuan.ngtest.Util.JsonUtil;
import com.xyuan.ngtest.Util.ProjectPropUtil;
import com.xyuan.ngtest.process.ExecuteFactory;

public class SuiteInit{
	private String projectName = "ps";
	
	@BeforeSuite
	public void suiteInit(){
		ProjectPropUtil.setPojectName(projectName);
		int round = DatabaseHelper.getMaxRound(projectName) + 1;
		DatabaseHelper.newRunReports(Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getSuite().getName(),round);
		PublicDataHelper.initRoundData(round);
		
	}
	
	@AfterSuite
	public void suiteGC(){
		int round = PublicDataHelper.getRound().getRound();
		int apitotal = PublicDataHelper.getRound().getApitotal();
		int success = PublicDataHelper.getRound().getSuccess();
		int fail = PublicDataHelper.getRound().getFail();
		int notrun = PublicDataHelper.getRound().getNotrun();
		DatabaseHelper.updateRunReports(round, apitotal, success, fail, notrun, projectName);
	}
	
	//TEST前的初始化，登录，设置TOKEN
	@BeforeTest
	public void login() throws Exception{
		//预登录后登录
		PublicDataHelper.newPublicData(Thread.currentThread().getId());
		PublicDataHelper.getIns(Thread.currentThread().getId()).initOutputData();
		
		String response = (new ExecuteFactory()).preloginExecute();
//		PDHelper.getIns(Thread.currentThread().getId()).getOutput().setValue(ProjectPropUtil.getTokenStr(), "MDY0NDQ0NTBhZTZmZmVlMTY5Yjg2YWI2ODgyMTY4OGI=");

//		assertThat(JsonUtil.getToken(response),notNullValue());
//		FuncUtil.newDetailReport(null, null, "success");
	}
}
