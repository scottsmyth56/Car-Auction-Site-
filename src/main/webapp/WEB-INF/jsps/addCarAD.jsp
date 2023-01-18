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
		<div>
		<form action="/redirect" class="nav-form">
				<button class="log" aria-label="log in">Home</button>
			</form>
			<form action="/logout" class="nav-form">
				<button class="log" aria-label="log in">Logout</button>
			</form>
		</div>
	</header>
	<body>
	<div class="form-wrapper">
		<div class="login-form">

			<form:form method="POST" action="/saveCar"
				enctype="multipart/form-data">
				<table>
					<tr>
						<td><form:input path="dealerID" type="hidden" /></td>
					</tr>
					<tr>
						<td><form:label path="make">Make</form:label></td>
						<td><form:input path="make" class="input-text" /></td>
					</tr>
					<tr>
						<td><form:label path="model">Model</form:label></td>
						<td><form:input path="model" class="input-text" /></td>
					</tr>
					<tr>
						<td><form:label path="price">Price:</form:label></td>
						<td><form:input path="price" class="input-text" /></td>
					</tr>
					<tr>
						<td><form:label path="colour">Colour</form:label></td>
						<td><form:input path="colour" class="input-text" /></td>
					</tr>
					<tr>
						<td><form:label path="engineSize">Engine Size</form:label></td>
						<td><form:input path="engineSize" class="input-text" /></td>
					</tr>
					<tr>
						<td><form:label path="extraInfo">Extra Info:</form:label></td>
						<td><form:input path="extraInfo" class="input-text" /></td>
					</tr>
					<tr>
						<td><form:label path="exteriorImage">Exterior Image 1</form:label></td>
						<td><form:input type="file" path="fileExterior"
								accept="image/png, image/jpeg" /></td>
					</tr>
					<tr>
						<td><form:label path="exteriorImage2">Exterior Image 2</form:label></td>
						<td><form:input type="file" path="fileExterior2"
								accept="image/png, image/jpeg" /></td>
					</tr>
					<tr>
						<td><form:label path="InteriorImage">Interior Image </form:label></td>
						<td><form:input type="file" path="fileInterior"
								accept="image/png, image/jpeg" /></td>
					</tr>

					<tr>
						<td colspan="2"><input type="submit" value="Save"
						class="ActionBTN" style="width:100%;" /></td>
					</tr>
				</table>
			</form:form>
		</div>
	</div>
</body>
</html>