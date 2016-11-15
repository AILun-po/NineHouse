package edu.ae.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ae.entity.Questions;
import edu.ae.manager.QuestionManager;

public class EditQuestion extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		
		String content = request.getParameter("content");
		content = new String (content.getBytes("ISO-8859-1"),"utf-8");
		
		String character = request.getParameter("character");
		
		Questions qs = new Questions();
		qs.setId(id);
		qs.setContent(content);
		qs.setRelatedCharacter(character);
		
		System.out.println(id+content+character);
		
		QuestionManager qsm = new QuestionManager();
		qsm.updateQuestion(qs);
		
		request.getRequestDispatcher("/xtmanage/question/editquestion.jsp?id="+id+"").forward(request, response);
		
	}

}
