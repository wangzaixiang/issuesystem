package com.duowan.yy.sysop.issues.flow.impl

import java.util.Map;

import com.duowan.yy.sysop.issues.flow.Flow;
import com.duowan.yy.sysop.issues.flow.FlowCode;
import com.duowan.yy.sysop.issues.flow.FlowEngine;
import com.duowan.yy.sysop.issues.flow.FlowInstance;
import com.duowan.yy.sysop.issues.flow.FlowStatus;

class FlowEngineImpl implements FlowEngine {

	@Override
	public Flow getFlow(String name) {
		assert false
	}

	@Override
	public FlowInstance startFlow(Map args, Flow flow) {
		
		FlowStatus start = flow.getStatus(flow.startStatus)
		FlowInstance instance = new FlowInstance(args)
		
		// id "${flow.name}-${nextid}
		instance._id = "${flow.name}-0"
		instance.status = flow.startStatus;
		if(start.onEntry){
			executeCode(start.onEntry, instance)
		}
		
		return instance;
	}

	void executeCode(FlowCode code, FlowInstance instance){
		
	}
	
}
