<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cars For You</title>
<link href="/css/style.css" rel="stylesheet">
</head>
<body>
	<header>
		<div id="logo-div">
			<img src="/images/logo.png" alt="Cars for you Logo" width="150px"
				height="150" id="logo-img">
		</div>
		<div id="title-btn-div">
			<h1 id="main-title">CarsForYou</h1>
		</div>
		</header>
	<div class="form-wrapper">
		<div class="login-form">
		<h2 class="header-body">Edit Car Ad</h2>
	<form:form method="POST" action="/updateCar"
		enctype="multipart/form-data">

		<form:input path="dealerID" type="hidden"/>
		<form:input type="hidden" path="carAdID" />
		<br><br>

		<form:label path="make">Make</form:label>
		<form:input path="make" class="input-text" value="${car.make}" />
		<br><br>
		<form:label path="model">Model</form:label>
		<form:input path="model" class="input-text" value="${car.model}" />
		<br><br>
		<form:label path="price">Price:</form:label>
		<form:input path="price" class="input-text" value="${car.price}" />
		<br><br>
		<form:label path="colour">Colour</form:label>
		<form:input path="colour" class="input-text"/>
		<br><br>
		<form:label path="engineSize">Engine Size</form:label>
		<form:input path="engineSize" class="input-text" value="${car.engineSize}" />
		<br><br>
		<form:label path="extraInfo">Extra Info:</form:label>
		<form:input path="extraInfo" class="input-text" value="${car.extraInfo}" />
		<br><br>
		<form:label path="exteriorImage">Exterior Image 1</form:label>
		<form:input type="file" path="fileExterior" accept="image/png, image/jpeg" />
		<br><br>
		<form:label path="exteriorImage2">Exterior Image 2</form:label>
		<form:input type="file" path="fileExterior2" accept="image/png, image/jpeg" />
		<br><br>
		<form:label path="InteriorImage">Interior Image </form:label>
		<form:input type="file" path="fileInterior" accept="image/png, image/jpeg" />
		<br><br>
		<input type="submit" class="ActionBTN" style="width:100%;" value="Save" class="viewDetails" />

	</form:form>

</div>
</div>

</body>
</html>