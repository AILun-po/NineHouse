package edu.ae.manager;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import edu.ae.entity.AnswerRecord;
import edu.ae.entity.ExportExcel;
import edu.ae.entity.Questions;
import edu.ae.entity.User;

public class DataExportManager {

	public HSSFWorkbook exportAllUserInfo(){
		//���嵥Ԫ��ͷ   
        String worksheetTitle = "����ѧ����Ϣ"; 
           
        HSSFWorkbook wb = new HSSFWorkbook();   
           
        //�����б�ͷLIST   
        List<String> fialList = new ArrayList<String>();   
        fialList.add("���");   
        fialList.add("ѧ��");   
        fialList.add("����");   
        fialList.add("�Ա�");   
        fialList.add("����");   
        fialList.add("����");   
        fialList.add("ϵͳ����");   
        fialList.add("ʵ�ʽ��");
        fialList.add("��½ʱ��");
        fialList.add("����ʱ��/����");
  
        // ���õ�Ԫ������   
        HSSFFont font = wb.createFont();   
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);   
        font.setFontName("����");   
        font.setFontHeight((short) 200);   
        
        
        // ����ñ��������   
        int number = fialList.size()-1;   
        //==================================================================   
        // ������Ԫ����ʽ   
        HSSFCellStyle cellStyleTitle = wb.createCellStyle();   
        // ָ����Ԫ����ж���   
        cellStyleTitle.setAlignment(HSSFCellStyle.ALIGN_CENTER);   
        // ָ����Ԫ��ֱ���ж���   
        cellStyleTitle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);   
        // ָ������Ԫ��������ʾ����ʱ�Զ�����   
        cellStyleTitle.setWrapText(false);   
        //��������
        cellStyleTitle.setFont(font);
        //------------------------------------------------------------------   
        HSSFCellStyle cellStyle = wb.createCellStyle();   
        // ָ����Ԫ����ж���   
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);   
        // ָ����Ԫ��ֱ���ж���   
        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);   
        // ָ������Ԫ��������ʾ����ʱ�Զ�����   
        cellStyle.setWrapText(false);
        
        // ���õ�Ԫ������   
        HSSFFont font1 = wb.createFont();   
        font1.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);   
        font1.setFontName("����");   
        font1.setFontHeight((short) 200);
        cellStyle.setFont(font1);
        //------------------------------------------------------------------   
           
           
        for(int z=0; z<1; z++){   
        	//��������   
            String worksheet = "����ѧ����Ϣ";   
               
            HSSFSheet sheet = wb.createSheet(worksheet);   
  
            ExportExcel exportExcel = new ExportExcel(wb, sheet);   
  
            // ��������ͷ��   
            exportExcel.createNormalHead(worksheetTitle, number);   
            //�����һ��   
            HSSFRow row1 = sheet.createRow(1);   
            HSSFCell cell1 = null;   
            for(int i = 0; i < fialList.size(); i++) {   
                cell1 = row1.createCell(i);   
                cell1.setCellStyle(cellStyleTitle);   
                cell1.setCellValue(new HSSFRichTextString(fialList.get(i).toString()));   
            }
            
            Questions qs;
            List userlist = new ArrayList();
            UserManager um = new UserManager();
            String id,stuId,name,gender,age,nativeplace,sysResult,relResult,start_datetime,during_time;
            during_time="��Ч";
            userlist = um.queryAllUser();
            int size = userlist.size();
        	int xuhao=0;
            String RelResult[]={"","һ","��","��","��","��","��","��","��","��"};
            //���ÿ���˵���Ϣ
            User user;
            String[] genders = {"��","Ů"};
            DecimalFormat df = new DecimalFormat("#.00");  
    		SimpleDateFormat  format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
    		java.util.Date date1 = null,date2=null;
            HSSFRow row = sheet.createRow(2); 
            HSSFCell cell = row.createCell(1);
            for(int i=1;i<=size;i++){
            	user = new User();
            	user = (User)userlist.get(i-1);
          	
            	id = Integer.toString(i);
            	stuId = user.getStuId();
            	name = user.getName();
            	gender = genders[Integer.parseInt(user.getGender())];
            	age =Integer.toString(user.getAge());
            	nativeplace = user.getNativeplace();
            	sysResult = user.getSysResult();
            
            	if(user.getRelResult()!=null)
            	{
            	xuhao=Integer.parseInt(user.getRelResult());
            	}
            	relResult = RelResult[xuhao];
            	start_datetime = user.getStart_datetime();
            	
            	if(user.getEnd_datetime()!=null)
            	{
					try {
						date1=format.parse(user.getStart_datetime());
					} catch (ParseException e) {
						e.printStackTrace();
					}
					try {
						date2=format.parse(user.getEnd_datetime());
					} catch (ParseException e) {
						e.printStackTrace();
					}
            
    		    long time1=date1.getTime();  
                long time2=date2.getTime();  
                during_time=String.valueOf(df.format(Math.abs(time2-time1)/(60000.0)));
            	}
                
                row = sheet.createRow(i+1);   
            	cell = row.createCell(0);   
            	cell.setCellStyle(cellStyle);   
            	cell.setCellValue(new HSSFRichTextString(id));   
            	cell = row.createCell(1);   
            	cell.setCellStyle(cellStyle);
            	cell.setCellValue(new HSSFRichTextString(stuId));   
            	cell = row.createCell(2);   
            	cell.setCellStyle(cellStyle);
            	cell.setCellValue(new HSSFRichTextString(name));   
            	cell = row.createCell(3);   
            	cell.setCellStyle(cellStyle);
            	cell.setCellValue(new HSSFRichTextString(gender));   
            	cell = row.createCell(4);   
            	cell.setCellStyle(cellStyle);
            	cell.setCellValue(new HSSFRichTextString(age));   
            	cell = row.createCell(5);   
            	cell.setCellStyle(cellStyle);
            	cell.setCellValue(new HSSFRichTextString(nativeplace));   
            	cell = row.createCell(6);   
            	cell.setCellStyle(cellStyle);
            	cell.setCellValue(new HSSFRichTextString(sysResult));   
            	cell = row.createCell(7);   
            	cell.setCellStyle(cellStyle);
            	cell.setCellValue(new HSSFRichTextString(relResult));
            	cell = row.createCell(8);   
            	cell.setCellStyle(cellStyle);
            	cell.setCellValue(new HSSFRichTextString(start_datetime));
            	cell = row.createCell(9);   
            	cell.setCellStyle(cellStyle);
            	cell.setCellValue(new HSSFRichTextString(during_time));
            }
        }
		
		return wb;
	}
	
	
	/**
	 * ���������û�����Ϣ��ѡ��
	 * @throws IOException 
	 * @throws WriteException 
	 * @throws RowsExceededException 
	 */
	public HSSFWorkbook exportAllUserInfoAndAnswer() {
		
		//���嵥Ԫ��ͷ   
        String worksheetTitle = "����ѧ����Ϣ�ʹ����¼"; 
           
        HSSFWorkbook wb = new HSSFWorkbook();   
        
        //�����б�ͷLIST   
        List<String> fialList = new ArrayList<String>();   
        fialList.add("���");   
        fialList.add("ѧ��");   
        fialList.add("����");   
        fialList.add("�Ա�");   
        fialList.add("����");   
        fialList.add("����");   
        fialList.add("ϵͳ����");   
        fialList.add("ʵ�ʽ��");
        fialList.add("��½ʱ��");
        fialList.add("����ʱ��/����");
        String qs_i = null;
        for(int j=0;j<180;j++){
      		 qs_i = Integer.toString(j+1);
      		fialList.add(qs_i);
      	}
  
        // ���õ�Ԫ������   
        HSSFFont font = wb.createFont();   
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);   
        font.setFontName("����");   
        font.setFontHeight((short) 200);   
        
        
        // ����ñ��������   
        int number = fialList.size()-1;   
        //==================================================================   
        // ������Ԫ����ʽ   
        HSSFCellStyle cellStyleTitle = wb.createCellStyle();   
        // ָ����Ԫ����ж���   
        cellStyleTitle.setAlignment(HSSFCellStyle.ALIGN_CENTER);   
        // ָ����Ԫ��ֱ���ж���   
        cellStyleTitle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);   
        // ָ������Ԫ��������ʾ����ʱ�Զ�����   
        cellStyleTitle.setWrapText(false);   
        //��������
        cellStyleTitle.setFont(font);
        //------------------------------------------------------------------   
        HSSFCellStyle cellStyle = wb.createCellStyle();   
        // ָ����Ԫ����ж���   
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);   
        // ָ����Ԫ��ֱ���ж���   
        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);   
        // ָ������Ԫ��������ʾ����ʱ�Զ�����   
        cellStyle.setWrapText(false);
        
        // ���õ�Ԫ������   
        HSSFFont font1 = wb.createFont();   
        font1.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);   
        font1.setFontName("����");   
        font1.setFontHeight((short) 200);
        cellStyle.setFont(font1);
        //------------------------------------------------------------------   
           
        List userlist = new ArrayList();
        List answerlist = new ArrayList();
        UserManager um = new UserManager();
      
        userlist = um.queryAllUser();
        //answerlist = um.getAllAnswer();
        answerlist = um.getAllAnswerByUser(userlist);
        int size = userlist.size();
        int size1 = answerlist.size(); 
    	int xuhao=0;
        String RelResult[]={"","һ","��","��","��","��","��","��","��","��"};
        for(int z=0; z<1; z++){   
        	//��������   
            String worksheet = "����ѧ����Ϣ�ʹ����¼";   
               
            HSSFSheet sheet = wb.createSheet(worksheet);   
  
            ExportExcel exportExcel = new ExportExcel(wb, sheet);   
  
            // ��������ͷ��   
            exportExcel.createNormalHead(worksheetTitle, number);   
            //�����һ��   
            HSSFRow row1 = sheet.createRow(1);   
            HSSFCell cell1 = null;   
            for(int i = 0; i < fialList.size(); i++) {   
                cell1 = row1.createCell(i);   
                cell1.setCellStyle(cellStyleTitle);   
                cell1.setCellValue(new HSSFRichTextString(fialList.get(i).toString()));   
            }
            
            //���ÿ���˵���Ϣ
            User user;
            DecimalFormat df = new DecimalFormat("#.00");  
    		SimpleDateFormat  format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
    		java.util.Date date1 = null,date2=null;
            AnswerRecord answerrec;
            String id,stuId,name,gender,age,nativeplace,sysResult,relResult,start_datetime,during_time;
            during_time="��Ч";
            String[] genders = {"��","Ů"};
            String[] choices = {"��","��","��ȷ��"};
            HSSFRow row = sheet.createRow(2); 
            HSSFCell cell = row.createCell(1);
            int anscount=0;
            for(int i=1;i<=size;i++){
            	//��õ�ǰ���û��ʹ����¼
            	user = new User();
            	user = (User)userlist.get(i-1);

            	id = Integer.toString(i);
            	stuId = user.getStuId();
            	name = user.getName();
            	if(user.getGender().equals("0")||user.getGender().equals("1")){
            		gender = genders[Integer.parseInt(user.getGender())];
            	}else{
            		gender = "��ȷ��";
            	}
            	age =Integer.toString(user.getAge());
            	nativeplace = user.getNativeplace();
            	sysResult = user.getSysResult();
            	if(user.getRelResult()!=null)
            		xuhao=Integer.parseInt(user.getRelResult());
               relResult =RelResult[xuhao] ;
            	
            	start_datetime = user.getStart_datetime();
             	if(user.getEnd_datetime()!=null)
            	{
					try {
						date1=format.parse(user.getStart_datetime());
					} catch (ParseException e) {
						e.printStackTrace();
					}
					try {
						date2=format.parse(user.getEnd_datetime());
					} catch (ParseException e) {
						e.printStackTrace();
					}
            
	    		    long time1=date1.getTime();  
	                long time2=date2.getTime();  
	                during_time=String.valueOf(df.format(Math.abs(time2-time1)/(60000.0)));
            	}
            	
            	row = sheet.createRow(i+1);   
            	cell = row.createCell(0);   
            	cell.setCellStyle(cellStyle);   
            	cell.setCellValue(new HSSFRichTextString(id));   
            	cell = row.createCell(1);   
            	cell.setCellStyle(cellStyle);
            	cell.setCellValue(new HSSFRichTextString(stuId));   
            	cell = row.createCell(2);   
            	cell.setCellStyle(cellStyle);
            	cell.setCellValue(new HSSFRichTextString(name));   
            	cell = row.createCell(3);   
            	cell.setCellStyle(cellStyle);
            	cell.setCellValue(new HSSFRichTextString(gender));   
            	cell = row.createCell(4);   
            	cell.setCellStyle(cellStyle);
            	cell.setCellValue(new HSSFRichTextString(age));   
            	cell = row.createCell(5);   
            	cell.setCellStyle(cellStyle);
            	cell.setCellValue(new HSSFRichTextString(nativeplace));   
            	cell = row.createCell(6);   
            	cell.setCellStyle(cellStyle);
            	cell.setCellValue(new HSSFRichTextString(sysResult));   
            	cell = row.createCell(7);   
            	cell.setCellStyle(cellStyle);
            	cell.setCellValue(new HSSFRichTextString(relResult));
            	cell = row.createCell(8);   
            	cell.setCellStyle(cellStyle);
            	cell.setCellValue(new HSSFRichTextString(start_datetime));
             	cell = row.createCell(9);   
            	cell.setCellStyle(cellStyle);
            	cell.setCellValue(new HSSFRichTextString(during_time));

            	answerrec = new AnswerRecord();
            	if(anscount<size1){
            		answerrec = (AnswerRecord) answerlist.get(anscount);
            		if(answerrec.getId()==user.getId()){
		            	String answer = answerrec.getAnswer();
		            	String eachanswer = null;
		            	int index=0;
		            	for(int j=0,k=j+10;j<180;j++,k++){
		            		index = answer.charAt(j)-48;
		            		if(index<0||index>2)
		            			index=2;
		            		eachanswer = choices[index];
		            		cell = row.createCell(k);
		            		cell.setCellStyle(cellStyle);
		                	cell.setCellValue(new HSSFRichTextString(eachanswer));
		            	}
		            	anscount++;
            		}else{
                		for(int j=0,k=j+10;j<180;j++,k++){
    	            		cell = row.createCell(k);
    	            		cell.setCellStyle(cellStyle);
    	                	cell.setCellValue(new HSSFRichTextString("��ȷ��"));
    	            	}
                	}
            	}else{
            		for(int j=0,k=j+10;j<180;j++,k++){
	            		cell = row.createCell(k);
	            		cell.setCellStyle(cellStyle);
	                	cell.setCellValue(new HSSFRichTextString("��ȷ��"));
	            	}
            	}
            }
        }
		
		return wb;
	}
	
	public HSSFWorkbook exPresentRows(String idString){
        
		String[] ids = idString.split(",");
		//length��ʾҪ������id�ĸ���
		int length = ids.length-1;
		System.out.println("length:"+length);
        
        //���嵥Ԫ��ͷ   
        String worksheetTitle = "����ѧ����Ϣ�ʹ����¼";
           
        HSSFWorkbook wb = new HSSFWorkbook();   
        
        //�����б�ͷLIST   
        List<String> fialList = new ArrayList<String>();   
        fialList.add("���");   
        fialList.add("ѧ��");   
        fialList.add("����");   
        fialList.add("�Ա�");   
        fialList.add("����");   
        fialList.add("����");   
        fialList.add("ϵͳ����");   
        fialList.add("ʵ�ʽ��");
        fialList.add("��½ʱ��");
        fialList.add("����ʱ��/����");
        String qs_i = null;
        for(int j=0;j<180;j++){
      		 qs_i = Integer.toString(j+1);
      		fialList.add(qs_i);
      	}
  
        // ���õ�Ԫ������   
        HSSFFont font = wb.createFont();   
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);   
        font.setFontName("����");   
        font.setFontHeight((short) 200);   
        
        
        // ����ñ��������   
        int number = fialList.size()-1;   
        //==================================================================   
        // ������Ԫ����ʽ   
        HSSFCellStyle cellStyleTitle = wb.createCellStyle();   
        // ָ����Ԫ����ж���   
        cellStyleTitle.setAlignment(HSSFCellStyle.ALIGN_CENTER);   
        // ָ����Ԫ��ֱ���ж���   
        cellStyleTitle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);   
        // ָ������Ԫ��������ʾ����ʱ�Զ�����   
        cellStyleTitle.setWrapText(false);   
        //��������
        cellStyleTitle.setFont(font);
        //------------------------------------------------------------------   
        HSSFCellStyle cellStyle = wb.createCellStyle();   
        // ָ����Ԫ����ж���   
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);   
        // ָ����Ԫ��ֱ���ж���   
        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);   
        // ָ������Ԫ��������ʾ����ʱ�Զ�����   
        cellStyle.setWrapText(false);
        
        // ���õ�Ԫ������   
        HSSFFont font1 = wb.createFont();   
        font1.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);   
        font1.setFontName("����");   
        font1.setFontHeight((short) 200);
        cellStyle.setFont(font1);
        //------------------------------------------------------------------   
           
        List userlist = new ArrayList();
        List answerlist = new ArrayList();
        UserManager um = new UserManager();
      
        userlist = um.queryAllUserByIds(idString);
        answerlist = um.getAllAnswerByUser(userlist);
        int size = userlist.size();
        int size1 = answerlist.size();
        int xuhao=0;
        String RelResult[]={"","һ","��","��","��","��","��","��","��","��"};
        for(int z=0; z<1; z++){   
        	//��������   
            String worksheet = "����ѧ����Ϣ�ʹ����¼";   
               
            HSSFSheet sheet = wb.createSheet(worksheet);   
  
            ExportExcel exportExcel = new ExportExcel(wb, sheet);   
  
            // ��������ͷ��   
            exportExcel.createNormalHead(worksheetTitle, number);   
            //�����һ��   
            HSSFRow row1 = sheet.createRow(1);   
            HSSFCell cell1 = null;   
            for(int i = 0; i < fialList.size(); i++) {   
                cell1 = row1.createCell(i);   
                cell1.setCellStyle(cellStyleTitle);   
                cell1.setCellValue(new HSSFRichTextString(fialList.get(i).toString()));   
            }
            
            //���ÿ���˵���Ϣ
            User user;
            AnswerRecord answerrec;
            int presentId,anscount=0;
            DecimalFormat df = new DecimalFormat("#.00");  
    		SimpleDateFormat  format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
    		java.util.Date date1 = null,date2=null;
            String id,stuId,name,gender,age,nativeplace,sysResult,relResult,start_datetime,during_time;
            during_time="��Ч";
            String[] genders = {"��","Ů"};
            String[] choices = {"��","��","��ȷ��"};
            HSSFRow row = sheet.createRow(2); 
            HSSFCell cell = row.createCell(1);
            for(int i=1;i<=length;i++){
            	//��õ�ǰ���û��ʹ����¼
            	user = new User();
            	answerrec = new AnswerRecord();
            	
            	//��ȡ��һ��id
            	presentId = Integer.parseInt(ids[i])-1;
            	user = (User)userlist.get(i-1);
            	
            	id = Integer.toString(i);
            	stuId = user.getStuId();
            	name = user.getName();
            	if(user.getGender().equals("0")||user.getGender().equals("1")){
            		gender = genders[Integer.parseInt(user.getGender())];
            	}else{
            		gender = "��ȷ��";
            	}
            	age =Integer.toString(user.getAge());
            	nativeplace = user.getNativeplace();
            	sysResult = user.getSysResult();
            	if(user.getRelResult()!=null)
            	{
            	xuhao=Integer.parseInt(user.getRelResult());
            	}
            	relResult = RelResult[xuhao];
            	start_datetime = user.getStart_datetime();
            	if(user.getEnd_datetime()!=null)
            	{
					try {
						date1=format.parse(user.getStart_datetime());
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					try {
						date2=format.parse(user.getEnd_datetime());
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
            
    		    long time1=date1.getTime();  
                long time2=date2.getTime();  
                during_time=String.valueOf(df.format(Math.abs(time2-time1)/(60000.0)));
            	}
            	start_datetime = user.getStart_datetime();
          	
            	row = sheet.createRow(i+1);   
            	cell = row.createCell(0);   
            	cell.setCellStyle(cellStyle);   
            	cell.setCellValue(new HSSFRichTextString(id));   
            	cell = row.createCell(1);   
            	cell.setCellStyle(cellStyle);
            	cell.setCellValue(new HSSFRichTextString(stuId));   
            	cell = row.createCell(2);   
            	cell.setCellStyle(cellStyle);
            	cell.setCellValue(new HSSFRichTextString(name));   
            	cell = row.createCell(3);   
            	cell.setCellStyle(cellStyle);
            	cell.setCellValue(new HSSFRichTextString(gender));   
            	cell = row.createCell(4);   
            	cell.setCellStyle(cellStyle);
            	cell.setCellValue(new HSSFRichTextString(age));   
            	cell = row.createCell(5);   
            	cell.setCellStyle(cellStyle);
            	cell.setCellValue(new HSSFRichTextString(nativeplace));   
            	cell = row.createCell(6);   
            	cell.setCellStyle(cellStyle);
            	cell.setCellValue(new HSSFRichTextString(sysResult));   
            	cell = row.createCell(7);   
            	cell.setCellStyle(cellStyle);
            	cell.setCellValue(new HSSFRichTextString(relResult));
            	cell = row.createCell(8);   
            	cell.setCellStyle(cellStyle);
            	cell.setCellValue(new HSSFRichTextString(start_datetime));
            	cell = row.createCell(9);
            	cell.setCellStyle(cellStyle);
            	cell.setCellValue(new HSSFRichTextString(during_time));
            	
            	if(anscount<size1){
	            	answerrec = (AnswerRecord) answerlist.get(anscount);
	            	if(answerrec.getId()==user.getId()){
	            		anscount++;
		            	String answer = answerrec.getAnswer();
		            	String eachanswer = null;
		            	int index=0;
		            	for(int j=0,k=j+10;j<180;j++,k++){
		            		index = answer.charAt(j)-48;
		            		if(index<0||index>2)
		            			index=2;
		            		eachanswer = choices[index];
		            		cell = row.createCell(k);
		            		cell.setCellStyle(cellStyle);
		                	cell.setCellValue(new HSSFRichTextString(eachanswer));
		            	}
            		}else{
                		for(int j=0,k=j+10;j<180;j++,k++){
    	            		cell = row.createCell(k);
    	            		cell.setCellStyle(cellStyle);
    	                	cell.setCellValue(new HSSFRichTextString("��ȷ��"));
    	            	}
                	}
            	}else{
            		for(int j=0,k=j+10;j<180;j++,k++){
	            		cell = row.createCell(k);
	            		cell.setCellStyle(cellStyle);
	                	cell.setCellValue(new HSSFRichTextString("��ȷ��"));
	            	}
            	}
            }
        }
		
		return wb;
        
	}
	
	public HSSFWorkbook exPresent(String idString){
		
		
		String[] ids = idString.split(",");
		//length��ʾҪ������id�ĸ���
		int length = ids.length - 1;
        
        //���嵥Ԫ��ͷ   
        String worksheetTitle = "����ѧ����Ϣ"; 
           
        HSSFWorkbook wb = new HSSFWorkbook();   
        
        //�����б�ͷLIST   
        List<String> fialList = new ArrayList<String>();   
        fialList.add("���");   
        fialList.add("ѧ��");   
        fialList.add("����");   
        fialList.add("�Ա�");   
        fialList.add("����");   
        fialList.add("����");   
        fialList.add("ϵͳ����");   
        fialList.add("ʵ�ʽ��");
        fialList.add("��½ʱ��");
        fialList.add("����ʱ��/����");
  
        // ���õ�Ԫ������   
        HSSFFont font = wb.createFont();   
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);   
        font.setFontName("����");   
        font.setFontHeight((short) 200);   
        
        
        // ����ñ��������   
        int number = fialList.size()-1;   
        //==================================================================   
        // ������Ԫ����ʽ   
        HSSFCellStyle cellStyleTitle = wb.createCellStyle();   
        // ָ����Ԫ����ж���   
        cellStyleTitle.setAlignment(HSSFCellStyle.ALIGN_CENTER);   
        // ָ����Ԫ��ֱ���ж���   
        cellStyleTitle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);   
        // ָ������Ԫ��������ʾ����ʱ�Զ�����   
        cellStyleTitle.setWrapText(false);   
        //��������
        cellStyleTitle.setFont(font);
        //------------------------------------------------------------------   
        HSSFCellStyle cellStyle = wb.createCellStyle();   
        // ָ����Ԫ����ж���   
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);   
        // ָ����Ԫ��ֱ���ж���   
        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);   
        // ָ������Ԫ��������ʾ����ʱ�Զ�����   
        cellStyle.setWrapText(false);
        
        // ���õ�Ԫ������   
        HSSFFont font1 = wb.createFont();   
        font1.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);   
        font1.setFontName("����");   
        font1.setFontHeight((short) 200);
        cellStyle.setFont(font1);
        //------------------------------------------------------------------   
           
        List userlist = new ArrayList();
        UserManager um = new UserManager();
      
        userlist = um.queryAllUser();
        int size = userlist.size();
        for(int z=0; z<1; z++){   
        	//��������   
            String worksheet = "����ѧ����Ϣ";   
               
            HSSFSheet sheet = wb.createSheet(worksheet);   
  
            ExportExcel exportExcel = new ExportExcel(wb, sheet);   
  
            // ��������ͷ��   
            exportExcel.createNormalHead(worksheetTitle, number);   
            //�����һ��   
            HSSFRow row1 = sheet.createRow(1);   
            HSSFCell cell1 = null;   
            for(int i = 0; i < fialList.size(); i++) {   
                cell1 = row1.createCell(i);   
                cell1.setCellStyle(cellStyleTitle);   
                cell1.setCellValue(new HSSFRichTextString(fialList.get(i).toString()));   
            }
            
            //���ÿ���˵���Ϣ
            User user;
        	int xuhao=0;
            String RelResult[]={"","һ","��","��","��","��","��","��","��","��"};
            int presentId;
            DecimalFormat df = new DecimalFormat("#.00");  
    		SimpleDateFormat  format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
    		java.util.Date date1 = null,date2=null;
            String id,stuId,name,gender,age,nativeplace,sysResult,relResult,start_datetime,during_time;
            during_time="��Ч";
            String[] genders = {"��","Ů"};
            HSSFRow row = sheet.createRow(2); 
            HSSFCell cell = row.createCell(1);
            for(int i=1;i<=length;i++){
            	//��õ�ǰ���û��ʹ����¼
            	user = new User();
            	
            	//��ȡ��һ��id
            	presentId = Integer.parseInt(ids[i])-1;
            	if(presentId>=size){
            		System.out.println("presentId:"+presentId);
            		break;
            	}
            	user = (User)userlist.get(presentId);

            	
            	id = Integer.toString(user.getId());
            	stuId = user.getStuId();
            	name = user.getName();
            	gender = genders[Integer.parseInt(user.getGender())];
            	age =Integer.toString(user.getAge());
            	nativeplace = user.getNativeplace();
            	sysResult = user.getSysResult();
            	if(user.getRelResult()!=null)
            	{
            	xuhao=Integer.parseInt(user.getRelResult());
            	}
            	relResult = RelResult[xuhao];
            	start_datetime = user.getStart_datetime();
            	if(user.getEnd_datetime()!=null)
            	{
					try {
						date1=format.parse(user.getStart_datetime());
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					try {
						date2=format.parse(user.getEnd_datetime());
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
            
    		    long time1=date1.getTime();  
                long time2=date2.getTime();  
                during_time=String.valueOf(df.format(Math.abs(time2-time1)/(60000.0)));
            	}
            	start_datetime = user.getStart_datetime();
          	
            	row = sheet.createRow(i+1);   
            	cell = row.createCell(0);   
            	cell.setCellStyle(cellStyle);   
            	cell.setCellValue(new HSSFRichTextString(id));   
            	cell = row.createCell(1);   
            	cell.setCellStyle(cellStyle);
            	cell.setCellValue(new HSSFRichTextString(stuId));   
            	cell = row.createCell(2);   
            	cell.setCellStyle(cellStyle);
            	cell.setCellValue(new HSSFRichTextString(name));   
            	cell = row.createCell(3);   
            	cell.setCellStyle(cellStyle);
            	cell.setCellValue(new HSSFRichTextString(gender));   
            	cell = row.createCell(4);   
            	cell.setCellStyle(cellStyle);
            	cell.setCellValue(new HSSFRichTextString(age));   
            	cell = row.createCell(5);   
            	cell.setCellStyle(cellStyle);
            	cell.setCellValue(new HSSFRichTextString(nativeplace));   
            	cell = row.createCell(6);   
            	cell.setCellStyle(cellStyle);
            	cell.setCellValue(new HSSFRichTextString(sysResult));   
            	cell = row.createCell(7);   
            	cell.setCellStyle(cellStyle);
            	cell.setCellValue(new HSSFRichTextString(relResult));
            	cell = row.createCell(8);   
            	cell.setCellStyle(cellStyle);
            	cell.setCellValue(new HSSFRichTextString(start_datetime));
            	cell = row.createCell(9);   
            	cell.setCellStyle(cellStyle);
            	cell.setCellValue(new HSSFRichTextString(during_time));
            	
            }
        }
		
		return wb;
		
	}
}
