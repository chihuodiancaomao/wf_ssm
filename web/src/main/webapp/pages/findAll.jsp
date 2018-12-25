<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>product表所有信息</title>
</head>
<body>
    <c:forEach items="${product}" var="product">
        主键: ${product.id}<br>
        编号:${product.productNum}<br>
        名称: ${product.productName}<br>
        出发城市:${product.cityName}<br>
        出发时间:${product.departureTime}<br>
        departureTimeStr:${product.departureTimeStr}<br>
        产品价格: ${product.productPrice}<br>
        产品描述:${product.productDesc}<br>
        状态:${product.productStatus}<br>
        productStatusStr:${product.productStatusStr}<br>
        <p></p>
    </c:forEach>
</body>
</html>
