<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<html>
<head>
	<title>OA</title>
    <%@ include file="/WEB-INF/jsp/common/common.jsp" %>
    <link href="${pageContext.request.contextPath}/style/blue/login.css" type=text/css rel=stylesheet />
	<script type="text/javascript">
	/* window是登录页面 */
		if( window.parent != window ){//这说明window有上级页面
			window.parent.location.href = window.location.href;//将window有上级页面的地址指向本登录页面
		}
	</script>
</head>

<body leftmargin=0 topmargin=0 marginwidth=0 marginheight=0 class=PageBody >
<s:debug></s:debug>
<s:form action="loginout_login">
 
    <div id="CenterAreaBg">
        <div id="CenterArea"> 
            <div id="LogoImg"><img border="0" src="${pageContext.request.contextPath}/style/blue/images/logo.png" /></div>
            <div id="LoginInfo">
                <table border=0 cellspacing=0 cellpadding=0 width=100%>
                	<tr>
                		<td colspan="2">
                			<%-- 显示错误消息 --%>
							<div style="color:red"><s:fielderror></s:fielderror></div>
                		</td>
                	</tr>
                    <tr>
                        <td width=45 class="Subject">
                        	<img border="0" src="${pageContext.request.contextPath}/style/blue/images/login/userId.gif" />
                        </td>
                        <td>
                        	<%-- 用户名 --%>
                        	<s:textfield size="20" cssClass="TextField" name="loginName" />
                        </td>
                        <td rowspan="2" style="padding-left:10px;"><input type="image" src="${pageContext.request.contextPath}/style/blue/images/login/userLogin_button.gif"/></td>
                    </tr>
                    <tr>
                        <td class="Subject"><img border="0" src="${pageContext.request.contextPath}/style/blue/images/login/password.gif" /></td>
                        <td>
							<%-- 密码，不需要回显 --%>                        
                        	<s:password size="20" cssClass="TextField" showPassword="true" name="password" />
                        </td>
                    </tr>
                </table>
            </div>
            <div id="CopyRight"><a href="javascript:void(0)">&copy; oa </a></div>
        </div>
    </div>

</s:form>

</body>

</html>

	