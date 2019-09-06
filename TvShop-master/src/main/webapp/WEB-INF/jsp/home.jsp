<%@page import="org.springframework.web.servlet.ModelAndView"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<title> Welcome to CT Electronics </title>
</head>

<body>

<c:choose>
	<c:when test="${username != null}">
   <h1> <a href="userInfo"> ${username}  </a></h1>
   <h1> <a href="logout"> Logout   </a></h1>
   </c:when>
 <c:when test="${username == null }">
 <h1><a href="login"> Register/Login</a>  </h1>
 </c:when>
 
 
 </c:choose>


<c:forEach var="entry" items="${tvList}"> 

<div class="card">
 <a href="tvinfo?id=${entry.getTvId()}&name=${entry.getTvName()}&brand=${entry.getTvBrand()}&price=${entry.getTvPrice()}&des=${entry.getTvDescription()}"> <img alt="Bravia TV" src="${entry.getTvPath()}" style="width:425px;height:227px;"  > </a>
  <h1>${entry.getTvName()}</h1>
  <p class="price">Rs. ${entry.getTvPrice()}</p>
  <p>${entry.getTvDescription()}</p>
  <p><a href="tvinfo?id=${entry.getTvId()}&name=${entry.getTvName()}&brand=${entry.getTvBrand()}&price=${entry.getTvPrice()}&des=${entry.getTvDescription()}"> <button>Add to Cart</button> </a></p>
  <c:if test="${ role eq 'admin' }"> 
  <p><a href="delete?id=${entry.getTvId()}&name=${entry.getTvName()}&brand=${entry.getTvBrand()}&price=${entry.getTvPrice()}&des=${entry.getTvDescription()}"> <button>Delete</button> </a></p>
   <p><a href="modify?id=${entry.getTvId()}&name=${entry.getTvName()}&brand=${entry.getTvBrand()}&price=${entry.getTvPrice()}&des=${entry.getTvDescription()}"> <button>Modify Information</button> </a></p>
   </c:if>
</div>

</c:forEach>



<style>

.card {
  box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);
  max-width: 500px;
  margin: auto;
  text-align: center;
  font-family: arial;
}

.price {
  color: grey;
  font-size: 22px;
}

.card button {
  border: none;
  outline: 0;
  padding: 12px;
  color: white;
  background-color: #000;
  text-align: center;
  cursor: pointer;
  width: 100%;
  font-size: 18px;
}

.card button:hover {
  opacity: 0.7;
}


</style>


</body>





</html>
