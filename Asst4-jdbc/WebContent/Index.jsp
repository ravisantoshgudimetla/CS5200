<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="edu.neu.cs5200.Asst4.ds.dao.*,edu.neu.cs5200.Asst4.ds.model.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
Hello World
<%
User user= new User();
user.setUname("ravi");
user.setFname("test");
user.setLname("test1");
user.setEmail("ravi@example.com");
user.setDob(22 July,1990);
UserDAO userdao= new UserDAO();
%>
</body>
</html>