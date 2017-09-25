package com.xyuan.ngtest.Util;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class MyXMLUtil {
	public static MyLog loger = MyLog.getLoger();
	private Element root;
	
	public MyXMLUtil(String file){
		SAXReader reader = new SAXReader();  
		FileInputStream fis = null;
	    Document doc = null;
		try {
			fis = new FileInputStream(file);
			doc = reader.read(fis);
			this.root =  doc.getRootElement();  
		} catch (Exception e) {
			loger.error(e.getMessage());
		}finally{
			try{
                if(null != fis){
                    fis.close();
                }
            }catch(Exception e){
                e.printStackTrace();
            }
		}
	}
	
	public Element getRootElement() {
		return this.root;
	}
	
	public Element getConfigElement(String modelname){
		return (Element)this.root.selectSingleNode(String.format("/Config/model[@name=\"%s\"]",modelname));
	}

	public Element getPublicHeadersConfig(){
		return (Element)this.root.selectSingleNode(String.format("/Config/headers"));
	}
	
	public String getAssertString(String method,String testcasename){
		return ((Element)this.root.selectSingleNode(String.format("/TestSuite/method[@name=\"%s\"]/TestCase[@name=\"%s\"]",method,testcasename))).getTextTrim();
	}
	
	public String getAssertMode(String method,String testcasename){
		Element ele = (Element)this.root.selectSingleNode(String.format("/TestSuite/method[@name=\"%s\"]/TestCase[@name=\"%s\"]",method,testcasename));
		return ((Element)this.root.selectSingleNode(String.format("/TestSuite/method[@name=\"%s\"]/TestCase[@name=\"%s\"]",method,testcasename))).attributeValue("mode");
	}
	
	public Element getDataElement(String testcasename){
		return (Element)this.root.selectSingleNode(String.format("/TestSuite/TestCase[@name=\"%s\"]",testcasename));
	}
}
