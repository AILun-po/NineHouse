package edu.ae.entity;   
  
import java.io.File;   
import java.io.FileNotFoundException;   
import java.io.FileOutputStream;   
import java.io.IOException;   
  
import org.apache.poi.hssf.usermodel.HSSFCell;   
import org.apache.poi.hssf.usermodel.HSSFCellStyle;   
import org.apache.poi.hssf.usermodel.HSSFFont;   
import org.apache.poi.hssf.usermodel.HSSFRichTextString;   
import org.apache.poi.hssf.usermodel.HSSFRow;   
import org.apache.poi.hssf.usermodel.HSSFSheet;   
import org.apache.poi.hssf.usermodel.HSSFWorkbook;   
import org.apache.poi.hssf.util.HSSFColor;   
import org.apache.poi.hssf.util.Region;   
  
/**  
* EXCEL��������.  
*  
* @author sun  
* @version   
*/    
public class ExportExcel {     
       
    private HSSFWorkbook wb = null;   
    private HSSFSheet sheet = null;    
       
    /**  
    * @param wb  
    * @param sheet  
    */  
    public ExportExcel(HSSFWorkbook wb, HSSFSheet sheet) {   
        //super();   
        this.wb = wb;   
        this.sheet = sheet;   
    }   
       
    /**  
    * ����ͨ��EXCELͷ��  
    *  
    * @param headString ͷ����ʾ���ַ�  
    * @param colSum �ñ��������  
    */  
    public void createNormalHead(String headString, int colSum) {   
  
        HSSFRow row = sheet.createRow(0);   
       
        // ���õ�һ��   
        HSSFCell cell = row.createCell(0);   
        //row.setHeight((short) 1000);   
       
        // ���嵥Ԫ��Ϊ�ַ�������   
        cell.setCellType(HSSFCell.ENCODING_UTF_16);// ���Ĵ���   
        cell.setCellValue(new HSSFRichTextString(headString));   
       
        // ָ���ϲ�����   
        /**  
         * public Region(int rowFrom,  
         * short colFrom,  
         * int rowTo,  
         * short colTo)  
         */  
        sheet.addMergedRegion(new Region(0, (short) 0, 0, (short) colSum));   
           
        //���嵥Ԫ���ʽ����ӵ�Ԫ�����ʽ������ӵ�������   
        HSSFCellStyle cellStyle = wb.createCellStyle();   
        //���õ�Ԫ��ˮƽ��������   
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // ָ����Ԫ����ж���   
        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// ָ����Ԫ��ֱ���ж���   
        cellStyle.setWrapText(true);// ָ����Ԫ���Զ�����   
       
        // ���õ�Ԫ������   
        HSSFFont font = wb.createFont();   
        //font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);   
        //font.setFontName("����");   
        //font.setFontHeight((short) 600);   
        //cellStyle.setFont(font);     
        cell.setCellStyle(cellStyle);   
    }   
       
    /**  
    * ����ͨ�ñ���ڶ���  
    *  
    * @param params ͳ����������  
    * @param colSum ��Ҫ�ϲ�����������  
    */  
    public void createNormalTwoRow(String[] params, int colSum) {   
        //�����ڶ���   
        HSSFRow row1 = sheet.createRow(1);   
           
        row1.setHeight((short) 400);   
       
        HSSFCell cell2 = row1.createCell(0);   
       
        cell2.setCellType(HSSFCell.ENCODING_UTF_16);   
        cell2.setCellValue(new HSSFRichTextString("ʱ�䣺" + params[0] + "��" + params[1]));   
       
        // ָ���ϲ�����   
        /**  
         *  public Region(int rowFrom,  
            short colFrom,  
            int rowTo,  
            short colTo)  
         */  
        sheet.addMergedRegion(new Region(1, (short) 0, 1, (short) colSum));   
       
        HSSFCellStyle cellStyle = wb.createCellStyle();   
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // ָ����Ԫ����ж���   
        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// ָ����Ԫ��ֱ���ж���   
        cellStyle.setWrapText(true);// ָ����Ԫ���Զ�����   
       
        // ���õ�Ԫ������   
        HSSFFont font = wb.createFont();   
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);   
        font.setFontName("����");   
        font.setFontHeight((short) 250);   
        cellStyle.setFont(font);   
       
        cell2.setCellStyle(cellStyle);   
    }   
    /**  
    * ���ñ������  
    *  
    * @param columHeader �����ַ�������  
    */  
    public void createColumHeader(String[] columHeader) {   
  
        // ������ͷ  �ڵ�����   
        HSSFRow row2 = sheet.createRow(2);   
       
        // ָ���и�   
        row2.setHeight((short) 600);   
       
        HSSFCellStyle cellStyle = wb.createCellStyle();   
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // ָ����Ԫ����ж���   
        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// ָ����Ԫ��ֱ���ж���   
        cellStyle.setWrapText(true);// ָ����Ԫ���Զ�����   
       
        // ��Ԫ������   
        HSSFFont font = wb.createFont();   
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);   
        font.setFontName("����");   
        font.setFontHeight((short) 250);   
        cellStyle.setFont(font);   
  
        /*cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); // ���õ��޸�ı߿�Ϊ����  
        cellStyle.setBottomBorderColor(HSSFColor.BLACK.index); // ���õ�Ԫ��ı߿���ɫ��  
        cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);  
        cellStyle.setLeftBorderColor(HSSFColor.BLACK.index);  
        cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);  
        cellStyle.setRightBorderColor(HSSFColor.BLACK.index);  
        cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);  
        cellStyle.setTopBorderColor(HSSFColor.BLACK.index);*/  
       
        // ���õ�Ԫ�񱳾�ɫ   
        cellStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);   
        cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);   
       
        HSSFCell cell3 = null;   
       
        for (int i = 0; i < columHeader.length; i++) {   
        cell3 = row2.createCell(i);   
        cell3.setCellType(HSSFCell.ENCODING_UTF_16);   
        cell3.setCellStyle(cellStyle);   
        cell3.setCellValue(new HSSFRichTextString(columHeader[i]));   
        }   
    }   
       
    /**  
    * �������ݵ�Ԫ��  
    *  
    * @param wb HSSFWorkbook  
    * @param row HSSFRow  
    * @param col short�͵�������  
    * @param align ���뷽ʽ  
    * @param val ��ֵ  
    */  
    public void cteateCell(HSSFWorkbook wb, HSSFRow row, int col,short align, String val) {   
        HSSFCell cell = row.createCell(col);   
        cell.setCellType(HSSFCell.ENCODING_UTF_16);   
        cell.setCellValue(new HSSFRichTextString(val));   
        HSSFCellStyle cellstyle = wb.createCellStyle();   
        cellstyle.setAlignment(align);   
        cell.setCellStyle(cellstyle);   
    }    
    /**  
    * �����ϼ���  
    * @param colSum ��Ҫ�ϲ�����������  
    * @param cellValue  
    */  
    public void createLastSumRow(int colSum, String[] cellValue) {   
  
        HSSFCellStyle cellStyle = wb.createCellStyle();   
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // ָ����Ԫ����ж���   
        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// ָ����Ԫ��ֱ���ж���   
        cellStyle.setWrapText(true);// ָ����Ԫ���Զ�����   
       
        // ��Ԫ������   
        HSSFFont font = wb.createFont();   
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);   
        font.setFontName("����");   
        font.setFontHeight((short) 250);   
        cellStyle.setFont(font);   
        //��ȡ���������һ��   
        HSSFRow lastRow = sheet.createRow((short) (sheet.getLastRowNum() + 1));   
        HSSFCell sumCell = lastRow.createCell(0);   
       
        sumCell.setCellValue(new HSSFRichTextString("�ϼ�"));   
        sumCell.setCellStyle(cellStyle);   
        //�ϲ� ���һ�еĵ�����-���һ�еĵ�һ��   
        sheet.addMergedRegion(new Region(sheet.getLastRowNum(), (short) 0,sheet.getLastRowNum(), (short) colSum));// ָ���ϲ�����   
       
        for (int i = 2; i < (cellValue.length + 2); i++) {   
            //�������һ�еĵ�����   
            sumCell = lastRow.createCell(i);   
            sumCell.setCellStyle(cellStyle);   
            //�������� ��0��ʼ��   
            sumCell.setCellValue(new HSSFRichTextString(cellValue[i-2]));   
        }   
    }   
    /**  
    * ����EXCEL�ļ�  
    *  
    * @param fileName �ļ���  
    */  
    public void outputExcel(String fileName) {   
        FileOutputStream fos = null;   
        try {   
            fos = new FileOutputStream(new File(fileName));   
            wb.write(fos);   
            fos.close();   
        } catch (FileNotFoundException e) {   
            e.printStackTrace();   
        } catch (IOException e) {   
            e.printStackTrace();   
        }   
    }   
       
       
    //*****************************************************   
    // set && get   
    //*****************************************************   
  
    /**   
    * @return the sheet   
    */   
    public HSSFSheet getSheet() {   
        return sheet;   
    }   
  
    /**  
    * @param sheet the sheet to set  
    */  
    public void setSheet(HSSFSheet sheet) {   
        this.sheet = sheet;   
    }   
  
    /**  
    * @return the wb  
    */  
    public HSSFWorkbook getWb() {   
        return wb;   
    }   
  
    /**  
    * @param wb the wb to set  
    */  
    public void setWb(HSSFWorkbook wb) {   
        this.wb = wb;   
    }   
}  