package edu.ae.manager;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import edu.ae.entity.ExportExcel;
import edu.ae.entity.Questions;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

/**
 * 
 * ���Ĺ���
 *
 */
public class QuestionManager {
	
	
	private Connection conn = null;
	private Statement stmt = null;
	private ResultSet rs = null;
	
	/**
	 * 
	 * ����Ը����Ͳ�ѯ��Ŀ
	 * 
	 */
	public List queryByCharacter(String character){
		List qslist = new ArrayList();
		Questions qs;
		try {
			connect();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from question where relatedCharacter = '"+character+"'");
			while(rs.next()){
				qs = new Questions();
				qs.setId(Integer.parseInt(rs.getString("id")));
				qs.setContent(rs.getString("content"));
				qs.setRelatedCharacter(rs.getString("relatedCharacter"));
				qslist.add(qs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			release();
		}
		
		return qslist;
		
	}
	
	public void addQuestion(String content,String character){
		try{
			connect();
			Statement stmt1 = conn.createStatement();
			rs = stmt1.executeQuery("select * from question");
			int size=0;
			while(rs.next()) size++;
			
			size++;
			stmt = conn.createStatement();
			stmt.executeUpdate("insert into question values('"+size+"','"+content+"',"+character+")");
		}catch(SQLException e){

			e.printStackTrace();
		}finally{
			release();
		}
					
	}
	
	/**
	 * 
	 * ���id��ѯ��Ŀ
	 * 
	 */
	public Questions queryById(int id){
		Questions qs = null;
		try {
			connect();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from question where id = '"+id+"'");
			rs.next();
			qs = new Questions();
			qs.setId(Integer.parseInt(rs.getString("id")));
			qs.setContent(rs.getString("content"));
			qs.setRelatedCharacter(rs.getString("relatedCharacter"));
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			release();
		}
		
		return qs;
		
	}
	
	public void updateQuestion(Questions qs){
		try {
			connect();
			stmt = conn.createStatement();
			stmt.executeUpdate("update question set content='"+qs.getContent()+"',relatedCharacter='"+qs.getRelatedCharacter()+"' where id='"+qs.getId()+"'");
//			System.out.println("update question success");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			release();
		}
		createCharacterList();
	}
	
	public void updateAllQuestionId(){
		try {
			connect();
			stmt = conn.createStatement();
			Statement stmt1 = conn.createStatement();
			rs = stmt.executeQuery("select * from question");
			int i = 1;
			int id;
			while(rs.next()){
				id = rs.getInt("id");
				stmt1.executeUpdate("update question set id='"+i+"' where id='"+id+"'");
				i++;
			}
			stmt1.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			release();
		}
		createCharacterList();
	}
	
	/**
	 * ���������ÿ���Ӧ�Ը����͵�list
	 */
	public void createCharacterList(){
		try {
			connect();
			stmt = conn.createStatement();
			Statement stmt1 = conn.createStatement();
			
			rs = stmt.executeQuery("select * from question");
			ResultSet rs1 = stmt1.executeQuery("select * from character_list");
			
			int id=1;
			String relatedCharacter,characterlist = "";
			while(rs.next()){
				relatedCharacter = rs.getString("relatedCharacter");
				characterlist = characterlist + relatedCharacter;
			}
			
			if(rs1.next()){
				stmt1.executeUpdate("update character_list set character_listcol='"+characterlist+"'");
			}else{
				stmt1.executeUpdate("insert into character_list values('"+id+"','"+characterlist+"')");
			}
			rs1.close();
			stmt1.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			release();
		}
	}
	
	public String getCharacterList(){
		String characterlist = null;
		try {
			connect();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from character_list");
			if(rs.next()){
				characterlist = rs.getString("character_listcol");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			release();
		}
		return characterlist;
	}
	
	public void deleteQuestion(int id){
		System.out.println(id);
		try {
			connect();
			stmt = conn.createStatement();
			stmt.executeUpdate("delete from question where id='"+id+"'");
//			System.out.println("delete question success");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			release();
			
		}
		updateAllQuestionId();
	}
	
	/**
	 * 
	 * �������
	 */
	public void questionImport(File file){
		int i;
        Sheet sheet;
        Workbook book = null;
        Cell cell1,cell2,cell3;
        
        try { 
        	connect();
        	stmt = conn.createStatement();
        	
            try {
				book= Workbook.getWorkbook(file);
			} catch (BiffException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} 
             
            //��õ�һ�����������(excel��sheet�ı�Ŵ�0��ʼ,0,1,2,3,....)
            sheet=book.getSheet(0); 
             
            stmt.executeUpdate("delete from question where 1=1");
            
            Questions qs;
            
            i = 1;
            int rows = sheet.getRows();
            while(i<rows){
            	//System.out.println("create success2");
                //��ȡÿһ�еĵ�Ԫ�� 
                cell1=sheet.getCell(0,i);//���У��У�
                cell2=sheet.getCell(1,i);
                cell3=sheet.getCell(2,i);
                
                if("".equals(cell1.getContents())==true)    //����ȡ�����Ϊ��
                    break;
                
                qs = new Questions();
                qs.setId(i);
                qs.setContent(cell2.getContents());
                qs.setRelatedCharacter(cell3.getContents());
                
//                System.out.println(qs.getId()+"\t"+qs.getContent()+"\t"+qs.getRelatedCharacter());
                stmt.addBatch("INSERT into `question` VALUES('"+qs.getId()+"','"+qs.getContent()+"','"+qs.getRelatedCharacter()+"')");
                //System.out.println("create success1");
                stmt.executeBatch();
                i++;
            }
//            System.out.println("����ɹ�!");
            book.close(); 
        }catch(SQLException e){
//        	System.out.println("����ʧ��");
        }finally{
        	release();
        }
        createCharacterList();
	}
	
	/**
	 * 
	 * ��ѯ������Ŀ
	 */
	public List<Questions> queryAllQuestion(){
		Questions qs;
		List<Questions> qslist = new ArrayList<Questions>();
		
		try {
			connect();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from question ");
			while(rs.next()){
				qs = new Questions();
				qs.setId(Integer.parseInt(rs.getString("id")));
				qs.setContent(rs.getString("content"));
				qs.setRelatedCharacter(rs.getString("relatedCharacter"));
				qslist.add(qs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			release();
		}
		
		return qslist;
	}
	
	
	/**
	 * 
	 * �������
	 * @return 
	 * @throws IOException 
	 * @throws WriteException 
	 * @throws RowsExceededException 
	 */
	public HSSFWorkbook questionExport(){
		
        //���嵥Ԫ��ͷ   
//        String worksheetTitle = "������Ŀ"; 
           
        HSSFWorkbook wb = new HSSFWorkbook();   
           
        //�����б�ͷLIST   
        List<String> fialList = new ArrayList<String>();   
        fialList.add("���");   
        fialList.add("��Ŀ");   
        fialList.add("��Ӧ�Ը�");   
  
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
            String worksheet = "��Ŀ";   
               
            HSSFSheet sheet = wb.createSheet(worksheet);   
  
            ExportExcel exportExcel = new ExportExcel(wb, sheet);   
  
            // ��������ͷ��   
//            exportExcel.createNormalHead(worksheetTitle, number);   
            //�����һ��   
            HSSFRow row1 = sheet.createRow(0);   
            HSSFCell cell1 = null;   
            for(int i = 0; i < fialList.size(); i++) {   
                cell1 = row1.createCell(i);   
                cell1.setCellStyle(cellStyleTitle);   
                cell1.setCellValue(new HSSFRichTextString(fialList.get(i).toString()));   
            }
            
            List<Questions> qslist = new ArrayList<Questions>();
    		qslist = queryAllQuestion();
    		
            Questions qs;
            String id,content,relatedCharacter;
            
            int size = qslist.size();
          
            HSSFRow row = sheet.createRow(2); 
            HSSFCell cell = row.createCell(1);
            for(int i=1;i<=size;i++){
            	qs = (Questions)qslist.get(i-1);
          	
            	id = Integer.toString(qs.getId());
            	content = qs.getContent();
            	relatedCharacter = qs.getRelatedCharacter();
          	
            	row = sheet.createRow(i);   
            	cell = row.createCell(0);   
            	cell.setCellStyle(cellStyle);   
            	cell.setCellValue(new HSSFRichTextString(id));   
            	cell = row.createCell(1);   
            	cell.setCellStyle(cellStyle);
            	cell.setCellValue(new HSSFRichTextString(content));   
            	cell = row.createCell(2);   
            	cell.setCellStyle(cellStyle);
            	cell.setCellValue(new HSSFRichTextString(relatedCharacter));   
            }
        }
		
		return wb;
	}
	
	public List<String> queryResultContent(){
		List<String> contentlist = new ArrayList<String>();
		List<String> contentlist1 = new ArrayList<String>();
		String content = null;
		try {
			connect();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from result_content order by id asc");
			while(rs.next()){
				content = rs.getString("content");
				contentlist.add(content);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			release();
		}
		
		int index=0;
		int size = contentlist.size();
		for(int i=0;i<size;i++){
			content = (String)contentlist.get(i);
			if(content!=null){
		        while((index=content.indexOf("_"))!=-1){
					content = content.substring(0,index)+"\n"+content.substring(index+1);
					//content = content.substring(index+1);
				}
		        contentlist1.add(content);
			}
		}
		return contentlist1;
	}
	
	public String queryResultContentById(int id){
		String content = null;
		try {
			connect();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from result_content where id="+id);
			if(rs.next()){
				content = rs.getString("content");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			release();
		}
		
		int index=0;
		if(content!=null){
			while((index=content.indexOf("_"))!=-1){
				content = content.substring(0,index)+"\n"+content.substring(index+1);
			}
		}
		return content;
	}
	
	public void updateResultContent(String[] content){
		try {
			connect();
			stmt = conn.createStatement();
			int j = 0;
			for(int i=0;i<=11;i++){
				j = i;
				stmt.executeUpdate("update result_content set content='"+content[i]+"' where id='"+j+"' ");
				//System.out.println("id:"+i);
			}
//			System.out.println("update result_content success");
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
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/ninehouse","root","Pwp123456");
//			System.out.println("��ݿ����ӳɹ�");
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
}
