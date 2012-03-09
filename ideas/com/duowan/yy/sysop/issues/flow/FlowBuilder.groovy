package com.duowan.yy.sysop.issues.flow

import java.util.Map;

class FlowBuilder extends BuilderSupport {

	FlowBuilder(){
	}

	@Override
	protected void setParent(Object parent, Object child) {
	}

	@Override
	protected Object createNode(Object name) {
		return createNode(name, [:]);
	}

	@Override
	protected Object createNode(Object name, Object value) {
		return createNode(name, [:], value);
	}

	@Override
	protected Object createNode(Object name, Map attributes) {
		return createNode(name, attributes, null);
	}

	@Override
	protected Object createNode(Object name, Map attributes, Object value) {
		Object node;
		switch(name){
			case "flow":
				node = new Flow(attributes);		break
			case "fields":
				node = [];							break
			case "field":
				node = new Field(attributes);		break
			case "statuses":
				node = [];							break
			case "status":
				node = new FlowStatus(attributes);	break
			case "actions":
				node = [];							break;
			case "action":
				node = new FlowAction(attributes);	break
		}
		if(value != null)
			node.name = value
		return node
	}

	@Override
	protected void nodeCompleted(Object parent, Object node) {
		if(parent != null)
			complete(parent, node);	
	}
	
	void complete(Flow flow, List children) {
		children.each { item->
			switch(item){
				case Field: flow.fields.add item; break
				case FlowStatus: flow.statuses.add item; break
				case FlowAction: flow.actions.add item; break
			}
		}
	}
	void complete(FlowStatus status, FlowAction action){
		status.actions.add action
	}
	
	void complete(FlowStatus status, List children){
		children.each { item->
			switch(item){
				case FlowAction: status.actions.add item; break;
				default: assert false
			}
		}
	}
	
	void complete(List parent, Object child) {
		parent.add(child)
	}
	
	void complete(Object parent, Object node) {
		System.err.println "complete ${parent.getClass()} - ${node.getClass()}"
	}
	

		
	void propertyMissing(String name, value) {
		current[name] = value
	}
}

