package com.java.oa;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class testSessionFactory {
	@Test
	public void testSessionFactory(){
		new ClassPathXmlApplicationContext("applicationContext.xml").getBean("sessionFactory");
	}
 
}
