<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Note List</title>
</head>
<body>
<h2>Note List</h2>
<p><a href='<c:url value="/create" />'>Create new</a></p>
<table>
    <thead>
    </thead>
    <tr>
        <th>№</th>
        <th>sentence</th>
        <th>createdDate</th>
        <th>dateСompletion</th>
        <th>importance</th>
        <th></th>
    </tr>
    <c:forEach var="notes" items="${note}">
        <td>${notes.id}</td>
        <td>${notes.sentence}</td>
        <td>${notes.createdDate}</td>
        <td>${notes.dateСompletion}</td>
        <td>${notes.importance}</td>

        <td>
            <a href='<c:url value="/edit?id=${notes.id}" />'>Edit</a> |

            <form method="post" action='<c:url value="/delete" />' style="display:inline;">
                <input type="hidden" name="id" value="${notes.id}">
                <input type="submit" value="Delete">
            </form>
        </td>
        </tr>
    </c:forEach>
    <a href='<c:url value="/searchId.jsp" />'>Search Note</a> |

</table>
</body>
</html>