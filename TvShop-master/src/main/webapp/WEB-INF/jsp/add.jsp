<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
      <%@taglib prefix="spring"  uri="http://www.springframework.org/tags/form"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Tv </title>
</head>
<body>

<h1> Add new Tv to the database</h1>

<h3>${requestScope.tvAddStatusMessage}</h3>
    <spring:form method="POST" action="addtv" modelAttribute="tv" enctype="multipart/form-data">
    		
    		<div style="color:red">
				<spring:errors path="tvName"> </spring:errors><br>

				<spring:errors path="tvBrand"> </spring:errors><br>
				<spring:errors path="tvPrice"> </spring:errors><br>

				<spring:errors path="tvDescription"> </spring:errors><br>

				</div>
             <table>
                <tr>
                    <td><spring:label path="tvName">Name: </spring:label></td>
                    <td><spring:input path="tvName"/></td>
                </tr>
                <tr>
                    <td><spring:label path="tvBrand">Brand: </spring:label></td>
                    <td><spring:input path="tvBrand"/></td>
                </tr>
                <tr>
                    <td><spring:label path="tvPrice">Price: </spring:label></td>
                    <td><spring:input path="tvPrice"/></td>
                </tr>
                
                <tr>
                    <td><spring:label path="tvDescription">Description: </spring:label></td>
                    <td><spring:input path="tvDescription"/></td>
                </tr>
                
                 <tr> 
                     <td> Select TV Image:  </td>
                     <td><input type="file" name="file"/></td> 
                 </tr> 
                <tr>
                    <td><input type="submit" value="Add"/></td>
                </tr>
            </table>
        </spring:form>

</body>
</html>