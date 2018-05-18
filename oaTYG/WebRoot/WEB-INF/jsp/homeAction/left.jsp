<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
	<title>导航菜单</title>
	<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/blue/menu.css" />
</head>
<body style="margin: 0">
<div id="Menu"> 

    <ul id="MenuUl">
		<%-- 一级菜单 --%>
		<s:iterator value="#application.topPrivilegeList">
			<s:if test=" #session.user.hasPrivilegeByName(name) "><!-- 这个name参数是从#application.topPrivilegeList中循环的时候获取的 -->
		        <li class="level1">
		            <div onClick=" $(this).next().toggle()" class="level1Style">
		            	<img src="style/images/MenuIcon/${id}.gif" class="Icon" /> 
		            	${name}
		            </div>
		            <ul class="MenuLevel2">
		            	<%-- 二级菜单 --%>
		            	<s:iterator value="children">
			      		 	<s:if test=" #session.user.hasPrivilegeByName(name) ">
				                <li class="level2">
				                    <div class="level2Style">
				                    	<img src="style/images/MenuIcon/menu_arrow_single.gif" />
				                    	<%-- 这个超链接不适用<s:a>标签！！ --%>
				                    	<%-- <a href="${pageContext.request.contextPath}${url}.do" target="right">${name}</a> --%>
										<%-- 但是经测试现在可以使用<s:a>标签！！ --%>
										<%-- <s:a action="%{url}" target="right">${name}</s:a>	 --%>
										<a href="${pageContext.request.contextPath}${url}.do" target="right">${name}</a>
				                    </div>
				                </li>
			                </s:if>
		            	</s:iterator>
		            </ul>
		        </li>
	        </s:if>
		</s:iterator>
    </ul>
</div>
</body>
</html>
