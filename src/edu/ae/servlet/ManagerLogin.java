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
		
		//��jspҳ���ȡusername password
		String username = request.getParameter("username");
		String password = request.getParameter("passwd");
		
		//����Ա��
		Managers mg = new Managers();
		mg.setUsername(username);
		mg.setPassword(password);
		
		//�û�������
		UserManager um = new UserManager();
		//�жϹ���Ա�����Ƿ���ȷ
		boolean flag = um.isManager(mg);
		if(flag){
			//��½�ɹ�,��תҳ��
//			System.out.println("��½�ɹ�");
			request.getRequestDispatcher("/index.jsp?username="+username+"").forward(request, response);
		}else{
			//��½ʧ��
			System.out.println("��½ʧ��");
			request.getRequestDispatcher("/fail.jsp").forward(request, response);
		}
	}

}
