package com.duowan.yy.sysop.issues.flow;

import static org.junit.Assert.*;

import org.junit.Test;

import org.junit.Before;

import com.duowan.yy.sysop.issues.flow.impl.FlowEngineImpl

class TestFlowEngine {
	
	FlowEngine flowengine
	
	@Before
	void setup(){
		flowengine = new FlowEngineImpl()
	}

	@Test
	void testSimple(){
		
//		Flow flow = flowengine.getFlow("bug")
		Flow bugFlow = BugFlow2.BugFlow
		
		Map args = [subject: "Simple Java Bug",
			creator: "wangzx"
			]
		FlowInstance bug = flowengine.startFlow(bugFlow, args)
		
		println bug
		
		assertEquals(bug.status, "open")
		
	}
	
}
