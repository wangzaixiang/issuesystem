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
	List<FlowCode> presave = []
	
	@XmlTransient
	List<FlowCode> postsave = []
	
	void setPrecondition(Object code){
		precondition = FlowCode.from code
	}
	
}
