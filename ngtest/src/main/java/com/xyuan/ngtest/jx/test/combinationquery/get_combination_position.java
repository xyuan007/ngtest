package com.xyuan.ngtest.jx.test.combinationquery;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.lang.reflect.Method;

import org.dom4j.Element;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.xyuan.ngtest.Helper.DataHelper;
import com.xyuan.ngtest.Util.FuncUtil;
import com.xyuan.ngtest.Util.MyXMLUtil;
import com.xyuan.ngtest.process.ExecuteFactory;

//大类资产配置
public class get_combination_position {
	private String className = FuncUtil.getRearName(this.getClass().getName());
	
	@DataProvider(name = "data")
	public Object[][] getTestData() throws Exception {
		DataHelper dh = new DataHelper("combinationquery",className);
		return dh.getDataArray();
	}
	
	@Test(dataProvider = "data")
	public void get_combination_position(Element testcase,Method method) throws Exception{
		String response = (new ExecuteFactory()).createExecute(testcase,method.getName(),testcase.attributeValue("name"));
		String expected = (new MyXMLUtil("assert\\combinationquery.xml")).getAssertString(method.getName(),testcase.attributeValue("name"));
		
		assertThat(FuncUtil.chopString(response),equalTo(FuncUtil.chopString(expected)));
	}
	
	
}
