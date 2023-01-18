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
	<div class="search">
		<div class="search-form">
			<h3 id="search-header">Enter Lot Number's to Compare</h3>
			<form action="/compareTwoCars" method="post">
				<input type="text" name="car1" id="compareInput"
					placeholder="Car Number 1" /> <input type="text" name="car2"
					id="compareInput" placeholder="Car Number 2" /> <input
					type="submit" value="SEARCH" id="search-btn" />
			</form>
		</div>
	</div>
	<c:if test="${not empty cars}">
		<div class="job-wrapper">
			<table class="trainee-table">
				<thead>
					<tr>
						<th>Lot No:</th>
						<th>Image:</th>
						<th>Make:</th>
						<th>Model:</th>
						<th>Colour:</th>
						<th>Price</th>
						<th>Engine Size</th>
						<th>View More</th>
					</tr>
				</thead>
				<tbody id="myTable">
					<c:forEach items="${cars}" var="car">
						<tr>
							<td>${car.carAdID}</td>
							<td><img style="height: 200px; width: 200px;"
								src="data:image/png;base64, ${car.base64Exterior}" /></td>
							<td><c:out value="${car.make}" /></td>
							<td><c:out value="${car.model}" /></td>
							<td><c:out value="${car.colour}" /></td>
							<td><c:out value="${car.price}" /></td>
							<td><c:out value="${car.engineSize}" /></td>
							<td>
								<form action="/CustomerViewCar/${car.carAdID}" method="get">
									<input type="submit" class="ActionBTN" id="carBTN" value="View" />
								</form>
							</td>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</c:if>
	<c:if test="${not empty car1}">
	
		<div class="search">
			<div class="search-form">
				<form action="/CompareCars" method="get">
					<input type="submit" value="RESET" id="search-btn" />
				</form>
			</div>
		</div>
	</c:if>
	
	

	<div class="car-left">
		<c:if test="${not empty car1}">
			<div class="slideshow-container">
				<div class="mySlides1 fade">
					<div class="numbertext">1 / 3</div>
					<img src="data:image/png;base64, ${car1.base64Exterior}"
						style="width: 100%">
					<div class="caption">Caption Text</div>
				</div>

				<div class="mySlides1 fade">
					<div class="numbertext">2 / 3</div>
					<img src="data:image/png;base64, ${car1.base64Exterior2}"
						style="width: 100%">
					<div class="caption">Caption Two</div>
				</div>

				<div class="mySlides1 fade">
					<div class="numbertext">3 / 3</div>
					<img src="data:image/png;base64, ${car1.base64Interior}"
						style="width: 100%">
					<div class="caption">Caption Three</div>
				</div>
				<a class="prev" onclick="plusSlides(-1)">&#10094;</a> <a
					class="next" onclick="plusSlides(1)">&#10095;</a>
			</div>
			<br>
			<div style="text-align: center">
				<span class="dot1" onclick="currentSlide(1)"></span> <span
					class="dot1" onclick="currentSlide(2)"></span> <span class="dot1"
					onclick="currentSlide(3)"></span>
			</div>
			<div class="car-info">
				<h3>Make: <c:out value="${car1.make}" /></h3>
				<h3>Model <c:out value="${car1.model}" /></h3>
				<h3>Colour <c:out value="${car1.colour}" /></h3>
				<h3>Price: £ <c:out value="${car1.price}" /></h3>
				<h3>Engine Size: <c:out value="${car1.engineSize}" /></h3>
				<h3>Extra Info: <c:out value="${car1.extraInfo}" /></h3>
			</div>
		</c:if>
	</div>
	<div class="car-right">
		<c:if test="${not empty car2}">
			<div class="slideshow-container">
				<div class="mySlides2 fade">
					<div class="numbertext">1 / 3</div>
					<img src="data:image/png;base64, ${car2.base64Exterior}"
						style="width: 100%">
				</div>

				<div class="mySlides2 fade">
					<div class="numbertext">2 / 3</div>
					<img src="data:image/png;base64, ${car2.base64Exterior2}"
						style="width: 100%">
				</div>

				<div class="mySlides2 fade">
					<div class="numbertext">3 / 3</div>
					<img src="data:image/png;base64, ${car2.base64Interior}"
						style="width: 100%">
				</div>


				<a class="prev" onclick="plusSlides(-1)">&#10094;</a> <a
					class="next" onclick="plusSlides(1)">&#10095;</a>
			</div>
			<br>
			<br>
			<div style="text-align: center">
				<span class="dot2" onclick="currentSlide(1)"></span> <span
					class="dot2" onclick="currentSlide(2)"></span> <span class="dot2"
					onclick="currentSlide(3)"></span>
			</div>
			<div class="car-info">
				<h3>Make: <c:out value="${car2.make}" /></h3>
				<h3>Model <c:out value="${car2.model}" /></h3>
				<h3>Colour <c:out value="${car2.colour}" /></h3>
				<h3>Price: £ <c:out value="${car2.price}" /></h3>
				<h3>Engine Size: <c:out value="${car2.engineSize}" /></h3>
				<h3>Extra Info: <c:out value="${car2.extraInfo}" /></h3>
			</div>
		</c:if>
		</div>
	<script>
		let slideIndex = 1;
		showSlides1(slideIndex);
		showSlides2(slideIndex);

		function plusSlides(n) {
			showSlides1(slideIndex += n);
			showSlides2(slideIndex += n);
		}

		function currentSlide(n) {
			showSlides1(slideIndex = n);
			showSlides2(slideIndex = n);
		}

		function showSlides1(n) {
			let i;
			let slides = document.getElementsByClassName("mySlides1");
			let dots = document.getElementsByClassName("dot1");
			if (n > slides.length) {
				slideIndex = 1
			}
			if (n < 1) {
				slideIndex = slides.length
			}
			for (i = 0; i < slides.length; i++) {
				slides[i].style.display = "none";
			}
			for (i = 0; i < dots.length; i++) {
				dots[i].className = dots[i].className.replace(" active", "");
			}
			slides[slideIndex - 1].style.display = "block";
			dots[slideIndex - 1].className += " active";
		}

		function showSlides2(n) {
			let i;
			let slides = document.getElementsByClassName("mySlides2");
			let dots = document.getElementsByClassName("dot2");
			if (n > slides.length) {
				slideIndex = 1
			}
			if (n < 1) {
				slideIndex = slides.length
			}
			for (i = 0; i < slides.length; i++) {
				slides[i].style.display = "none";
			}
			for (i = 0; i < dots.length; i++) {
				dots[i].className = dots[i].className.replace(" active", "");
			}
			slides[slideIndex - 1].style.display = "block";
			dots[slideIndex - 1].className += " active";
		}
	</script>
</body>
</html>