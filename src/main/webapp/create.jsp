
<!DOCTYPE html>
<html>
<style>
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
<body>

<div>
        <form method="post">
        <label for="sentence">Sentence</label>
        <input type="text" id="sentence" name="sentence" placeholder="Your note">

        <label for="Date">Date Complete (dd-MM-yyyy HH:mm:ss)</label>
        <input type="text" id="Date" name="date" placeholder="Date complete">

        <label for="importance">Importance</label>
        <select id="importance" name="importance">
            <option value="1">1</option>
            <option value="2">2</option>
            <option value="3">3</option>
            <option value="4">4</option>


        </select>

        <input type="submit" value="Submit">
    </form>
</div>

</body>
</html>