package com.java.oa;

import org.jbpm.api.ProcessEngine;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class testProcessEngine {
	// 测试ProcessEngine
		@Test
		public void testProcessEngine() throws Exception {
			ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
			ProcessEngine processEngine = (ProcessEngine) ac.getBean("processEngine");
			System.out.println(processEngine);
		}
 
}
