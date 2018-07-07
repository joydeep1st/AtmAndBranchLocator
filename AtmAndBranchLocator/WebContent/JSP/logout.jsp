<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta charset="utf-8" />

<!-- Set the viewport width to device width for mobile -->
<meta name="viewport" content="width=device-width" />

<title>Welcome to ATM and Branch Locator Site</title>

<!-- Included CSS Files  -->
<link rel="stylesheet" href="stylesheets/foundation.css">
<link rel="stylesheet" href="stylesheets/app.css">
<link rel="stylesheet" href="stylesheets/w3.css">
<!-- <link rel="stylesheet" href="stylesheets/dropdown.css"> -->

<!--[if lt IE 9]>
    <link rel="stylesheet" href="stylesheets/ie.css">
  <![endif]-->

<script src="javascripts/modernizr.foundation.js"></script>

<!-- IE Fix for HTML5 Tags -->
<!--[if lt IE 9]>
    <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
  <![endif]-->

<style>
.dropbtn {
	background-color: #99A3A4;
	color: white;
	padding: 16px;
	font-size: 16px;
	border: none;
	cursor: pointer;
}

.dropdown {
	position: relative;
	display: inline-block;
}

.dropdown-content {
	display: none;
	position: absolute;
	background-color: #f9f9f9;
	min-width: 160px;
	box-shadow: 0px 8px 16px 0px rgba(0, 0, 0, 0.2);
	z-index: 1;
}

.dropdown-content a {
	color: black;
	padding: 12px 16px;
	text-decoration: none;
	display: block;
}

.dropdown-content a:hover {
	background-color: #2ba6cb;
}

.dropdown:hover .dropdown-content {
	display: block;
}

.dropdown:hover .dropbtn {
	background-color: #AEB6BF;
}

* {
	box-sizing: border-box;
}

body {
	font: 16px Arial;
	/*background: linear-gradient(to bottom right, #666666, #000000);*/
}

.autocomplete {
	/*the container must be positioned relative:*/
	position: relative;
	display: inline-block;
}

input {
	border: 1px solid transparent;
	background-color: #f1f1f1;
	padding: 10px;
	font-size: 16px;
}

input[type=text] {
	background-color: #f1f1f1;
	width: 100%;
}

input[type=submit] {
	background-color: #5da423;
	color: #fff;
	cursor: pointer;
	width: 8em;
}

.autocomplete-items {
	position: absolute;
	border: 1px solid #d4d4d4;
	border-bottom: none;
	border-top: none;
	z-index: 99;
	/*position the autocomplete items to be the same width as the container:*/
	top: 100%;
	left: 0;
	right: 0;
}

.autocomplete-items div {
	padding: 10px;
	cursor: pointer;
	background-color: #fff;
	border-bottom: 1px solid #d4d4d4;
}

.autocomplete-items div:hover {
	/*when hovering an item:*/
	background-color: #e9e9e9;
}

.autocomplete-active {
	/*when navigating through the items using the arrow keys:*/
	background-color: DodgerBlue !important;
	color: #ffffff;
}

.doPadding {
	padding-bottom: 2.5 em;
}

p.custompara {
	font-size: 14px;
	color: white;
}

a.styles {
	color: #a49a23;
	text-decoration: underline;
}

html{
	padding-top: 70px;
	background-color: #f7e6ee;
}
</style>

</head>
<body>

	<%
		//response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		if (session.getAttribute("USERNAME") == null) {
			response.sendRedirect(request.getContextPath());
		}
	%>

	<div class="row">
		<div class="twelve columns">
			<img src="images/Picture1.png" style="height: 120px;" />
		</div>
		<div class="row">
			<div class="twelve columns">

				<div class="nine columns">
					<p class="custompara">Logout from the Application</p>
					<p class="custompara">
						<a href="<%=request.getContextPath()%>"
							class="styles">log in</a> again...
					</p>
				</div>
				<div class="six columns"></div>
				<div class="seven columns"></div>
			</div>
		</div>
	</div>
</body>
</html>