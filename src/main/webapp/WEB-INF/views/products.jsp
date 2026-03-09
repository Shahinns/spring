<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<title>Products</title>
</head>
<body>
<section>
<div class="jumbotron">
<div class="container">
<h1>Products</h1>
<p>All the available products in our store</p>
</div>
</div>
</section>
<section class="container">
<div class="row">
<c:forEach items="${products}" var="product" varStatus="status">
<div class="col-sm-6 col-md-3" style="padding-bottom: 15px">
<div class="thumbnail" style="height: 450px; overflow: hidden;">
<img src="<c:url value="/resource/images/${product.productId}.jpg"/>"
     alt="image" style="width:100%; height:170px; object-fit:cover;"/>
<div class="caption">
<h3>${product.name}</h3>
<p style="height:80px; overflow:hidden;">${product.description}</p>
<p>$${product.unitPrice}</p>
<p>Available ${product.unitsInStock} units in stock</p>
<p>
<a href="<spring:url value="/products/product?id=${product.productId}"/>" class="btn btn-primary">
    <span class="glyphicon-info-sign glyphicon"></span> Details
</a>
</p>
</div>
</div>
</div>
<c:if test="${status.count % 4 == 0}">
    <div class="clearfix"></div>
</c:if>
</c:forEach>
</div>
</section>
</body>
</html>