package com.xyuan.ngtest.ps.usercenter;

import java.lang.reflect.Method;
import java.util.Map;
import org.dom4j.Element;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.xyuan.ngtest.Helper.DataHelper;
import com.xyuan.ngtest.MyAssert.MyAssert;
import com.xyuan.ngtest.Util.FuncUtil;
import com.xyuan.ngtest.Util.MyXMLUtil;
import com.xyuan.ngtest.process.ExecuteFactory;

public class favfocusgroup_checkname {
	private String className = FuncUtil.getRearName(this.getClass().getName());
	  
	@DataProvider(name = "data")
	public Object[][] getTestData(ITestContext context) throws Exception {
		String dataname = null;
		Map<String,String> paramters = context.getCurrentXmlTest().getAllParameters();
		for(Map.Entry<String, String> entry :  paramters.entrySet()){
			if(entry.getKey().equals(className)){
				dataname = entry.getValue();
				break;
			}
		}
		
		DataHelper dh = new DataHelper("usercenter",className);
		return dh.getDataArray(dataname);
	}
	
	@Test(dataProvider = "data")
	public void favfocusgroup_checkname(Element testcase,Method method) throws Exception{
		String response = (new ExecuteFactory()).createExecute(testcase,method.getName(),testcase.attributeValue("name"));
		String expected = (new MyXMLUtil("assert\\usercenter.xml")).getAssertString(method.getName(),testcase.attributeValue("name"));
		String mode = (new MyXMLUtil("assert\\usercenter.xml")).getAssertMode(method.getName(),testcase.attributeValue("name"));
		
		MyAssert.asserting(response, expected, mode);
	}
	
}
