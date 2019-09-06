<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
      <%@taglib prefix="form"  uri="http://www.springframework.org/tags/form"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome Admin</title>
</head>
<body>

<h1> Welcome Admin </h1>

<c:choose>
<c:when test="${role eq 'admin'}">
<ul>
  <li><a href="add">Add new TV </a></li>
  <li><a href="displayTvAdmin">Display All TVs</a></li>
  <li><a href="#contact">Delete TV</a></li>
  <li><a href="modify">Modify Information of TV</a></li>
  <li><a href="logout">Log out</a></li>
</ul>
</c:when>

</c:choose>

</body>
</html>