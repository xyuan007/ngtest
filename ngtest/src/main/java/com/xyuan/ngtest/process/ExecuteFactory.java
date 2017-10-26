package com.xyuan.ngtest.process;

import java.sql.Timestamp;
import java.util.List;

import org.dom4j.Element;

import com.xyuan.ngtest.Helper.ConfigHelper;
import com.xyuan.ngtest.Helper.DataHelper;
import com.xyuan.ngtest.Helper.PublicDataHelper;
import com.xyuan.ngtest.MyAssert.MyAssert;
import com.xyuan.ngtest.Util.FuncUtil;
import com.xyuan.ngtest.Util.MyXMLUtil;

public class ExecuteFactory {
	//执行用例
    public String createExecute(Element data,String method,String datatag) throws Exception{
    	IExecute exe = null;
    	
    	if(data==null)
    		throw new Exception(String.format("接口:%s下的用例:%s的数据配置为空", method,datatag));
    	
    	//为每一个线程初始化用例数据
		PublicDataHelper.getIns(Thread.currentThread().getId()).initCaseData(null, null, 1, method,datatag);

		Element config = (new ConfigHelper(method)).getConfigElement();
    	exe = getExecute(config);
    	
		return exe.execute(data,config,method);
    }
    
    //执行接口用例流程
    public void createFlowExecute(Element data,String classname,String testcasename) throws Exception{
    	IExecute exe = null;
    	
    	if(data==null)
    		throw new Exception(String.format("数据文件:%s.xml下的用例:%s的数据配置为空",classname, testcasename));
    	
    	//为每一个线程初始化用例数据
		PublicDataHelper.getIns(Thread.currentThread().getId()).initCaseData(testcasename, null, 1, null,null);
    	
    	List mList = data.elements();
    	for(int i=0;i<mList.size();i++){
    		Element temp = (Element) mList.get(i);
    		String methodName = temp.attributeValue("name");
    		List dataList = temp.elements();
    		
    		for(int j=0;j<dataList.size();j++){
        		Element tempdata = (Element) dataList.get(j);
        		String dataTag = tempdata.attributeValue("name");
        		
        		PublicDataHelper.getIns(Thread.currentThread().getId()).getCasedata().setDataTag(dataTag);
        		PublicDataHelper.getIns(Thread.currentThread().getId()).getCasedata().setMethodName(methodName);
        		Element config = (new ConfigHelper(methodName)).getConfigElement();
            	exe = getExecute(config);
            	
        		String response = exe.execute(tempdata,config,methodName);
        		String expected = (new MyXMLUtil(String.format("assert\\%s.xml", classname))).getFlowAssertString(testcasename,methodName,dataTag);
        		String mode = (new MyXMLUtil(String.format("assert\\%s.xml", classname))).getFlowAssertMode(testcasename,methodName,dataTag);
        		
        		MyAssert.asserting(response, expected, mode);
        		FuncUtil.newDetailReport(null,null,"success");
    			
    		}
    	}
    	

    }
    
    public void loginExecute() throws Exception{
    	IExecute exe = null;
    	
    	//为每一个线程初始化用例数据
		PublicDataHelper.getIns(Thread.currentThread().getId()).initCaseData("login", null, 1, "login","login");

		Element config = (new ConfigHelper("login")).getConfigElement();
    	exe = getExecute(config);
    	DataHelper dh = new DataHelper("usercenter");
    	
		String response = exe.execute(dh.getLoginData(),config,"login");
		
    } 

    public void preloginExecute()throws Exception{
    	IExecute exe = null;
    	//公共数据初始化
		PublicDataHelper.getIns(Thread.currentThread().getId()).initCaseData("prelogin",  null, 1, "prelogin","prelogin");

    	Element config = (new ConfigHelper("prelogin")).getConfigElement();
    	exe = getExecute(config);
    	
		String response = exe.execute(null,config,"prelogin");
		
		if(response != null && response.length()!=0)
			loginExecute();
    }
    
    private IExecute getExecute(Element config) throws Exception{
    	String protocol = config.elementText("protocol");
    	Class cs = Class.forName("com.xyuan.ngtest.processImpl." + protocol + "Processer");
    	Object  obj = cs.newInstance();
    	return (IExecute)obj;
    }
    
}
