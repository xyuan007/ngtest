package com.xyuan.ngtest.jx.test.report;

import java.lang.reflect.Method;

import org.dom4j.Element;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.xyuan.ngtest.Helper.DataHelper;
import com.xyuan.ngtest.Helper.PublicDataHelper;
import com.xyuan.ngtest.Util.FuncUtil;
import com.xyuan.ngtest.Util.JsonUtil;
import com.xyuan.ngtest.Util.MyXMLUtil;
import com.xyuan.ngtest.process.ExecuteFactory;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class get_achieve_attribute {
	private String res;
	private String className = FuncUtil.getRearName(this.getClass().getName());
	
	@DataProvider(name = "data")
	public Object[][] getTestData() throws Exception {
		DataHelper dh = new DataHelper("report",className);
		return dh.getDataArray();
	}
	
	@Test(dataProvider = "data")
	public void get_achieve_attribute(Element testcase,Method method) throws Exception{
		String response = (new ExecuteFactory()).createExecute(testcase,method.getName(),testcase.attributeValue("name"));
		
		//保存GUID
		String result = JsonUtil.getResult(response);
		PublicDataHelper.getIns(Thread.currentThread().getId()).getOutput().setValue("guid", result);
		PublicDataHelper.getIns(Thread.currentThread().getId()).getCasedata().setCycleFlag("true");
		
		assertThat(result,notNullValue());
		
//		//多次循环直到拿到结果
		while(PublicDataHelper.getIns(Thread.currentThread().getId()).getCasedata().getCycleFlag().equals("true"))
			this.res = get_achieve_attribute_cycle(testcase.attributeValue("name"));
		String expected = (new MyXMLUtil("assert\\report.xml")).getAssertString(method.getName(),testcase.attributeValue("name"));
		
		assertThat(FuncUtil.chopString(this.res),equalTo(FuncUtil.chopString(expected)));
	}
	
	private String get_achieve_attribute_cycle(String testcasename) throws Exception{
		String method = Thread.currentThread().getStackTrace()[1].getMethodName();
		Element cur = (Element)(new MyXMLUtil("data\\cycle.xml")).getDataElement(testcasename);
		String response = (new ExecuteFactory()).createExecute(cur,method,testcasename);
		
		FuncUtil.AssertForTimeout(response, method);
		return response;
	}
}
