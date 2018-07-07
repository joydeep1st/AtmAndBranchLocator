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

.tablink {
	background-color: #555;
	color: white;
	float: left;
	border: none;
	outline: none;
	cursor: pointer;
	padding: 14px 16px;
	font-size: 17px;
	width: 25%;
}

.tablink:hover {
	background-color: #777;
}

/* Style the tab content */
.tabcontent {
	color: white;
	display: none;
	padding-top: 50px;
	/* padding: 50px; */
	text-align: center;
	/*width: 600px;*/
}


#Search {
	background-color: #D0D3D4;
}

#History {
	background-color: #CBD8DC;	
}

#historyContents{
	overflow-y: auto;
	height: 280px;
	left: 3px;
}

#Tokyo {
	background-color: blue;
}

#Oslo {
	background-color: orange;
}

#resultTable{
	border-collapse: collapse;
    border-spacing: 0;
}
#resultTable td{
	padding-left: 4px;
	padding-bottom: 0px;
}

</style>

<script type="text/javascript">
	/* window.onbeforeunload = function() {
		return "You work will be lost.";
	}; */
	function CallServlet(str){
		switch(str.toLowerCase()){
		case 'getallfromjsons':
			document.getElementById("atmbranch").submit();
			break;
		case 'getzipsfromjsons':
			document.getElementById("locations").submit();
			break;
		
		}		
	}
</script>
</head>
<body>

	<%
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Cache-Control", "no-store");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);
		if (session.getAttribute("USERNAME") == null) {
			response.sendRedirect(request.getContextPath());
		}
	%>

	<%-- <div class="row">
		<div class="four columns">
			<font color="#ECF0F1" size="2px">Welcome <b><%=session.getAttribute("USERNAME")%></b></font>
			&nbsp <a href="<%=request.getContextPath()%>/JSP/logout.jsp" onclick="LogoutServlet"><font
				color="#a49a23" size="2px"><u>logout</u></font></a>
		</div>
		<div class="twelve columns">
			<img src="images/Picture1.png" style="height: 120px;" />
		</div>
		<!-- <div class="row"> -->
		<div class="twelve columns">
			<button class="tablink" onclick="openCity('Search', this, '#7B7D7D')"
				id="defaultOpen">Search</button>
			<button class="tablink"
				onclick="openCity('History', this, '#7B7D7D')">History</button>

			<div id="Search" class="tabcontent">
				<table>
					<tbody>
						<tr>
							<td>Bank Name:</td>
							<td><select>
									<option name="">Select a Bank</option>
									<option name="LyodBank">Lyod Bank</option>
									<option name="Barclays">Barclays</option>
									<option name="RBS">Royal Bank of Scotland</option>
							</select></td>
						</tr>
						</table>
						<form id="atmbranch" name="atmbranch" action="/GetAllFromJsons" method="post">
						<table>
						<tr>
							<td>Branch/ATM:</td>
							<td>
							<input type="radio" name="atm" value="atm" onclick="CallServlet('GetAllFromJsons')">ATM
  							<input type="radio" name="atm" value="branch">Branch<br>
  							<input type="hidden" name="atm" value="atm123"/><br>
  							</td>							
						</tr>
						</table>
						</form>
						<table>
						<tr>
							<td>Locality:</td>
							<td><input type="text" id="locality" name="locality"
								placeholder="Locality" /></td>
						</tr>
						<tr>
							<td>Zip Code:</td>
							<td>
								<div class="autocomplete">
									<input id="myInput" type="text" name="myInput"
										placeholder="ZipCode">
								</div> <script type="text/javascript">
									function autocomplete(inp, arr) {
										//alert("hivdvvsdv" + inp);
										/*the autocomplete function takes two arguments,
										the text field element and an array of possible autocompleted values:*/
										var currentFocus;
										/*execute a function when someone writes in the text field:*/
										inp
												.addEventListener(
														"input",
														function(e) {

															var a, b, i, val = this.value;
															/*close any already open lists of autocompleted values*/
															closeAllLists();
															if (!val) {
																return false;
															}
															currentFocus = -1;
															/*create a DIV element that will contain the items (values):*/
															a = document
																	.createElement("DIV");
															a
																	.setAttribute(
																			"id",
																			this.id
																					+ "autocomplete-list");
															a
																	.setAttribute(
																			"class",
																			"autocomplete-items");
															/*append the DIV element as a child of the autocomplete container:*/
															this.parentNode
																	.appendChild(a);
															/*for each item in the array...*/
															for (i = 0; i < arr.length; i++) {
																/*check if the item starts with the same letters as the text field value:*/
																if (arr[i]
																		.substr(
																				0,
																				val.length)
																		.toUpperCase() == val
																		.toUpperCase()) {
																	/*create a DIV element for each matching element:*/
																	b = document
																			.createElement("DIV");
																	/*make the matching letters bold:*/
																	b.innerHTML = "<strong>"
																			+ arr[i]
																					.substr(
																							0,
																							val.length)
																			+ "</strong>";
																	b.innerHTML += arr[i]
																			.substr(val.length);
																	/*insert a input field that will hold the current array item's value:*/
																	b.innerHTML += "<input type='hidden' value='" + arr[i] + "'>";
																	/*execute a function when someone clicks on the item value (DIV element):*/
																	b
																			.addEventListener(
																					"click",
																					function(
																							e) {
																						/*insert the value for the autocomplete text field:*/
																						inp.value = this
																								.getElementsByTagName("input")[0].value;
																						/*close the list of autocompleted values,
																						(or any other open lists of autocompleted values:*/
																						closeAllLists();
																					});
																	a
																			.appendChild(b);
																}
															}
														});
										/*execute a function presses a key on the keyboard:*/
										inp
												.addEventListener(
														"keydown",
														function(e) {
															var x = document
																	.getElementById(this.id
																			+ "autocomplete-list");
															if (x)
																x = x
																		.getElementsByTagName("div");
															if (e.keyCode == 40) {
																/*If the arrow DOWN key is pressed,
																increase the currentFocus variable:*/
																currentFocus++;
																/*and and make the current item more visible:*/
																addActive(x);
															} else if (e.keyCode == 38) { //up
																/*If the arrow UP key is pressed,
																decrease the currentFocus variable:*/
																currentFocus--;
																/*and and make the current item more visible:*/
																addActive(x);
															} else if (e.keyCode == 13) {
																/*If the ENTER key is pressed, prevent the form from being submitted,*/
																e
																		.preventDefault();
																if (currentFocus > -1) {
																	/*and simulate a click on the "active" item:*/
																	if (x)
																		x[currentFocus]
																				.click();
																}
															}
														});
										function addActive(x) {
											/*a function to classify an item as "active":*/
											if (!x)
												return false;
											/*start by removing the "active" class on all items:*/
											removeActive(x);
											if (currentFocus >= x.length)
												currentFocus = 0;
											if (currentFocus < 0)
												currentFocus = (x.length - 1);
											/*add class "autocomplete-active":*/
											x[currentFocus].classList
													.add("autocomplete-active");
										}
										function removeActive(x) {
											/*a function to remove the "active" class from all autocomplete items:*/
											for (var i = 0; i < x.length; i++) {
												x[i].classList
														.remove("autocomplete-active");
											}
										}
										function closeAllLists(elmnt) {
											/*close all autocomplete lists in the document,
											except the one passed as an argument:*/
											var x = document
													.getElementsByClassName("autocomplete-items");
											for (var i = 0; i < x.length; i++) {
												if (elmnt != x[i]
														&& elmnt != inp) {
													x[i].parentNode
															.removeChild(x[i]);
												}
											}
										}
										/*execute a function when someone clicks in the document:*/
										document.addEventListener("click",
												function(e) {
													closeAllLists(e.target);
												});
									}

									/*An array containing all the country names in the world:*/
									var countries = 
										[ "Afghanistan", "Albania",
											"Algeria", "Andorra", "Angola",
											"Anguilla", "Antigua & Barbuda",
											"Argentina", "Armenia", "Aruba",
											"Australia", "Austria",
											"Azerbaijan", "Bahamas", "Bahrain",
											"Bangladesh", "Barbados",
											"Belarus", "Belgium", "Belize",
											"Benin", "Bermuda", "Bhutan",
											"Bolivia", "Bosnia & Herzegovina",
											"Botswana", "Brazil",
											"British Virgin Islands", "Brunei",
											"Bulgaria", "Burkina Faso",
											"Burundi", "Cambodia", "Cameroon",
											"Canada", "Cape Verde",
											"Cayman Islands",
											"Central Arfrican Republic",
											"Chad", "Chile", "China",
											"Colombia", "Congo",
											"Cook Islands", "Costa Rica",
											"Cote D Ivoire", "Croatia", "Cuba",
											"Curacao", "Cyprus",
											"Czech Republic", "Denmark",
											"Djibouti", "Dominica",
											"Dominican Republic", "Ecuador",
											"Egypt", "El Salvador",
											"Equatorial Guinea", "Eritrea",
											"Estonia", "Ethiopia",
											"Falkland Islands",
											"Faroe Islands", "Fiji", "Finland",
											"France", "French Polynesia",
											"French West Indies", "Gabon",
											"Gambia", "Georgia", "Germany",
											"Ghana", "Gibraltar", "Greece",
											"Greenland", "Grenada", "Guam",
											"Guatemala", "Guernsey", "Guinea",
											"Guinea Bissau", "Guyana", "Haiti",
											"Honduras", "Hong Kong", "Hungary",
											"Iceland", "India", "Indonesia",
											"Iran", "Iraq", "Ireland",
											"Isle of Man", "Israel", "Italy",
											"Jamaica", "Japan", "Jersey",
											"Jordan", "Kazakhstan", "Kenya",
											"Kiribati", "Kosovo", "Kuwait",
											"Kyrgyzstan", "Laos", "Latvia",
											"Lebanon", "Lesotho", "Liberia",
											"Libya", "Liechtenstein",
											"Lithuania", "Luxembourg", "Macau",
											"Macedonia", "Madagascar",
											"Malawi", "Malaysia", "Maldives",
											"Mali", "Malta",
											"Marshall Islands", "Mauritania",
											"Mauritius", "Mexico",
											"Micronesia", "Moldova", "Monaco",
											"Mongolia", "Montenegro",
											"Montserrat", "Morocco",
											"Mozambique", "Myanmar", "Namibia",
											"Nauro", "Nepal", "Netherlands",
											"Netherlands Antilles",
											"New Caledonia", "New Zealand",
											"Nicaragua", "Niger", "Nigeria",
											"North Korea", "Norway", "Oman",
											"Pakistan", "Palau", "Palestine",
											"Panama", "Papua New Guinea",
											"Paraguay", "Peru", "Philippines",
											"Poland", "Portugal",
											"Puerto Rico", "Qatar", "Reunion",
											"Romania", "Russia", "Rwanda",
											"Saint Pierre & Miquelon", "Samoa",
											"San Marino",
											"Sao Tome and Principe",
											"Saudi Arabia", "Senegal",
											"Serbia", "Seychelles",
											"Sierra Leone", "Singapore",
											"Slovakia", "Slovenia",
											"Solomon Islands", "Somalia",
											"South Africa", "South Korea",
											"South Sudan", "Spain",
											"Sri Lanka", "St Kitts & Nevis",
											"St Lucia", "St Vincent", "Sudan",
											"Suriname", "Swaziland", "Sweden",
											"Switzerland", "Syria", "Taiwan",
											"Tajikistan", "Tanzania",
											"Thailand", "Timor L'Este", "Togo",
											"Tonga", "Trinidad & Tobago",
											"Tunisia", "Turkey",
											"Turkmenistan", "Turks & Caicos",
											"Tuvalu", "Uganda", "Ukraine",
											"United Arab Emirates",
											"United Kingdom",
											"United States of America",
											"Uruguay", "Uzbekistan", "Vanuatu",
											"Vatican City", "Venezuela",
											"Vietnam", "Virgin Islands (US)",
											"Yemen", "Zambia", "Zimbabwe" ];

									/*initiate the autocomplete function on the "myInput" element, and pass along the countries array as possible autocomplete values:*/
									autocomplete(document
											.getElementById("myInput"),
											countries);
								</script>
							</td>
						</tr>
						<tr>
							<td></td>
							<td><input type="submit" value="Search"></td>
						</tr>
					</tbody>
				</table>
			</div>

			<div id="History" class="tabcontent"></div>




			<script>
				function openCity(cityName, elmnt, color) {
					var i, tabcontent, tablinks;
					tabcontent = document.getElementsByClassName("tabcontent");
					for (i = 0; i < tabcontent.length; i++) {
						tabcontent[i].style.display = "none";
					}
					tablinks = document.getElementsByClassName("tablink");
					for (i = 0; i < tablinks.length; i++) {
						tablinks[i].style.backgroundColor = "";
					}
					document.getElementById(cityName).style.display = "block";
					elmnt.style.backgroundColor = color;

				}
				// Get the element with id="defaultOpen" and click on it
				document.getElementById("defaultOpen").click();
			</script>
		</div>
		<!-- </div> -->

	</div> --%>