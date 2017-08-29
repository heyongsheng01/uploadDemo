<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>list</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<style type="text/css">
a:link {
	text-decoration: none;
}

a:visited {
	text-decoration: none;
}

a:hover {
	color: #999999;
	text-decoration: underline;
}
</style>
</head>
<body>
	<br />
	<%
		List<String> list = (List<String>) request.getAttribute("maps");
		for (int i = 0; i < list.size(); i++) {
			String name = list.get(i);
	%>
	<a href="<%=basePath%>fileOperate/download?filename=<%=name%>"><%=name%></a>
	<br>
	<br>
	<%
		}
	%>
	<br />
	<br />
	<a href="<%=basePath%>fileOperate/upload.jsp">上传文件</a>
</body>
</html>