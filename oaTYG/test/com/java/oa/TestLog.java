package com.java.oa;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

public class TestLog {

	private Log log = LogFactory.getLog(TestLog.class);
    
	@Test
	public void test() throws Exception {
		log.debug("这是debug级别"); // 调试
		log.info("这是info级别"); // 信息
		log.warn("这是warn级别"); // 警告
		log.error("这是error级别"); // 错误
		log.fatal("这是fatal级别"); // 严重错误
	 
	}
}
