package edu.ae.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import edu.ae.entity.User;
import edu.ae.manager.UserManager;

public class TesterLoginServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String userName = request.getParameter("username");	
		String userNum = request.getParameter("usernumber");
		int userAge = Integer.parseInt(request.getParameter("age"));
		String nativePlace = request.getParameter("nativeplace");
		String gender = request.getParameter("gender1");
		
		User user =new User();
        user.setAge(userAge);
        user.setGender(gender);
        user.setStuId(userNum);
        user.setName(userName);
        user.setNativeplace(nativePlace);
       	
        int amount=0;
		UserManager um=new UserManager();
		//System.out.println(user.getNativeplace());
		//System.out.println(user.getGender());
		amount=um.resgiterTestUser(user);
		
		if(amount>0)
		   request.getRequestDispatcher("/index.jsp").forward(request, response);
		else
		   request.getRequestDispatcher("/error.jsp").forward(request, response);
	}

}
