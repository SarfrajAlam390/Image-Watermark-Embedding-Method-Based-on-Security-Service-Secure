<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>Resister</title>
		<!-- Description, Keywords and Author -->
		<meta name="description" content="Your description">
		<meta name="keywords" content="Your,Keywords">
		<meta name="author" content="ResponsiveWebInc">
		
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		
		<!-- Styles -->
		<!-- Bootstrap CSS -->
		<link href="css/bootstrap.min.css" rel="stylesheet">
		<!-- Font awesome CSS -->
		<link href="css/font-awesome.min.css" rel="stylesheet">		
		<!-- Custom CSS -->
		<link href="css/style.css" rel="stylesheet">
		
		<!-- Favicon -->
		<link rel="shortcut icon" href="#">
	</head>
	
	<body>
	
		<div class="wrapper">
			<!-- header -->
			<jsp:include page="menu.jsp"></jsp:include>
			
			<!-- main content -->
			<div class="main-content">
				<div class="container">
					<!-- register area -->
					<div class="register-area">
						<!-- heading -->
						<h3>Sign Up, For An Account</h3>
						<%String status = request.getParameter("status");
						if(status!=null)
						{%>
							<h3 style="color: red;"><%out.print(status); %></h3>
						<%}
						%>
						<!-- paragraph -->
						<form role="form" action="./RegisterServlet" method="post" id="register-form">
							<div class="form-group">
								<label for="exampleInputName1">Name</label>
								<input type="text" class="form-control" name="name" id="exampleInputName1" placeholder="Full Name" required="">
							</div>
							<div class="form-group">
								<label for="exampleInputPassword1">Password</label>
								<input type="password" class="form-control" name="password" id="exampleInputPassword1" placeholder="Password" required="">
							</div>
							<div class="form-group">
								<label for="exampleInputEmail1">Email address</label>
								<input type="email" class="form-control" name="email" id="exampleInputEmail1" placeholder="Enter email">
							</div>
							<div class="form-group">
								<label for="exampleInputDob1">Date of Birth</label>
								<input type="date" class="form-control" name="dob" id="exampleInputDob1" placeholder="DD-MM-YYY">
							</div>
							<div class="form-group">
								<label>Gender</label><br>
								<div class="btn-group" data-toggle="buttons">
									<label class="btn btn-warning btn-sm">
										<input type="radio" name="gender" id="option1" value="Male" required=""> Male
									</label>
									<label class="btn btn-warning btn-sm">
										<input type="radio" name="gender" id="option2" value="Female" required=""> Female
									</label>
								</div>
							</div>
							
							
							<div class="form-group">
								<label for="address">Address</label>
								<textarea rows="4" cols="" class="form-control" name="address" required=""></textarea>
							</div>
							<button type="submit" class="btn btn-warning">Signup</button>&nbsp;
							<button type="reset" class="btn btn-default">Reset</button>
						</form>
					</div>
				</div>
			</div>
			
			<!-- footer -->
		</div>		
		
		<!-- Javascript files -->
		<!-- jQuery -->
		<script src="js/jquery.js"></script>
		<!-- Bootstrap JS -->
		<script src="js/bootstrap.min.js"></script>
		<!-- Respond JS for IE8 -->
		<script src="js/respond.min.js"></script>
		<!-- HTML5 Support for IE -->
		<script src="js/html5shiv.js"></script>
		<!-- Custom JS -->
		<script src="js/custom.js"></script>
	</body>	
</html>