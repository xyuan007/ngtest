package com.xyuan.ngtest.datapojo;

public class ThreadData {
	private CaseData casedata;
	private OutputData output;
	private OutputData pubOutput;
	private boolean  runFlag;

	public boolean getRunFlag() {
		return runFlag;
	}

	public void setRunFlag(boolean runFlag) {
		this.runFlag = runFlag;
	}
	
	public void initCaseData(String modelName,String apitype,int round,String testcasename){
		this.casedata = new CaseData(modelName, apitype, round,testcasename);
	}

	public CaseData getCasedata() {
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
