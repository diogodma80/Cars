<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Insert title here</title>
</head>
<body>
	<h1>Hello App Engine!</h1>
	<form action="<%=request.getContextPath()%>/hello" method="post">
		Name: <input type="text" name="name"> 
		<br> <br> 
		Last name <input type="text" name="lastName"> 
		<br>
		<input type="submit" name="Send">
	</form>
	<hr>
	<a href='/hello'>The servlet</a>
</body>
</html>