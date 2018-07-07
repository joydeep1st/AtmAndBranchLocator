package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogoutServlet extends HttpServlet {
	
private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

		
	}
	
	/**
	 * gets the session and invalidates it
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		System.out.println("Logout Started");
		request.getRequestDispatcher("/JSP/login.jsp").include(request, response);
		request.getSession().invalidate();
        response.sendRedirect(request.getContextPath());
        System.out.println("Logging out...");
        //request.getRequestDispatcher("/JSP/login.jsp").forward(request, response);
	}

}
