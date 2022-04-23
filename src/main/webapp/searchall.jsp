<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Search Node</title>
</head>
<body>
<h2>Search Node</h2>
<table>
  <thead>
  </thead>
  <<th>№</th><th>Name</th><th>Date</th><th>importance</th></tr>
  <c:forEach var="notes" items="${note}">
    <td>${notes.id}</td>
    <td>${notes.sentence}</td>
    <td>${notes.createdDate}</td>
    <td>${notes.dateСompletion}</td>
    <td>${notes.importance}</td>

    <td>
      <a href='<c:url value="/index" />'>Go home</a> |
    </td></tr>
  </c:forEach>
</table>
</body>
</html>