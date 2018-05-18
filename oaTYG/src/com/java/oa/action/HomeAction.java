package com.java.oa.action;

import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

@Controller
//这个控制器中没有公共资源，也就没有线程安全问题所以可以是单例
public class HomeAction extends ActionSupport {
    //加载index.jsp
	public String index() throws Exception {
		return "index";
	}
	
	//加载index.jsp中的top.jsp
	public String top() throws Exception {
		return "top";
	}
	
	//加载index.jsp中的bottom.jsp
	public String bottom() throws Exception {
		return "bottom";
	}
	
	//加载index.jsp中的left.jsp
	public String left() throws Exception {
		return "left";
	}
	
	//加载index.jsp中的right.jsp
	public String right() throws Exception {
		return "right";
	}

}
