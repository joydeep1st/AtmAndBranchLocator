<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="Utility.ConfigProvider"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<jsp:include page="../slide_1.jsp"></jsp:include>
<title></title>
</head>
<script>
	function populateLocs(strLocations) {
		var x = document.getElementById("locality");
		var i;
		for (i = x.options.length - 1; i >= 0; i--) {
			x.remove(i);
		}
		var y = strLocations.split(",");
		for (var i = 0; i < y.length; i++) {
			var option = document.createElement("option");
			option.id = y[i].trim();
			option.value = y[i].trim();
			option.text = y[i].trim();
			x.add(option);
		}

	}

	function populateZips(strZips) {
		var x = document.getElementById("zipcode");
		var i;
		for (i = x.options.length - 1; i >= 0; i--) {
			x.remove(i);
		}
		var y = strZips.split(",");
		for (var i = 0; i < y.length; i++) {
			var option = document.createElement("option");
			option.text = y[i];
			x.add(option);
		}
		x.selectedIndex = 1;
	}

	function reloadvalues(str, locs, zips, selectedLoc, zipcode, address) {
		document.getElementById("banks").value = 'Barclays';
		switch (str) {
		case "atm":
			document.getElementById("atm").checked = true;
			break;
		case "branch":
			document.getElementById("branch").checked = true;
			break;
		}
		if (!(locs == null || locs == 'null')) {
			populateLocs(locs);
		}
		if (!(selectedLoc == null)) {
			document.getElementById('locality').value = selectedLoc;
		}
		if (!(zips == null || zips == 'null')) {
			populateZips(zips);
		}
		if (!(address == null || address == 'null')) {
			var searchedVal = '';
			searchedVal = searchedVal + 'Bank: Barclays\r\n\r\n';
			searchedVal = searchedVal + 'Location: ' + selectedLoc + '\r\n\r\n';
			var addr = address.split(':;:')[0];
			var addrLines = addr.split('::')[0] + '\r\n' + addr.split('::')[1]
					+ '\r\n' + addr.split('::')[2];
			var coordinates = address.split(':;:')[1];
			searchedVal = searchedVal + 'Address:\r\n' + addr.split('::')[0]
					+ '\r\n';
			searchedVal = searchedVal + addr.split('::')[1] + '\r\n';
			searchedVal = searchedVal + addr.split('::')[2] + '\r\n';
			searchedVal = searchedVal + zipcode + '\r\n\r\n';
			searchedVal = searchedVal + 'Coordinates:\r\n' + coordinates;
			document.getElementById("locality").value = selectedLoc;
			document.getElementById("zipcode").value = zipcode;
			document.getElementById('rightcolumn').innerText = searchedVal;
		}
	}

	function getLocations() {
		return document.getElementById('locality').value;
	}
</script>
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
<style>
table {
	margin-bottom: 0px;
}

form {
	margin-bottom: 0px;
}

#fileContents {
	margin-left: 0px;
	left: 0%; top : 50%;
	font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
	border-collapse: collapse;
	width: 100%;
	font-size: 12px;
	top: 50%;
}

#fileContents td, #fileContents th {
	border: 1px solid #ddd;
	padding: 8px;
}

#fileContents tr:nth-child(even) {
	background-color: #f2f2f2;
}

#fileContents tr:hover {
	background-color: #ddd;
}

#fileContents th {
	padding-top: 2px;
	padding-bottom: 12px;
	text-align: left;
	background-color: #4CAF50;
	color: white;
}

#leftcolumn, #rightcolumn {
	border: 1px solid yellow;
	float: left;
	background-color: #D0D3D4;
	height: 280px;
}

#leftcolumn {
	width: 310px;
}

#rightcolumn {
	width: 260px;
	color: blue;
	text-align: left;
	padding-left: 5px;
	padding-top: 5px;
}

#foundLocality, #foundAddress, #foundZip, #foundCoor {
	background-color: transparent;
	border: none;
	border: none transparent;
}

html{
	padding-top: 70px;
	background-color: #f7e6ee;
}
</style>
<body
	onload="reloadvalues('<%=request.getAttribute("radiobutton")%>','<%=request.getAttribute("locations")%>','<%=request.getAttribute("zips")%>','<%=request.getAttribute("prevSelectedLocality")%>','<%=request.getAttribute("zipcode")%>','<%=request.getAttribute("address")%>')">

	<%
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Cache-Control", "no-store");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);
		if (session.getAttribute("USERNAME") == null) {
			response.sendRedirect(request.getContextPath());
		}
	%>

	<div class="row">
		<div class="four columns">
			<font color="#ECF0F1" size="2px">Welcome <b><%=session.getAttribute("USERNAME")%></b></font>
			&nbsp <a href="<%=request.getContextPath()%>/JSP/logout.jsp"
				onclick="LogoutServlet"><font color="#a49a23" size="2px"><u>logout</u></font></a>
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
				<div id="leftcolumn">
					<table>
						<tbody>
							<tr>
								<td style="width: 80px;">Bank Name:</td>
								<td><select id="banks" name=banks" style="width: 200px;">
										<option name="">Select a Bank</option>
										<option name="LyodBank">Lyod Bank</option>
										<option name="Barclays">Barclays</option>
										<option name="RBS">Royal Bank of Scotland</option>
								</select></td>
							</tr>
					</table>
					<form id="atmbranch" name="atmbranch" action="GetAllFromJsons"
						method="post">
						<table>
							<tr>
								<td>Branch/ATM:</td>
								<td><input type="radio" id="atm" name="atm" value="atm"
									onclick="CallServlet('GetAllFromJsons')">ATM <input
									type="radio" id="branch" name="atm" value="branch"
									onclick="CallServlet('GetAllFromJsons')">Branch <input
									type="hidden" name="atm" value="atm123" /></td>
							</tr>
						</table>
					</form>
					<form id="locations" name="locations" action="GetAllFromJsons"
						method="post">
						<table>
							<tr>
								<td style="width: 80px;">Locality:</td>
								<td><select id="locality" name="locality" style="width: 200px;"
									onchange="CallServlet('GetzipsFromJsons')">

								</select> <input type="hidden" id="hiddenlocs" name="hiddenlocs"
									value="return getLocations()" /></td>
							</tr>
						</table>
					</form>
					<form id="search" name="search" action="GetAllFromJsons"
						method="post">
						<table>
							<tr>
								<td>Zip Code:</td>
								<td><select id="zipcode" name="zipcode" value=""
									style="width: 140px;">

								</select></td>
							</tr>
							<tr>
								<td></td>
								<td><input type="submit" value="Search"></td>
							</tr>
							</tbody>
						</table>
					</form>
				</div>
				<div id="rightcolumn">					

				</div>
			</div>


			<div id="History" class="tabcontent">
				<%
					String uname = (String) session.getAttribute("USERNAME");
				%>
				<%
					String dburl = ConfigProvider.db_atm_branch_locator_Con_URL;
					String dbusername = ConfigProvider.dbuname;
					String dbpassword = ConfigProvider.dbpassword;
					String dbdriver = ConfigProvider.Driver;
				%>
				<sql:setDataSource var="fetchHistorydetails" driver="<%=dbdriver%>"
					url="<%=dburl%>" user="<%=dbusername%>" password="<%=dbpassword%>" />

				<sql:query dataSource="${fetchHistorydetails}" var="result">select * from tbl_history where username='<%=uname%>';
				</sql:query>
				<div id="historyContents">
				<table id="fileContents">					
					<tr>
						<th>BankName</th>
						<th>Search Type</th>
						<th>Address</th>
						<th>Coordinates</th>
					</tr>
					<c:forEach var="row" items="${result.rows}">
						<tr>
							<c:set var = "add" value = "${row.address}"/>
							<c:set var = "type" value = "${row.type}"/>
							<td>${row.bankname}</td>
							<td>${fn:toUpperCase(type)}</td>
							<td>${row.locality} ${fn:replace(add,'::',' ')} ${row.zip}</td>
							<td>${row.coordinates}</td>							
						</tr>
					</c:forEach>					
				</table>
				</div>
			</div>




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

	</div>

</body>
</html>