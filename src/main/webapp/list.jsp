<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
      <h1>List</h1>
      <c:forEach var="user" items="${list}">
          <tr>
              <td><c:out value="${user}" /></td>
          </tr>
      </c:forEach>
</body>
</html>