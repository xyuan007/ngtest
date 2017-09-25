package com.xyuan.ngtest.datapojo;

public class RoundData {
	private int round;
	private int apitotal;
	private int success;
	private int fail;
	private int notrun;
	
	public RoundData(int round){
		this.round = round;
	}
	
	public synchronized int getRound() {
		return round;
	}
	public synchronized int getApitotal() {
		return apitotal;
	}
	public synchronized int getSuccess() {
		return success;
	}
	public synchronized int getFail() {
		return fail;
	}
	public synchronized int getNotrun() {
		return notrun;
	}
	
	public synchronized void incSuccess(){
		this.success++;
		this.apitotal++;
	}
	
	public synchronized void incNotrun(){
		this.notrun++;
		this.apitotal++;
	}
	
	public synchronized void incFail(){
		this.fail++;
		this.apitotal++;
	}

}
