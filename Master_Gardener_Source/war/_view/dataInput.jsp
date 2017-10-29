<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<link rel="icon" type="image/png" href="images/nittany_lions_logo.jpg">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="generator"
	content="Script Eden ( http://scripteden.net/ ) Template Builder v2.0.0">
<title>${account.name}- Master Gardener Data</title>
<!--pageMeta-->

<!-- Loading Bootstrap -->
<link href="bootstrap/css/bootstrap.css" rel="stylesheet">

<!-- Loading Flat UI -->
<link href="css/flat-ui.css" rel="stylesheet">

<link href="css/style.css" rel="stylesheet">



<!-- HTML5 shim, for IE6-8 support of HTML5 elements. All other JS at the end of file. -->
<!--[if lt IE 9]>
      <script src="js/html5shiv.js"></script>
      <script src="js/respond.min.js"></script>
    <![endif]-->

<!--headerIncludes-->



</head>
<body>

	<div id="page" class="page">

		<header class="item header margin-top-0 header10" id="header10">

			<div class="wrapper">

				<nav role="navigation"
					class="navbar navbar-blue navbar-embossed navbar-lg navbar-fixed-top">

					<div class="container">

						<div class="navbar-header">
							<a href="home" class="navbar-brand brand"> <img
								src="images/nittany_lions_logo.jpg" id="logo">
							</a>
							<button data-target="#navbar-collapse-02" data-toggle="collapse"
								class="navbar-toggle" type="button">
								<span class="sr-only">Toggle navigation</span>
							</button>
						</div>
						<!--/.navbar-header -->
						<form class="search-collapse"
							action="${pageContext.servletContext.contextPath}/data"
							method="post">
							<input type="text" id="searchText" name="keyword"
								placeholder="Search..." required> <input type="image"
								src="images/search_black.jpg" value="Search" id="searchButton">
						</form>
						<br>

						<div id="navbar-collapse-02" class="collapse navbar-collapse">
							<ul class="nav navbar-nav navbar-right">
								<li class="propClone">
									<form action="${pageContext.servletContext.contextPath}/dataInput"
										method="get">
										<input name="buttonPress" type="image" value="Data"
											class="homeLinks" src="images/Data-Board.png" title="Data" />
									</form>
								</li>
								<li class="propClone">
									<form action="${pageContext.servletContext.contextPath}/user"
										method="get">
										<input name="buttonPress" type="image" value="Account"
											class="homeLinks" src="images/user.png" title="My Account" />
									</form>
								</li>
								<li class="propClone">
									<form
										action="${pageContext.servletContext.contextPath}/editUser"
										method="get">
										<input name="submit" type="image" value="submit"
											class="homeLinks" src="images/gear.png"
											title="Account Settings" />
									</form>
								</li>
								<li class="propClone">
									<form action="${pageContext.servletContext.contextPath}/login"
										method="post">
										<input name="loginSubmit" type="image" value="Logout"
											class="homeLinks" src="images/logout.png" alt="submit"
											title="Log Out">
									</form>
								</li>
							</ul>
						</div>
						<!--/.navbar-collapse -->
					</div>
					<!-- /.container -->
				</nav>


				<!-- 	
    		<form id="gardenName" action="" method="post">
            	<input type="text" id="username" name="gardenNameEntry"placeholder="Garden Name" value="" required>
                <input type="password" id="username" name="password"placeholder="Password" value="" required>
                <input type="Submit" value="Login" id="loginSubmit" name="loginSubmit">




				<%--
                <div id="errorMessage">
                	<c:if test="${! empty errorMessage}">
						<tr>${errorMessage}</tr>
					</c:if>
				</div>
				--%>


			</form><!-- /.data -->
				<div class="dataInputContainer">
					<form action="${$pageContext.servletContext.contextPath}/dataInput" method="post">
						<table>
							<tr><td>Garden Name:</td>
								<c:if test="${! empty gardenName}">
									<td><input type="text" id="password" name="gardenname" placeholder="Garden Name" value="${gardenName}"></td>
								</c:if>

								<c:if test="${empty gardenName}">
									<td><input type="text" id="gardenName" name="gardenname" placeholder="Garden Name"></td>
								</c:if>
							</tr>

							<tr><td>Date:</td>
								<c:if test="${! empty date}">
									<td><input type="text" id="date" name="date" placeholder="Date" value="${date}"></td>
								</c:if>

								<c:if test="${empty date}">
									<td><input type="text" id="date" name="date" placeholder="Date"></td>
								</c:if>
							</tr>

							<tr><td>Start Time:</td>
								<c:if test="${! empty startTime}">
									<td><input type="text" id="startTime" name="starttime" placeholder="Start Time" value="${startTime}"></td>
								</c:if>

								<c:if test="${empty startTime}">
									<td><input type="text" id="startTime" name="startime" placeholder="Start Time"></td>
								</c:if>
							</tr>

							<tr><td>End Tiem:</td>
								<c:if test="${! empty endTime}">
									<td><input type="text" id="endTime" name="endtime" placeholder="End Time" value="${endTime}"></td>
								</c:if>

								<c:if test="${empty endTime}">
									<td><input type="text" id="endTime" name="endtime" placeholder="End Time"></td>
								</c:if>
							</tr>

							<tr><td>Temperature:</td>
								<c:if test="${! empty temperature}">
									<td><input type="text" id="temperature" name="temperature" placeholder="Temperature" value="${temperature}"></td>
								</c:if>

								<c:if test="${empty temperature}">
									<td><input type="text" id="temperature" name="temperature" placeholder="temperature"></td>
								</c:if>
							</tr>

							<tr><td>Windo:</td>
								<c:if test="${! empty wind}">
									<td><input type="text" id="wind" name="wind" placeholder="Wind" value="${wind}"></td>
								</c:if>

								<c:if test="${empty wind}">
									<td><input type="text" id="wind" name="wind" placeholder="Wind"></td>
								</c:if>
							</tr>

							<tr><td>Cloud Cover:</td>
								<c:if test="${! empty cloudCover}">
									<td><input type="text" id="cloudCover" name="cloudcover" placeholder="Cloud Cover" value="${cloudCover}"></td>
								</c:if>

								<c:if test="${empty cloudCover}">
									<td><input type="text" id="cloudCover" name="cloudcover" placeholder="Cloud Cover"></td>
								</c:if>
							</tr>

							<tr><td>Plant Type:</td>
								<c:if test="${! empty plantType}">
									<td><input type="text" id="plantType" name="planttype" placeholder="Plant Type" value="${plantType}"></td>
								</c:if>

								<c:if test="${empty plantType}">
									<td><input type="text" id="plantType" name="planttype" placeholder="Plant Type"></td>
								</c:if>
							</tr>

							<tr><td>Pollinator Type:</td>
								<c:if test="${! empty pollinatorType}">
									<td><input type="text" id="pollinatorType" name="pollinatortype" placeholder="Pollinator Type" value="${pollinatorType}"></td>
								</c:if>

								<c:if test="${empty pollinatorType}">
									<td><input type="text" id="pollinatorType" name="pollinatortype" placeholder="Pollinator Type"></td>
								</c:if>
							</tr>

							<tr><td>Pollinator Count:</td>
								<c:if test="${! empty pollinatorCount}">
									<td><input type="text" id="pollinatorCount" name="pollinatorcount" placeholder="Pollinator Count" value="${pollinatorCount}"></td>
								</c:if>

								<c:if test="${empty pollinatorCount}">
									<td><input type="text" id="pollinatorCount" name="pollinatorcount" placeholder="Pollinator Count"></td>
								</c:if>
							</tr>

							<tr><td>Average Height of Plot:</td>
								<c:if test="${! empty avgHeightofPlot}">
									<td><input type="text" id="avgHeightofPlot" name="avgheightofplot" placeholder="Average Hieght of Plot" value="${avgHeightofPlot}"></td>
								</c:if>

								<c:if test="${empty abgHeightofPlot}">
									<td><input type="text" id="avgHeightofPlot" name="avgheightofplot" placeholder="Average Height of Plot"></td>
								</c:if>
							</tr>

							<tr><td>Size of Plot:</td>
								<c:if test="${! empty sizePlot}">
									<td><input type="text" id="sizePlot" name="sizeplot" placeholder="Size of Plot" value="${sizePlot}"></td>
								</c:if>

								<c:if test="${empty sizePlot}">
									<td><input type="text" id="sizePlot" name="sizeplot" placeholder="Size of Plot"></td>
								</c:if>
							</tr>

							<tr><td>Blooms Open:</td>
								<c:if test="${! empty bloomsOpen}">
									<td><input type="text" id="bloomsOpen" name="bloomsopen" placeholder="Blooms Open" value="${bloomsOpen}"></td>
								</c:if>

								<c:if test="${empty bloomsOpen}">
									<td><input type="text" id="bloomsOpen" name="bloomsopen" placeholder="Blooms Open"></td>
								</c:if>
							</tr>

							<tr><td>Flower Coverage:</td>
								<c:if test="${! empty flowerCoverage}">
									<td><input type="text" id="flowerCoverage" name="flowercoverage" placeholder="Flower Coverage" value="${flowerCoverage}"></td>
								</c:if>

								<c:if test="${empty flowerCoverage}">
									<td><input type="text" id="flowerCoverage" name="flowercoverage" placeholder="Flower Coverage"></td>
								</c:if>
							</tr>

							<tr><td>Plant Vigor:</td>
								<c:if test="${! empty plantVigor}">
									<td><input type="text" id="plantVigor" name="plantvigor" placeholder="Plant Vigor" value="${plantVigor}"></td>
								</c:if>

								<c:if test="${empty plantVigor}">
									<td><input type="text" id="plantVigor" name="plantvigor" placeholder="Plant Vigor"></td>
								</c:if>
							</tr>

							<tr><td>Comments:</td>
								<c:if test="${! empty comments}">
									<td><input type="text" id="comments" name="comments" placeholder="Comments" value="${Comments}"></td>
								</c:if>

								<c:if test="${empty comments}">
									<td><input type="text" id="comments" name="comments" placeholder="Comments"></td>
								</c:if>
							</tr>

							<tr>

							</tr>


							<tr><td><input type="submit" id="loginSubmit" name="buttonPress" value="Submit Data"></td></tr>
						</table>
						<c:if test="${! empty errorMessage}">
							<tr>${errorMessage}</tr>
						</c:if>
					</form>


				</div>
			</div>



				<%--
				<form id="gardenName" action="" method="post">
					<input type="text" name="gardenNameEntry" placeholder="Garden Name"
						value="" required>
				</form>

				<div class="dropdown">
					<button onclick="myFunction()" class="dropbtn">Dropdown</button>
					<div id="myDropdown" class="dropdown-content">
						<a href="#home">Home</a> <a href="#about">About</a> <a
							href="#contact">Contact</a>
					</div>
				</div>




				<script>
				/* When the user clicks on the button, 
				toggle between hiding and showing the dropdown content */
				function myFunction() {
				    document.getElementById("myDropdown").classList.toggle("show");
				}

				// Close the dropdown if the user clicks outside of it
				window.onclick = function(event) {
				  if (!event.target.matches('.dropbtn')) {

				    var dropdowns = document.getElementsByClassName("dropdown-content");
				    var i;
				    for (i = 0; i < dropdowns.length; i++) {
				      var openDropdown = dropdowns[i];
				      if (openDropdown.classList.contains('show')) {
				        openDropdown.classList.remove('show');
				      }
				    }
				  }
				}
																				</script>


				<form id="Date" action="" method="post">
					<input type="text" name="dateField" placeholder="Date" value=""
						required>
				</form>

				<form id="startTime" action="" method="post">
					<input type="text" name="startTimeField" placeholder="Start Time"
						value="" required>
				</form>

				<form id="endTime" action="" method="post">
					<input type="text" name="endTimeField" placeholder="End Time"
						value="" required>
				</form>

				<form id="temperature" action="" method="post">
					<input type="text" name="temperatureField"
						placeholder="Temperature" value="" required>
				</form>

				<form id="wind" action="" method="post">
					<input type="text" name="windField" placeholder="Wind" value=""
						required>
				</form>

				<form id="cloudCover" action="" method="post">
					<input type="text" name="cloudCoverField" placeholder="Cloud COver"
						value="" required>
				</form>

				<form id="plantType" action="" method="post">
					<input type="text" name="plantTypeField" placeholder="Plant Type"
						value="" required>
				</form>

				<form id="pollinatorType" action="" method="post">
					<input type="text" name="pollinatorTypeField"
						placeholder="Pollinator Type" value="" required>
				</form>

				<form id="pollinatorCount" action="" method="post">
					<input type="text" name="pollinatorTypeCounFieldt"
						placeholder="Pollinator Count" value="" required>
				</form>

				<form id="avgHeightofPlot" action="" method="post">
					<input type="text" name="avgHeightofPlotField"
						placeholder="Plot Height Avg" value="" required>
				</form>

				<form id="sizePlot" action="" method="post">
					<input type="text" name="sizePlotField" placeholder="Plot Size"
						value="" required>
				</form>

				<form id="bloomsOpen" action="" method="post">
					<input type="text" name="bloomsOpenField" placeholder="Blooms Open"
						value="" required>
				</form>

				<form id="flowerCoverage" action="" method="post">
					<input type="text" name="flowerCoverageField"
						placeholder="Flower Coverage" value="" required>
				</form>

				<form id="plantVigor" action="" method="post">
					<input type="text" name="plantVigorField" placeholder="Plant Vigor"
						value="" required>
				</form>
				--%>




			</div>
	<%-- /.wrapper --%>



			<!--pageContent-->
			<div>
				<%--  	<div id="coverPhotoContainer">
						<img src="images/black_user.jpg" id="userPhoto">
					</div>
					<div class="pageInfo" style="height: 100px;">
						<p id="userName">${account.name}</p>
						<div id="bio">${account.description}</div>

						<h1 style="position:relative;left:10px;">Groups you belohhng to:</h1>

						<form id="GroupGet" method="post">
								<table id="userGroups">
									<c:forEach items="${groups}" var="group">
										<tr class="groupList">
										<td style="color:white"><input type="submit" value="${group.name}" id="groupDisplay" name="Submit" class="buttonAsLink"></td>
										<td class="descriptionCol" style="color:white">"${group.description}"</td>
										</tr>
									</c:forEach>
								</table>
						</form>





					</div>

				--%>
				<div class="sidebar">
					<button onclick="toggleSideBar()" id="toggle"
						style="right: 0px; color: white">Group Options</button>
					<div id="sideBar" style="right: -300px;">
						<form id="GroupGet" method="post">
							<ul class="list-unstyled">
								<li class="sidebarItem"><a
									href="http://localhost:8081/Master-Gardener/createGroup"
									style="color: white">Create New Group</a></li>
								<c:forEach items="${groups}" var="group">
									<tr>
										<td class="groupListItem"><input class="sidebarItem"
											type="Submit" value="${group.name}" id="Submit" name="Submit">
										</td>
									</tr>
								</c:forEach>
							</ul>
						</form>
						<!--<input type="Submit" value=${group.name } id="Submit" name="Submit">-->
					</div>
				</div>
			</div>

			<!--/pageContent-->
	</div>
	<!-- /.wrapper -->

	</header>
	<!-- /.item -->
	</div>
	<!-- /#page -->
	<!-- Load JS here for greater good =============================-->
	<script src="js/jquery-1.8.3.min.js" />
	<script src="js/jquery-ui-1.10.3.custom.min.js" />
	<script src="js/jquery.ui.touch-punch.min.js" />
	<script src="js/bootstrap.min.js" />
	<script src="js/bootstrap-select.js" />
	<script src="js/bootstrap-switch.js" />
	<script src="js/flatui-checkbox.js" />
	<script src="js/flatui-radio.js" />
	<script src="js/jquery.tagsinput.js" />
	<script src="js/jquery.placeholder.js" />
	<script src="js/jquery.nivo.slider.pack.js" />
	<script src="js/application.js" />
	<script src="js/over.js" />
	<script>

	function toggleSideBar() {
    	var x = document.getElementById('sideBar');
    	var y = document.getElementById('toggle');
    	if (x.style.right === '-300px') {
        	x.style.right = '0%';
        	y.style.right = '300px';
    	} else {
        	x.style.right = '-300px';
        	y.style.right = '0px';
    	}
	}

	$(function(){

		if( $('#nivoSlider').size() > 0 ) {

	    	$('#nivoSlider').nivoSlider({
	    		effect: 'random',
				pauseTime: 5000
	    	});

		}

	})
																									</script>


</body>
</html>