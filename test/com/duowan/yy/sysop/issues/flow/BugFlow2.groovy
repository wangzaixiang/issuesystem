package com.duowan.yy.sysop.issues.flow

import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBContext;

import com.duowan.yy.sysop.issues.*

class BugFlow2 {

	static Flow BugFlow = new FlowBuilder().flow("bug", label:"程序故障") {

		fields {
			field("subject")
			field("creator")
			field("priority")
			field("module")
			field("version")
			field("body")
			field("create-date")
			field("due-date")
		}
		startStatus = "open"
		statuses {

			status("open") {

				action("in-progress") {
					description = "正在处理"
					defaultTarget = "in-progress"
				}
			}

			status("in-progress") {
			}
			status("resolved")
			status("closed")
			status("reopen")
		}

		actions {

			action("close", label:"关闭任务") {
			}
			action("asign", label:"分配任务") {
			}
			action("re-open", label: "重新打开") {
				precondition = {
					["resolved", "closed"].isCase( status )
				}
			}
		}
	}.postInit()

	public static void main(String[] args) {

		System.out.println(BugFlow);

		JAXBContext ctx = JAXBContext.newInstance(Flow);
		def marshaller = ctx.createMarshaller();
		marshaller.setProperty(marshaller.JAXB_FORMATTED_OUTPUT, true)
		marshaller.marshal(BugFlow, System.out)
	}

}