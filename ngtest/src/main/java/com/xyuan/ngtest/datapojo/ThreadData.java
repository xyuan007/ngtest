package com.xyuan.ngtest.datapojo;

public class ThreadData {
	private TestCaseData casedata;
	private OutputData output;
	private OutputData pubOutput;
	private boolean  runFlag;

	public boolean getRunFlag() {
		return runFlag;
	}

	public void setRunFlag(boolean runFlag) {
		this.runFlag = runFlag;
	}
	
	public void initCaseData(String testcaseName,String apitype,int round,String methodName,String datatag){
		this.casedata = new TestCaseData(testcaseName, apitype, round,methodName,datatag);
	}

	public TestCaseData getCasedata() {
		return casedata;
	}

	public OutputData getOutput() {
		return output;
	}

	public void initOutputData(){
		this.output = new OutputData();
	}
	
//	public void initPublicOutputData(){
//		this.pubOutput = new OutputData();
//	}
//	
//	public OutputData getPublicOutput() {
//		return pubOutput;
//	}
	
}
