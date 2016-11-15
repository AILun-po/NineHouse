package edu.ae.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ae.entity.Managers;
import edu.ae.manager.UserManager;

public class ModifyManagerInfo extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
//		System.out.println("get manager info");
		String username = request.getParameter("username");
		String passwd = request.getParameter("passwd");
		String name = request.getParameter("name");
		String workId = request.getParameter("workId");
		String school = request.getParameter("school");
		String college = request.getParameter("college");
		String major = request.getParameter("major");
		String gread = request.getParameter("gread");
		String classes = request.getParameter("classes");
		String s_age = request.getParameter("age");
		int age = 0;
		if(!s_age.equals("")){
			age = Integer.parseInt(s_age);
		}
		String gender = request.getParameter("gender");
		String nativeplace = request.getParameter("nativeplace");
		
		username = new String (username.getBytes("ISO-8859-1"),"utf-8");
		passwd = new String (passwd.getBytes("ISO-8859-1"),"utf-8");
		name = new String (name.getBytes("ISO-8859-1"),"utf-8");
		workId = new String (workId.getBytes("ISO-8859-1"),"utf-8");
		school = new String (school.getBytes("ISO-8859-1"),"utf-8");
		college = new String (college.getBytes("ISO-8859-1"),"utf-8");
		major = new String (major.getBytes("ISO-8859-1"),"utf-8");
		gread = new String (gread.getBytes("ISO-8859-1"),"utf-8");
		classes = new String (classes.getBytes("ISO-8859-1"),"utf-8");
		gender = new String (gender.getBytes("ISO-8859-1"),"utf-8");
		nativeplace = new String (nativeplace.getBytes("ISO-8859-1"),"utf-8");
		
//		System.out.println("get manager info success");
//		System.out.println(username+name+workId+school+college+major+gread+classes+gender+age+nativeplace);
		
		Managers ma = new Managers();
		ma.setUsername(username);
		ma.setPassword(passwd);
		ma.setName(name);
		ma.setWorkId(workId);
		ma.setSchool(school);
		ma.setCollege(college);
		ma.setMajor(major);
		ma.setGread(gread);
		ma.setClasses(classes);
		ma.setAge(age);
		ma.setGender(gender);
		ma.setNativeplace(nativeplace);
		
		UserManager um = new UserManager();
		um.updateManager(ma);
		
		request.getRequestDispatcher("/mypage.jsp?username="+username+"").forward(request, response);
	}

}
