package edu.ae.servlet;

/**
 * 被测试者登陆验证暂时不需要
 */
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ae.entity.User;
import edu.ae.manager.UserManager;

@SuppressWarnings("serial")
public class LoginServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String userName = request.getParameter("userName");	
		String passwd = request.getParameter("passwd");
		
		User user =new User();
		user.setName(userName);
		user.setStuId(passwd);
		
		UserManager um=new UserManager();
//		System.out.println(user.getName());
//		System.out.println(user.getStuId());
		int flag = um.isValid(user);
			
		if(flag==1){
//			System.out.println("已存在该数据");
			request.getRequestDispatcher("/success.jsp").forward(request, response);
		}
		else{
			int isture= um.resgiterUser(user);
			if(isture==1){
				request.getRequestDispatcher("/success.jsp").forward(request, response);
				System.out.println("已注册该数据");
			}
			else{
				request.getRequestDispatcher("/fail.jsp").forward(request, response);
			}
		}
		
	}

}
