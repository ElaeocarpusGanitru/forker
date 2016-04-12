<%@ page language="java" pageEncoding="UTF-8"%>
<html>
<head>
	<title>Itcast OA</title>
	<%@ include file="/WEB-INF/jsp/public/public.jspf"%>	
	<script type="text/javascript" src="${pageContext.request.contextPath}/script/jquery_treeview/jquery.cookie.js"></script>
</head>

	<!-- 嵌套页面 -->
	<frameset rows="100,*,25" framespacing=0 border=0 frameborder="0">
		<!-- top页面 -->
		<frame noresize name="TopMenu" scrolling="no" src="${pageContext.request.contextPath}/home_top.action">
		
		<!-- main页面 -->
		<frameset cols="180,*" id="resize">
			<!-- 左侧页面 -->
			<frame noresize name="menu" scrolling="yes" src="${pageContext.request.contextPath}/home_left.action">
			
			<!-- 右侧页面 -->
			<frame noresize name="right" scrolling="yes" src="${pageContext.request.contextPath}/home_right.action">
		</frameset>
		
		<!-- bottom页面 -->
		<frame noresize name="status_bar" scrolling="no" src="${pageContext.request.contextPath}/home_bottom.action">
	</frameset>

	<noframes><body>
</body>
</noframes></html>



