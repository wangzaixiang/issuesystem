package com.duowan.yy.sysop.issues.flow

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
class Value {	
	String	label;
	String	text;
}

@XmlAccessorType(XmlAccessType.FIELD)
class Field {

	String name;
	String type;
	String category;
	String label;
	String description;
	String inputtype;
	int	length;
	String	pattern;
	String	constraint;
	boolean required; 
	Value[]	values;
}
