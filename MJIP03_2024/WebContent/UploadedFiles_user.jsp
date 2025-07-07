<!DOCTYPE html>
<%@page import="java.util.ArrayList"%>
<%@page import="visual.security.evaluation.dao.ViewDAO"%>
<%@page import="visual.security.evaluation.bean.Bean"%>
<html>
	<head>
		<meta charset="utf-8">
		<title>Files</title>
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
	<%
	int uid =(Integer)session.getAttribute("uid");
	ArrayList<Bean> al = new ViewDAO().userViewUploadedFiles(uid); %>
		<div class="wrapper">
			<!-- header -->
			<jsp:include page="userMenu.jsp"></jsp:include>
			
			<!-- main content -->
			<div class="main-content">
				<div class="container">
					<!-- login area -->
					<div class="login-area">
						<!-- heading -->
						<h3>Image Importance</h3>
						<%String status = request.getParameter("status");
						if(status!=null)
						{%>
							<h3 style="color: red;"><%out.print(status); %></h3>
						<%}
						%>
						<!-- paragraph -->
						<%if(!al.isEmpty()){%>
							<div class="form-group">
				<table align="center" border="1" bordercolor="red"  style="">
              <tr align="center">
              <th style="color: blue; font-family: Times New Roman; padding: 20px;">Uid</th>
              <th style="color: blue; font-family: Times New Roman; padding: 20px;"> Security Key</th>
              <th style="color: blue; font-family: Times New Roman; padding: 20px;">View Image</th>
              
              </tr>
              <%for(Bean b: al){ %>
              <tr>
              <td style="color: red; font-family: Times New Roman; padding: 20px;"><%=b.getUid() %></td>
              <td style="color: red; font-family: Times New Roman; padding: 20px;"><%=b.getPercentage()%></td>
              <td style="color: red; font-family: Times New Roman; padding: 20px;"><a href="./ViewFile_user.jsp?fid=<%=b.getUid()%>">View</a></td>
              </tr>
              <%} %>
              </table>
							</div>
							<%}else{ %>
							<div class="form-group">
								<h3>No Files to display</h3>
							</div>
							<%} %>
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