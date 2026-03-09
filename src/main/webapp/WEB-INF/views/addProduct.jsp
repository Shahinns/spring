<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
<title>Products</title>
</head>
<body>
<section>
<div class="jumbotron">
<div class="container">
<h1>Products</h1>
<p>Add products</p>
<a href="${pageContext.request.contextPath}/logout" class="btn btn-danger btn-mini pull-right">logout</a>
<div class="pull-right" style="padding-right:50px">
<a href="?language=en">English</a>|<a href="?language=nl">Dutch</a>
</div>
</div>
</div>
</section>
<section class="container">
<form:form modelAttribute="newProduct" class="form-horizontal" enctype="multipart/form-data">
<fieldset>
<legend>Add new product</legend>

<form:errors path="*" cssClass="alert alert-danger" element="div"/>

<div class="form-group">
  <label class="control-label col-lg-2" for="productId">
    <spring:message code="addProduct.form.productId.label" text="Product ID"/>
  </label>
  <div class="col-lg-10">
    <form:input id="productId" path="productId" type="text" class="form-control"/>
    <form:errors path="productId" cssClass="text-danger"/>
  </div>
</div>

<div class="form-group">
  <label class="control-label col-lg-2" for="name">
    <spring:message code="addProduct.form.name.label" text="Name"/>
  </label>
  <div class="col-lg-10">
    <form:input id="name" path="name" type="text" class="form-control"/>
    <form:errors path="name" cssClass="text-danger"/>
  </div>
</div>

<div class="form-group">
  <label class="control-label col-lg-2" for="unitPrice">
    <spring:message code="addProduct.form.unitPrice.label" text="Price"/>
  </label>
  <div class="col-lg-10">
    <form:input id="unitPrice" path="unitPrice" type="text" class="form-control"/>
    <form:errors path="unitPrice" cssClass="text-danger"/>
  </div>
</div>

<div class="form-group">
  <label class="control-label col-lg-2" for="manufacturer">
    <spring:message code="addProduct.form.manufacturer.label" text="Manufacturer"/>
  </label>
  <div class="col-lg-10">
    <form:input id="manufacturer" path="manufacturer" type="text" class="form-control"/>
  </div>
</div>

<div class="form-group">
  <label class="control-label col-lg-2" for="category">
    <spring:message code="addProduct.form.category.label" text="Category"/>
  </label>
  <div class="col-lg-10">
    <form:input id="category" path="category" type="text" class="form-control"/>
  </div>
</div>


<div class="form-group">
  <label class="control-label col-lg-2" for="units_in_stock">
    <spring:message code="addProduct.form.units_in_stock.label" text="Units in Stock"/>
  </label>
  <div class="col-lg-10">
    <form:input id="unitsInStock" path="unitsInStock" type="text" class="form-control"/>
  </div>
</div>

<div class="form-group">
  <label class="control-label col-lg-2" for="description">
    <spring:message code="addProduct.form.description.label" text="Description"/>
  </label>
  <div class="col-lg-10">
    <form:textarea id="description" path="description" rows="2" class="form-control"></form:textarea>
  </div>
</div>

<div class="form-group">
  <label class="control-label col-lg-2" for="condition">
    <spring:message code="addProduct.form.condition.label" text="Condition"/>
  </label>
  <div class="col-lg-10">
    <form:radiobutton path="condition" value="New"/>New
    <form:radiobutton path="condition" value="Old"/>Old
    <form:radiobutton path="condition" value="Refurbished"/>Refurbished
  </div>
</div>

<div class="form-group">
  <label class="control-label col-lg-2" for="productImage">
    <spring:message code="addProduct.form.productImage.label" text="Product Image"/>
  </label>
  <div class="col-lg-10">
    <form:input id="productImage" path="productImage" type="file" class="form-control"/>
  </div>
</div>

<div class="form-group">
  <div class="col-lg-offset-2 col-lg-10">
    <input type="submit" id="btnAdd" class="btn btn-primary" value="Add"/>
  </div>
</div>

</fieldset>
</form:form>
</section>
</body>
</html>