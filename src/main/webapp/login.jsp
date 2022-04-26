<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Note Form</title>
</head>
<body>
<form action="auth" method="POST">
    Login: <input name ="username"/>
    <br><br>
    Password: <input name="password"/>
    <br><br>
</select>
    <a href='<c:url value="/regis.jsp" />'>Registr</a> |


    <input type="submit" value="Submit"/>
</form>
</body>
</html>