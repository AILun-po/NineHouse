package edu.ae.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ae.manager.QuestionManager;

public class EditResultContent extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String content = request.getParameter("content");
		content = new String (content.getBytes("ISO-8859-1"),"utf-8");
		
		String[] content_g = new String[12];
		for(int i=1;i<=11;i++){
			content_g[i] = request.getParameter("con"+i);
			content_g[i] = new String (content_g[i].getBytes("ISO-8859-1"),"utf-8");
		}
		content_g[0] = content;
		
//		for(int i=0;i<11;i++){
//			System.out.println(content_g[i]+"\n");
//		}
		
		int index = 0;
		for(int i=0;i<=11;i++){
			while((index=content_g[i].indexOf("\n"))!=-1){
        		content_g[i] = content_g[i].substring(0,index)+"_"+content_g[i].substring(index+1);
//        		System.out.println(i+":  "+content_g[i]);
			}
		}
		QuestionManager qsm = new QuestionManager();
		qsm.updateResultContent(content_g);
		request.getRequestDispatcher("/modifyresultjsp.jsp").forward(request, response);
	}

}
