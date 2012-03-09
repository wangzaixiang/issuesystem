package com.duowan.yy.sysop.issues.flow

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;

@XmlAccessorType(XmlAccessType.FIELD)
class FlowCode {

	String	name;
	
	String	source;
	
	@XmlTransient
	Closure<?>	compiled;
	
	static FlowCode from(String source){
		return new FlowCode(source: source)
	}
	static FlowCode from(Closure<?> closure){
		return new FlowCode(source: "//unknown source", compiled: closure)
	}
}
