package com.duowan.yy.sysop.issues.flow

import javax.xml.bind.annotation.XmlAccessType
import javax.xml.bind.annotation.XmlAccessorType
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement @XmlAccessorType(XmlAccessType.FIELD)
class Flow {
	
	class FlowRuntime {
		
		Map<String, FlowStatus> statusByName = statuses.collectEntries { it -> [it.name, it] }
		Map<String, Field> fieldsByName = fields.collectEntries { it -> [it.name, it] };
		Map<String, FlowAction> actionsByName = actions.collectEntries { it-> [it.name, it] }
		
		FlowStatus getStatus(String name) {
			return statusByName[name]
		}
		Field getField(String name){
			return fieldsByName[name]
		}
		Field getAction(String){
			return actionsByName[name]
		}
	}
	
	String	name;
	
	String	label;
	
	String	description;
	
	String	startStatus;
	
	@XmlElementWrapper(name="fields") @XmlElement(name="field")
	List<Field>	fields = []

	@XmlElementWrapper(name="statuses") @XmlElement(name="status")
	List<FlowStatus> statuses = []

	@XmlElementWrapper(name="actions") @XmlElement(name="action")
	List<FlowAction> actions = []
	
	@XmlTransient
	@Delegate
	FlowRuntime _runtime;
	
	Flow postInit(){
		_runtime = new FlowRuntime()
		return this
	}
}
