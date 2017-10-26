package com.xyuan.ngtest.Util;

import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.dom4j.Element;

import com.xyuan.ngtest.Helper.DatabaseHelper;
import com.xyuan.ngtest.Helper.PublicDataHelper;

public class FuncUtil {
	private static  MyLog loger = MyLog.getLoger();
	static String timestamp  = null;
	
	static public synchronized void AssertForTimeout(String response,String className) {
		if(response.contains("此次请求没有查询到相应内容")){
			//超时的话，设置循环标志为FALSE
			if(PublicDataHelper.getIns(Thread.currentThread().getId()).getCasedata().getCycleNum() == ProjectPropUtil.getTimeOut()){
				PublicDataHelper.getIns(Thread.currentThread().getId()).getCasedata().setCycleFlag("false");
				org.junit.Assert.fail(className+"超时未返回正确数据");
			}
			try {
				PublicDataHelper.getIns(Thread.currentThread().getId()).getCasedata().incCycleNum();
				Thread.sleep(ProjectPropUtil.getWaitTime()*1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		else{
			//拿到结果，设置循环标志为FALSE
			PublicDataHelper.getIns(Thread.currentThread().getId()).getCasedata().setCycleFlag("false");
//			assertThat(chopString(response),equalTo(chopString(expected)));
		}
	}
	
	public static synchronized  String getOutputValue(String key){
		String res = (String) PublicDataHelper.getIns(Thread.currentThread().getId()).getOutput().getValue(key);
		System.out.println("key:::::::"+key+"   value:::::::::" + res);
		return res;
	}
	
	public static synchronized  String getTimestamp(){
		timestamp = String.valueOf(Calendar.getInstance().getTimeInMillis());
		return timestamp;
	}
	
	public static synchronized  String getRandomNumber(String length){
		String base = "0123456789";     
		int len = Integer.parseInt(length);
	    Random random = new Random();     
	    StringBuffer sb = new StringBuffer();     
	    for (int i = 0; i < len; i++) {     
	        int number = random.nextInt(base.length());     
	        sb.append(base.charAt(number));     
	    }
	    return sb.toString();
	}
	
	public static synchronized  String getRandomMac(){
		String base = "ABCDEF1234567890";
	    Random random = new Random();     
	    int number = 0;
	    StringBuffer sb = new StringBuffer();  
	    for(int i=0;i<5;i++){
	    	number = random.nextInt(base.length());     
	        sb.append(base.charAt(number)); 
	    	number = random.nextInt(base.length());     
	        sb.append(base.charAt(number)); 
	        sb.append(":");
	    }
    	number = random.nextInt(base.length());     
        sb.append(base.charAt(number)); 
    	number = random.nextInt(base.length());     
        sb.append(base.charAt(number)); 
        
	    return sb.toString();
	}
	
	public static synchronized String getValueFromFunc(Element temp){
		Object obj = null;
		try{
			Class clazz = Class.forName(temp.attributeValue("func"));

			if(temp.attributeValue("paramtype") == null){
				Method method = clazz.getMethod(temp.getTextTrim(), null);  
				obj = method.invoke(null);
			}
			else{
				Class[] args = new Class[1];
				args[0] = String.class;
				Method method = clazz.getMethod(temp.getTextTrim(),args);
				
				Object[] p = new Object[1];
				if(temp.attributeValue("paramtype").equals("output")){
					p[0] = (String)PublicDataHelper.getIns(Thread.currentThread().getId()).getOutput().getValue((String)temp.attributeValue("paramvalue"));
				}
				else{
					p[0] = (String)temp.attributeValue("paramvalue");
				}
				obj = method.invoke(null,p);
			}
		}catch(Exception e){
		}
		return String.valueOf(obj);
	}
	
	private static synchronized  String sha1(String strSrc) {
	    MessageDigest md=null;
	    String strDes=null;
	 
	    byte[] bt=strSrc.getBytes();
	    try {
             md=MessageDigest.getInstance("SHA-1");
             md.update(bt);
             strDes=bytes2Hex(md.digest());  //to HexString
            }catch (Exception e) {
                    System.out.println("Invalid algorithm.");
                    return null;
            }
        	return strDes.toUpperCase();
	}
 
    private static synchronized  String bytes2Hex(byte[]bts) {
    	String des="";
    	String tmp=null;
    	for (int i=0;i<bts.length;i++) {
    		tmp=(Integer.toHexString(bts[i] & 0xFF));
    		if (tmp.length()==1) {
    			des+="0";
            }
            des+=tmp;
        }
        return des;
    }
    
    public static synchronized  String chopString(String input){
    	return removeUUID(removeID(input)).replaceAll("\\s+", "");
    }
    
    //去掉UUID
    public static synchronized  String removeUUID(String input){
    	Pattern pattern = Pattern.compile("[0-9a-zA-Z]{8}-[0-9a-zA-Z]{4}-[0-9a-zA-Z]{4}-[0-9a-zA-Z]{4}-[0-9a-zA-Z]{12}", Pattern.DOTALL);
    	Matcher matcher = pattern.matcher(input);
    	String string = matcher.replaceAll("");
    	return  string;
    }
    
    
    //去提ID和GUID
    public static synchronized  String removeID(String input){
    	Pattern pattern = Pattern.compile("\"_id\":(\\d+),", Pattern.DOTALL);
    	Matcher matcher = pattern.matcher(input);
    	
    	String string = matcher.replaceAll("");

    	pattern = Pattern.compile("\"parent_id\":(\\d+),", Pattern.DOTALL);
    	matcher = pattern.matcher(string);
    	string  = matcher.replaceAll("");
    	
    	pattern = Pattern.compile("\"guid\":\"([0-9a-zA-Z]{32})\",", Pattern.DOTALL);
    	matcher = pattern.matcher(string);
    	string  = matcher.replaceAll("");
    	
    	return  string;
    }
    
    //得到纯粹的类名
    public static synchronized String getRearName(String fullClass){
    	String[] temp = fullClass.split("\\.");
    	return temp[temp.length-1];
    }
    
    public static synchronized void newDetailReport(Timestamp starttime,Timestamp endtime,String status){
		int round = PublicDataHelper.getRound().getRound();
		String testcasename = PublicDataHelper.getIns(Thread.currentThread().getId()).getCasedata().getTestcaseName();
		String apitype = PublicDataHelper.getIns(Thread.currentThread().getId()).getCasedata().getApitype();
		String apiname = PublicDataHelper.getIns(Thread.currentThread().getId()).getCasedata().getMethodName();	
		String datatag = PublicDataHelper.getIns(Thread.currentThread().getId()).getCasedata().getDataTag();
		String responsecode = PublicDataHelper.getIns(Thread.currentThread().getId()).getCasedata().getResponsecode();
		String message = "";
		String exectime = PublicDataHelper.getIns(Thread.currentThread().getId()).getCasedata().getExectime();
		String projectname = PublicDataHelper.getIns(Thread.currentThread().getId()).getCasedata().getProjectName();
		String requesturl = PublicDataHelper.getIns(Thread.currentThread().getId()).getCasedata().getRequestURL();
		String requestdata = PublicDataHelper.getIns(Thread.currentThread().getId()).getCasedata().getRequestData();
		String responsedata = PublicDataHelper.getIns(Thread.currentThread().getId()).getCasedata().getResponseData();
		
		DatabaseHelper.newReports(round, apitype, apiname,datatag, message, starttime, endtime, exectime, responsecode, status, testcasename, projectname,requesturl,requestdata,responsedata);
    }
    
    public static String getPSPassword(String modulus) throws Exception{
    	String res =  RSAUtil.getEncryptPass(modulus);
    	return res;
    }
    
    public static String getMD5(String message) {  
        String md5str = "";  
        try {  
            // 1 创建一个提供信息摘要算法的对象，初始化为md5算法对象  
            MessageDigest md = MessageDigest.getInstance("MD5");  
            // 2 将消息变成byte数组  
            byte[] input = message.getBytes();  
            // 3 计算后获得字节数组,这就是那128位了  
            byte[] buff = md.digest(input);  
            // 4 把数组每一字节（一个字节占八位）换成16进制连成md5字符串  
            md5str = bytes2Hex(buff);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return md5str;  
    }

	public static void main(String[] args) throws Exception{
		System.out.println(getMD5(getMD5("11111111")+"11111111"));
	}
}