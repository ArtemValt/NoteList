<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<tr>
  <th><a href='<c:url value="/create" />'>Create new</a></th>
  <th> <a href='<c:url value="/searchId.jsp" />'>Search Notebook.Note </a></th>
</tr>

<!DOCTYPE html>
<html>
<head>
  <style>
    a:link, a:visited {
      background-color: #77d239;
      color: white;
      padding: 15px 25px;
      text-align: center;
      text-decoration: none;
      display: inline-block;
    }

    a:hover, a:active {
      background-color: #77d239;
    }
  </style>
  <style>
    .city {
      background-color: rgba(206, 171, 58, 0.94);
      color: white;
      border: 2px solid black;
      margin: 10px;
      padding: 10px;
    }
  </style>
</head>
<body>
<c:forEach var="notes" items="${note}">
  <div class="city">
    <h2>${notes.sentence}</h2>
    <p>ID=${notes.id}</p>
    <p>When created ${notes.createdDate}</p>
    <p>Date of completion ${notes.dateСompletion}</p>
    <p> importance ${notes.importance}</p>
    <form action='<c:url value="/edit" />' style="display:inline;">
      <input type="hidden" name="id" value="${notes.id}">
      <input type="submit" value="Edit">
    </form>
    <form method="post" action='<c:url value="/delete" />' style="display:inline;">
      <input type="hidden" name="id" value="${notes.id}">
      <input type="submit" value="Delete">
    </form>
    <th><a href='<c:url value="/index" />'>Go home</a></th>
  </div>
</c:forEach>




</body>
</html>