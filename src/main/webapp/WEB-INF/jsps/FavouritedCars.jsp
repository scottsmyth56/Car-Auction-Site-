<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cars For You</title>
<link href="css/style.css" rel="stylesheet">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<script>
	$(document).ready(
			function() {
				$("#myInput").on(
						"keyup",
						function() {
							let value = $(this).val().toLowerCase();
							$("#myTable tr").filter(
									function() {
										$(this).toggle(
												$(this).text().toLowerCase()
														.indexOf(value) > -1)
									});
						});
			});
</script>
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



	<div class="search">
		<div class="search-form">
			<h3 id="search-header">SEARCH CARS</h3>
			<form action="opp/filtered" method="post">
				<input type="text" name="location" id="myInput" /> <input
					type="submit" value="SEARCH" id="search-btn" />
			</form>
		</div>
	</div>
	<div class="job-wrapper">
		<table class="trainee-table">
			<thead>
				<tr>
					<th>Image:</th>
					<th>Make:</th>
					<th>Model:</th>
					<th>Price:</th>
					<th>Colour:</th>
					<th>Engine Size:</th>
				</tr>
			</thead>
			<tbody id="myTable">
				<c:forEach items="${cars}" var="car">
					<tr>
						<td><img style="height: 200px; width: 200px;" src="data:image/png;base64, ${car.base64Exterior}" /></td>
						<td><c:out value="${car.make}" /></td>
						<td><c:out value="${car.model}" /></td>
						<td><c:out value="${car.price}" /></td>
						<td><c:out value="${car.colour}" /></td>
						<td><c:out value="${car.engineSize}" /></td>
						<td>
							<form action="/unfavouriteCar" method="get">
							<input type ="hidden" name="carID" value="${car.carAdID}">
								<input type="submit" class="ActionBTN" value="Unfavourite" />
							</form>
						</td>
						<td>
							<form action="/CustomerViewCar/${car.carAdID}" method="get">
								<input type="submit" class="ActionBTN" value="View" />
							</form>

						</td>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>