<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="results"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>Results</title>

<meta name="viewport" content="width=device-width, initial-scale=1">
<!--  css Files -->
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link href="<results:url value="/resources/css/result.css" />"
	rel="stylesheet">
<link href="<results:url value="/resources/css/twentytwenty.css" />"
	rel="stylesheet">
<!--  js files -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="http://rawgit.com/saribe/eModal/master/dist/eModal.min.js"></script>

</head>

<body>

	<div class="container">
		<div class="main_header">
			<h1>AtomJpeg Test</h1>
		</div>
		<div class="user_input_form">
			<form id="searchinput" action="/atomjpeg/ready" method="POST">
				<div class="row">
					<div class="col-md-8">
						<input id="url" name="url"
							placeholder="http://www.yourwebsite.com" type="text"
							class="form-control searchinput" value="">
					</div>
					<div class="col-md-4">
						<button class="btn-primary">Start</button>
					</div>
				</div>
				<!-- End of first row-->

				<!-- Start of 2nd row -->
				<div class="row">
					<div class="col-md-3 atom_option">
						<fieldset>
							<legend>Atom option</legend>
						</fieldset>
						<div class="row">
							<div class="col-md-12">
								<select class="form-control" name="profile" id="profile">

									<option value="" style="background-color: black;">Profile</option>
									<option value="">Fast</option>
									<option value="Baseline">Baseline</option>
									<option value="Main">Main</option>
									<option value="High">High</option>
								</select>
							</div>

						</div>
						<div class="row">
							<div class="col-md-12">
								<select class="form-control" name="level" id=""level"">
									<option value="">Level</option>
									<option value="High Quality">High Quality</option>
									<option value="Normal">Normal</option>
									<option value="High Compression">High Compression</option>
									<option value="Extreme">Extreme</option>
								</select>
							</div>
						</div>
					</div>
					<div class="col-md-9">
						<div class="row">
							<div class="col-md-12">
								<fieldset>
									<legend>Image option</legend>
									<div class="checkboxes">
										<input id="jpg" name="jpg" type="checkbox"><span
											class="check_box">JPG</span> <input id="png" name="png"
											type="checkbox"><span class="check_box">PNG</span> <input
											id="gif" name="gif" type="checkbox"><span
											class="check_box">GIF</span>
									</div>
								</fieldset>
							</div>
						</div>
						<div class="row">
							<div class="col-md-12 network_option">
								<fieldset>
									<legend>Network option</legend>
									Monthly PageView : <input id="pageview" name="pageview"
										type="text"> Billing per 1GB($): <input id="billing"
										name="billing" type="text">
								</fieldset>
							</div>
						</div>
					</div>
				</div>
			</form>
		</div>


	</div>



	<div class="container">
		<!-- Start of 1st row -->
		<div class="result">
			<div class="report">
				<h3>Report</h3>
				<div class="row">
					<table class="table">
						<thead>
							<tr>
								<th>Images</th>
								<th>Less Bandwidth</th>
								<th>Size Reduction</th>
								<th>Faster Load Time</th>
								<th>Annual CDN Savings</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>${numberOfImages}</td>
								<td>${less_bandwidth}</td>
								<td>${size_reduction_percentage}</td>
								<td>0.07 sec</td>
								<td>$400,208</td>
							</tr>

						</tbody>
					</table>

				</div>
			</div>
			<a href="http://localhost:8080/atomjpeg/">Home</a>
			<form id="searchinput" action="/atomjpeg/result" method="POST">
				<div class="row">
					<div class="col-md-4">
						<button class="btn-primary">Display Images</button>
					</div>
				</div>
			</form>
		</div>
	</div>


	<!--  <script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>
<script src='https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.0.0-alpha.6/js/bootstrap.min.js'></script>
  -->
	<script src="<results:url value="/resources/js/main.js"/>"></script>
	<script src="<results:url value="/resources/js/modernizer.js"/>"></script>
	<script src="<results:url value="resources/js/jquery.event.move.js"/>"></script>
	<script
		src="<results:url value="resources/js/jquery.twentytwenty.js"/>"></script>
	<script src="<results:url value="resources/js/jquery.visible.js"/>"></script>
	<script>
		window.onload = function() {
			$("a").click(function() {

				var modal_name = $(this)[0]["attributes"][0]["value"];
				console.log(modal_name);
				setTimeout(function() {
					$(".20_container").twentytwenty();
				},

				300);
				/*
				var image1 = $(modal_name+" .image1");
				console.log(image1);
				
				
				
				var image1_width = image1[0].width;
				var image1_height = image1[0].height;
				var handler_position = image1_width/2;
				console.log(image1_width);
				
				if(image1_width < 300){
				$(".twentytwenty-container").css("padding-left","150");
				//$(".twentytwenty-handle").css("left",handler_position+150);
				}
				
				var handler_position = image1_width/2;
				$(modal_name + " .twentytwenty-container").css("height",(image1_height - 100) + "px");
				$(modal_name + " .twentytwenty-before").removeAttr("style");
				$(modal_name + " .twentytwenty-after").removeAttr("style");
				$(".twentytwenty-handle").css("left",handler_position);
				  $(modal_name).on("load",function(){
				  console.log(modal_name + " is loaded");
				  });


				$(modal_name + " .20_container").twentytwenty();
				
				 */

			});
		}
		/*
		 $(document).ready(function(){
		 alert("ffs");	
		 $("a").click(function(){
		 $(".20_container").twentytwenty();
		 //var image1 = document.getElementById("image1");
		 //	$(".20_container").twentytwenty();	

		 //var modal_name = $(this)[0]["href"].split("#")[1];
		 alert("dsgds");
		 var modal_name = $(this)[0]["attributes"][0]["value"];
		 console.log(modal_name);
		 var image1 = $(modal_name+" .image1");
		 console.log(image1);
		 var image1_width = image1[0].width;
		 var image1_height = image1[0].height;
		 console.log(image1_height);
		 $(".twentytwenty-container").css("height",(image1_height - 100) + "px");
		 $(".twentytwenty-before").removeAttr("style");
		 $(".twentytwenty-after").removeAttr("style");
		 $(".twentytwenty-handle").css("left:,"200px");
		 });
		 });
		 */
	</script>
</body>
</html>
