package edu.ae.servlet;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import edu.ae.entity.Domain;
import edu.ae.entity.ExportExcel;
import edu.ae.manager.DataExportManager;

public class ExportPresentCols extends HttpServlet {

	private static final long serialVersionUID = 1L;   
	  
    protected void doGet(HttpServletRequest request, HttpServletResponse response)   
            throws ServletException, IOException {   
        this.doPost(request, response);   
    }   
  
    protected void doPost(HttpServletRequest request, HttpServletResponse response)   
            throws ServletException, IOException {   
//        System.out.println("helloworld");   
           
        String fileName = "所有学生信息.xls";   
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
        wb = datama.exportAllUserInfo();
        
        try {   
            bufferedOutPut.flush();   
            wb.write(bufferedOutPut);   
            bufferedOutPut.close();   
        } catch (IOException e) {   
            e.printStackTrace();   
            System.out.println( "Output   is   closed ");   
        } finally { 
        	
        }  
        
        RequestDispatcher rd = request.getRequestDispatcher("/xtmanage/usermanage/userlist.jsp");  
        try {  
            rd.forward(request, response);  
                 return;  
        }catch(Exception e){}
        
//        request.getRequestDispatcher("/xtmanage/usermanage/userlist.jsp").forward(request, response);
    }

}
