package com.xyuan.ngtest.datapojo;

import java.util.Date;

import com.xyuan.ngtest.Util.ProjectPropUtil;

public class CaseData {
	private int round;//轮次
	private String apitype;//API类型
	private String responsecode;//返回码
	private String message;//错误信息
	private String status;//执行状态
	private Date starttime;//开始时间
	private Date endtime;//结束时间
	private String exectime;//执行时间（秒）
	private String modelName ;//模块名称，也就是调用的API的名称
//	private String classname;//测试类名
	private String outputField;//输出的字段名
	private String testcasename;//测试用例名称，测试数据中的TESTCASE名
//	private String index;//顺序号
	private String projectname;//测试项目名称
	private String requestURL;//请求URL
	private String requestData;//请求数据
	private String responseData;//响应数据
	private int cycleNum;
	private String cycleFlag;
	
	public CaseData(String modelName,String apitype,int round,String testcasename){
		this.round = round;
		this.apitype = apitype;
		this.modelName = modelName;
		this.testcasename = testcasename;
		this.projectname = ProjectPropUtil.getProjectName();
		this.cycleNum = 0;
		this.cycleFlag = "true";
	}
	
	public CaseData(String modelName,String apitype,int round){
		this.round = round;
		this.apitype = apitype;
		this.modelName = modelName;
		this.projectname = ProjectPropUtil.getProjectName();
		this.cycleNum = 0;
		this.cycleFlag = "true";
	}

	public String getTestcasename() {
		return testcasename;
	}

	public void setTestcasename(String testcasename) {
		this.testcasename = testcasename;
	}

	public int getCycleNum() {
		return cycleNum;
	}

	public void incCycleNum() {
		this.cycleNum++;
	}

	public String getProjectname() {
		return projectname;
	}

	public void setProjectname(String projectname) {
		this.projectname = projectname;
	}


	public String getRequestURL() {
		return requestURL;
	}

	public void setRequestURL(String requestURL) {
		this.requestURL = requestURL;
	}

	public String getRequestData() {
		return requestData;
	}

	public void setRequestData(String requestData) {
		this.requestData = requestData;
	}

	public String getResponseData() {
		return responseData;
	}

	public void setResponseData(String responseData) {
		this.responseData = responseData;
	}

	public String getTestcaseName() {
		return testcasename;
	}

	public void setTestcaseName(String testcasename) {
		this.testcasename = testcasename;
	}

//	public String getIndex() {
//		return index;
//	}
//
//	public void setIndex(String index) {
//		this.index = index;
//	}
	
	public String getProjectName(){
		return projectname;
	}

	public int getRound() {
		return round;
	}

	public void setRound(int round) {
		this.round = round;
	}

	public String getApitype() {
		return apitype;
	}

	public void setApitype(String apitype) {
		this.apitype = apitype;
	}

	public String getResponsecode() {
		return responsecode;
	}

	public void setResponsecode(String responsecode) {
		this.responsecode = responsecode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getStarttime() {
		return starttime;
	}

	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}

	public Date getEndtime() {
		return endtime;
	}

	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}

	public String getExectime() {
		return exectime;
	}

	public void setExectime(String exectime) {
		this.exectime = exectime;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public String getOutputField() {
		return outputField;
	}

	public void setOutputField(String outputField) {
		this.outputField = outputField;
	}

	public String getCycleFlag() {
		return cycleFlag;
	}

	public void setCycleFlag(String cycleFlag) {
		this.cycleFlag = cycleFlag;
	}
	
	
}
