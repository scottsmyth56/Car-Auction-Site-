<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
	<div class="hero">
		<div class="text">
			<c:if test="${not empty customer}">
				<h2>Our Raffle Winner for this Month - ${customer.firstName}
					${customer.lastName}</h2>
			</c:if>
			<form action="/runRaffle" method="get">
				<input type="submit" value="Run Raffle" class="blueBTN"
					id="shop-now" />
			</form>


		</div>
	</div>


		<div class="search">
			<form action="/addCar">
				<input type="submit" class="ActionBTN" value="Add Car AD" />
			</form>
		</div>
	<c:if test="${empty activeList}">
		<h2 class ="body-header">You currently have no Active Car Advertisements</h2>
	</c:if>
	<c:if test="${not empty activeList}">
	<h2 class ="body-header">Cars for Sale</h2>
		<ul class="cards">
			<c:forEach items="${activeList}" var="car">

				<li class="cards_item">
					<div class="card">
						<div class="card_image">
							<img src="data:image/png;base64, ${car.base64Exterior}">
						</div>
						<div class="card_content">

							<h2 class="card_title">${car.make}${car.model}</h2>
							<p class="card_text"><strong>Price:</strong>${car.price}</p>
							<p class="card_text"><strong>Year:</strong> ${car.colour}</p>
							<p class="card_text"><strong>Engine Size:</strong> ${car.engineSize}</p>
							
							<form action="/DealerviewBidding/${car.carAdID}" method="get">
								<input type="submit" value="View Bidding" class = "ActionBTN" id ="carBTN" />
							</form>

							<form action="/editCar/${car.carAdID}" method="post">
								<input type="submit" value="Edit" class = "ActionBTN" id ="carBTN" />
							</form>

							<form action="/deleteCar/${car.carAdID}" method="post">
								<input type="submit" value="Delete" class = "ActionBTN" id ="carBTN" />
							</form>


						</div>
					</div>
				</li>
			</c:forEach>
		</ul>
	</c:if>
	<c:if test="${empty soldList}">
		<h2 class ="body-header">You have not sold any cars yet</h2>
	</c:if>
	
	<c:if test="${not empty soldList}">
	<h2 class ="body-header">Sold Cars</h2>
		<ul class="cards">
			<c:forEach items="${soldList}" var="car">

				<li class="cards_item">
					<div class="card">
						<div class="card_image">
							<img src="data:image/png;base64, ${car.base64Exterior}">
						</div>
						<div class="card_content">

							<h2 class="card_title">${car.make} ${car.model}</h2>
							<p class="card_text"><strong>Price: £</strong>${car.price}</p>
							<p class="card_text"><strong>Colour: </strong> ${car.colour}</p>
							<p class="card_text"><strong>Engine Size: </strong> ${car.engineSize}</p>
							
							<form action="/DealerviewBidding/${car.carAdID}" method="get">
								<input type="submit" value="View Bidding"  class = "ActionBTN" id ="carBTN"/>
							</form>

							<form action="/editCar/${car.carAdID}" method="post">
								<input type="submit" value="Edit" class = "ActionBTN" id ="carBTN" />
							</form>

							<form action="/deleteCar/${car.carAdID}" method="post">
								<input type="submit" value="Delete" class = "ActionBTN" id ="carBTN" />
							</form>


						</div>
					</div>
				</li>
			</c:forEach>
		</ul>

	</c:if>
</body>
</html>