package edu.ae.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ae.manager.QuestionManager;

public class DeleteQuestion extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		int id = Integer.parseInt(request.getParameter("id"));
		System.out.println("id"+id);
		QuestionManager qsm = new QuestionManager();
		qsm.deleteQuestion(id);
		
		
		request.getRequestDispatcher("/xtmanage/question/question.jsp").forward(request, response);
	}

}
