<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>错误消息</title>
</head>
<body>
<s:if test="%{#request.errorMsg==null}">
	<p>对不起，系统发生了未知的错误</p>
</s:if>
<s:else>
	<p>${requestScope.errorMsg}</p>
</s:else>
</body>
</html>