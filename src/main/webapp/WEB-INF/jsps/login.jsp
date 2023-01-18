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
			<form action="/login" method="post">

				<label for="username">User name:</label> 
				<input type="text" class="input-text" name="username" placeholder="User name"> 
				
				<label for="password">Password:</label> 
				<input type="password" class="input-text" name="password" placeholder="Password"> 
				
				<input type="submit" class="ActionBTN" style="width:100%;"  value="Log in">
			</form>
			<div class="register-div">
				<a class="registerlink"href= "/registerCustomer">Customer Registration</a>
				<a class="registerlink" href= "/registerDealer">Dealer Registration</a>
			</div>
		</div>
	</div>
</body>
</html>