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

@WebServlet("/ContactServlet")
public class ContactServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Bean b = new Bean();
		b.setUname(request.getParameter("name"));
		b.setEmail(request.getParameter("email"));
		b.setAddress(request.getParameter("message"));
		
		try {
			int i = new SecurityDAO().feedBack(b);
			if(i!=0) 
			{
				RequestDispatcher rd= request.getRequestDispatcher("contact.jsp?status=Successfully Submited");
				rd.include(request, response);
			}
			else 
			{
				RequestDispatcher rd= request.getRequestDispatcher("contact.jsp?status=Not Submited");
				rd.include(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher rd= request.getRequestDispatcher("contact.jsp?status=Some Internal Error");
			rd.include(request, response);
		}
	}
}