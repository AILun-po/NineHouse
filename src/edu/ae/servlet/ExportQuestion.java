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

import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import edu.ae.manager.DataExportManager;
import edu.ae.manager.QuestionManager;

@SuppressWarnings("serial")
public class ExportQuestion extends HttpServlet {

	
	private static final long serialVersionUID = 1L;   
	  
    protected void doGet(HttpServletRequest request, HttpServletResponse response)   
            throws ServletException, IOException {   
        this.doPost(request, response);   
    }   
  
    protected void doPost(HttpServletRequest request, HttpServletResponse response)   
            throws ServletException, IOException {   
//		System.out.println("export question");   

        String fileName = "所有题目.xls";
        fileName = new String(fileName.getBytes("GBK"),"iso8859-1");   
        response.reset();   
        response.setHeader("Content-Disposition","attachment;filename="+fileName);//指定下载的文件名   
        response.setContentType("application/vnd.ms-excel");   
        response.setHeader("Pragma", "no-cache");   
        response.setHeader("Cache-Control", "no-cache");   
        response.setDateHeader("Expires", 0);   
        OutputStream output = response.getOutputStream();
        BufferedOutputStream bufferedOutPut = new BufferedOutputStream(output);   
           
        QuestionManager qsm = new QuestionManager();
        HSSFWorkbook wb = new HSSFWorkbook();
        wb = qsm.questionExport();
        try {   
            bufferedOutPut.flush();   
            wb.write(bufferedOutPut);   
            bufferedOutPut.close();   
        } catch (IOException e) {   
            e.printStackTrace();   
            System.out.println( "Output   is   closed ");   
        } finally { 
        	
        }  
        
        RequestDispatcher rd = request.getRequestDispatcher("/xtmanage/question/question.jsp");  
        try {  
            rd.forward(request, response);  
                 return;  
        }catch(Exception e){}   
    } 

}
