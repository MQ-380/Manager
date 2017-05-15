<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
    	用户登录<br/>
		<s:fielderror></s:fielderror>
		<table>
			<tr>
				<td>
				<form action="userLogin.action" method="post">
					<s:textfield name="adid" label="用户名" size="20"></s:textfield>
					<s:textfield name="adpwd" label="密　码" size="20"></s:textfield>
					<s:submit value="提交"></s:submit>
				</form>
				</td>
			</tr>
		</table>	
  </body>
</html>
