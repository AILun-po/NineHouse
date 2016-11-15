package edu.ae.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ae.entity.User;
import edu.ae.manager.UserManager;

public class TesterServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List questionList=null;   
		
		String userName = request.getParameter("username");	
		userName = new String (userName.getBytes("ISO-8859-1"),"utf-8");
		
		String userNum = request.getParameter("usernumber");
		
		int userAge = Integer.parseInt(request.getParameter("age"));
		//System.out.println("age"+userAge);
		
		String nativePlace = request.getParameter("native");
		nativePlace = new String (nativePlace.getBytes("ISO-8859-1"),"utf-8");
		
		Date date=new Date();
        DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String start_time=format.format(date);
        
		String gender = request.getParameter("gender1");
		
		User user =new User();
        user.setAge(userAge);
        user.setGender(gender);
        user.setStuId(userNum);
        user.setName(userName);
        user.setNativeplace(nativePlace);
        //System.out.println("TesterServlet"+start_time);
        user.setStart_datetime(start_time);
        
       	int amount=0;
		UserManager um=new UserManager();
		amount = um.resgiterTestUser(user);
		int userid = um.GetUserId(user);
        //System.out.println("TesterServlet id"+userid);
		//System.out.println(user.getNativeplace());
      
		//随机生成答题的顺序
        int[] sort=new int[180];
        String s_sort = "";
        int i=0;
        for(i=0;i<180;i++){
        	sort[i]=i+1;
        }
        ArrayList list = new ArrayList();
        for(i = 0;i < sort.length;i++)
        {  
//        	 System.out.print(sort[i]+", ");  
             list.add(sort[i]); 
        }
        Collections.shuffle(list);
        Iterator ite = list.iterator();
        i=0;
        while(ite.hasNext()){  
        	sort[i]=Integer.parseInt(ite.next().toString());
        	s_sort += Integer.toString(sort[i]) + ",";
        	i++;       
        }  
        //根据sort读取所有题目
        questionList=um.queryAllQuestion(sort,userid);
        
//		System.out.println("TestServlet s_sort:"+s_sort);
		if(amount>0){
		   request.setAttribute("questionList",questionList);
		   request.setAttribute("userid", userid);
		   request.setAttribute("s_sort", s_sort);
		   request.getRequestDispatcher("/test.jsp").forward(request, response);
		}
		else{
		   request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
	}
}
