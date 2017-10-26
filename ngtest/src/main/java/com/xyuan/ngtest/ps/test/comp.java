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

public class comp {
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
		String expected = (new MyXMLUtil("assert\\comp.xml")).getAssertString(methodName,testcase.attributeValue("name"));
		String mode = (new MyXMLUtil("assert\\comp.xml")).getAssertMode(methodName,testcase.attributeValue("name"));
		
		MyAssert.asserting(response, expected, mode);
	}
	
	//法律诉讼_法院公告list
	@Test(dataProvider = "data")
	public void lawcourtnoticelist(Element testcase) throws Exception{
		TestAndAssert(testcase);
	}
	
	//法律诉讼_失信被执行人detail
	@Test(dataProvider = "data")
	public void lawexecdetail(Element testcase) throws Exception{
		TestAndAssert(testcase);
	}
	
	//法律诉讼_失信被执行人list
	@Test(dataProvider = "data")
	public void lawexecutedlist(Element testcase) throws Exception{
		TestAndAssert(testcase);
	}
	
	//法律诉讼_开庭公告detail
	@Test(dataProvider = "data")
	public void lawholdcourtinfodetail(Element testcase) throws Exception{
		TestAndAssert(testcase);
	}
	
	//法律诉讼_开庭公告list
	@Test(dataProvider = "data")
	public void lawholdcourtinfolist(Element testcase) throws Exception{
		TestAndAssert(testcase);
	}
	
	//证监会处罚_市场禁入人detail
	@Test(dataProvider = "data")
	public void manadministrativesanctiondetail(Element testcase) throws Exception{
		TestAndAssert(testcase);
	}
	
	//证监会处罚_市场禁入人list
	@Test(dataProvider = "data")
	public void manadministrativesanctionlist(Element testcase) throws Exception{
		TestAndAssert(testcase);
	}
	
	//证监会处罚_行政处罚公司detail
	@Test(dataProvider = "data")
	public void repadministrativesanctiondetail(Element testcase) throws Exception{
		TestAndAssert(testcase);
	}
	
	//证监会处罚_行政处罚公司list
	@Test(dataProvider = "data")
	public void repadministrativesanctionlist(Element testcase) throws Exception{
		TestAndAssert(testcase);
	}
	
	//基协异常_信息list
	@Test(dataProvider = "data")
	public void compassociationinfolllist(Element testcase) throws Exception{
		TestAndAssert(testcase);
	}
	
	//工商异常_经营异常listApi
	@Test(dataProvider = "data")
	public void abnormaloperate(Element testcase) throws Exception{
		TestAndAssert(testcase);
	}
	
	//工商异常_行政处罚listApi
	@Test(dataProvider = "data")
	public void adminsanction(Element testcase) throws Exception{
		TestAndAssert(testcase);
	}
	
	
	//模糊查询
	@Test(dataProvider = "data")
	public void all_search(Element testcase) throws Exception{
		TestAndAssert(testcase);
	}
	
	//尽职调查列表
	@Test(dataProvider = "data")
	public void comp_askcontext(Element testcase) throws Exception{
		TestAndAssert(testcase);
	}
	
	//尽职调查详情
	@Test(dataProvider = "data")
	public void comp_askcontextdetail(Element testcase) throws Exception{
		TestAndAssert(testcase);
	}
	
	//基金协会信息
	@Test(dataProvider = "data")
	public void comp_assoinfo(Element testcase) throws Exception{
		TestAndAssert(testcase);
	}
	
	//公司基本信息
	@Test(dataProvider = "data")
	public void comp_baseinfo(Element testcase) throws Exception{
		TestAndAssert(testcase);
	}
	
	//工商基本信息
	@Test(dataProvider = "data")
	public void comp_buss_bsinfo(Element testcase) throws Exception{
		TestAndAssert(testcase);
	}
	
	//工商变更 信息
	@Test(dataProvider = "data")
	public void comp_buss_changehis(Element testcase) throws Exception{
		TestAndAssert(testcase);
	}
	
	//证监会处罚_行政处罚公司list
	@Test(dataProvider = "data")
	public void comp_buss_shareholderinfo(Element testcase) throws Exception{
		TestAndAssert(testcase);
	}
	
	//核心团队
	@Test(dataProvider = "data")
	public void comp_corepeople(Element testcase) throws Exception{
		TestAndAssert(testcase);
	}
	
	//投资策略
	@Test(dataProvider = "data")
	public void comp_descinfo(Element testcase) throws Exception{
		TestAndAssert(testcase);
	}
	
	//产品列表
	@Test(dataProvider = "data")
	public void comp_fundinfo(Element testcase) throws Exception{
		TestAndAssert(testcase);
	}
	
	//新闻 
	@Test(dataProvider = "data")
	public void comp_newsinfo(Element testcase) throws Exception{
		TestAndAssert(testcase);
	}
	
	
	//获奖信息
	@Test(dataProvider = "data")
	public void comp_prizeinfo(Element testcase) throws Exception{
		TestAndAssert(testcase);
	}
	
	//法律诉讼
	@Test(dataProvider = "data")
	public void comp_run_infos(Element testcase) throws Exception{
		TestAndAssert(testcase);
	}
	
	//知识产权 
	@Test(dataProvider = "data")
	public void comp_run_intinfos(Element testcase) throws Exception{
		TestAndAssert(testcase);
	}
	
	//对外投资
	@Test(dataProvider = "data")
	public void comp_run_investabroad(Element testcase) throws Exception{
		TestAndAssert(testcase);
	}
	
	//招聘信息
	@Test(dataProvider = "data")
	public void comp_run_recruitinfo(Element testcase) throws Exception{
		TestAndAssert(testcase);
	}
	
	//招聘详情
	@Test(dataProvider = "data")
	public void comp_run_recruitinfodetail(Element testcase) throws Exception{
		TestAndAssert(testcase);
	}
	
	//税务信息
	@Test(dataProvider = "data")
	public void comp_run_taxinfo(Element testcase) throws Exception{
		TestAndAssert(testcase);
	}
	
	//公司搜索
	@Test(dataProvider = "data")
	public void comp_search(Element testcase) throws Exception{
		TestAndAssert(testcase);
	}
	
	//条件选择
	@Test(dataProvider = "data")
	public void comp_tree(Element testcase) throws Exception{
		TestAndAssert(testcase);
	}
	
	//公司年报基本信息
	@Test(dataProvider = "data")
	public void comp_yearreport_baseinfo(Element testcase) throws Exception{
		TestAndAssert(testcase);
	}
	
	//年报投资信息
	@Test(dataProvider = "data")
	public void comp_yearreport_investinfo(Element testcase) throws Exception{
		TestAndAssert(testcase);
	}
	
	//年报投资者
	@Test(dataProvider = "data")
	public void comp_yearreport_sponsor(Element testcase) throws Exception{
		TestAndAssert(testcase);
	}
	
	//年报年列表
	@Test(dataProvider = "data")
	public void comp_yearreport_years(Element testcase) throws Exception{
		TestAndAssert(testcase);
	}
	
	//股权变更 
	@Test(dataProvider = "data")
	public void comp_yearreport_stockchanges(Element testcase) throws Exception{
		TestAndAssert(testcase);
	}
	
	//枚举值
	@Test(dataProvider = "data")
	public void dimenum(Element testcase) throws Exception{
		TestAndAssert(testcase);
	}
}
