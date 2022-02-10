<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Add New Product</title>
</head>
<body>
<h1>Hello, Please add product details to add a new product</h1>
<br/>
<form action="Product" method="post">
    Product Name: <input type="text" name="productName"><br/>
    Product Price: <input type="text" name="productPrice"><br/>
    Product Description: <input type="text" name="productDescription"><br/>
    <input type="submit" value="Add">
</form>
<h2>${message}</h2>
</body>
</html>