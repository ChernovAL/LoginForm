<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>

<%
    if (request.getAttribute("errors") != null) {
%>
    <div style="color: red;"><%=request.getAttribute("errors")%></div>
<%
    }
%>

<%!
    private String attributeNotNull(HttpServletRequest request,String attribute) {
        return (request.getAttribute(attribute) != null) ? (String) request.getAttribute(attribute) : "";
    }
%>
<input type="hidden" name="errors">
<form id="user_data" method="post" action="/val">
    <h3>Login</h3>
    Name:</br>
    <input type="text" name="user_name" placeholder="Name" value="<%=attributeNotNull(request,"user_name")%>"></br>
    Second name:</br>
    <input type="text" name="second_name" placeholder="Second name" value="<%=attributeNotNull(request,"second_name")%>"></br>
    Email:</br>
    <input type="email" name="email" placeholder="Email" value="<%=attributeNotNull(request,"email")%>"></br>
    Password: </br>
    <input type="password" name="password" placeholder="Password"></br>
    Confirm : </br>
    <input type="password" name="confirm" placeholder="Confirm password"></br>
    Birthday: </br>
    <input type="date" name="birthday" placeholder="01/01/2012" value="<%=attributeNotNull(request,"birthday")%>"></br>
    <input type="submit" value="Ok">
    <input type="reset" value="Clear">
</form>
<a href="/list">List</a>
</body>
</html>
