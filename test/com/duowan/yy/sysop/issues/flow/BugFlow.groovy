package com.duowan.yy.sysop.issues.flow

		
Flow("bug") {
	fields {
		subject
		creator 
		priority 
		module 
		version 
		body 
		create_date
		due-date
	}
	
	statuses {
		
	}
	
	
	Status("open") {
		
	}
	Status("in-progress"){
		
	}
	Status("resolved") {
		
	}
	Status("close") {
		
	}
	Status("reopen") {
		
	}
	
	Action("close") {
		
		precondition: { it.status != "close" }
		
		Input("note") 
		
		defaultTarget: "close"
	}
	
}
		
