<%@ page import="java.util.List" %>
<%@ page import="com.in6k.user.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
      <h1>Users</h1>
      <%
          List<User> users = (List<User>)request.getAttribute("usersList");
          for (User u: users) {
      %>
      <div style="border: 4px double #808080; width: 300px; margin: 10px;">
          <h3><%="Name: " + u.getName()%></h3>
          <h3><%="Second name: " + u.getSecondName()%></h3>
          <h3><%="Birthday: " + u.getBirthday()%></h3>
          <h3><%="Email: " + u.getEmail()%></h3>
          <h3><%="Password: " + u.getPassword()%></h3>
          <a href="">Edit</a>
      </div>
      <%
          }
      %>
</body>
</html>