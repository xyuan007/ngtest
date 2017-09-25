package com.xyuan.ngtest.Util;

import java.util.List;
import org.dom4j.Element;
import com.xyuan.ngtest.Helper.PublicDataHelper;

public class OutputUtil {
	public static  void saveOutput(Element config,String response) throws Exception{
		List output = config.elements("output");
		if(output == null || output.size() == 0)
			return;
		
		for(int i=0;i<output.size();i++){
			Element temp = (Element)output.get(i);
			String key = temp.attributeValue("name");
			String resKey = temp.getTextTrim();
			String value = JsonUtil.getOutput(resKey,response);
			
			PublicDataHelper.getIns(Thread.currentThread().getId()).getOutput().setValue(key, value);
		}
	}
	
	public static String getOutput(String key){
		return (String) PublicDataHelper.getIns(Thread.currentThread().getId()).getOutput().getValue(key);
	}
	
}
