package com.xyuan.ngtest.process;

import org.dom4j.Element;

public interface IExecute {
	public String execute(Element data,Element config,String modelname) throws Exception;
}
