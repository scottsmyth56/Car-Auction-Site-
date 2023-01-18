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
			<form action="/logout">
				<button class="log" aria-label="log in">Logout</button>
			</form>
		</div>
	</header>
	<div class="hero">
		<div class="text">
			<h1>Featured Dealer</h1>
			<h3>${dealer.dealerName}</h3>
			<h3>${dealer.location}</h3>
		</div>
	</div>

	<div class="search">
		<div class="search-form">
			<h3 id="search-header">SEARCH CARS</h3>
			<form action="/filtered" method="post">
				<input type="text" name="input" id="input" /> <input type="submit"
					value="SEARCH" id="search-btn" />
			</form>
		</div>
	</div>
	<div class="search">
		<form action="/DealerViewOwnCarAds" method="get">
			<input type="submit" class="ActionBTN" value="View My Ads" />
		</form>
	</div>

	<ul class="cards">
		<c:forEach items="${Cars}" var="car">

			<li class="cards_item">
				<div class="card">
					<div class="card_image">
						<img src="data:image/png;base64, ${car.base64Exterior}">
					</div>
					<div class="card_content">

						<h2 class="card_title">${car.make}${car.model}</h2>
						<p class="card_text">
							<strong>Price:</strong>${car.price}</p>
						<p class="card_text">
							<strong>Year:</strong> ${car.colour}
						</p>
						<p class="card_text">
							<strong>Engine Size:</strong> ${car.engineSize}
						</p>
					
					</div>
				</div>
			</li>
		</c:forEach>
	</ul>
</body>
</html>