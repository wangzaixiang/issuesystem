package com.duowan.yy.sysop.issues.flow

class FlowInstance {

	Map<String, Object>items = [:]
	
	String getStatus(){
		return items["status"]
	}
	
	void setStatus(String status){
		items["status"] = status
	}
	
	String get_id(){
		return items["_id"]
	}
	
	void set_id(String id){
		items["_id"] = id;
	}
	
	void propertyMissing(String name, Object value){
		//TODO check
		items[name] = value
	}
	Object propertyMissing(String name){
		return items[name]
	}
	
	@Override
	public String toString() {
		return items.toString()
	}
}
