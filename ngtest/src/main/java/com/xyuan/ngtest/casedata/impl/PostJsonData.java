package com.xyuan.ngtest.casedata.impl;

import net.sf.json.JSONObject;

import org.dom4j.Element;

import com.xyuan.ngtest.Util.FuncUtil;
import com.xyuan.ngtest.Util.OutputUtil;
import com.xyuan.ngtest.casedata.ITestcaseData;

public class PostJsonData implements ITestcaseData{

	public Object getCaseData(Element data) throws Exception {
		if(data == null){
			return "";
		}
		else if(data!=null && data.elements().size() == 0){
			return data.getTextTrim();
		}
		else{
			JSONObject json = new JSONObject();
			for(int i=0;i<data.elements().size();i++){
				Element temp = (Element)data.elements().get(i);
				String val = null;
				if(temp.attributeValue("type") == null)
					val = temp.getTextTrim();
				else if(temp.attributeValue("type").equals("output")){
					val = OutputUtil.getOutput(temp.getTextTrim());
				}
				
				if(temp.attributeValue("datatype") == null){
					json.put(temp.getName(), val);
				}
				else if(temp.attributeValue("datatype").equals("int")){
					json.put(temp.getName(), Integer.valueOf(val));
				}
				
			}
			
			return json.toString();

		}
	}

}
