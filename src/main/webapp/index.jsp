<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Products</title>
</head>
<body>
<h2>Note List</h2>
<p><a href='<c:url value="/create" />'>Create new</a></p>
<table>
    <thead>
<tr>
    <th>sentence</th>
    <th>date</th>
    <th>importance</th>
</tr>
</thead>
    <tr><th>№</th><th>Name</th><th>Date</th><th>importance</th><th></th></tr>
    <c:forEach var="notes" items="${note}">
        <td>${notes.id}</td>
        <td>${notes.sentence}</td>
        <td>${notes.date}</td>
        <td>${notes.importance}</td>

        <td>
                <a href='<c:url value="/edit?id=${notes.id}" />'>Edit</a> |
                <form method="post" action='<c:url value="/delete" />' style="display:inline;">
                    <input type="hidden" name="id" value="${notes.id}">
                    <input type="submit" value="Delete">
                </form>
            </td></tr>
    </c:forEach>
</table>
</body>
</html>