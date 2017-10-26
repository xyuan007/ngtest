package com.xyuan.ngtest.ps.test;

import java.lang.reflect.Method;

import org.dom4j.Element;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.xyuan.ngtest.Helper.DataHelper;
import com.xyuan.ngtest.MyAssert.MyAssert;
import com.xyuan.ngtest.Util.FuncUtil;
import com.xyuan.ngtest.Util.MyXMLUtil;
import com.xyuan.ngtest.process.ExecuteFactory;

public class manfund {
	private String className = FuncUtil.getRearName(this.getClass().getName());
	private String methodName;
	
	//拿数据
	@DataProvider(name = "data")
	public Object[][] getTestData(Method method,ITestContext context) throws Exception {
		String testcontent = context.getCurrentXmlTest().getParameter("testcontent");
		
		methodName = method.getName();
		DataHelper dh = new DataHelper(className);
		
		return dh.getDataArray(methodName, testcontent);
	}
	
	//执行请求与验证的过程
	private void TestAndAssert(Element testcase) throws Exception{
		String response = (new ExecuteFactory()).createExecute(testcase,methodName,testcase.attributeValue("name"));
		String expected = (new MyXMLUtil("assert\\manfund.xml")).getAssertString(methodName,testcase.attributeValue("name"));
		String mode = (new MyXMLUtil("assert\\manfund.xml")).getAssertMode(methodName,testcase.attributeValue("name"));
		
		MyAssert.asserting(response, expected, mode);
	}
	
	//法律诉讼_法院公告list
	@Test(dataProvider = "data")
	public void fund_baseinfo(Element testcase) throws Exception{
		TestAndAssert(testcase);
	}
	
	//法律诉讼_失信被执行人detail
	@Test(dataProvider = "data")
	public void fund_dailyperf(Element testcase) throws Exception{
		TestAndAssert(testcase);
	}
	
	//法律诉讼_失信被执行人list
	@Test(dataProvider = "data")
	public void fund_detailinfo(Element testcase) throws Exception{
		TestAndAssert(testcase);
	}
	
	//法律诉讼_开庭公告detail
	@Test(dataProvider = "data")
	public void fund_factiorinfo(Element testcase) throws Exception{
		TestAndAssert(testcase);
	}
	
	//法律诉讼_开庭公告list
	@Test(dataProvider = "data")
	public void fund_highposition(Element testcase) throws Exception{
		TestAndAssert(testcase);
	}
	
	//证监会处罚_市场禁入人detail
	@Test(dataProvider = "data")
	public void fund_navdata(Element testcase) throws Exception{
		TestAndAssert(testcase);
	}
	
	//证监会处罚_市场禁入人list
	@Test(dataProvider = "data")
	public void fund_newsinfo(Element testcase) throws Exception{
		TestAndAssert(testcase);
	}
	
	//证监会处罚_行政处罚公司detail
	@Test(dataProvider = "data")
	public void fund_search(Element testcase) throws Exception{
		TestAndAssert(testcase);
	}
	
	//证监会处罚_行政处罚公司list
	@Test(dataProvider = "data")
	public void fund_tree(Element testcase) throws Exception{
		TestAndAssert(testcase);
	}
	
	//基协异常_信息list
	@Test(dataProvider = "data")
	public void man_baseinfo(Element testcase) throws Exception{
		TestAndAssert(testcase);
	}
	
	//工商异常_经营异常listApi
	@Test(dataProvider = "data")
	public void man_fundlist(Element testcase) throws Exception{
		TestAndAssert(testcase);
	}
	
	//工商异常_行政处罚listApi
	@Test(dataProvider = "data")
	public void man_newsinfo(Element testcase) throws Exception{
		TestAndAssert(testcase);
	}
	
	
	@Test(dataProvider = "data")
	public void man_search(Element testcase) throws Exception{
		TestAndAssert(testcase);
	}
	
	
	@Test(dataProvider = "data")
	public void man_tree(Element testcase) throws Exception{
		TestAndAssert(testcase);
	}
}
