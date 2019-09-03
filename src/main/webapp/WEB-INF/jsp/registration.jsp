<%@taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@page isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>

<spring:form action="register" method="post" modelAttribute="user" >
<div style="color:red">errors
<spring:errors path="firstName"> </spring:errors>

<spring:errors path="address"> </spring:errors>
</div>
<tr>
<td>
First Name : <spring:input type="text" path="firstName" name="name"/><br>
</td>
<td>
Last Name : <spring:input type="text" path="lastName" name="price"/><br>
</td>
<td>
Address: <spring:input type="text" path="address" name="qty"/><br>
</td>

<td>
Mobile No: <spring:input type="text" path="mobileNo" name="qty"/><br>
</td>

<td>
Username: <spring:input type="text" path="username" name="qty"/><br>
</td>

<td>
Password:  <spring:input type="text" path="password" name="qty"/><br>
</td>
</tr>

<input type="submit" value="Register"/>
</spring:form>
</body>
</html>