package edu.ae.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ae.entity.User;
import edu.ae.manager.UserManager;

public class ModifyUserInfo extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int segments = Integer.parseInt(request.getParameter("segment"));
		int nowpages = Integer.parseInt(request.getParameter("nowpage"));
		int eachsize = Integer.parseInt(request.getParameter("eachsize"));
		
		int id = Integer.parseInt(request.getParameter("value1"));
//		
//		String stuId = request.getParameter("value2");
//		
//		String name = request.getParameter("value3");
//		name = new String (name.getBytes("ISO-8859-1"),"utf-8");
//		
//		String gender = request.getParameter("value4");
//		gender = new String (gender.getBytes("ISO-8859-1"),"utf-8");
//		if(gender.equals("男")){
//			gender = "0";
//		}else{
//			gender = "1";
//		}
//		
//		int age = Integer.parseInt(request.getParameter("value5"));
//		
//		String nativeplace = request.getParameter("value6");
//		nativeplace = new String (nativeplace.getBytes("ISO-8859-1"),"utf-8");
		
//		String sysResult = request.getParameter("value7");
//		sysResult = new String (sysResult.getBytes("ISO-8859-1"),"utf-8");
		String result[] = {"一","二","三","四","五","六","七","八","九"};
//		for(int i=1;i<=9;i++){
//			if(sysResult.equals(result[i-1])){
//				sysResult = ""+i;
//				break;
//			}
//		}
		
		String relResult = request.getParameter("value2");
		relResult = new String (relResult.getBytes("ISO-8859-1"),"utf-8");
		int i;
		for(i=1;i<=9;i++){
			if(relResult.equals(result[i-1])){
				relResult = Integer.toString(i);
				break;
			}
		}
		if(i>9){
			relResult = "";
		}
		
		User user = new User();
		user.setId(id);
//		user.setStuId(stuId);
//		user.setName(name);
//		user.setGender(gender);
//		user.setAge(age);
//		user.setNativeplace(nativeplace);
//		user.setSysResult(sysResult);
		user.setRelResult(relResult);
//		System.out.println(id+stuId+name+gender+age+user.getNativeplace()+sysResult+relResult);
		UserManager um = new UserManager();
		um.updateUser(user);
//		System.out.println("success");
		
		request.getRequestDispatcher("/xtmanage/usermanage/userlist.jsp?segment="+segments+"&nowpage="+nowpages+"&eachsize="+eachsize+"").forward(request, response);
	}

}
