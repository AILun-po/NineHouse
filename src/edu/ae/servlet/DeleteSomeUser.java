package edu.ae.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ae.manager.UserManager;

public class DeleteSomeUser extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		int segments = Integer.parseInt(request.getParameter("segment"));
		int nowpages = Integer.parseInt(request.getParameter("nowpage"));
		int eachsize = Integer.parseInt(request.getParameter("eachsize"));
		
		String num = (request.getParameter("deletenum"));
		if(num!=null)
		{
		String id_num=num.substring(0,num.length()-1);
		UserManager um = new UserManager();
		um.deleteSomeUser(id_num);
		}
		else
		{
			//´íÎó
		}
		request.getRequestDispatcher("/xtmanage/usermanage/userlist.jsp?segment="+segments+"&nowpage="+nowpages+"&eachsize="+eachsize+"").forward(request, response);
	}
}

