package com.xyuan.ngtest.processImpl;

import org.dom4j.Element;
import com.xyuan.ngtest.Helper.ConfigHelper;
import com.xyuan.ngtest.Helper.PublicDataHelper;
import com.xyuan.ngtest.Util.MyLog;
import com.xyuan.ngtest.Util.OutputUtil;
import com.xyuan.ngtest.Util.httpExecuteUtil;
import com.xyuan.ngtest.casedata.ITestcaseData;
import com.xyuan.ngtest.casedata.impl.GetData;
import com.xyuan.ngtest.datapojo.ConfigData;
import com.xyuan.ngtest.process.IExecute;

public class httpgetProcesser implements IExecute{
	private static MyLog loger = MyLog.getLoger();
	
	public String execute(Element data,Element config,String apiName) throws Exception{
		String response = null;
		String testcaseName = PublicDataHelper.getIns(Thread.currentThread().getId()).getCasedata().getTestcaseName();
		
		loger.info("开始执行HTTP处理流程");
		//配置数据
		loger.info("取得配置数据数据" + apiName);
		ConfigData cd = ConfigHelper.getConfigData(config);
		
		//业务数据
		loger.info("取得业务数据:" + testcaseName);
		ITestcaseData itd = new GetData();
		String body = (String)itd.getCaseData(data);
		
		String url = cd.getUrl();
		if(body != null && body.length() > 0)
			url = url + body;
		
//		System.out.println(url);
		
		//执行
		loger.info("执行HTTP请求");
		response = httpExecuteUtil.httpGetExecute(url, cd.getHeaders());
		
		//后续数据处理
		OutputUtil.saveOutput(config, response);
		
		return response;
	}
	
	
}
