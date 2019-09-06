<%@taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@page isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>

<spring:form action="register" method="post" modelAttribute="user" >
<div style="color:red">
<spring:errors path="firstName"> </spring:errors><br>

<spring:errors path="lastName"> </spring:errors><br>
<spring:errors path="address"> </spring:errors><br>

<spring:errors path="mobileNo"> </spring:errors><br>
<spring:errors path="username"> </spring:errors><br>

<spring:errors path="password"> </spring:errors><br>
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
Mobile No: <spring:input type="number" path="mobileNo" name="qty"/><br>
</td>

<td>
Username: <spring:input type="text" path="username" name="qty"/><br>
</td>

<td>
Password:  <spring:input type="password" path="password" name="qty"/><br>
</td>
</tr>

<input type="submit" value="Register"/>
</spring:form>
</body>
</html>