package com.duowan.yy.sysop.issues.flow.impl

import java.util.Map;

import com.duowan.yy.sysop.issues.flow.Flow;
import com.duowan.yy.sysop.issues.flow.FlowAction;
import com.duowan.yy.sysop.issues.flow.FlowCode;
import com.duowan.yy.sysop.issues.flow.FlowEngine;
import com.duowan.yy.sysop.issues.flow.FlowInstance;
import com.duowan.yy.sysop.issues.flow.FlowStatus;
import com.gmongo.GMongo;
import com.mongodb.DB;
import com.mongodb.DBObject;

class FlowEngineImpl implements FlowEngine {
	
	DB mongo;
	
	FlowEngineImpl(){
		GMongo gMongo = new GMongo("localhost");
		mongo = gMongo.getDB("issuedb");
	}

	public Flow getFlow(String name) {
		assert false
	}

	@Override
	public FlowInstance startFlow(Flow flow, Map<String,Object> args) {
		
		FlowStatus start = flow.getStatus(flow.startStatus)
		FlowInstance instance = new FlowInstance(args)
		
		int nextid = nextFlowInstanceId(flow.name);
		instance._id = "${flow.name}-${nextid}"
		instance.status = flow.startStatus;
		
		FlowAction init = flow.getAction("<init>")
		if(init != null){
			init.presave.each { FlowCode code->
				if(code.compiled) {
					Closure closure = code.compiled;
					Binding binding = new Binding(self: instance)
					closure.setDelegate(binding)
					closure.setResolveStrategy(Closure.DELEGATE_ONLY)
					closure.call()
				}
			}
		}
		
		mongo["issues"].insert(instance as Map)
		
		if(init != null){
			init.postsave.each { FlowCode code->
				if(code.compiled) {
					Closure closure = code.compiled;
					Binding binding = new Binding(self: instance)
					closure.setDelegate(binding)
					closure.setResolveStrategy(Closure.DELEGATE_ONLY)
					closure.call()
				}
			}
		}

		return instance;
	}
	
	int	nextFlowInstanceId(String flowName) {
		List<DBObject> results = mongo["paras"].find(_id: "flowid-${flowName}").toArray()
		if(results.size == 0){
			mongo["paras"].insert(_id: "flowid-${flowName}", value: 2);
			return 1;
		}
		else {
			int current = results[0]["value"] as int
			mongo["paras"].save(_id: "flowid-${flowName}", value: current+1);
			return current;
		}
	}

	void executeCode(FlowCode code, FlowInstance instance){
		if(code.compiled != null){
			Closure<?> compiled = code.compiled
			Binding binding = new Binding(self: instance)
			compiled.setDelegate(binding);
			compiled.setResolveStrategy(Closure.DELEGATE_ONLY)
			compiled.call();
		}
		else {	// TODO compile the script and run
			
		}
	}


	
}
