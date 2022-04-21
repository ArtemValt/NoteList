<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Note</title>
</head>
<body>
<h2>Node List</h2>
<p><a href='/untitled1_war_exploded/create'>Create new</a></p>
<table>
    <tr><th>Name</th><th>Price</th><th></th></tr>
    <c:forEach var="note" items="${note}">
        <tr><td>${note.sentence}</td>
            <td>${note.date}</td>
            <td>${note.imp}</td>

            <td>
                <a href='<c:url value="/edit?id=${note.id}" />'>Edit</a> |
                <form method="post" action='<c:url value="/delete" />' style="display:inline;">
                    <input type="hidden" name="id" value="${note.id}">
                    <input type="submit" value="Delete">
                </form>
            </td></tr>
    </c:forEach>
</table>
</body>
</html>