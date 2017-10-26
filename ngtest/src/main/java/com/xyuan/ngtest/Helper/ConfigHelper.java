package com.xyuan.ngtest.Helper;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.dom4j.Element;

import com.xyuan.ngtest.Util.MyLog;
import com.xyuan.ngtest.Util.MyXMLUtil;
import com.xyuan.ngtest.Util.OutputUtil;
import com.xyuan.ngtest.Util.ProjectPropUtil;
import com.xyuan.ngtest.datapojo.ConfigData;

public class ConfigHelper {
	private static MyLog loger = MyLog.getLoger();
	Element ele = null;
	
	public ConfigHelper(String modelname) throws Exception {
		String configFile = String.format("config\\%s.xml", ProjectPropUtil.getProjectName());
		
		ele =  (new MyXMLUtil(configFile)).getConfigElement(modelname);
		if(ele == null){
			String logInfo = "config文件中，模块：" + modelname + "未进行配置";
			loger.error(logInfo);
			throw new Exception(logInfo);
		}
	}
	
	public Element getConfigElement(){
		return this.ele;
	}
	
	public  String getUrl(){
		return ele.elementText("url");
	}
	
	public String getProtocol(){
		return ele.elementText("protocol");
	}
	
	public String getCycle(){
		return ele.elementText("cycle");
	}
	
	//取得配置数据
	public static ConfigData getConfigData(Element ele) throws Exception {
		ConfigData cd = null;
		Map<String,String> header = null;
		
		try{
			cd = new ConfigData();
			cd.setProtocol(ele.elementText("protocol"));
			cd.setUrl(ele.elementText("url"));
			
			//特定接口的HEADER
			Element eleHeader = ele.element("headers");
			header = new HashMap<String,String>();
			header = handleHeader(eleHeader,header);
			
			//公共的HEADER
			String configFile = String.format("config\\%s.xml", ProjectPropUtil.getProjectName());
			Element pubHeaders =  (new MyXMLUtil(configFile)).getPublicHeadersConfig();
			header = handleHeader(pubHeaders,header);
			
			cd.setHeaders(header);
		}
		catch(Exception e){
			String logInfo = "获得模块" + PublicDataHelper.getIns(Thread.currentThread().getId()).getCasedata().getMethodName() + "的配置数据时出错：" + e.getMessage();
			loger.error(logInfo);
			throw new Exception(logInfo);
		}
		return cd;
	}
	
	private static Map<String, String> handleHeader(Element eleHeader,Map<String, String> header) throws Exception{
		if(eleHeader!=null){
			for(int i=0;i<eleHeader.elements().size();i++){
				Element temp = (Element) eleHeader.elements().get(i);
				if(temp.attributeValue("type") == null)
					header.put(temp.attributeValue("name"), temp.getTextTrim());
				else if(temp.attributeValue("type").equals("func")){
					Class clazz = Class.forName(temp.attributeValue("clazz"));
					Method method = clazz.getMethod(temp.getTextTrim(), null);  
					Object obj = method.invoke(null);
					
					header.put(temp.attributeValue("name"), (String)obj);
				}
				else if(temp.attributeValue("type").equals("output")){
					String headername = temp.attributeValue("name");
					String value = OutputUtil.getOutput(headername);
					
					header.put(headername, value);
				}
			}
		}
		return header;
		
	}
	
}
