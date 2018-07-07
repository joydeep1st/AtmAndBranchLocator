package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import api.GetApiFromBarclays;
import bean.LoginBean;
import dao.LoginDao;


public class LoginServlet extends HttpServlet{
	
private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		//GetApiFromBarclays api = new GetApiFromBarclays();
		//api.getPojoForBarclays();
		String username = request.getParameter("USERNAME");
		String password = request.getParameter("PASSWORD");
		HttpSession session;
		LoginBean bean = new LoginBean();
		bean.setUser_name(username);
		bean.setPassword(password);
		System.out.println("UserName : " + username + " Password : " + password);
		/*
		 * sends the credentials to DAO class to check if they are correct
		 * if correct, redirect the user to welcome page/home page
		 * if incorrect, display error message on the same login page
		 */
		
		boolean validationResult = LoginDao.validate(bean);
		
		if(validationResult){
			session = request.getSession();
			session.setAttribute("USERNAME", username);
			request.getRequestDispatcher("/JSP/welcome.jsp").forward(request, response);
		}else{
			request.setAttribute("errorMessage", "Invalid User Name or Password");
			request.getRequestDispatcher("/JSP/login.jsp").forward(request, response);
		}		
	}

}
