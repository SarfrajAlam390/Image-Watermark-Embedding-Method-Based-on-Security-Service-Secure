<!DOCTYPE html>
<%@page import="visual.security.evaluation.bean.Bean"%>
<%@page import="java.util.ArrayList"%>
<html>
	<head>
		<meta charset="utf-8">
		<title>Image</title>
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
		
		<link rel="shortcut icon" href="#">
		<style type="text/css">
    #pro
{
	display: none;
	width: 100%;
	background-color: #ddd;
}

#progress
{
	width: 0.5%;
	height: 30px;
	background-color: #4CAF50;
	color: red;
}
</style>
	</head>
	
	<body>
	<%ArrayList<Bean> al = (ArrayList)session.getAttribute("image");%>
		<div class="wrapper">
			<!-- header -->
			<jsp:include page="userMenu.jsp"></jsp:include>
			
			<!-- main content -->
			<div class="main-content">
				<div class="container">
					<!-- login area -->
					<div class="login-area">
						<!-- heading -->
						<h3>Plain and Encrypted Images</h3>
						<div id="pro" class="form-group">
							<div id="progress"></div>
						</div>
						<%String status = request.getParameter("status");
						if(status!=null)
						{%>
							<h3 style="color: red;"><%out.print(status); %></h3>
						<%}
						%>
						<!-- paragraph -->
						<form action="./ChangeContrastServlet_user" method="post" role="form" id="login-form">
							<div class="form-group">
                    <%if(al.isEmpty()){ %>
                        	<h2 style="color: white;"><b>No Files to Display</b></h2>
                        <%}else{ %>
                        <div style="margin-left: -25%;">
                        <table>
                        <tr>
                        <td style="padding: 40px;">
                        <h3>Plain Image</h3>
                        <% int i = 0;
                        for(Bean b:al){
                        	i++;
                        	session.setAttribute(i + "img", b.getPlainImage());
                        %>
                            <img src="image.jsp?val=<%=i %>" height="200" width="200" class="tm-service-img">
                          <%} %>
                          </td>
                          <td style="padding: 40px;">
                          <h3>Encrypted Image</h3>
                        <% int j = 0;
                        for(Bean b:al){
                        	j++;
                        	session.setAttribute(j + "img", b.getPlainImage());
                        %>
                            <img src="image.jsp?val=<%=j %>" height="200" width="200" class="tm-service-img">
                            <%} %>
                            </td>
                          </tr>
                          </table>
                          </div>
                          <%} %>
                        </div>
							<button type="submit" class="btn btn-warning" onclick="onc();">Input</button>
						</form>
						<script type="text/javascript">
	function onc()
	{
		 document.getElementById("pro").style.display = "block";
		var val =  document.getElementById("progress");
		var width = 1;
		var id = setInterval(frame,60);
		
		function frame()
		{
			
			if(width>=0 && width<=19)
			{
				val.innerHTML="wait";
			}
			if(width>=20 && width<=60)
				{
					val.innerHTML="Apply Multi-resolution";
				}
				if(width>=61 && width<=100)
				{
					val.innerHTML="Applied";
				}
			if(width>=100)
			{
				clearInterval(id);
			}
			else
			{
				width++;
				val.style.width = width + "%";
			}
		}
		
	}
	</script>
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