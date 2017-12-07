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
								<td>${less_bandwidth} KB</td>
								<td>${size_reduction_percentage} %</td>
								<td>0.07 sec</td>
								<td>$400,208</td>
							</tr>

						</tbody>
					</table>

				</div>
			</div>

			<!-- End of 1st row -->
			<!-- Start of 2nd row-->
			<div class="result_images">
				<h3>Image Resuts</h3>

				<a href="http://localhost:8080/atomjpeg/">Home</a>
				<h4>All Searched Images:</h4>
				<div class="row one_image_result">
					<c:forEach items="${allImageResults}" var="tempItem"
						varStatus="loop_in_modal">
						<c:set var="element" value="${tempItem}" />
						<!--  modal is below  -->
						<!-- Modal HTML -->
						<div id="myModal${loop_in_modal.index}" class="modal fade">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-header">
										<!-- <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button> -->
										<!--<h4 class="modal-title">${element[6]}</h4>-->
									</div>
									<div class="modal-body">
										<div class="20_container">
											<img src="<c:url value ="${element[0]}" />"
												class="image1 image1" alt="Original Image"> <img
												src="<c:url value ="${element[1]}" />"
												class="image2  image2" alt="Modified Image">

										</div>

										<span class="cd-handle"></span>
										</figure>
										<!-- cd-image-container -->
									</div>
									<div class="modal-footer">
										<table class="table table-inverse">
											<thead>
												<tr>
													<th scope="row">Original Image</th>
													<th></th>
													<th>AtomJPEG Result</th>
												</tr>
											</thead>
											<tbody>
												<tr>
													<td scope="row">${element[2]}bytes</td>
													<td></td>
													<td>${element[3]}bytes</td>
												</tr>
												<tr>
													<td scope="row"></td>
													<td>${element[5]}</td>
													<td></td>
												</tr>
												<tr>
													<th scope="row"></th>
													<td>${element[4]}</td>
													<td></td>
												</tr>
											</tbody>
										</table>
										<button type="button" class="btn btn-primary"
											data-dismiss="modal">Close</button>

									</div>
								</div>
							</div>
						</div>

						<!-- end of modal -->
						<a href="#myModal${loop_in_modal.index}" data-toggle="modal">
							<div class="row">

								<div class="col-md-4">
									<img class="img-responsive"
										src="<c:url value ="${element[0]}" />" />
								</div>
								<div class="col-md-4">
									<p style="overflow: auto; word-wrap: break-word;">${element[6]}
									</p>

									<!--Images Information-->
									<table class="table table-inverse">
										<thead>
											<tr>
												<th scope="row">Original Image</th>
												<th></th>
												<th>AtomJPEG Result</th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td scope="row">${element[2]}bytes</td>
												<td></td>
												<td>${element[3]}bytes</td>
											</tr>
											<tr>
												<td scope="row"></td>
												<td>${element[5]}</td>
												<td></td>
											</tr>
											<tr>
												<th scope="row"></th>
												<td>${element[4]}</td>
												<td></td>
											</tr>
										</tbody>
									</table>
								</div>
								<div class="col-md-4" style="float: right;">

									<img class="img-responsive"
										src="<c:url value ="${element[1]}" />">

								</div>
							</div>
						</a>
					</c:forEach>
				</div>
			</div>
			<!-- End of 2nd row-->
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
