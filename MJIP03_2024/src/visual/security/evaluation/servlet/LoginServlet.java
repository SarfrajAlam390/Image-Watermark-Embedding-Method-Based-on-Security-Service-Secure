package visual.security.evaluation.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import visual.security.evaluation.bean.Bean;
import visual.security.evaluation.dao.SecurityDAO;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Bean b = new Bean();
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		b.setEmail(email);
		b.setPassword(password);
		int uid = 0;
		String uname = null;
		String mail = null;
		if(email.equalsIgnoreCase("admin@gmail.com")&& password.equalsIgnoreCase("admin")) 
		{
			RequestDispatcher rd = request.getRequestDispatcher("adminHome.jsp?status=Welcome Admin");
			rd.include(request, response);
		}
		else 
		{
			try {
				ArrayList<Bean> al =  new SecurityDAO().login(b);
				for(Bean login:al) 
				{
					uid = login.getUid();
					mail = login.getEmail();
					uname = login.getUname();
				}
				if(!al.isEmpty()) 
				{
					HttpSession ses = request.getSession();
					ses.setAttribute("uid", uid);
					ses.setAttribute("email", mail);
					ses.setAttribute("uname", uname);
					RequestDispatcher rd = request.getRequestDispatcher("userHome.jsp?status=Welcome "+uname);
					rd.include(request, response);
				}
				else 
				{
					RequestDispatcher rd = request.getRequestDispatcher("login.jsp?status=Invalid Email and Password");
					rd.include(request, response);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				RequestDispatcher rd = request.getRequestDispatcher("login.jsp?status=Some Internal Error");
				rd.include(request, response);
			}
		}
	}
}