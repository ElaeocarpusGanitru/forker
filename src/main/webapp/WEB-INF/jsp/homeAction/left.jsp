<%@ page language="java" pageEncoding="UTF-8"%>
<html>
<head>
<title>导航菜单</title>
<%@ include file="/WEB-INF/jsp/public/public.jspf"%>
<link type="text/css" rel="stylesheet" href="style/blue/menu.css" />
<script type="text/javascript">
	function topListClick(object)
	{
		$(object).next().toggle();
	}
	
</script>
</head>

<body style="margin: 0">
	<div id="Menu">
		<ul id="MenuUl">
			<s:iterator value="#application.privilegeList">
				<s:if test="#session.user.hasPrivilegeByName(name)"> 
					<li class="level1">
						<div onClick="topListClick(this);" class="level1Style">
							<img src="style/images/MenuIcon/${id}.gif" class="Icon" />
							${name}
						</div>
	
						<ul class="MenuLevel2" id="subMenu">
							<s:iterator value="children">
								<li class="level2">
									<div class="level2Style">
										<img src="style/images/MenuIcon/menu_arrow_single.gif" /> <a
											target="right"
											href="${pageContext.request.contextPath}${url}.action">
											${name}</a>
									</div>
								</li>
							</s:iterator>
						</ul>
					</li>
					</s:if>
			</s:iterator>
		</ul>
	</div>
</body>
</html>