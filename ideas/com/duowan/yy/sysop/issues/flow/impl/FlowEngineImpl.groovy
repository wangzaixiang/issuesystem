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

	/**
	 * 启动一个流程，实际上是执行流程的<init> action
	 */
	@Override
	public FlowInstance startFlow(Flow flow, Map<String,Object> args) {
		
		FlowInstance instance = new FlowInstance(args)
		
		int nextid = nextFlowInstanceId(flow.name);
		instance._id = "${flow.name}-${nextid}"
		
		FlowAction init = flow.getAction("<init>")
		if(init == null)
			throw new RuntimeException("每个流程必须有 <init> 动作");
		
		executeFlowAction(flow, instance, init);
			
		return instance;
	}
	
	void executeFlowAction(Flow flow, FlowInstance instance, FlowAction action) {

		// check precondition
		
		if(action.defaultTarget != null)
			instance.status = action.defaultTarget;
			
		action.presave.each { FlowCode code->
			if(code.compiled) {
				Closure closure = code.compiled;
				Binding binding = new Binding(self: instance)
				closure.setDelegate(binding)
				closure.setResolveStrategy(Closure.DELEGATE_ONLY)
				closure.call()
			}
		}
		
		mongo["issues"].save(instance as Map)
		
		action.postsave.each { FlowCode code->
			if(code.compiled) {
				Closure closure = code.compiled;
				Binding binding = new Binding(self: instance)
				closure.setDelegate(binding)
				closure.setResolveStrategy(Closure.DELEGATE_ONLY)
				closure.call()
			}
		}

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
