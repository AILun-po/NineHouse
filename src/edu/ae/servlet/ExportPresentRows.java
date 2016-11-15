package edu.ae.servlet;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import edu.ae.manager.DataExportManager;

public class ExportPresentRows extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
//		System.out.println("export presentRows");   
		String idString = request.getParameter("idString");
//		System.out.println(idString);

        String fileName = "部分学生信息和答题记录.xls";
        fileName = new String(fileName.getBytes("GBK"),"iso8859-1");   
        response.reset();   
        response.setHeader("Content-Disposition","attachment;filename="+fileName);//指定下载的文件名   
        response.setContentType("application/vnd.ms-excel");   
        response.setHeader("Pragma", "no-cache");   
        response.setHeader("Cache-Control", "no-cache");   
        response.setDateHeader("Expires", 0);   
        OutputStream output = response.getOutputStream();
        BufferedOutputStream bufferedOutPut = new BufferedOutputStream(output);   
           
        DataExportManager datama = new DataExportManager();
        
        HSSFWorkbook wb = new HSSFWorkbook();
        wb = datama.exPresentRows(idString);
        
        try {   
            bufferedOutPut.flush();   
            wb.write(bufferedOutPut);   
            bufferedOutPut.close();   
        } catch (IOException e) {   
            e.printStackTrace();   
            System.out.println( "Output   is   closed ");   
        } finally { 
        	
        }  
        
        RequestDispatcher rd = request.getRequestDispatcher("/xtmanage/usermanage/userlist.jsp?segment="+1+"");  
        try {  
            rd.forward(request, response);  
                 return;  
        }catch(Exception e){} 
	}

}
