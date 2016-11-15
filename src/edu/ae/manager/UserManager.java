package edu.ae.manager;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import edu.ae.entity.*;


public class UserManager {

	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	
	public boolean isManager(Managers mg){
		
		boolean flag = false;  //flag=1 ���ڣ�flag=0������
		
		try {
			connect();
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery("select * from manager where username = '"+mg.getUsername()+"' ");
			rs.next();
			
			if(rs.getString("password").equals(mg.getPassword())){
				flag = true;
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}finally{
			release();
//			this.deleteIllegal();
		}
		
		return flag;
	}
	
	public Managers queryManager(String username){
		Managers ma = new Managers();
		try {
			connect();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from manager where username='"+username+"'");
//			System.out.println("��ѯ�ɹ�");
			rs.next();
			ma.setUsername(username);
			ma.setPassword(rs.getString("password"));
			ma.setWorkId(rs.getString("workId"));
			ma.setName(rs.getString("name"));
			ma.setSchool(rs.getString("school"));
			ma.setCollege(rs.getString("college"));
			ma.setMajor(rs.getString("major"));
			ma.setGread(rs.getString("gread"));
			ma.setClasses(rs.getString("classes"));
			ma.setGender(rs.getString("gender"));
			ma.setAge(rs.getInt("age"));
			ma.setNativeplace(rs.getString("nativeplace"));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ma;
	}
	
	public void updateManager(Managers ma){
		try {
			connect();
			stmt = conn.createStatement();
			stmt.executeUpdate("update manager set username='"+ma.getUsername()+"',password='"+ma.getPassword()+"',name='"+ma.getName()+"',workId='"+ma.getWorkId()+"',age='"+ma.getAge()+"',nativeplace='"+ma.getNativeplace()+"',gender='"+ma.getGender()+"',school='"+ma.getSchool()+"',college='"+ma.getCollege()+"',gread='"+ma.getGread()+"',classes='"+ma.getClasses()+"',major='"+ma.getMajor()+"' where id=0");
//			System.out.println("update manager success");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			release();
		}
	}
	
	public void updateManagerPasswd(Managers ma){
		try {
			connect();
			stmt = conn.createStatement();
			stmt.executeUpdate("update manager set password='"+ma.getPassword()+"'"); 
//			System.out.println("update manager success");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			release();
		}
	}
	
	public int isValid(User user){
		int flag = 0;  //flag=1 ���ڣ�flag=0������
	    try {
	    	connect();
			stmt = conn.createStatement();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		try {
			rs= stmt.executeQuery("SELECT * from user where username='"+user.getName()+"'and password='"+user.getStuId()+"'");
			while(rs.next()){
				flag=1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		release();
		
		return flag;
	}
	
	public int resgiterUser(User user){
		int amount=0;
		try{
			connect();
			stmt = conn.createStatement();
		}catch (SQLException e){
			e.printStackTrace();
		}
		try{
//			System.out.println(user.getName());
//			System.out.println(user.getStuId());
			amount = stmt.executeUpdate("insert into manager  values('"+user.getStuId()+"','"+user.getName()+"',"+0.0+")");
		}catch(SQLException e){

			e.printStackTrace();
		}finally{
			release();
		}
					
		return amount;
	}

	/**
	 * ��ѯ�����û�
	 */

	public List queryAllUser(){
		List userlist = new ArrayList();
		User user = new User();
		try {
			connect();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from user order by id asc");
//			System.out.println("��ѯ�ɹ�");
			while(rs.next()){
				user = new User();
				user.setId(rs.getInt("id"));
				user.setStuId(rs.getString("stuId"));
				user.setName(rs.getString("name"));
				user.setGender(rs.getString("gender"));
				user.setNativeplace(rs.getString("nativeplace"));
				user.setSysResult(rs.getString("sysResult"));
				user.setRelResult(rs.getString("relResult"));
				user.setAge(rs.getInt("age"));
				user.setStart_datetime(rs.getString("start_datetime"));
				user.setEnd_datetime(rs.getString("end_datetime"));
				userlist.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return userlist;
	}
	
	/*
	 * ��ѯ��ݿ��е�index+1����ʼ��size����¼
	 */
	public List querySomeUser(int index,int size){
		System.out.println("index:"+index+" size:"+size);
		List userlist = new ArrayList();
		User user = new User();
		if(index>=0&&size>=0){
			try {
				connect();
				stmt = conn.createStatement();
				rs = stmt.executeQuery("select * from user order by id asc");
				System.out.println("��ѯ�ɹ�");
				int i = 0;
				while(rs.next()){
					if(i>=index+size)
						break;
					if(i>=index){
						user = new User();
						user.setId(rs.getInt("id"));
						user.setStuId(rs.getString("stuId"));
						user.setName(rs.getString("name"));
						user.setGender(rs.getString("gender"));
						user.setNativeplace(rs.getString("nativeplace"));
						user.setSysResult(rs.getString("sysResult"));
						user.setRelResult(rs.getString("relResult"));
						user.setAge(rs.getInt("age"));
						user.setStart_datetime(rs.getString("start_datetime"));
						user.setEnd_datetime(rs.getString("end_datetime"));
						userlist.add(user);
					}
					i++;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return userlist;
	}
	
	public List queryAllUserByIds(String idString){
		String[] ids = idString.split(",");
		//length��ʾҪ������id�ĸ���
		int length = ids.length;
		System.out.println("ids[0]:"+ids[0]);
		List userlist = new ArrayList();
		User user = new User();
		try {
			connect();
			stmt = conn.createStatement();
			int id=0;
			for(int i=1;i<length;i++){
				if(ids[i]!=null){
					id = Integer.parseInt(ids[i]);
					rs = stmt.executeQuery("select * from user where id='"+id+"'");
					if(rs.next()){
						user = new User();
						user.setId(rs.getInt("id"));
						user.setStuId(rs.getString("stuId"));
						user.setName(rs.getString("name"));
						user.setGender(rs.getString("gender"));
						user.setNativeplace(rs.getString("nativeplace"));
						user.setSysResult(rs.getString("sysResult"));
						user.setRelResult(rs.getString("relResult"));
						user.setAge(rs.getInt("age"));
						user.setStart_datetime(rs.getString("start_datetime"));
						user.setEnd_datetime(rs.getString("end_datetime"));
						userlist.add(user);
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return userlist;
	}
	
	public User queryByStuId(String stuId){
		User user = new User();
		
		try {
			connect();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from user where stuId = '"+stuId+"'");
			rs.next();
			user.setId(rs.getInt("id"));
			user.setStuId(rs.getString("stuId"));
			user.setName(rs.getString("name"));
			user.setGender(rs.getString("gender"));
			user.setAge(rs.getInt("age"));
			user.setNativeplace(rs.getString("nativeplace"));
			user.setSysResult(rs.getString("sysResult"));
			user.setRelResult(rs.getString("relResult"));
			user.setStart_datetime(rs.getString("start_datetime"));
			user.setEnd_datetime(rs.getString("end_datetime"));
				
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			release();
		}
		
		return user;
	}
	
	public User queryById(int id){
		User user = new User();
		
		try {
			connect();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from user where id = '"+id+"'");
			rs.next();
			user.setId(rs.getInt("id"));
			user.setStuId(rs.getString("stuId"));
			user.setName(rs.getString("name"));
			user.setGender(rs.getString("gender"));
			user.setAge(rs.getInt("age"));
			user.setNativeplace(rs.getString("nativeplace"));
			user.setSysResult(rs.getString("sysResult"));
			user.setRelResult(rs.getString("relResult"));
			user.setStart_datetime(rs.getString("start_datetime"));
			user.setEnd_datetime(rs.getString("end_datetime"));
				
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			release();
		}
		
		return user;
	}
	
	public void updateUser(User user){
//		System.out.println(user.getNativeplace());
		int amount=0;
		try {
			connect();
			stmt = conn.createStatement();
//			amount = stmt.executeUpdate("update user set stuId='"+user.getStuId()+"',name='"+user.getName()+"',gender='"+user.getGender()+"',nativeplace='"+user.getNativeplace()+"',sysResult='"+user.getSysResult()+"',relResult='"+user.getRelResult()+"',age='"+user.getAge()+"' where id='"+user.getId()+"'");
			amount = stmt.executeUpdate("update user set relResult='"+user.getRelResult()+"' where id='"+user.getId()+"'");
			//System.out.println("update success");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			release();
		}
	}
	
	public void deleteUserById(int id){
		try {
			connect();
			stmt = conn.createStatement();
			stmt.executeUpdate("delete from user where id='"+id+"'");
			stmt.executeUpdate("delete from answer_record where id='"+id+"'");
			stmt.executeUpdate("delete from result where id='"+id+"'");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			release();
		}
//		updateAllUserId();
	}
	
	public void deleteSomeUser(String idNum) {
		// TODO Auto-generated method stub

		 String[] sourceStrArray = idNum.split(",");
		 int[] num=  new int[sourceStrArray.length];
	        for (int i = 0; i < sourceStrArray.length; i++) {
	        	num[i]=Integer.parseInt(sourceStrArray[i]);
	        }
		
			connect();
			try {
				stmt = conn.createStatement();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			for(int i=0;i< sourceStrArray.length;i++)
			{
			//stmt = conn.createStatement();
			try {
			     stmt.executeUpdate("delete from user where id='"+num[i]+"'");
			     stmt.executeUpdate("delete from answer_record where id='"+num[i]+"'");
			     stmt.executeUpdate("delete from result where id='"+num[i]+"'");
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			release();
		
	}
	
	
	public void deleteAllUser(){
		try {
			connect();
			stmt = conn.createStatement();
			stmt.executeUpdate("delete from user where 1=1");
			stmt.executeUpdate("delete from answer_record where 1=1");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			release();
		}
	}
	
	public void deleteAllResult(){
		try {
			connect();
			stmt = conn.createStatement();
			stmt.executeUpdate("delete from result where 1=1");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			release();
		}
	}
	
	/**
	 * ���������û���id,��˳�����
	 */
	public void updateAllUserId(){
		List userlist = new ArrayList();
		List answerlist = new ArrayList();
		userlist = this.queryAllUser();
		answerlist = this.getAllAnswerByUser(userlist);
		
		int size = userlist.size();
		int size1 = answerlist.size();
		
		User user = new User();
		AnswerRecord ans = new AnswerRecord();
		try {
			this.deleteAllUser();
			connect();
			Statement stmt1 = null;
			Statement stmt2 = null;
			stmt1 = conn.createStatement();
			stmt2 = conn.createStatement();
			int i = 1;
			for(i=1;i<=size;i++){
				user = new User();
				user = (User)userlist.get(i-1);
				stmt1.executeUpdate("insert into user(id,stuId,name,gender,age,nativeplace,sysResult,relResult,start_datetime,end_datetime) values('"+user.getId()+"','"+user.getStuId()+"','"+user.getName()+"','"+user.getGender()+"',"+user.getAge()+",'"+user.getNativeplace()+"','"+user.getSysResult()+"','"+user.getRelResult()+"','"+user.getStart_datetime()+"','"+user.getEnd_datetime()+"')");
			}
			for(i=1;i<=size1;i++){
				ans = new AnswerRecord();
				ans = (AnswerRecord)answerlist.get(i-1);
				stmt2.executeUpdate("insert into answer_record(id,record) values('"+ans.getId()+"','"+ans.getAnswer()+"')");
			}
			stmt1.close();
			stmt2.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			release();
		}
		
	}
	
	public AnswerRecord getAnswer(int id){
		
		AnswerRecord answerrec = new AnswerRecord();
		try {
			connect();
			stmt = conn.createStatement();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		try {
			rs = stmt.executeQuery("select * from answer_record where id = '"+id+"'");
			if(rs.next()){
				answerrec.setId(rs.getInt("id"));
				answerrec.setAnswer(rs.getString("record"));
			}else{
				answerrec=null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			release();
		}
		return answerrec;
		
	}
	
	public boolean isUser(int id){
		
		boolean flag = false;  //flag=1 ���ڣ�flag=0������
		try {
			connect();
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery("select * from user where id = '"+id+"' ");
			if(rs.next()){
				flag = true;
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}finally{
			release();
		}
		
		return flag;
	}
	
	public void setAnswerRec(AnswerRecord ans){
		int id = ans.getId();
		if(isUser(id)){
			try {
				connect();
				stmt = conn.createStatement();
				stmt.executeUpdate("insert into answer_record  values('"+ans.getId()+"','"+ans.getAnswer()+"')");
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				release();
			}
		}
		
	}
	
	public List getAllAnswer(){
		
		AnswerRecord answerrec = new AnswerRecord();
		List anslist = new ArrayList();
		
		try {
			connect();
			stmt = conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			rs = stmt.executeQuery("select * from answer_record ");
			while(rs.next()){
				answerrec = new AnswerRecord();
				answerrec.setId(rs.getInt("id"));
				answerrec.setAnswer(rs.getString("record"));
				anslist.add(answerrec);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			release();
		}
		
		return anslist;
		
	}
	
	public List getAllAnswerByUser(List userlist){
		
		AnswerRecord answerrec = new AnswerRecord();
		List anslist = new ArrayList();
		
		try {
			connect();
			stmt = conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		int size = userlist.size();
		int id=0;
		for(int i=0;i<size;i++){
			id = ((User)userlist.get(i)).getId();
			try {
				rs = stmt.executeQuery("select * from answer_record where id='"+id+"'");
				if(rs.next()){
					answerrec = new AnswerRecord();
					answerrec.setId(rs.getInt("id"));
					answerrec.setAnswer(rs.getString("record"));
					anslist.add(answerrec);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		release();
		return anslist;
		
	}
	
	public String analysisCharacterById(int id){
		String analysischaracter = null;
		QuestionManager qsm = new QuestionManager();
		AnswerRecord answerrec = new AnswerRecord();
		
		String characterlist = qsm.getCharacterList();
		answerrec = getAnswer(id);
		String answer = answerrec.getAnswer();
		
//		System.out.println("characterlist:"+characterlist+"answerrec"+answerrec.getAnswer());
		int[][] character = new int[9][3];
		for(int i=0;i<9;i++){
			character[i][0] = character[i][1] = character[i][2] = 0;
		}
		for(int i=0;i<characterlist.length();i++){
			//��i+1���Ӧ�Ը�
			int index = characterlist.charAt(i)-48-1;
			//��i+1���ѡ��
			int index1 = answer.charAt(i)-48;
			
			character[index][index1]++;
		}
		analysischaracter = Integer.toString(character[0][0])+","+Integer.toString(character[0][1])+","+Integer.toString(character[0][2]);
		for(int i=1;i<9;i++){
			analysischaracter += ","+Integer.toString(character[i][0])+","+Integer.toString(character[i][1])+","+Integer.toString(character[i][2]);
		}
		
//		System.out.println("analysischaracter:"+analysischaracter);
		
		return analysischaracter;
	} 
	
	public void updateAnswerById(int id,String answer){
//		System.out.println("id="+id);
		try {
			connect();
			stmt = conn.createStatement();
			stmt.executeUpdate("update answer_record set record='"+answer+"' where id='"+id+"'");
//			System.out.println("update answer success");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			release();
		}
	}
	
	public int resgiterTestUser(User user){
		int amount=0;
		try{
			connect();
			stmt = conn.createStatement();
		}catch (SQLException e){
			e.printStackTrace();
		}
		try{
			amount = stmt.executeUpdate("insert into user(stuId,name,gender,age,nativeplace,start_datetime) values('"+user.getStuId()+"','"+user.getName()+"','"+user.getGender()+"',"+user.getAge()+",'"+user.getNativeplace()+"','"+user.getStart_datetime()+"')");
//			System.out.println("resgiterTestUser success");
		}catch(SQLException e){
			
			e.printStackTrace();
		}
		
		release();
		
					
		return amount;
	}

	public User GetUserObject(int userId) {
		User user = null;
		try{
			connect();
			stmt = conn.createStatement();
//			System.out.println("success");
		}catch (SQLException e){
			e.printStackTrace();
		}
		try{
//			System.out.println("userId");
            rs = stmt.executeQuery("select * from user where id ="+userId);
			rs.next();
			user.setId(rs.getInt("id"));
			user.setName(rs.getString("name"));
			user.setGender(rs.getString("gender"));
			user.setAge(rs.getInt("age"));
			user.setNativeplace(rs.getString("nativeplace"));
			user.setStuId(rs.getString("stuId"));
			user.setStart_datetime(rs.getString("start_datetime"));
		}catch(SQLException e){

			e.printStackTrace();
		}finally{
			release();
		}
					
		return user;
	}

	public String GetQuestion(AnswerRecord answer) {
		
		String Question_sort="";
		UserManager um=new UserManager();
		try{
			connect();
			stmt = conn.createStatement();
		}catch (SQLException e){
			e.printStackTrace();
		}
		try{
//			System.out.println("GetQuestion userId"+answer.getId());
            stmt.executeUpdate("update result set result1='"+answer.getAnswer()+"' where userid="+answer.getId());
//	        System.out.println("update success!");
	        Question_sort=um.GetQuestionOne(answer.getId());
//	        System.out.println("GetQuestion"+Question_sort);
		}catch(SQLException e){
			e.printStackTrace();
		}
			
			release();
//	      System.out.println("GetQuestion"+Question_sort);
	    	return Question_sort;
	}

	//���id��ѯ�����˳���Ӧ���Ը�����
	private String GetQuestionOne(int id) {
		String Question_sort="";
		try{
			connect();
			stmt = conn.createStatement();
		}catch (SQLException e){
			e.printStackTrace();
		}
		try{
//			System.out.println("GetQuestionOne"+id);
	        rs=stmt.executeQuery("select * from result where userid="+id);
	        rs.next();
	        Question_sort=rs.getString("question_sort");
//	        System.out.println("GetQuestion"+Question_sort);
		}catch(SQLException e){

			e.printStackTrace();
		}		
		release();
	    //System.out.println("GetQuestion"+Question_sort);
	    return Question_sort;
	}

	public int GetUserId(User user) {
	    int userid = 0;
		try{
			connect();
			stmt = conn.createStatement();
		}catch (SQLException e){
//			System.out.println("error");
			e.printStackTrace();
		}
		try{
			rs = stmt.executeQuery("select * from user where stuid = '"+user.getStuId()+"' and name='"+user.getName()+"' and gender='"+user.getGender()+"' and age="+user.getAge()+" and nativeplace='"+user.getNativeplace()+"' and start_datetime='"+user.getStart_datetime()+"'");
//			System.out.println("UserManger OK!");
			rs.next();
			userid=rs.getInt("id");
			
			//System.out.println("UserManger userid"+userid);
		}catch(SQLException e){
//			System.out.println("error");
			e.printStackTrace();
		}finally{
//			System.out.println("error");
			release();
		}			
		return userid;
	}

	//�����Ŀ��˳���ѯ������Ŀ
	public List queryAllQuestion(int[] sort,int userid) {
		String queston1="";
		Questions question;
		ArrayList questionList=new ArrayList();
		
		try{
			connect();
			stmt = conn.createStatement();
	
		   }catch (SQLException e){
//			System.out.println(" connect error");
			e.printStackTrace();
		   }
		try{
//			System.out.println("length"+sort.length);
			for(int i=0;i<sort.length;i++)
			{
			question=new Questions();
			rs = stmt.executeQuery("select * from question where id="+sort[i]);
			rs.next();
			question.setId(rs.getInt("id"));
			question.setContent(rs.getString("content"));
			question.setRelatedCharacter(rs.getString("relatedCharacter"));
			
			queston1=queston1+question.getRelatedCharacter(); 
			questionList.add(question);
		}
		}catch(SQLException e){
//			System.out.println("executeQuery error");
			e.printStackTrace();
		}finally{
//			System.out.println("release");
			release();
		}
//		System.out.println("queryAllQuestion"+queston1);
		UserManager um=new UserManager();
		um.resgisterEmptyAnswerRecord(userid,queston1);
		return questionList;
	}

	private void resgisterEmptyAnswerRecord(int userid, String queston1) {
		try{
			connect();
			stmt = conn.createStatement();
		}catch (SQLException e){
			e.printStackTrace();
		}
		try{
//			System.out.println("resgisterEmptyAnswerRecord");
            stmt.executeUpdate("insert into result(userid,question_sort) values("+userid+",'"+queston1+"')");
//            System.out.println("resgisterEmptyAnswerRecord success");
		}catch(SQLException e){
			e.printStackTrace();
		}
		release();			
	}

	public void SetUserEndDateTime(int id, String time) {
		try{
			connect();
			stmt = conn.createStatement();
		}catch (SQLException e){
			e.printStackTrace();
		}
		try{
//			System.out.println("SetUserDateTime"+time+"id"+id);
            stmt.executeUpdate("update user set end_datetime='"+time+"' where id="+id);
		}catch(SQLException e){
			e.printStackTrace();
		}
		release();	
	}
	
	public void SetSysResult(String[] str3,int userid){
		String str="";
		String[] character = {"һ","��","��","��","��","��","��","��","��"};
		int index = 0;
		for(int i=0;i<3;i++){
			index = Integer.parseInt(str3[i]);
			if(index>0&&index<=9){
				str+=character[index-1]+",";
			}
			System.out.println("123"+str);
		}
		try{
			connect();
			stmt = conn.createStatement();
		}catch (SQLException e){
			e.printStackTrace();
		}
		try{
//			System.out.println("SetSysResult"+"id"+userid);
            stmt.executeUpdate("update user set sysResult='"+str.substring(0, str.length()-1)+"' where id="+userid);
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		release();	
	}
	
	public void deleteIllegal(){
		deleteIllegalUser();
		deleteIllegalAnswer();
	}
	
	public void deleteIllegalUser(){
		try {
			connect();
			Statement stmt1 = null;
			Statement stmt2 = null;
			ResultSet rs1 = null;
			stmt = conn.createStatement();
			stmt1 = conn.createStatement();
			stmt2 = conn.createStatement();
			rs = stmt.executeQuery("select * from user");
			
			int id = 0;
			while(rs.next()){
				id = rs.getInt("id");
				rs1 = stmt1.executeQuery("select * from answer_record where id="+id+"");
				if(!rs1.next()){
					stmt2.executeUpdate("delete from user where id="+id+"");
				}
			}
			if(rs1!=null){
				rs1.close();
			}
			if(stmt1!=null){
				stmt1.close();
			}
			if(stmt2!=null){
				stmt2.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			release();
		}
	}
	
	public void deleteIllegalAnswer(){
		try {
			connect();
			Statement stmt1 = null;
			Statement stmt2 = null;
			ResultSet rs1 = null;
			stmt = conn.createStatement();
			stmt1 = conn.createStatement();
			stmt2 = conn.createStatement();
			rs = stmt.executeQuery("select * from answer_record");
			
			int id = 0;
			while(rs.next()){
				id = rs.getInt("id");
				rs1 = stmt1.executeQuery("select * from user where id="+id+"");
				if(!rs1.next()){
					stmt2.executeUpdate("delete from answer_record where id="+id+"");
				}
			}
			if(rs1!=null){
				rs1.close();
			}
			if(stmt1!=null){
				stmt1.close();
			}
			if(stmt2!=null){
				stmt2.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			release();
		}
	}
	
	public void connect(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
//			System.out.println("����سɹ�!");
		}catch (ClassNotFoundException e) {
//		   System.out.println("�����ʧ��!");
		   e.printStackTrace();
		}
		try {
			//5rcyApp74SwM
			//1234
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/ninehouse","root","Pwp123456");
			System.out.println("��ݿ����ӳɹ�");
		} catch (SQLException e) {
//			System.out.println("��ݿ�����ʧ��");
		}
		
	}
	
	public void release(){
		
		if(rs!=null){
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(stmt!=null){
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(conn!=null){

			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
//		System.out.println("�ͷųɹ�");
	}

	public void userInfoImport(File file){
		int i;
        Sheet sheet;
        Workbook book = null;
        Cell []cell = new Cell[10];
        
        try { 
//        	Connection conn1 = null;
//        	Statement stmt1 = null;
//        	ResultSet rs1 = null;
        	
        	connect();
        	
        	stmt = conn.createStatement();
        	//stmt1 = conn1.createStatement();
            try {
				book= Workbook.getWorkbook(file);
			} catch (BiffException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} 
             
            //��õ�һ�����������(excel��sheet�ı�Ŵ�0��ʼ,0,1,2,3,....)
            sheet=book.getSheet(0); 
             
            User user = new User();
            
            i = 2;//?????�ҿ����ǵ����п�ʼ�����
            int rows = sheet.getRows();
            
            DecimalFormat df = new DecimalFormat("#.00");  
    		SimpleDateFormat  format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
    		java.util.Date date1 = null,date2=null;
    		
            while(i<rows){
            	//System.out.println("create success2");
                //��ȡÿһ�еĵ�Ԫ�� 
                cell[0]=sheet.getCell(1,i);//���У��У�
                cell[1]=sheet.getCell(2,i);
                cell[2]=sheet.getCell(3,i);
                int gender;
                try{
                	gender = cell[2].toString().equals("��")?0:1;
                }catch(Exception e){
                	i++;
                	continue;
                }
                cell[3]=sheet.getCell(4,i);
                int age;
                try{
                	age = Integer.parseInt(cell[3].toString());
                }catch(Exception e){
                	continue;
                }
                cell[4]=sheet.getCell(5,i);
                cell[5]=sheet.getCell(6,i);
                cell[6]=sheet.getCell(7,i);
                cell[7]=sheet.getCell(8,i);
                
                cell[8]=sheet.getCell(9,i);
                long during_time;
                try{
                	during_time = (long)(Float.parseFloat(cell[8].toString())*60000);
                }catch(Exception e){
                	during_time = 0;
                }
               if(cell[0].toString()!=null)
                {user.setStuId(cell[0].toString());
                }
               else
            	   user.setStuId(""); 
                
                user.setName(cell[1].toString());
                user.setGender(Integer.toString(gender));
                user.setAge(age);
                user.setNativeplace(cell[4].toString());
             
                user.setSysResult(cell[5].toString());
                if(cell[6].toString()!=null)
                    user.setRelResult(cell[6].toString());
                else
                	user.setRelResult("");
                user.setStart_datetime(cell[7].toString());
                //if(user.getEnd_datetime()!=null)
            	//{
					try {
						date1=format.parse(user.getStart_datetime());
					} catch (ParseException e) {
						//e.printStackTrace();
						i++;
						continue;
					}
					try {
						date2=new java.util.Date(date1.getTime()+during_time*60000);
					} catch (Exception e) {
						//e.printStackTrace();
						i++;
						continue;
					}
            	//}
					user.setEnd_datetime(date2.toString());
                if("".equals(cell[0].getContents())==true){    //����ȡ�����Ϊ��
                	i++;
                	continue;
                }
                

                 
                
                try{
                	stmt.addBatch("INSERT into user VALUES('"+user.getStuId()+"','"+user.getName()+"','"+user.getGender()+"','"+user.getAge()+"','"+user.getNativeplace()+"','"+user.getSysResult()+"','"+user.getRelResult()+"','"+user.getStart_datetime()+"','"+user.getEnd_datetime()+"')");
                	stmt.executeBatch();
                }catch(Exception e){
                	i++;
                	continue;
                }
                
//                String answer;
//                String answerrec = "";
//                for(int j=10;j<=190;j++){
//                	answer = sheet.getCell(j,i).toString();
//                	if(answer.equals("��")){
//                		answerrec = answerrec+"0";
//                	}else if(answer.equals("��")){
//                		answerrec = answerrec+"1";
//                	}else{
//                		answerrec = answerrec+"2";
//                	}
//                }
//                try{
//                	rs1 = stmt1.executeQuery("select max(id) from user");
//                	if(rs1!=null){
//                		rs1.next();
//                		int answerID = rs1.getInt("id");
//                		stmt1.addBatch("insert into answer_record values(answerID,answerrec)");
//                		stmt1.executeBatch();
//                	}else{
//                		i++;
//                    	continue;
//                	}
//                	
//                }catch(Exception e){
//                	i++;
//                	continue;
//                }
                i++;
            }
            /*
            if(rs1!=null){
    			try {
    				rs1.close();
    			} catch (SQLException e) {
    				e.printStackTrace();
    			}
    		}
    		if(stmt1!=null){
    			try {
    				stmt1.close();
    			} catch (SQLException e) {
    				e.printStackTrace();
    			}
    		}
    		if(conn1!=null){

    			try {
    				conn1.close();
    			} catch (SQLException e) {
    				e.printStackTrace();
    			}
    		}
    		*/
            
            book.close(); 
        }catch(SQLException e){
        }finally{
        	
        	release();
        }
	}
	
}
