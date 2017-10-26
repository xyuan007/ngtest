package com.xyuan.ngtest.Helper;

import java.util.ArrayList;
import java.util.List;
import org.dom4j.Element;
import com.xyuan.ngtest.Util.*;

public class DataHelper {
	Element ele = null;
	Element root = null;
	private static MyLog logger = MyLog.getLoger();
	
	public DataHelper(String dir,String testName)  {
		String configFile = null;
		try{
			configFile = String.format("data\\%s\\%s.xml",dir, testName);
			root = (new MyXMLUtil(configFile)).getRootElement();
		}catch(Exception ex){
			logger.error(ex.getMessage());
			logger.info("路径：" + configFile + "下未找到数据文件，返回空数据");
		}
	}
	
	public DataHelper(String dir)  {
		String configFile = null;
		try{
			configFile = String.format("data\\%s\\%s.xml",ProjectPropUtil.getProjectName(),dir);
			root = (new MyXMLUtil(configFile)).getRootElement();
		}catch(Exception ex){
			logger.error(ex.getMessage());
			logger.info("路径：" + configFile + "下未找到数据文件，返回空数据");
		}
	}
	
	public Object[][] getDataArray(){
		try{
			Object[] temp = root.selectNodes("/TestSuite/TestCase").toArray();
			Object[][] res = new Object[temp.length][1];
			for(int i=0;i<temp.length;i++){
				res[i][0] = temp[i];
			}
			
			return res;
		}catch(Exception ex){
			logger.error(ex.getMessage());
		}
		return null;
	}
	
	public Element getLoginData(){
		return (Element)root.selectSingleNode("/TestSuite/TestMethod[@name=\"login\"]/TestCase[@name=\"login\"]");
	}
	
	public Object[][] getDataArray(String dataname){
		try{
			Object[] temp = root.selectNodes(String.format("/TestSuite/TestMethod[@name=\"%s\"]/TestCase", dataname)).toArray();
			Object[][] res = new Object[temp.length][1];
			for(int i=0;i<temp.length;i++){
				res[i][0] = temp[i];
			}
			
			return res;
		}catch(Exception ex){
			logger.error(ex.getMessage());
		}
		return null;
	}
	
	public Object[][] getDataArray(String methodname,String testcontent){
		try{
			if(testcontent == null || testcontent.equals("all"))
				return getDataArray(methodname);
			
			List tempList = new ArrayList();
			if(testcontent.equals("rest")){
				List eles = root.selectNodes(String.format("/TestSuite/TestMethod[@name=\"%s\"]/TestCase", methodname));
				List<String> runedTestCase = DatabaseHelper.getRunedTestCase(PublicDataHelper.getIns(Thread.currentThread().getId()).getCasedata().getRound(), methodname);
				
				for(int i=0;i<eles.size();i++){
					Element ele = (Element)eles.get(i);
					if(runedTestCase.contains(ele.attributeValue("name")) == false)
						tempList.add(ele);
				}
			}
			else{
				String[] berun = testcontent.split(",");
				for(int i=0;i<berun.length;i++){
					Element ele = (Element)root.selectSingleNode(String.format("/TestSuite/TestMethod[@name=\"%s\"]/TestCase[@name=\"%s\"]", methodname,berun[i]));
					tempList.add(ele);
				}
			}
			
			Object[] temp = tempList.toArray();
			Object[][] res = new Object[temp.length][1];
			for(int i=0;i<temp.length;i++){
				res[i][0] = temp[i];
			}
			
			return res;
		}catch(Exception ex){
			logger.error(ex.getMessage());
		}
		return null;
	}
	
	public String getDataByField(String field){
		Element eleField = ele.element(field);
		
		if(eleField != null)
			return eleField.getTextTrim();
		else
			return null;
	}
	
	public Object[][] getFlowDataArray(String dataname){
		try{
			Object[] temp = root.selectNodes(String.format("/TestSuite/TestFlow[@name=\"%s\"]", dataname)).toArray();
			Object[][] res = new Object[temp.length][1];
			for(int i=0;i<temp.length;i++){
				res[i][0] = temp[i];
			}
			
			return res;
		}catch(Exception ex){
			logger.error(ex.getMessage());
		}
		return null;
	}
	
	
//	private Element getDataElement(){
//		return this.ele;
//	}
//	
//	private String getJsonBody() throws Exception{
//		XML2Json xj = new XML2Json();
//		return xj.getJSONObject(this.ele).toString();
//	}
	
//	private Map<String,String> getPostParam(){
//		return null;
//	}
//	
//	public String getHttpGetString(){
//		return null;
//	}
	
	
}
