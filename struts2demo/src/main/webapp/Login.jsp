<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Basic Struts 2 Application - Welcome</title>
</head>
<body>
<h1>Welcome To Struts 2!</h1>
<p>Get your own personal hello by filling out and submitting this form.</p>
<s:property value="loginName"/>&emsp;${password}
<s:form action="login.action" method="POST">
    <s:textfield name="loginName" label="用户名"/>
    <s:password name="password" label="密码"/>
    <s:submit value="登录"/>
</s:form>
</body>
</html>