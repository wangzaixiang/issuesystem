package com.duowan.yy.sysop.issues.flow

import java.util.Map

interface FlowEngine {

	Flow getFlow(String name);
	
	FlowInstance startFlow(Flow flow, Map<String,Object> args);
	
}
