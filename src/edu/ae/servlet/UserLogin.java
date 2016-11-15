package edu.ae.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class UserLogin extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	} 

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String stuId = request.getParameter("stuId");
		stuId = new String (stuId.getBytes("ISO-8859-1"),"utf-8");
		String name = request.getParameter("name");
		name = new String (name.getBytes("ISO-8859-1"),"utf-8");
		String gender = request.getParameter("gender");
		gender = new String (gender.getBytes("ISO-8859-1"),"utf-8");
		String sage = request.getParameter("age");
		sage = new String (sage.getBytes("ISO-8859-1"),"utf-8");
		int age = 0;
		if(sage!=null){
			age = Integer.parseInt(sage);
		}
		String nativeplace = request.getParameter("nativeplace");
		nativeplace = new String (nativeplace.getBytes("ISO-8859-1"),"utf-8");
//		System.out.println(stuId+name+gender+age+nativeplace);
		
		request.getRequestDispatcher("userlogin.jsp").forward(request, response);
	}

}
