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
		</header>
<div class="form-wrapper">
		<div class="login-form">
			<form action="/registerCustomer" method="post">
				
					<label for="firstName">First Name:</label>
					<input type="text"   class="input-text" name="firstName" >
					<br>
					<label for="lastName">Last Name:</label>
					<input type="text"  class="input-text" name="lastName" >
					<br>
					<label for="username">Email:</label>
					<input type="text" class="input-text"name="username" >
					<br>
					<label for="username">Password:</label>
					<input type="password" class="input-text" name="password" >		
					<br>
					<label for="phoneNum">Phone:  Format(+3530876161663)</label>
					<input type="text" class="input-text" name="phoneNum" >		
			
				<input type="submit" class="ActionBTN" style="width:100%;" value="Register">
	</form>
</div>
</div>
</body>
</html>