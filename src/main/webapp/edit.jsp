<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit product</title>
</head>
<body>
<h3>Edit product</h3>
<form method="post">
    <input type="hidden" value="${notes.id}" name="id"/>
    <label>Note</label><br>
    <input name="name" value="${notes.name}"/><br><br>
    <label>date(dd-MM-yyyy HH:mm:ss)</label><br>
    <input name="date" value="${notes.date}"/><br><br>
    importance: <select name="importance"> value="${notes.price}" /><br><br>
    <option>1</option>
    <option>2</option>
    <option>3</option>
    <option>4</option>

    <%--    <input name="importance" value="${notes.price}" type="number" min="0" /><br><br>--%>
    <input type="submit" value="Send"/>
</form>
</body>
</html>