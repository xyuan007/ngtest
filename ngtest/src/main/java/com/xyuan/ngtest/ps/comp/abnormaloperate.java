package com.xyuan.ngtest.ps.comp;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.lang.reflect.Method;

import org.dom4j.Element;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.xyuan.ngtest.Helper.DataHelper;
import com.xyuan.ngtest.MyAssert.MyAssert;
import com.xyuan.ngtest.Util.FuncUtil;
import com.xyuan.ngtest.Util.MyXMLUtil;
import com.xyuan.ngtest.process.ExecuteFactory;

public class abnormaloperate {
	private String className = FuncUtil.getRearName(this.getClass().getName());
	
	@DataProvider(name = "data")
	public Object[][] getTestData() throws Exception {
		DataHelper dh = new DataHelper("comp",className);
		return dh.getDataArray();
	}
	
	@Test(dataProvider = "data")
	public void test(Element testcase,Method method) throws Exception{
		String response = (new ExecuteFactory()).createExecute(testcase,className,testcase.attributeValue("name"));
		String expected = (new MyXMLUtil("assert\\comp.xml")).getAssertString(className,testcase.attributeValue("name"));
		String mode = (new MyXMLUtil("assert\\comp.xml")).getAssertMode(className,testcase.attributeValue("name"));
		
		MyAssert.asserting(response, expected, mode);
	}
	
}
