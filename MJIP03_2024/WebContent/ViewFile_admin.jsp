<!DOCTYPE html>
<%@page import="visual.security.evaluation.dao.ViewDAO"%>
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
		
		<!-- Favicon -->
		<link rel="shortcut icon" href="#">
	</head>
	
	<body>
	<%
	int uid =Integer.parseInt(request.getParameter("uid"));
	int fid =Integer.parseInt(request.getParameter("fid"));
	ArrayList<Bean> al = new ViewDAO().userViewImages(fid,uid);%>
		<div class="wrapper">
			<!-- header -->
			<jsp:include page="adminMenu.jsp"></jsp:include>
			
			<!-- main content -->
			<div class="main-content">
				<div class="container">
					<!-- login area -->
					<div class="login-area">
						<!-- heading -->
						<h3>Texture Maps</h3>
						<%String status = request.getParameter("status");
						if(status!=null)
						{%>
							<h3 style="color: red;"><%out.print(status); %></h3>
						<%}
						%>
							<div class="form-group">
                    <%if(al.isEmpty()){ %>
                        	<h2 style="color: white;"><b>No Files to Display</b></h2>
                        <%}else{ %>
                        <table>
                        <tr>
                        <td padding: 20px;>
                        <% int i = 0;
                        for(Bean b:al){
                        	i++;
                        	session.setAttribute(i + "img", b.getPlainImage());
                        %>
                            <img src="image.jsp?val=<%=i %>" height="100" width="100" class="tm-service-img">
                          <%} %>
                           <h4>Plain Image</h4>
                          </td>
                          <td padding: 20px;>
                        <% int j = 0;
                        for(Bean b:al){
                        	j++;
                        	session.setAttribute(j + "img1", b.getContrastImage());
                        %>
                            <img src="image1.jsp?val=<%=j %>" height="100" width="100" class="tm-service-img">
                          <%}%>
                          <h4>congruency pahse</h4>
                          </td>
                          <td padding: 20px;>
                        <% int k = 0;
                        for(Bean b:al){
                        	k++;
                        	session.setAttribute(k + "img2", b.getAmplitudeImage());
                        %>
                            <img src="image2.jsp?val=<%=k %>" height="100" width="100" class="tm-service-img">
                          <%}%>
                          <h4>amplitude Image</h4>
                          </td>
                          </tr>
                       	 <tr>
                        <td padding: 20px;>
                        <% int l = 0;
                        for(Bean b:al){
                        	l++;
                        	session.setAttribute(l + "img", b.getEncryptedImage());
                        %>
                            <img src="image.jsp?val=<%=l %>" height="100" width="100" class="tm-service-img">
                          <%} %>
                           <h4>Encrypted Image</h4>
                          </td>
                          <td padding: 20px;>
                        <% int m = 0;
                        for(Bean b:al){
                        	m++;
                        	session.setAttribute(m + "img3", b.getEncryptcontrastImage());
                        %>
                            <img src="image3.jsp?val=<%=m %>" height="100" width="100" class="tm-service-img">
                          <%}%>
                          <h4>Encrypted congruency</h4>
                          </td>
                          <td padding: 20px;>
                        <% int n = 0;
                        for(Bean b:al){
                        	n++;
                        	session.setAttribute(n + "img4", b.getEncryptamplitudeImage());
                        %>
                            <img src="image4.jsp?val=<%=n %>" height="100" width="100" class="tm-service-img">
                          <%}%>
                          <h4>Encrypted amplitude</h4>
                          </td>
                          </tr>
                          </table>
                        <%} %>
                          
                        </div>
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