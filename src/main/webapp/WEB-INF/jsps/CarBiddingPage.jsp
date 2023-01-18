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
		<h3 id="search-header">Online Bidding for ${car.make}
			${car.model}</h3>
	</div>
</div>
<body>
	<div class="slideshow-container">
		<div class="mySlides1 fade">
			<div class="numbertext">1 / 3</div>
			<img src="data:image/png;base64, ${car.base64Exterior}"
				style="width: 100%">
		</div>

		<div class="mySlides1 fade">
			<div class="numbertext">2 / 3</div>
			<img src="data:image/png;base64, ${car.base64Exterior2}"
				style="width: 100%">
		</div>

		<div class="mySlides1 fade">
			<div class="numbertext">3 / 3</div>
			<img src="data:image/png;base64, ${car.base64Interior}"
				style="width: 100%">
		</div>
		<a class="prev" onclick="plusSlides(-1)">&#10094;</a> <a class="next"
			onclick="plusSlides(1)">&#10095;</a>
	</div>
	<br>
	<div style="text-align: center">
		<span class="dot1" onclick="currentSlide(1)"></span> 
		<span class="dot1"onclick="currentSlide(2)"></span> 
		<span class="dot1"onclick="currentSlide(3)"></span>
	</div>
<div class="bidding"> 
<div class="inner-bidding">
	<h3 class="bid-info">Winning Bid: £</h3><h3 id="WinningBid" class="bid-info">${winningBid.bidPrice}</h3>
	<br>
	<h3 class="bid-info">Winning Bidder: </h3><h3  class="bid-info">${winningBid.bidderEmail}</h3>
 	<p id ="bidcheck" style ="display:none">Please Enter a bid amount higher than the winning Bid</p>
	<c:if test="${empty Listofbids}">
		<p>They are currently no bids</p>
	</c:if>

	
		<table id="bids">
			<tr>
				<th>Bid Price</th>
				<th>Bidder</th>
				<th>Action</th>
			</tr>
			<c:forEach items="${Listofbids}" var="bid">
			<tr>
				<td>£${bid.bidPrice}</td>
				<td>${bid.bidderEmail}</td>
				<c:if test="${bid.customerID == customer.customerID }">
					<td>
						<form action="/removeBid" method="post">
							<input type="hidden" name="carAdID" value="${car.carAdID}">
							<input type="hidden" name="bidID" value="${bid.bidID}"> 
							<input type="submit" class="ActionBTN" value="Remove Bid" />
						</form>

					</td>
				</c:if>
			</tr>
				</c:forEach>
		</table>

	<form action="/placeBid" method="post">
		<input type="hidden" name="dealerID" value="${car.dealerID}">
		<input type="hidden" name="carAdID" value="${car.carAdID}"> <input
			type="hidden" name="customerID" value="${customer.customerID}">

		<input type="text" onkeyup="checkBid();" id="addBid" class="input-text" placeholder="Enter Bid Price" name="bidPrice">
		<input type="submit" id="submit" class="ActionBTN" style="width:100%;" value="Place Bid" />
	</form>
	</div>
	
	
</div>

<footer>

footer
</footer>
	<script>
		function checkBid() {
	
			let WinningBid = document.getElementById("WinningBid");
			var x = WinningBid.textContent;
			var num1 = Number(x);
			
			let newBid = document.getElementById("addBid").value;
			var num2 = Number(newBid);

			if (num2 < num1) {
				document.getElementById("submit").disabled = true;
				document.getElementById("bidcheck").style.display="block";
				 document.getElementById("bidcheck").style.color="Red";
			} else {
				document.getElementById("submit").disabled = false;
				document.getElementById("bidcheck").style.display="none";
			}
		}
	</script>
	<script>
		let slideIndex = 1;
		showSlides(slideIndex);

		function plusSlides(n) {
			showSlides(slideIndex += n);
		}

		function currentSlide(n) {
			showSlides(slideIndex = n);
		}

		function showSlides(n) {
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
	</script>

</body>
</html>