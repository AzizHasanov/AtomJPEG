
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%-- the Spring Framework form tags can be used to bind a form to a model, 
so that form can be auto-filled with model data. --%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>AtomJPEG Main Page </title>

<!-- css files -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">
<link rel="stylesheet prefetch"
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.0.0-alpha.6/css/bootstrap.min.css">
<link href="<c:url value="/resources/css/style.css" />"
	rel="stylesheet">
<!-- js files-->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script>
<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script src="//rawgit.com/saribe/eModal/master/dist/eModal.min.js"></script> 
</head>
<body>


	<div class="container">
		<div class="main_header">
			<h1>AtomJPEG Test</h1>
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
										<input id="jpg" name="jpg" type="checkbox"><span class="check_box">JPG</span>
										<input id="png" name="png" type="checkbox"><span class="check_box">PNG</span>
										<input id="gif" name="gif" type="checkbox"><span class="check_box">GIF</span>
									</div>
								</fieldset>
							</div>
						</div>
						<div class="row">
							<div class="col-md-12 network_option">
								<fieldset>
									<legend>Network option</legend>
									Monthly PageView : <input id="pageview" name="pageview" type="text"> 
									Billing per 1GB($): <input id="billing" name="billing" type="text">
								</fieldset>
							</div>
						</div>
					</div>
				</div>
			</form>
		</div>

	</div>

	<!--  <script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>
<script src='https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.0.0-alpha.6/js/bootstrap.min.js'></script>
  -->

	<script> eModal.alert('You shall not pass!');</script>

</body>