package edu.ae.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ae.manager.UserManager;

public class DeleteAllUser extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int type = Integer.parseInt(request.getParameter("type"));
		UserManager um = new UserManager();
		if(type==1){
			um.deleteIllegal();
		}else{
			um.deleteAllUser();
		}
		
		request.getRequestDispatcher("/xtmanage/usermanage/userlist.jsp?segment="+1+"&nowpage="+1+"&eachsize="+10+"").forward(request, response);
	}

}
