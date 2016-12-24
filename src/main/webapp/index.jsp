<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<link rel="stylesheet" href="css/style.css">
<head>
    <title>ServletTestProject</title>
</head>
<body>
<form action="filter" method="get">
    <div class="info">
    <span>Filter by:</span>
    <input type="text" name="q" title="" placeholder="write text here">
    <span>Limit of chars on page:</span>
    <input type="number" name="limit" title="" placeholder="0-10000">
    <span>Length of string in line:</span>
    <input type="number" name="length" title="" placeholder="0-10000">
    <span>Represent the file`s MetaData?</span>
    <select name="includeMetaData" title="">
        <option value="false">false</option>
        <option value="true">true</option>
    </select>
    <input type="submit" value="Search">
    </div>
</form>
</body>
</html>
