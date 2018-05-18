package com.java.oa.util;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.java.oa.domain.Privilege;
import com.java.oa.service.PrivilegeService;

public class OAInitListener implements ServletContextListener {

	private Log log = LogFactory.getLog(OAInitListener.class);

	// 初始化
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext application = sce.getServletContext();
 
		// 获取Spring的监听器中创建的Spring容器对象
		ApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(application);
		// 从Spring的容器中取出PrivilegeService的对象实例. 由于在privilegeServiceImpl中使用的注解@Service("privilegeService")所以这里获取的是privilegeService
		PrivilegeService privilegeService = (PrivilegeService) ac.getBean("privilegeService");

		// 1，查询所有顶级的权限列表并放到application作用域中
		List<Privilege> topPrivilegeList = privilegeService.findTopList();
		application.setAttribute("topPrivilegeList", topPrivilegeList);
		log.info("====== topPrivilegeList已经放到application作用域中了！ ======");
		
		// 2，查询出所有的权限的URL集合并放到application作用域中
		List<String> allPrivilegeUrls = privilegeService.getAllPrivilegeUrls();
		application.setAttribute("allPrivilegeUrls", allPrivilegeUrls);
		log.info("====== allPrivilegeUrls已经放到application作用域中了！ ======");
	}

	// 销毁
	public void contextDestroyed(ServletContextEvent sce) {

	}

}
