package com.duowan.yy.sysop.issues.flow

import javax.xml.bind.annotation.XmlAccessType
import javax.xml.bind.annotation.XmlAccessorType
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

@XmlAccessorType(XmlAccessType.FIELD)
class FlowStatus {

	String name;
	String label;
	String description;
	
	@XmlElement(name="action") @XmlElementWrapper(name="actions")
	List<FlowAction> actions = []
	
	FlowCode onEntry;
	FlowCode onExit;
}
