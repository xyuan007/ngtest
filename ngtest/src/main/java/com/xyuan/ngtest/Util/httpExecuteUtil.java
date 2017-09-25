package com.xyuan.ngtest.Util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.Map;
import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.dom4j.Element;
import com.xyuan.ngtest.Helper.PublicDataHelper;
import com.xyuan.ngtest.casedata.ITestcaseData;
import com.xyuan.ngtest.casedata.impl.PostData;
import com.xyuan.ngtest.casedata.impl.PostJsonData;

public class httpExecuteUtil {
	private static MyLog loger = MyLog.getLoger();
	
	public static String httpGetExecute(String url, Map<String, String> headers) throws Exception {
		String res = null;
		DefaultHttpClient httpclient = HttpClientUtil.getClientByUrl(url);
		HttpGet request = new HttpGet(urlConvert(url));

		try{
			//设置HEADER
			loger.info("设置HTTP的HEADER信息");
			if (headers != null) {  
	             for (Map.Entry<String,String> entry : headers.entrySet()) { 
	            	 request.addHeader(entry.getKey(), entry.getValue());  
	             }  
	      	}

			//执行并返回结果
			loger.info("执行并返回结果，记录下调用时间和返回码");
			Date d1 = new Date();
			CloseableHttpResponse response = httpclient.execute(request);
			long time = new Date().getTime() - d1.getTime();
			
	        HttpEntity resEntity = response.getEntity();
	        res = EntityUtils.toString(resEntity, ProjectPropUtil.getCharSet());
	        
			//记录请求
	        PublicDataHelper.getIns(Thread.currentThread().getId()).getCasedata().setRequestURL(url);
	        PublicDataHelper.getIns(Thread.currentThread().getId()).getCasedata().setResponsecode(String.valueOf(response.getStatusLine().getStatusCode()));
	        PublicDataHelper.getIns(Thread.currentThread().getId()).getCasedata().setExectime(String.valueOf(time));
	        PublicDataHelper.getIns(Thread.currentThread().getId()).getCasedata().setResponseData(res);
	        PublicDataHelper.getIns(Thread.currentThread().getId()).getCasedata().setApitype("httpget");
		}
		catch(Exception ex){
			throw new Exception(ex);
		}
		finally{
			request.releaseConnection();
		}
		
		return res;
	}
	
	public static String httpPostExecute(Element data,String url, Map<String, String> headers,String protocol) throws Exception {
		String res = null;
		ITestcaseData itd = null;
		Object body = null;
		DefaultHttpClient httpclient = HttpClientUtil.getClientByUrl(url);
		HttpPost post = new HttpPost(url);
		
		try{
			//设置HEADER
			loger.info("设置HTTP的HEADER信息");
			if (headers != null) {  
	             for (Map.Entry<String,String> entry : headers.entrySet()) { 
	                 post.addHeader(entry.getKey(), entry.getValue());  
	             }  
	      	}
			
			if(protocol.equals("httppost")){
				//业务数据
				itd = new PostData();
				body = (Object)itd.getCaseData(data);
				
				//设置ENTITY
				loger.info("设置HTTP的ENTITY");
				if(body != null && !body.equals("")){
					//设置ENTITY
					loger.info("设置HTTP的ENTITY");
					post.setEntity((UrlEncodedFormEntity)body);
				}
			}
			else if(protocol.equals("httppostjson")){
				//业务数据
				itd = new PostJsonData();
				body = (Object)itd.getCaseData(data);
				post.addHeader("content-type","application/json;charset=UTF-8");  
				StringEntity se = new StringEntity((String)body,"utf-8");
		        se.setContentType("application/json");
		        se.setContentEncoding("UTF-8");
		        post.setEntity(se);
			}
			
			System.out.println("body:::::::::::"+body);
			
			//执行并返回结果
			loger.info("执行并返回结果，记录下调用时间和返回码");
			Date d1 = new Date();
			CloseableHttpResponse response = httpclient.execute(post);
			long time = new Date().getTime() - d1.getTime();
			
	        HttpEntity resEntity = response.getEntity();
	        res = EntityUtils.toString(resEntity, ProjectPropUtil.getCharSet());

			//记录请求
	        PublicDataHelper.getIns(Thread.currentThread().getId()).getCasedata().setRequestURL(url);
	        if(body != null){
		        if(body instanceof  UrlEncodedFormEntity)
		        	PublicDataHelper.getIns(Thread.currentThread().getId()).getCasedata().setRequestData(convertIS2String(((UrlEncodedFormEntity)body).getContent()));
		        else
		        	PublicDataHelper.getIns(Thread.currentThread().getId()).getCasedata().setRequestData((String)body);
	        }
	        else
	        	PublicDataHelper.getIns(Thread.currentThread().getId()).getCasedata().setRequestData("");
		    PublicDataHelper.getIns(Thread.currentThread().getId()).getCasedata().setResponsecode(String.valueOf(response.getStatusLine().getStatusCode()));
	        PublicDataHelper.getIns(Thread.currentThread().getId()).getCasedata().setExectime(String.valueOf(time));
	        PublicDataHelper.getIns(Thread.currentThread().getId()).getCasedata().setResponseData(res);
	        PublicDataHelper.getIns(Thread.currentThread().getId()).getCasedata().setApitype(protocol);
		}
		catch(Exception ex){
			System.out.println(ex.getMessage());
		}
		finally{
			post.releaseConnection();
		}
		
		return res;
	}
	
	private static String convertIS2String(InputStream is){
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));      
        StringBuilder sb = new StringBuilder();      
      
        String line = null;    
        
        try {      
             while ((line = reader.readLine()) != null) {      
                  sb.append(line + "\n");      
              }      
          } catch (IOException e) {      
              e.printStackTrace();      
          } finally {      
            try {      
                 is.close();      
              } catch (IOException e) {      
                  e.printStackTrace();      
             }      
          }      
         return sb.toString();  
	}
	
	private static String urlConvert(String url){
		url = url.replaceAll("\\{", "%7b");
		url = url.replaceAll("\\}", "%7d");
		url = url.replaceAll("\\\"", "%22");
		return url;
	}
}
