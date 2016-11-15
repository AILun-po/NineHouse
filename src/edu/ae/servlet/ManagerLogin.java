package edu.ae.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ae.entity.Managers;
import edu.ae.manager.UserManager;

@SuppressWarnings("serial")
public class ManagerLogin extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//从jsp页面获取username password
		String username = request.getParameter("username");
		String password = request.getParameter("passwd");
		
		//管理员类
		Managers mg = new Managers();
		mg.setUsername(username);
		mg.setPassword(password);
		
		//用户管理类
		UserManager um = new UserManager();
		//判断管理员密码是否正确
		boolean flag = um.isManager(mg);
		if(flag){
			//登陆成功,跳转页面
//			System.out.println("登陆成功");
			request.getRequestDispatcher("/index.jsp?username="+username+"").forward(request, response);
		}else{
			//登陆失败
			System.out.println("登陆失败");
			request.getRequestDispatcher("/fail.jsp").forward(request, response);
		}
	}

}
