package edu.ae.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ae.entity.AnswerRecord;
import edu.ae.entity.User;
import edu.ae.manager.UserManager;

@SuppressWarnings("serial")
public class ShowUserAnswer extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String stuId = request.getParameter("stuId");
		UserManager um = new UserManager();
		User user = um.queryByStuId(stuId);
		AnswerRecord ansrec = new AnswerRecord();
		ansrec = um.getAnswer(user.getId());
		
		request.getRequestDispatcher("showAnswer.jsp?user="+user+"&ansrec="+ansrec+"").forward(request, response);
	}

}
