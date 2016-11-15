package edu.ae.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ae.manager.UserManager;

public class ModifyUserAnswer extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String answer = request.getParameter("answer");
		System.out.println(id+" "+answer);
		UserManager um = new UserManager();
		um.updateAnswerById(id, answer);
		
		request.getRequestDispatcher("/xtmanage/usermanage/user_info_rec.jsp?id="+id+"").forward(request, response);
	}

}
