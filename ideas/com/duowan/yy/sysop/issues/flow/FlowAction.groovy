package com.duowan.yy.sysop.issues.flow

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement @XmlAccessorType(XmlAccessType.FIELD)
class FlowAction {

	String name;
	String label;
	String description;
	
	FlowCode	precondition;
	
	String	defaultTarget;
	
	@XmlTransient
	List<FlowCode> preActions = []

	@XmlTransient
	List<FlowCode> actions;
	
	@XmlTransient
	List<FlowCode> postActions;
	
	void setPrecondition(Closure<?> code){
		precondition = new FlowCode(compiled: code, source: "//unknown code")
	}
	void setPrecondtion(String source){
		precondition = new FlowCode(source: source)
	}
}
