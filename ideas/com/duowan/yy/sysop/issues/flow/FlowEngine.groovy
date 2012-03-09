package com.duowan.yy.sysop.issues.flow

interface FlowEngine {

	Flow getFlow(String name);
	
	FlowInstance startFlow(Map args, Flow flow);
	
}
