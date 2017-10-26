package com.xyuan.ngtest.Helper;

import java.util.HashMap;
import java.util.Map;

import com.xyuan.ngtest.datapojo.TestCaseData;
import com.xyuan.ngtest.datapojo.RoundData;
import com.xyuan.ngtest.datapojo.ThreadData;

//公共数据对象，针对多线程处理，根据不同线程ID写数据或拿数据
public class PublicDataHelper {
	private static Map<Long,ThreadData> instance = new HashMap<Long,ThreadData>();
	private static RoundData round;
	
	public static synchronized void newPublicData(Long threadID){
		instance.put(new Long(threadID), new ThreadData());
	}
	
	public static synchronized void initRoundData(int newRound){
		round = new RoundData(newRound);
	}
	
	public static synchronized RoundData getRound(){
		return round;
	}
	
	public static synchronized ThreadData getIns(Long threadID){
		return instance.get(threadID);
	}
	
	public static synchronized TestCaseData getCasedata(Long threadID){
		return instance.get(threadID).getCasedata();
	}

}

