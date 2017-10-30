package com.xyuan.ngtest.processImpl;

import org.dom4j.Element;
import com.xyuan.ngtest.Helper.ConfigHelper;
import com.xyuan.ngtest.Util.MyLog;
import com.xyuan.ngtest.Util.OutputUtil;
import com.xyuan.ngtest.Util.httpExecuteUtil;
import com.xyuan.ngtest.casedata.ITestcaseData;
import com.xyuan.ngtest.casedata.impl.PostData;
import com.xyuan.ngtest.datapojo.ConfigData;
import com.xyuan.ngtest.process.IExecute;

public class httppostProcesser implements IExecute{
	private static MyLog loger = MyLog.getLoger();
	
	public String execute(Element data,Element config,String apiName) throws Exception{
		String response = null;
		ITestcaseData itd = null;
		
		loger.info("开始执行HTTP处理流程");
		//配置数据
		loger.info("取得配置数据数据" + apiName);
		ConfigData cd = ConfigHelper.getConfigData(config);
		
		//业务数据
		itd = new PostData();
		Object body = (Object)itd.getCaseData(data);
		
		//执行
		loger.info("执行HTTP请求");
//		response = httpExecute(cd.getUrl(), cd.getHeaders(),cd.getProtocol(), body);
		response = httpExecuteUtil.httpPostExecute(data,cd.getUrl(), cd.getHeaders(),cd.getProtocol());

		//保存数据，供后续调用
		OutputUtil.saveOutput(config, response);
		
		return response;
	}
}
