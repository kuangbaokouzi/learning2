<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Entor图书管理系统-Loign</title>
</head>
<body>
<h1>Welcome To Entor图书管理系统!</h1>
<p><s:property value="messageStore.message"/></p>
<form action="login" method="post">
    用户名：<input type="text" name="loginName">
    密码：<input type="password" name="password">
    <input type="submit" value="登录">
</form>
</body>
</html>