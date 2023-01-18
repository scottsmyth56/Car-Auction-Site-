<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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

<div class="form-wrapper">
    <div class="login-form">

<h3>Booking Test Drive for ${car.make} ${car.model}</h3>
<h3>DealerShip car is located: ${dealer.dealerName} in  ${dealer.location} </h3>

<h3> Customer Name: ${customer.firstName} ${customer.lastName}</h3>

	<form action="/saveBooking" method="POST">
           
            <input type="hidden" name="dealerID" value="${car.dealerID}">
            <input type="hidden" name="carAdID" value="${car.carAdID}">
            <input type="hidden" name="customerID" value="${customer.customerID}">

            <br>
            <label for="bookingDate">Book a Date for Test Drive:</label>
            <input type="text" class="input-text" placeholder="yyyy-mm-dd" name="bookingDate"><br>
             <input type="submit" value="Save"  style ="width:100%"class="ActionBTN">
            <br>

	</form>
	</div>
	</div>
</body>
</html>