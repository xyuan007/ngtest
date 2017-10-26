package com.xyuan.ngtest.MyAssert;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.containsString;

public class MyAssert {
	public static void asserting(String response,String expected,String mode){
		if(mode == null || mode.equals(""))
			assertThat(response,containsString(expected));
		else if(mode.equals("equal")){
			assertThat(response,equalTo(expected));
		}
	}
}
