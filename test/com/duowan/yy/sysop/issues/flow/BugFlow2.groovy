package com.duowan.yy.sysop.issues.flow

import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBContext;

import com.duowan.yy.sysop.issues.*

class BugFlow2 {

	static Flow BugFlow = new FlowBuilder().flow("bug", label:"程序故障") {

		fields {
			field("subject", label:"主题")
			field("creator", label:"创建者")
			field("priority", label:"优先级")
			field("module", label:"模块")
			field("version", label:"版本")
			field("body",	label:"详细描述")
			field("create-date", label:"创建时间")
			field("due-date", label: "过期时间")
		}
		startStatus = "open"
		
		statuses {

			status("open") {
				
				onEntry = {
					
				}
				
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
			
			action("<init>") {
				presave {	// before save
					println "A new bug entered [id:${self._id} creator:${self.creator} subject:${self.subject}]"
				}
				postsave {
					println "send a email to user [id:${self._id} creator:${self.creator} subject:${self.subject}]"
				}
			}

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