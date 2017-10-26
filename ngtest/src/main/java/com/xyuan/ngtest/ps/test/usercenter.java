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

public class usercenter {
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
		String expected = (new MyXMLUtil("assert\\usercenter.xml")).getAssertString(methodName,testcase.attributeValue("name"));
		String mode = (new MyXMLUtil("assert\\usercenter.xml")).getAssertMode(methodName,testcase.attributeValue("name"));
		
		MyAssert.asserting(response, expected, mode);
	}
	
	//关注分组名称检查 
	@Test(dataProvider = "data")
	public void favfocusgroup_checkname(Element testcase) throws Exception{
		TestAndAssert(testcase);
	}
	
	//新建关注分组
	@Test(dataProvider = "data")
	public void favfocusgroup_create(Element testcase) throws Exception{
		TestAndAssert(testcase);
	}
	
	//删除关注分组
	@Test(dataProvider = "data")
	public void favfocusgroup_delete(Element testcase) throws Exception{
		TestAndAssert(testcase);
	}
	
	//关注分组列表
	@Test(dataProvider = "data")
	public void favfocusgroup_list(Element testcase) throws Exception{
		TestAndAssert(testcase);
	}
	
	//关注分组更新
	@Test(dataProvider = "data")
	public void favfocusgroup_update(Element testcase) throws Exception{
		TestAndAssert(testcase);
	}
	
	//公司关注列表
	@Test(dataProvider = "data")
	public void favfocuslistcomp(Element testcase) throws Exception{
		TestAndAssert(testcase);
	}
	
	//产品关注列表
	@Test(dataProvider = "data")
	public void favfocuslistfund(Element testcase) throws Exception{
		TestAndAssert(testcase);
	}
	
	//人员关注列表
	@Test(dataProvider = "data")
	public void favfocuslistman(Element testcase) throws Exception{
		TestAndAssert(testcase);
	}
	
	//新增关注
	@Test(dataProvider = "data")
	public void favfocus_create(Element testcase) throws Exception{
		TestAndAssert(testcase);
	}
	
	//删除关注
	@Test(dataProvider = "data")
	public void favfocus_delete(Element testcase) throws Exception{
		TestAndAssert(testcase);
	}
	
	//更新关注
	@Test(dataProvider = "data")
	public void favfocus_update(Element testcase) throws Exception{
		TestAndAssert(testcase);
	}
	
	//筛选名称检查
	@Test(dataProvider = "data")
	public void favmenu_checkname(Element testcase) throws Exception{
		TestAndAssert(testcase);
	}
	
	//新建筛选
	@Test(dataProvider = "data")
	public void favmenu_create(Element testcase) throws Exception{
		TestAndAssert(testcase);
	}
	
	//筛选删除
	@Test(dataProvider = "data")
	public void favmenu_delete(Element testcase) throws Exception{
		TestAndAssert(testcase);
	}
	
	//筛选详情
	@Test(dataProvider = "data")
	public void favmenu_detail(Element testcase) throws Exception{
		TestAndAssert(testcase);
	}
	
	//筛选列表
	@Test(dataProvider = "data")
	public void favmenu_list(Element testcase) throws Exception{
		TestAndAssert(testcase);
	}
	
	//更新筛选
	@Test(dataProvider = "data")
	public void favmenu_update(Element testcase) throws Exception{
		TestAndAssert(testcase);
	}
	
	//留言
	@Test(dataProvider = "data")
	public void message_create(Element testcase) throws Exception{
		TestAndAssert(testcase);
	}
	
	//负面信息统计信息
	@Test(dataProvider = "data")
	public void negativeinfo_allstatis(Element testcase) throws Exception{
		TestAndAssert(testcase);
	}
	
	//失信公司列表
	@Test(dataProvider = "data")
	public void negativeinfo_complist(Element testcase) throws Exception{
		TestAndAssert(testcase);
	}
	
	//失信被执行人详情
	@Test(dataProvider = "data")
	public void negativeinfo_mandetail(Element testcase) throws Exception{
		TestAndAssert(testcase);
	}
	
	//失信被执行人列表
	@Test(dataProvider = "data")
	public void negativeinfo_manlist(Element testcase) throws Exception{
		TestAndAssert(testcase);
	}
	
	//负面信息统计信息_标签，公司详情页面用
	@Test(dataProvider = "data")
	public void negativeinfo_stais(Element testcase) throws Exception{
		TestAndAssert(testcase);
	}
	
	//新增用户跟踪名单
	@Test(dataProvider = "data")
	public void usertrack_create(Element testcase) throws Exception{
		TestAndAssert(testcase);
	}
	
	//删除用户跟踪名单
	@Test(dataProvider = "data")
	public void usertrack_delete(Element testcase) throws Exception{
		TestAndAssert(testcase);
	}
	
	//编辑用户跟踪名单
	@Test(dataProvider = "data")
	public void usertrack_edit(Element testcase) throws Exception{
		TestAndAssert(testcase);
	}
	
	//用户跟踪名单列表
	@Test(dataProvider = "data")
	public void usertrack_list(Element testcase) throws Exception{
		TestAndAssert(testcase);
	}
	
	//用户基本信息
	@Test(dataProvider = "data")
	public void user_baseinfo(Element testcase) throws Exception{
		TestAndAssert(testcase);
	}
	
	//子账号列表
	@Test(dataProvider = "data")
	public void userinfo_list(Element testcase) throws Exception{
		TestAndAssert(testcase);
	}
	
	//新增子账号
	@Test(dataProvider = "data")
	public void userinfo_add(Element testcase) throws Exception{
		TestAndAssert(testcase);
	}
	
	//更新子账号信息
	@Test(dataProvider = "data")
	public void userinfo_update(Element testcase) throws Exception{
		TestAndAssert(testcase);
	}
	
	//删除子账号
	@Test(dataProvider = "data")
	public void userinfo_delete(Element testcase) throws Exception{
		TestAndAssert(testcase);
	}
	
	//重置密码
	@Test(dataProvider = "data")
	public void userinfo_resetpwd(Element testcase) throws Exception{
		TestAndAssert(testcase);
	}
	
	//用户分组列表
	@Test(dataProvider = "data")
	public void usergroup_list(Element testcase) throws Exception{
		TestAndAssert(testcase);
	}
	
	//增加用户分组
	@Test(dataProvider = "data")
	public void usergroup_add(Element testcase) throws Exception{
		TestAndAssert(testcase);
	}
	
	//用户分组删除
	@Test(dataProvider = "data")
	public void usergroup_delete(Element testcase) throws Exception{
		TestAndAssert(testcase);
	}
	
	//检查用户分组名称
	@Test(dataProvider = "data")
	public void usergroup_checkname(Element testcase) throws Exception{
		TestAndAssert(testcase);
	}
	
	//是否已关注
	@Test(dataProvider = "data")
	public void favfocusflag(Element testcase) throws Exception{
		TestAndAssert(testcase);
	}
	
	//发送手机SMS
	@Test(dataProvider = "data")
	public void auth_smssend(Element testcase) throws Exception{
		TestAndAssert(testcase);
	}
	
	//检查手机号是否存在
	@Test(dataProvider = "data")
	public void auth_checks(Element testcase) throws Exception{
		TestAndAssert(testcase);
	}
	
	//检查手机号是否存在
	@Test(dataProvider = "data")
	public void bindmobile(Element testcase) throws Exception{
		TestAndAssert(testcase);
	}
}
