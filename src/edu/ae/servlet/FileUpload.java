package edu.ae.servlet;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import edu.ae.manager.QuestionManager;

public class FileUpload extends HttpServlet {
    
    private ServletContext sc;
    private String savePath;
    private static final long serialVersionUID = 151650843430214502L;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8");
    
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload servletFileUpload = new ServletFileUpload(factory);//ͨ����������һ�������ļ��ϴ���servlet����
        
        try {
            List items = servletFileUpload.parseRequest(request);//����request
            Iterator iterator = items.iterator();
            while (iterator.hasNext()) {
                FileItem item = (FileItem) iterator.next();
                if(item.isFormField()){//���Ĳ����ֶ�
//                    System.out.println("���Ĳ������ƣ�"+item.getFieldName()+",���Ĳ���ֵ��"+item.getString("UTF-8"));
                }else {
                    if(item.getName()!=null && !item.getName().equals("")){//һ���ϴ����ļ�
//                        System.out.println("�ļ������ƣ�"+item.getName());
//                        System.out.println("�ļ��Ĵ�С��"+item.getSize());
//                        System.out.println("�ļ������ͣ�"+item.getContentType());
                        
                        File tempFile = new File(item.getName());//getName�õ����ļ����ư��������ڿͻ��˵�·��
                        File file = new File(sc.getRealPath("/")+savePath,tempFile.getName());
//                        System.out.println("absolutepath:"+file.getAbsolutePath()+"\n"+"path"+file.getPath());
                        item.write(file);//���ϴ����ļ�д�뵽file��
                        
                        /**
                         * ����
                         */
                        QuestionManager qsm = new QuestionManager();
                        qsm.questionImport(file);
                        
                        //request.setAttribute("message", "�ϴ��ļ��ɹ���");
                    }else{
                        request.setAttribute("message", "û��ѡ���ϴ��ļ���");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            //request.setAttribute("message", "�ϴ��ļ�ʧ�ܣ�");
        }
        
        request.getRequestDispatcher("/xtmanage/question/question.jsp").forward(request, response);//ת��������
    }
    
    @Override
    public void init(ServletConfig config) throws ServletException {
        savePath = config.getInitParameter("savePath");
        System.out.println("savePath:"+savePath);
        sc = config.getServletContext();        
    }
}
