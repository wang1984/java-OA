<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<html>
<head>
    <title>用户列表</title>
    <%@ include file="/WEB-INF/jsp/common/common.jsp"%>
</head>
<body>

<div id="Title_bar">
    <div id="Title_bar_Head"> 
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 用户管理
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<div id="MainArea">
    <table cellspacing="0" cellpadding="0" class="TableStyle">
       
        <!-- 表头-->
        <thead>
            <tr align=center valign=middle id=TableTitle>
                <td width="100">登录名</td>
                <td width="100">姓名</td>
                <td width="100">所属部门</td>
                <td width="200">岗位</td>
                <td>备注</td>
                <td>相关操作</td>
            </tr>
        </thead>
        
        <!--显示数据列表-->
        <tbody id="TableData" class="dataContainer" datakey="userList">
        
        <s:iterator value="recordList">
            <tr class="TableDetail1 template">
                <td>${loginName}&nbsp;</td>
                <td>${name}&nbsp;</td>
                <td>${department.name}&nbsp;</td>
                <td>
                	<s:iterator value="roles">
                		${name}
                	</s:iterator>&nbsp;
                </td>
                <td>${description}&nbsp;</td>
                <td>
                     <%-- 版本 0 没有权限判断   
                    <s:a action="user_delete?id=%{id}" onclick="return delConfirm()">删除</s:a>
                    <s:a action="user_editUI?id=%{id}">修改</s:a>
					<s:a action="user_initPassword?id=%{id}" onclick="return window.confirm('您确定要初始化密码为1234吗？')">初始化密码</s:a>
                     --%>
                    <%-- 版本 1  加个判断  
                       	 现在有两个标签组成分别完成权限判断和连接的功能
                   
                	<s:if test="#session.user.hasPrivilegeByName('用户删除')">
	                	<s:a action="user_delete?id=%{id}" onclick="return delConfirm()">删除</s:a>
                	</s:if>
                	<s:if test="#session.user.hasPrivilegeByName('用户修改')">
                    	<s:a action="user_editUI?id=%{id}">修改</s:a>
                    </s:if>
                	<s:if test="#session.user.hasPrivilegeByName('用户初始化密码')">
						<s:a action="user_initPassword?id=%{id}" onclick="return window.confirm('您确定要初始化密码为1234吗？')">初始化密码</s:a>
					</s:if>
					 --%>
					<%-- 版本 2  用自定义标签实现（判断权限的NAME）
					               自定义标签完成权限判断和连接的功能
					<oa:a action="user_delete?id=%{id}" privName="用户删除">删除</oa:a>
					<oa:a action="user_editUI?id=%{id}" privName="用户修改">修改</oa:a>
					--%>
					<%-- 版本 3.1  用自定义标签实现（判断权限的URL）
					<oa:a action="user_delete?id=%{id}" privUrl="user_delete">删除</oa:a>
					<oa:a action="user_editUI?id=%{id}" privUrl="user_edit">修改</oa:a>
					--%>
					<%-- 版本 3.2  用自定义标签实现（判断权限的URL，而URL在action中就有，但需要做一下处理才能得到我们想要的格式）
					<oa:a action="user_delete?id=%{id}">删除</oa:a>
					<oa:a action="user_editUI?id=%{id}">修改</oa:a>
					--%> 
					<%-- 版本 4 还是s:a标签，我们要修改<s:a>标签所对应的源码（最好的形式）           
						     源码的位置： 按住ctrl件鼠标点击s:a 找到tld文件     org.apache.struts2.views.jsp.ui.AnchorTag 
						     源码是只读的我们修改不了：  
						    在我们的工程下新建一个包和源码的包一致，新建一个同名的类  ，我们的工程下现在有两个同样的类，先找到谁就用谁
						    我们自己的同名类最终在tomcat的webapps\oaTYG\WEB-INF\classes目录中 而这个目录中的类是优先加载的，然后才去加载webapps\oaTYG\WEB-INF\lib中的类       
					--%>
					<s:a action="user_delete?id=%{id}" onclick="return delConfirm()">删除</s:a>
					<s:a action="user_editUI?id=%{id}">修改</s:a>
					<s:a action="user_initPassword?id=%{id}" onclick="return window.confirm('您确定要初始化密码为1234吗？')">初始化密码</s:a>
					
					
                </td>
            </tr>
        </s:iterator> 
            
        </tbody>
    </table>
    
    <!-- 其他功能超链接 -->
    <div id="TableTail">
        <div id="TableTail_inside">
            <s:a action="user_addUI"><img src="${pageContext.request.contextPath}/style/images/createNew.png" /></s:a>
        </div>
    </div>
</div>
<%-- 分页信息 --%>
<s:form id="pageForm" action="user_list"></s:form>
<%@ include file="/WEB-INF/jsp/common/pageView.jsp" %>

</body>
</html>

