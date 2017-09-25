package com.xyuan.ngtest.casedata.impl;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;
import org.dom4j.Element;

import com.xyuan.ngtest.Helper.PublicDataHelper;
import com.xyuan.ngtest.Util.FuncUtil;
import com.xyuan.ngtest.Util.MyLog;
import com.xyuan.ngtest.Util.ProjectPropUtil;
import com.xyuan.ngtest.casedata.ITestcaseData;

public class PostData implements ITestcaseData{
	private static MyLog loger = MyLog.getLoger();
	
	public Object getCaseData(Element data){
		if(data == null)
			return null;
		else{
			List<Element> eles = data.elements();
			//json
			if(eles.size()==0)
				return data.getTextTrim();
			
			List <NameValuePair> params = new ArrayList<NameValuePair>(); 
			for(int i=0;i<eles.size();i++){
				Element temp  = eles.get(i);
//				System.out.println("name::::::::::::::::::"+temp.getName());
				//处理特殊类型的数据
				if(temp.attributeValue("func") == null)
					params.add(new BasicNameValuePair(temp.getName(), temp.getTextTrim()));
				else{
					params.add(new BasicNameValuePair(temp.getName(), FuncUtil.getValueFromFunc(temp)));
				}
			}
			
			try {
				return new UrlEncodedFormEntity(params,ProjectPropUtil.getCharSet());
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				loger.error(e.getMessage());
			}
		}
		return null;
	}
}
