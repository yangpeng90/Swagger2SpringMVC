<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>插入用户</title>
</head>
<body>
	<form action="${pageContext.request.contextPath}/userInfo/insertUser" method="post">
		id:<input type="text" name="id" /><br/>
		nickname:<input type="text" name="nickname" /><br/>
		age:<input type="text" name="age" /><br/>
		createTime:<input type="text" name="createTime" /><br/>
		<input type="submit" value="提交">
	</form>
</body>
</html>