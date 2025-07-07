package visual.security.evaluation.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import visual.security.evaluation.bean.Bean;
import visual.security.evaluation.dao.SecurityDAO;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Bean b = new Bean();
		b.setUname(request.getParameter("name"));
		b.setPassword(request.getParameter("password"));
		b.setEmail(request.getParameter("email"));
		b.setDob(request.getParameter("dob"));
		b.setGender(request.getParameter("gender"));
		b.setAddress(request.getParameter("address"));
		
		try {
			int i = new SecurityDAO().reg(b);
			if(i!=0) 
			{
				RequestDispatcher rd = request.getRequestDispatcher("registration.jsp?status=Registred Successfull");
				rd.include(request, response);
			}
			else 
			{
				RequestDispatcher rd = request.getRequestDispatcher("registration.jsp?status=Not Registred");
				rd.include(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher rd = request.getRequestDispatcher("registration.jsp?status=Some Internal Error");
			rd.include(request, response);
		}
	}
}