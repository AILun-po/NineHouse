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
		//定义单元格报头   
        String worksheetTitle = "所有学生信息"; 
           
        HSSFWorkbook wb = new HSSFWorkbook();   
           
        //创建列标头LIST   
        List<String> fialList = new ArrayList<String>();   
        fialList.add("序号");   
        fialList.add("学号");   
        fialList.add("姓名");   
        fialList.add("性别");   
        fialList.add("年龄");   
        fialList.add("籍贯");   
        fialList.add("系统评测");   
        fialList.add("实际结果");
        fialList.add("登陆时间");
        fialList.add("答题时间/分钟");
  
        // 设置单元格字体   
        HSSFFont font = wb.createFont();   
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);   
        font.setFontName("宋体");   
        font.setFontHeight((short) 200);   
        
        
        // 计算该报表的列数   
        int number = fialList.size()-1;   
        //==================================================================   
        // 创建单元格样式   
        HSSFCellStyle cellStyleTitle = wb.createCellStyle();   
        // 指定单元格居中对齐   
        cellStyleTitle.setAlignment(HSSFCellStyle.ALIGN_CENTER);   
        // 指定单元格垂直居中对齐   
        cellStyleTitle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);   
        // 指定当单元格内容显示不下时自动换行   
        cellStyleTitle.setWrapText(false);   
        //设置字体
        cellStyleTitle.setFont(font);
        //------------------------------------------------------------------   
        HSSFCellStyle cellStyle = wb.createCellStyle();   
        // 指定单元格居中对齐   
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);   
        // 指定单元格垂直居中对齐   
        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);   
        // 指定当单元格内容显示不下时自动换行   
        cellStyle.setWrapText(false);
        
        // 设置单元格字体   
        HSSFFont font1 = wb.createFont();   
        font1.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);   
        font1.setFontName("宋体");   
        font1.setFontHeight((short) 200);
        cellStyle.setFont(font1);
        //------------------------------------------------------------------   
           
           
        for(int z=0; z<1; z++){   
        	//工作表名   
            String worksheet = "所有学生信息";   
               
            HSSFSheet sheet = wb.createSheet(worksheet);   
  
            ExportExcel exportExcel = new ExportExcel(wb, sheet);   
  
            // 创建报表头部   
            exportExcel.createNormalHead(worksheetTitle, number);   
            //定义第一行   
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
            during_time="无效";
            userlist = um.queryAllUser();
            int size = userlist.size();
        	int xuhao=0;
            String RelResult[]={"","一","二","三","四","五","六","七","八","九"};
            //输出每个人的信息
            User user;
            String[] genders = {"男","女"};
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
	 * 导出所有用户的信息和选择
	 * @throws IOException 
	 * @throws WriteException 
	 * @throws RowsExceededException 
	 */
	public HSSFWorkbook exportAllUserInfoAndAnswer() {
		
		//定义单元格报头   
        String worksheetTitle = "所有学生信息和答题记录"; 
           
        HSSFWorkbook wb = new HSSFWorkbook();   
        
        //创建列标头LIST   
        List<String> fialList = new ArrayList<String>();   
        fialList.add("序号");   
        fialList.add("学号");   
        fialList.add("姓名");   
        fialList.add("性别");   
        fialList.add("年龄");   
        fialList.add("籍贯");   
        fialList.add("系统评测");   
        fialList.add("实际结果");
        fialList.add("登陆时间");
        fialList.add("答题时间/分钟");
        String qs_i = null;
        for(int j=0;j<180;j++){
      		 qs_i = Integer.toString(j+1);
      		fialList.add(qs_i);
      	}
  
        // 设置单元格字体   
        HSSFFont font = wb.createFont();   
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);   
        font.setFontName("宋体");   
        font.setFontHeight((short) 200);   
        
        
        // 计算该报表的列数   
        int number = fialList.size()-1;   
        //==================================================================   
        // 创建单元格样式   
        HSSFCellStyle cellStyleTitle = wb.createCellStyle();   
        // 指定单元格居中对齐   
        cellStyleTitle.setAlignment(HSSFCellStyle.ALIGN_CENTER);   
        // 指定单元格垂直居中对齐   
        cellStyleTitle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);   
        // 指定当单元格内容显示不下时自动换行   
        cellStyleTitle.setWrapText(false);   
        //设置字体
        cellStyleTitle.setFont(font);
        //------------------------------------------------------------------   
        HSSFCellStyle cellStyle = wb.createCellStyle();   
        // 指定单元格居中对齐   
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);   
        // 指定单元格垂直居中对齐   
        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);   
        // 指定当单元格内容显示不下时自动换行   
        cellStyle.setWrapText(false);
        
        // 设置单元格字体   
        HSSFFont font1 = wb.createFont();   
        font1.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);   
        font1.setFontName("宋体");   
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
        String RelResult[]={"","一","二","三","四","五","六","七","八","九"};
        for(int z=0; z<1; z++){   
        	//工作表名   
            String worksheet = "所有学生信息和答题记录";   
               
            HSSFSheet sheet = wb.createSheet(worksheet);   
  
            ExportExcel exportExcel = new ExportExcel(wb, sheet);   
  
            // 创建报表头部   
            exportExcel.createNormalHead(worksheetTitle, number);   
            //定义第一行   
            HSSFRow row1 = sheet.createRow(1);   
            HSSFCell cell1 = null;   
            for(int i = 0; i < fialList.size(); i++) {   
                cell1 = row1.createCell(i);   
                cell1.setCellStyle(cellStyleTitle);   
                cell1.setCellValue(new HSSFRichTextString(fialList.get(i).toString()));   
            }
            
            //输出每个人的信息
            User user;
            DecimalFormat df = new DecimalFormat("#.00");  
    		SimpleDateFormat  format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
    		java.util.Date date1 = null,date2=null;
            AnswerRecord answerrec;
            String id,stuId,name,gender,age,nativeplace,sysResult,relResult,start_datetime,during_time;
            during_time="无效";
            String[] genders = {"男","女"};
            String[] choices = {"是","否","不确定"};
            HSSFRow row = sheet.createRow(2); 
            HSSFCell cell = row.createCell(1);
            int anscount=0;
            for(int i=1;i<=size;i++){
            	//获得当前的用户和答题记录
            	user = new User();
            	user = (User)userlist.get(i-1);

            	id = Integer.toString(i);
            	stuId = user.getStuId();
            	name = user.getName();
            	if(user.getGender().equals("0")||user.getGender().equals("1")){
            		gender = genders[Integer.parseInt(user.getGender())];
            	}else{
            		gender = "不确定";
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
    	                	cell.setCellValue(new HSSFRichTextString("不确定"));
    	            	}
                	}
            	}else{
            		for(int j=0,k=j+10;j<180;j++,k++){
	            		cell = row.createCell(k);
	            		cell.setCellStyle(cellStyle);
	                	cell.setCellValue(new HSSFRichTextString("不确定"));
	            	}
            	}
            }
        }
		
		return wb;
	}
	
	public HSSFWorkbook exPresentRows(String idString){
        
		String[] ids = idString.split(",");
		//length表示要导出的id的个数
		int length = ids.length-1;
		System.out.println("length:"+length);
        
        //定义单元格报头   
        String worksheetTitle = "部分学生信息和答题记录";
           
        HSSFWorkbook wb = new HSSFWorkbook();   
        
        //创建列标头LIST   
        List<String> fialList = new ArrayList<String>();   
        fialList.add("序号");   
        fialList.add("学号");   
        fialList.add("姓名");   
        fialList.add("性别");   
        fialList.add("年龄");   
        fialList.add("籍贯");   
        fialList.add("系统评测");   
        fialList.add("实际结果");
        fialList.add("登陆时间");
        fialList.add("答题时间/分钟");
        String qs_i = null;
        for(int j=0;j<180;j++){
      		 qs_i = Integer.toString(j+1);
      		fialList.add(qs_i);
      	}
  
        // 设置单元格字体   
        HSSFFont font = wb.createFont();   
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);   
        font.setFontName("宋体");   
        font.setFontHeight((short) 200);   
        
        
        // 计算该报表的列数   
        int number = fialList.size()-1;   
        //==================================================================   
        // 创建单元格样式   
        HSSFCellStyle cellStyleTitle = wb.createCellStyle();   
        // 指定单元格居中对齐   
        cellStyleTitle.setAlignment(HSSFCellStyle.ALIGN_CENTER);   
        // 指定单元格垂直居中对齐   
        cellStyleTitle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);   
        // 指定当单元格内容显示不下时自动换行   
        cellStyleTitle.setWrapText(false);   
        //设置字体
        cellStyleTitle.setFont(font);
        //------------------------------------------------------------------   
        HSSFCellStyle cellStyle = wb.createCellStyle();   
        // 指定单元格居中对齐   
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);   
        // 指定单元格垂直居中对齐   
        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);   
        // 指定当单元格内容显示不下时自动换行   
        cellStyle.setWrapText(false);
        
        // 设置单元格字体   
        HSSFFont font1 = wb.createFont();   
        font1.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);   
        font1.setFontName("宋体");   
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
        String RelResult[]={"","一","二","三","四","五","六","七","八","九"};
        for(int z=0; z<1; z++){   
        	//工作表名   
            String worksheet = "部分学生信息和答题记录";   
               
            HSSFSheet sheet = wb.createSheet(worksheet);   
  
            ExportExcel exportExcel = new ExportExcel(wb, sheet);   
  
            // 创建报表头部   
            exportExcel.createNormalHead(worksheetTitle, number);   
            //定义第一行   
            HSSFRow row1 = sheet.createRow(1);   
            HSSFCell cell1 = null;   
            for(int i = 0; i < fialList.size(); i++) {   
                cell1 = row1.createCell(i);   
                cell1.setCellStyle(cellStyleTitle);   
                cell1.setCellValue(new HSSFRichTextString(fialList.get(i).toString()));   
            }
            
            //输出每个人的信息
            User user;
            AnswerRecord answerrec;
            int presentId,anscount=0;
            DecimalFormat df = new DecimalFormat("#.00");  
    		SimpleDateFormat  format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
    		java.util.Date date1 = null,date2=null;
            String id,stuId,name,gender,age,nativeplace,sysResult,relResult,start_datetime,during_time;
            during_time="无效";
            String[] genders = {"男","女"};
            String[] choices = {"是","否","不确定"};
            HSSFRow row = sheet.createRow(2); 
            HSSFCell cell = row.createCell(1);
            for(int i=1;i<=length;i++){
            	//获得当前的用户和答题记录
            	user = new User();
            	answerrec = new AnswerRecord();
            	
            	//获取下一个id
            	presentId = Integer.parseInt(ids[i])-1;
            	user = (User)userlist.get(i-1);
            	
            	id = Integer.toString(i);
            	stuId = user.getStuId();
            	name = user.getName();
            	if(user.getGender().equals("0")||user.getGender().equals("1")){
            		gender = genders[Integer.parseInt(user.getGender())];
            	}else{
            		gender = "不确定";
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
    	                	cell.setCellValue(new HSSFRichTextString("不确定"));
    	            	}
                	}
            	}else{
            		for(int j=0,k=j+10;j<180;j++,k++){
	            		cell = row.createCell(k);
	            		cell.setCellStyle(cellStyle);
	                	cell.setCellValue(new HSSFRichTextString("不确定"));
	            	}
            	}
            }
        }
		
		return wb;
        
	}
	
	public HSSFWorkbook exPresent(String idString){
		
		
		String[] ids = idString.split(",");
		//length表示要导出的id的个数
		int length = ids.length - 1;
        
        //定义单元格报头   
        String worksheetTitle = "部分学生信息"; 
           
        HSSFWorkbook wb = new HSSFWorkbook();   
        
        //创建列标头LIST   
        List<String> fialList = new ArrayList<String>();   
        fialList.add("序号");   
        fialList.add("学号");   
        fialList.add("姓名");   
        fialList.add("性别");   
        fialList.add("年龄");   
        fialList.add("籍贯");   
        fialList.add("系统评测");   
        fialList.add("实际结果");
        fialList.add("登陆时间");
        fialList.add("答题时间/分钟");
  
        // 设置单元格字体   
        HSSFFont font = wb.createFont();   
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);   
        font.setFontName("宋体");   
        font.setFontHeight((short) 200);   
        
        
        // 计算该报表的列数   
        int number = fialList.size()-1;   
        //==================================================================   
        // 创建单元格样式   
        HSSFCellStyle cellStyleTitle = wb.createCellStyle();   
        // 指定单元格居中对齐   
        cellStyleTitle.setAlignment(HSSFCellStyle.ALIGN_CENTER);   
        // 指定单元格垂直居中对齐   
        cellStyleTitle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);   
        // 指定当单元格内容显示不下时自动换行   
        cellStyleTitle.setWrapText(false);   
        //设置字体
        cellStyleTitle.setFont(font);
        //------------------------------------------------------------------   
        HSSFCellStyle cellStyle = wb.createCellStyle();   
        // 指定单元格居中对齐   
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);   
        // 指定单元格垂直居中对齐   
        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);   
        // 指定当单元格内容显示不下时自动换行   
        cellStyle.setWrapText(false);
        
        // 设置单元格字体   
        HSSFFont font1 = wb.createFont();   
        font1.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);   
        font1.setFontName("宋体");   
        font1.setFontHeight((short) 200);
        cellStyle.setFont(font1);
        //------------------------------------------------------------------   
           
        List userlist = new ArrayList();
        UserManager um = new UserManager();
      
        userlist = um.queryAllUser();
        int size = userlist.size();
        for(int z=0; z<1; z++){   
        	//工作表名   
            String worksheet = "部分学生信息";   
               
            HSSFSheet sheet = wb.createSheet(worksheet);   
  
            ExportExcel exportExcel = new ExportExcel(wb, sheet);   
  
            // 创建报表头部   
            exportExcel.createNormalHead(worksheetTitle, number);   
            //定义第一行   
            HSSFRow row1 = sheet.createRow(1);   
            HSSFCell cell1 = null;   
            for(int i = 0; i < fialList.size(); i++) {   
                cell1 = row1.createCell(i);   
                cell1.setCellStyle(cellStyleTitle);   
                cell1.setCellValue(new HSSFRichTextString(fialList.get(i).toString()));   
            }
            
            //输出每个人的信息
            User user;
        	int xuhao=0;
            String RelResult[]={"","一","二","三","四","五","六","七","八","九"};
            int presentId;
            DecimalFormat df = new DecimalFormat("#.00");  
    		SimpleDateFormat  format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
    		java.util.Date date1 = null,date2=null;
            String id,stuId,name,gender,age,nativeplace,sysResult,relResult,start_datetime,during_time;
            during_time="无效";
            String[] genders = {"男","女"};
            HSSFRow row = sheet.createRow(2); 
            HSSFCell cell = row.createCell(1);
            for(int i=1;i<=length;i++){
            	//获得当前的用户和答题记录
            	user = new User();
            	
            	//获取下一个id
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
