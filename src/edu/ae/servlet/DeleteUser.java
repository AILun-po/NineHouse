package edu.ae.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ae.manager.UserManager;

public class DeleteUser extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int segment = Integer.parseInt((request.getParameter("segment")));
		int nowpage = Integer.parseInt(request.getParameter("nowpage"));
		int eachsize = Integer.parseInt(request.getParameter("eachsize"));
		int id = Integer.parseInt(request.getParameter("id"));
		UserManager um = new UserManager();
		um.deleteUserById(id);
		
		request.getRequestDispatcher("/xtmanage/usermanage/userlist.jsp?segment="+segment+"&nowpage="+nowpage+"&eachsize="+eachsize+"").forward(request, response);
	}

}
