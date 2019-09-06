<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
      <%@taglib prefix="spring"  uri="http://www.springframework.org/tags/form"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Modify TV Information</title>
</head>
<body>

<spring:form method="POST" action="modifytv" modelAttribute="modifytv" >
    		
    		<div style="color:red">
				<spring:errors path="tvName"> </spring:errors><br>

				<spring:errors path="tvBrand"> </spring:errors><br>
				<spring:errors path="tvPrice"> </spring:errors><br>

				<spring:errors path="tvDescription"> </spring:errors><br>

				</div>
             <table>
                <tr>
                    <td><spring:label path="tvName">Name: </spring:label></td>
                    <td><spring:input path="tvName" value="${modifytv.getTvName()}" /></td>
                </tr>
                <tr>
                    <td><spring:label path="tvBrand">Brand: </spring:label></td>
                    <td><spring:input path="tvBrand" value="${modifytv.getTvBrand()}"  /></td>
                </tr>
                <tr>
                    <td><spring:label path="tvPrice">Price: </spring:label></td>
                    <td><spring:input path="tvPrice" value="${modifytv.getTvPrice()}" /></td>
                </tr>
                
                <tr>
                    <td><spring:label path="tvDescription">Description: </spring:label></td>
                    <td><spring:input path="tvDescription" value="${modifytv.getTvDescription()}"  /></td>
                </tr>
                
                <tr>
                    <td><input type="submit" value="Modify"/></td>
                </tr>
            </table>
        </spring:form>


</body>
</html>