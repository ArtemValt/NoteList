<!DOCTYPE html>
<html><style>
    input[type=text], select {
        width: 100%;
        padding: 12px 20px;
        margin: 8px 0;
        display: inline-block;
        border: 1px solid #ccc;
        border-radius: 4px;
        box-sizing: border-box;
    }

    input[type=submit] {
        width: 100%;
        background-color: #4CAF50;
        color: white;
        padding: 14px 20px;
        margin: 8px 0;
        border: none;
        border-radius: 4px;
        cursor: pointer;
    }

    input[type=submit]:hover {
        background-color: #45a049;
    }

    div {
        border-radius: 5px;
        background-color: #f2f2f2;
        padding: 20px;
    }
</style>
<head>
    <meta charset="UTF-8">
    <title>Edit product</title>
</head>
<body>
<div>
    <h3>Edit product</h3>
    <form method="post">
        <input type="hidden" value="${notes.id}" name="id"/>
        <label>Notebook.Note</label><br>
        <input name="name" value="${notes.name}"/><br><br>
        <label>date(dd-MM-yyyy HH:mm:ss)</label><br>
        <input name="date" value="${notes.date}"/><br><br>
        importance: <select name="importance"> value="${notes.price}" /><br><br>
        <option>1</option>
        <option>2</option>
        <option>3</option>
        <option>4</option>
        <input type="submit" value="Send"/>
    </form>
</div>

</body>
</html>