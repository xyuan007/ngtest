package com.xyuan.ngtest.ps.manfund;

import java.lang.reflect.Method;
import org.dom4j.Element;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.xyuan.ngtest.Helper.DataHelper;
import com.xyuan.ngtest.MyAssert.MyAssert;
import com.xyuan.ngtest.Util.FuncUtil;
import com.xyuan.ngtest.Util.MyXMLUtil;
import com.xyuan.ngtest.process.ExecuteFactory;

public class man_search {
	private String className = FuncUtil.getRearName(this.getClass().getName());
	
	@DataProvider(name = "data")
	public Object[][] getTestData() throws Exception {
		DataHelper dh = new DataHelper("manfund",className);
		return dh.getDataArray();
	}
	
	@Test(dataProvider = "data")
	public void man_search(Element testcase,Method method) throws Exception{
		String response = (new ExecuteFactory()).createExecute(testcase,method.getName(),testcase.attributeValue("name"));
		String expected = (new MyXMLUtil("assert\\manfund.xml")).getAssertString(method.getName(),testcase.attributeValue("name"));
		String mode = (new MyXMLUtil("assert\\manfund.xml")).getAssertMode(method.getName(),testcase.attributeValue("name"));
		
		MyAssert.asserting(response, expected, mode);
	}
}
