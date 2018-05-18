<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%-- 分页信息 --%>
<div id=PageSelectorBar>
	<div id=PageSelectorMemo>
		页次：${currentPage}/${pageCount }页 &nbsp;
		每页显示：${pageSize }条 &nbsp;
		总记录数：${recordCount }条
	</div>
	<div id=PageSelectorSelectorArea>

		<a href="javascript: gotoPage(1)" title="首页" style="cursor: hand;">
			<img src="${pageContext.request.contextPath}/style/blue/images/pageSelector/firstPage.png"/>
		</a>
		
		<%-- 页码列表 --%>
		<s:iterator begin="%{beginPageIndex}" end="%{endPageIndex}" var="num">
			<%-- 非当前页，有链接 --%>
			<s:if test=" #num != currentPage ">
				<span class="PageSelectorNum" style="cursor:pointer"  onClick="gotoPage(${num});" >${num}</span>
			</s:if>
			<%-- 当前页，没有链接 --%>
			<s:else>
				<span class="PageSelectorNum PageSelectorSelected">${num}</span>
			</s:else>
		</s:iterator>

		<a href="javascript: gotoPage(${pageCount})" title="尾页" style="cursor: hand;">
			<img src="${pageContext.request.contextPath}/style/blue/images/pageSelector/lastPage.png"/>
		</a>
		
		转到：
		<select id="pn" onchange="gotoPage( this.value )">
			<s:iterator begin="1" end="%{pageCount}" var="num">
				<option value="${num}">${num}</option>
			</s:iterator>
		</select>
		
		<%-- 让select默认选中当前页 --%>
		<script type="text/javascript">
			$("#pn").val( "${currentPage}" );
		</script>
		
	</div>
</div>

<script type="text/javascript">
	/**
	 * 转到指定的页码
	 * @param {Object} pageNum
	 */
	function gotoPage( pageNum ){
		// 方式一： （只适用于forum_show的链接别的页面用就会有问题）
		//window.location.href = "forum_show.do?id=${id}&pageNum=" + pageNum;
		 
		// 方式二：
		$("#pageForm").append("<input type='hidden' name='pageNum' value='" + pageNum + "'>"); // 添加pageNum表单字段
		$("#pageForm").submit(); // 提交表单
	}
</script>