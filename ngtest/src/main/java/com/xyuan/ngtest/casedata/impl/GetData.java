package com.xyuan.ngtest.casedata.impl;

import java.net.URLEncoder;
import java.util.List;
import org.dom4j.Element;
import com.xyuan.ngtest.Util.FuncUtil;
import com.xyuan.ngtest.Util.MyLog;
import com.xyuan.ngtest.Util.ProjectPropUtil;
import com.xyuan.ngtest.casedata.ITestcaseData;

public class GetData implements ITestcaseData{
	private static MyLog loger = MyLog.getLoger();
	private boolean urlEncode = true;
	
	private StringBuffer getParamStr(StringBuffer sb,Element temp) throws Exception{
		if(temp.attributeValue("func") == null){
			sb.append(temp.getName());
			sb.append("=");
			String value = (urlEncode == true)?URLEncoder.encode(temp.getTextTrim(),ProjectPropUtil.getCharSet()):temp.getTextTrim();
			sb.append(value);
		}
		else{
			String obj = FuncUtil.getValueFromFunc(temp);
			
			sb.append(temp.getName());
			sb.append("=");
			String value = (urlEncode == true)?URLEncoder.encode(obj,ProjectPropUtil.getCharSet()):(String)obj;
			sb.append(value);
		}
		
		return sb;
	}
	
	public Object getCaseData(Element data) throws Exception {
		if(data == null)
			return null;
		else{
			StringBuffer sb = new StringBuffer();
			sb.append("?");
			List<Element> eles = data.elements();
			for(int i=0;i<eles.size()-1;i++){
				Element temp = eles.get(i);
				
				sb = getParamStr(sb,temp);
				sb.append("&");
			}
			Element temp = eles.get(eles.size()-1);
			sb = getParamStr(sb,temp);
//			sb.append(String.format("&%s=", ProjectPropUtil.getTokenStr()));
//			sb.append((String)PDHelper.getIns(Thread.currentThread().getId()).getOutput().getValue("token"));
			
			return sb.toString();
		}
	}
}
