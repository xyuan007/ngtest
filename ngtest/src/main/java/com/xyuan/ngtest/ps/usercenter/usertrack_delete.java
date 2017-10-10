package com.xyuan.ngtest.ps.usercenter;

import java.lang.reflect.Method;
import org.dom4j.Element;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.xyuan.ngtest.Helper.DataHelper;
import com.xyuan.ngtest.MyAssert.MyAssert;
import com.xyuan.ngtest.Util.FuncUtil;
import com.xyuan.ngtest.Util.MyXMLUtil;
import com.xyuan.ngtest.process.ExecuteFactory;

public class usertrack_delete {
	private String className = FuncUtil.getRearName(this.getClass().getName());
	
	@DataProvider(name = "data")
	public Object[][] getTestData() throws Exception {
		DataHelper dh = new DataHelper("usercenter",className);
		return dh.getDataArray();
	}
	
	@Test(dataProvider = "data")
	public void test(Element testcase,Method method) throws Exception{
		String response = (new ExecuteFactory()).createExecute(testcase,className,testcase.attributeValue("name"));
		String expected = (new MyXMLUtil("assert\\usercenter.xml")).getAssertString(className,testcase.attributeValue("name"));
		String mode = (new MyXMLUtil("assert\\usercenter.xml")).getAssertMode(className,testcase.attributeValue("name"));
		
		MyAssert.asserting(response, expected, mode);
	}
}
