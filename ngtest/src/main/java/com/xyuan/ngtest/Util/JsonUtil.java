package com.xyuan.ngtest.Util;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;

public class JsonUtil {
	private static JSONObject data = null;
	private static JSONObject json = null;
	
	public  static synchronized String getToken(String response) throws Exception{
		json = JSONObject.fromObject(response);
		data = JSONObject.fromObject(json.get("data"));
		
		assertThat((String)json.get("message"),equalTo("成功"));
		assertThat((Integer)data.get("status"),equalTo(1));
		assertThat((Integer)data.get("code"),equalTo(101));
		
		return (String) data.get(ProjectPropUtil.getTokenStr());
	}
	
	public  static synchronized String getResult(String response) throws Exception{
		json = JSONObject.fromObject(response);
		data = JSONObject.fromObject(json.get("data"));
		
		assertThat((String)json.get("message"),equalTo("成功"));
		assertThat((Integer)json.get("code"),equalTo(0));
		
		return (String) data.get("result");
	}
	
	public static synchronized String getOutput(String key,String type,String response) throws Exception{
		
		if(type != null && type.equals("regex")){
			Pattern p = Pattern.compile(key); 
			Matcher m = p.matcher(response); 
			m.find();
			String res =  m.group();
			return res;
		}
		else {
			json = JSONObject.fromObject(response);
			String[] path = key.split("\\.");
			if(path != null && path.length != 0){
				key = path[path.length-1];
				for(int i=0;i<path.length-1;i++){
					json = json.getJSONObject(path[i]);
				}
			}
			
			return String.valueOf(json.get(key));
		}
	}
	
	public static void main(String[] args){
		Pattern p = Pattern.compile("\\d{6}"); 
		Matcher m = p.matcher("成功(未实际发送):225230"); 
		m.find();
		String res =  m.group();
		System.out.println(res);
	}
}
