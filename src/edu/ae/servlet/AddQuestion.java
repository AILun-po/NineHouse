package edu.ae.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ae.manager.QuestionManager;

public class AddQuestion extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String content = request.getParameter("content");
		content = new String (content.getBytes("ISO-8859-1"),"utf-8");
		
		String character = request.getParameter("character");
		QuestionManager qsm = new QuestionManager();
		qsm.addQuestion(content, character);
		
		request.getRequestDispatcher("xtmanage/question/question.jsp").forward(request, response);
	}

}
