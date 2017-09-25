package com.xyuan.ngtest.process;

import org.dom4j.Element;

import com.xyuan.ngtest.Helper.ConfigHelper;
import com.xyuan.ngtest.Helper.PublicDataHelper;
import com.xyuan.ngtest.Util.FuncUtil;

public class ExecuteFactory {
    public String createExecute(Element data,String method,String testcasename) throws Exception{
    	IExecute exe = null;
    	
    	if(data==null)
    		throw new Exception(String.format("接口:%s下的用例:%s的数据配置为空", method,testcasename));
    	
    	//为每一个线程初始化用例数据
		PublicDataHelper.getIns(Thread.currentThread().getId()).initCaseData(method, null, 1, testcasename);

		Element config = (new ConfigHelper(method)).getConfigElement();
    	String protocol = config.elementText("protocol");
    	Class cs = Class.forName("com.xyuan.ngtest.processImpl." + protocol + "Processer");
    	Object  obj = cs.newInstance();
    	exe = (IExecute)obj;
    	
		return exe.execute(data,config,method);
    }
    
    public String loginExecute() throws Exception{
    	IExecute exe = null;
    	
    	//为每一个线程初始化用例数据
		PublicDataHelper.getIns(Thread.currentThread().getId()).initCaseData("login", null, 1, "login");

		Element config = (new ConfigHelper("login")).getConfigElement();
    	String protocol = config.elementText("protocol");
    	Class cs = Class.forName("com.xyuan.ngtest.processImpl." + protocol + "Processer");
    	Object  obj = cs.newInstance();
    	exe = (IExecute)obj;
    	
		return exe.execute(null,config,"login");
    } 

    public String preloginExecute()throws Exception{
    	IExecute exe = null;
    	//公共数据初始化
		PublicDataHelper.getIns(Thread.currentThread().getId()).initCaseData("prelogin",  null, 1, "prelogin");

    	Element config = (new ConfigHelper("prelogin")).getConfigElement();
    	String protocol = config.elementText("protocol");
    	Class cs = Class.forName("com.xyuan.ngtest.processImpl." + protocol + "Processer");
    	Object  obj = cs.newInstance();
    	exe = (IExecute)obj;
    	
		return exe.execute(null,config,"prelogin");
    }
    
}
