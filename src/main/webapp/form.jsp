<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
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
    <title>Note Form</title>
</head>
<body>
<div
        class="remind">
    <form action="hello" method="POST">
        Node: <input name="username"/>
        <br><br>
        Date: <input name="userdate"/>
        <br><br>
        <br><br>
        importance: <select name="importance">
        <option>1</option>
        <option>2</option>
        <option>3</option>
        <option>4</option>
    </select>
        <input type="submit" value="Submit"/>
</div>
</form>
</body>
</html>