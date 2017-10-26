package com.xyuan.ngtest.ps.test;

import java.lang.reflect.Method;
import org.dom4j.Element;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.xyuan.ngtest.Helper.DataHelper;
import com.xyuan.ngtest.Util.FuncUtil;
import com.xyuan.ngtest.process.ExecuteFactory;

public class usercenterFlow {
	private String className = FuncUtil.getRearName(this.getClass().getName());
	private String testcaseName;
	
	//拿数据
	@DataProvider(name = "data")
	public Object[][] getTestData(Method method) throws Exception {
		testcaseName = method.getName();
		DataHelper dh = new DataHelper(className);
		
		return dh.getFlowDataArray(testcaseName);
	}
	
	//关注分组名称测试
	@Test(dataProvider = "data")
	public void favfocusgroupTest(Element testcase) throws Exception{
		(new ExecuteFactory()).createFlowExecute(testcase,className,testcaseName);
	}
	
}
