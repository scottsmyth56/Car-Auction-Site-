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
	<div class="search">
		<div class="search-form">
			<img  class="dealer-logo" src="data:image/png;base64, ${dealer.base64Image}">
			<h2 class="dealer-info">${dealer.dealerName}</h2>
			<h3 class="dealer-info">${dealer.about}</h3>
		</div>
		</div>
		<div class="search">
			<form action="/EnterRaffle/${dealer.dealerID}" method="get">
				<input type="submit" class="ActionBTN" onclick ="enter()" style="width:100%;" value="Enter our Raffle for free valet" />
			</form>
		</div>
<c:if test="${empty forSale}">

<h2>Sorry, We currently have no Cars for sale at the moment</h2>

</c:if>
	<c:if test="${not empty forSale}">
	<h2 class ="body-header">Cars for Sale</h2>
		<ul class="cards">
			<c:forEach items="${forSale}" var="car">

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
							<form action="/viewBidding/${car.carAdID}" method="get">
								<input type="submit" class="ActionBTN" style="width:100%;" value="View Bidding" />
							</form>
							<form action="/CustomerViewCar/${car.carAdID}" method="get">
								<input type="submit" class="ActionBTN"  style="width:100%;" value="View" />
							</form>
						</div>
					</div>
				</li>
			</c:forEach>
		</ul>
	</c:if>
	<c:if test="${empty soldList}">
		<h2 class ="body-header">${dealer.dealerName} has  not sold any cars yet</h2>
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
							
						</div>
					</div>
				</li>
			</c:forEach>
		</ul>

	</c:if>
	<script>
	function enter(){
		
		alert("You have entered the Draw");
	}
	</script>
</body>
</html>