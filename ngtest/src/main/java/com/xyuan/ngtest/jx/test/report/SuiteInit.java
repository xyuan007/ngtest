package com.xyuan.ngtest.jx.test.report;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;

import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.xyuan.ngtest.Helper.DatabaseHelper;
import com.xyuan.ngtest.Helper.PublicDataHelper;
import com.xyuan.ngtest.Util.FuncUtil;
import com.xyuan.ngtest.Util.JsonUtil;
import com.xyuan.ngtest.Util.ProjectPropUtil;
import com.xyuan.ngtest.process.ExecuteFactory;

public class SuiteInit{
	@BeforeSuite
	public void suiteInit(){
		ProjectPropUtil.setPojectName("jx");
		int round = DatabaseHelper.getMaxRound("jx") + 1;
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
		DatabaseHelper.updateRunReports(round, apitotal, success, fail, notrun, "jx");
	}
	
	//TEST前的初始化，登录，设置TOKEN
	@BeforeTest
	public void login() throws Exception{
		PublicDataHelper.newPublicData(Thread.currentThread().getId());
		PublicDataHelper.getIns(Thread.currentThread().getId()).initOutputData();
		String response = (new ExecuteFactory()).loginExecute();
		PublicDataHelper.getIns(Thread.currentThread().getId()).getOutput().setValue("token", JsonUtil.getToken(response));

		assertThat(JsonUtil.getToken(response),notNullValue());
		FuncUtil.newDetailReport(null, null, "success");
	}
}
