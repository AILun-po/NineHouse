package edu.ae.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import edu.ae.entity.AnswerRecord;
import edu.ae.entity.User;
import edu.ae.manager.UserManager;

public class ResultServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		    doPost(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
            String id=request.getParameter("userid");
            //System.out.println("id="+id);
            int userid=Integer.parseInt(id);
            
            String result=request.getParameter("result");
            //System.out.println(result);
            
            String s_sort = request.getParameter("s_sort");
            //System.out.println("s_sort"+s_sort);
            String[] s_sortlist = s_sort.split(",");
            String record = "";
            int []a = new int[180];
            int k;
            for(int i=0;i<180;i++){
            	k = Integer.parseInt(s_sortlist[i])-1;
            	a[k] = result.charAt(i)-48;
            }
            for(int i=0;i<180;i++){
            	record += a[i];
            }
            //System.out.println("record:"+record+"\nlength: "+record.length());
            
            //每一题的答题记录1-180
            UserManager um=new UserManager();
            AnswerRecord ans=new AnswerRecord();
            ans.setId(userid);
            ans.setAnswer(record);
            um.setAnswerRec(ans);
            
            //记录答题结束时间
            Date date=new Date();
            DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String end_time=format.format(date); 
            um.SetUserEndDateTime(userid,end_time);
            
            
            //记录答题顺序和对应的选择
            AnswerRecord answer=new AnswerRecord();
            answer.setId(userid);
            answer.setAnswer(result);
            answer.setQusetion_sort(um.GetQuestion(answer));
            //System.out.println(answer.getQusetion_sort());
            
    		request.setAttribute("result", answer);
    		request.getRequestDispatcher("/result.jsp").forward(request, response);
    	
	}
}
