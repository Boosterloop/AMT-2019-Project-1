<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title>Country list</title>
</head>
<body>
  <h1>Countries</h1>
  <table>
    <c:forEach items="${countries}" var="country">
      <tr>
        <td>${country.code}</td>
        <td>${country.name}</td>
      </tr>
    </c:forEach>
  </table>
</body>
</html>
