<!DOCTYPE html>

<!-- paulirish.com/2008/conditional-stylesheets-vs-css-hacks-answer-neither/ -->
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7" lang="en"> <![endif]-->
<!--[if IE 7]>    <html class="no-js lt-ie9 lt-ie8" lang="en"> <![endif]-->
<!--[if IE 8]>    <html class="no-js lt-ie9" lang="en"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js" lang="en">
<!--<![endif]-->
<head>
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

html{
	padding-top: 70px;
	background-color: #f7e6ee;
}
</style>

</head>
<body>

	<div class="row">
		<div class="twelve columns">
			<img src="images/Picture1.png" style="height: 120px;" />
		</div>
		<div class="row">
			<div class="twelve columns">

				<div class="nine columns">
					<form action="LoginServlets" method="post">
						<table align="center">
							<tr>
								<td align="center"><font color="white">User Name:</font></td>
								<td align="center"><input type="text" id="USERNAME"
									name="USERNAME" /></td>
							</tr>
							<tr>
								<td align="center"><font color="white">Password:</font></td>
								<td align="center"><input type="password" id="PASSWORD"
									name="PASSWORD" /></td>
							</tr>
							<tr>
								<td colspan="2"><div align="center">
										<input type="submit" value="Submit" id="submit" name="submit">
									</div></td>
							</tr>
							<tr>
								<td colspan="2"><div align="center">
										<a href="#"><font color="#a49a23" size="2px"><u>Forgot
													Password</u></font></a>
									</div></td>
							</tr>
							<tr>
								<td colspan="2"><div align="center" style="color: red;">
										<%=(request.getAttribute("errorMessage") == null) ? "" : request.getAttribute("errorMessage")%>
									</div></td>
							</tr>
						</table>
					</form>
				</div>
				<div class="six columns"></div>
				<div class="seven columns"></div>
			</div>
		</div>
	</div>


</body>
</html>