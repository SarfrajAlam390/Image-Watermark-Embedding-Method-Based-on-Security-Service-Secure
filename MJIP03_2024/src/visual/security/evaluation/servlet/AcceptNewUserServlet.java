package visual.security.evaluation.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import visual.security.evaluation.dao.SecurityDAO;

@WebServlet("/AcceptNewUserServlet")
public class AcceptNewUserServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int uid =Integer.parseInt(request.getParameter("uid"));
		if(uid!=0) 
		{
			try {
				int i = new SecurityDAO().adminAcceptNewUser(uid);
				if(i!=0) {
				RequestDispatcher rd= request.getRequestDispatcher("AcceptNewUser_admin.jsp?status=Accepted Successfully");
				rd.include(request, response);
				}
				else 
				{
					RequestDispatcher rd= request.getRequestDispatcher("AcceptNewUser_admin.jsp?status=Not Accepted");
					rd.include(request, response);
				}
			} catch (Exception e) {
				e.printStackTrace();
				RequestDispatcher rd= request.getRequestDispatcher("AcceptNewUser_admin.jsp?status=Some Internal Error");
				rd.include(request, response);
			}
			
		}
		else 
		{
			RequestDispatcher rd= request.getRequestDispatcher("AcceptNewUser_admin.jsp?status=Error with Uid");
			rd.include(request, response);
		}
	}
}