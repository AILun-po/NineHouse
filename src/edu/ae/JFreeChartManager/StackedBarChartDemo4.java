package edu.ae.JFreeChartManager;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Paint;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.LegendItemCollection;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.SubCategoryAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.GroupedStackedBarRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.KeyToGroupMap;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.GradientPaintTransformType;
import org.jfree.ui.StandardGradientPaintTransformer;

import edu.ae.manager.UserManager;

public class StackedBarChartDemo4 extends HttpServlet {
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		
		String sid = request.getParameter("id");
		//System.out.println("sid"+sid);
		int id = 0;
		if(sid!=null){
			
			id = Integer.parseInt(sid);
			
			UserManager um = new UserManager();
			String analysischaracter = um.analysisCharacterById(id);
			String[] chartdata = analysischaracter.split(",");
			//System.out.println(chartdata[0]+"  "+chartdata[26]);
			int[] character = new int[27];
			for(int i=0;i<27;){
				character[i] = Integer.parseInt(chartdata[i]);
				character[i+2] = Integer.parseInt(chartdata[i+1]);
				character[i+1] = Integer.parseInt(chartdata[i+2]);
				i = i+3;
			}
		
			DefaultCategoryDataset result = new DefaultCategoryDataset();
	
	        result.addValue(character[0], "Product 1 (��)", "һ��");
	        result.addValue(character[3], "Product 1 (��)", "����");
	        result.addValue(character[6], "Product 1 (��)", "����");
	        result.addValue(character[9], "Product 1 (��)", "����");
	        result.addValue(character[12], "Product 1 (��)", "����");
	        result.addValue(character[15], "Product 1 (��)", "����");
	        result.addValue(character[18], "Product 1 (��)", "����");
	        result.addValue(character[21], "Product 1 (��)", "����");
	        result.addValue(character[24], "Product 1 (��)", "����");
	        
	        result.addValue(character[1], "Product 1 (��ȷ��)", "һ��");
	        result.addValue(character[4], "Product 1 (��ȷ��)", "����");
	        result.addValue(character[7], "Product 1 (��ȷ��)", "����");
	        result.addValue(character[10], "Product 1 (��ȷ��)", "����");
	        result.addValue(character[13], "Product 1 (��ȷ��)", "����");
	        result.addValue(character[16], "Product 1 (��ȷ��)", "����");
	        result.addValue(character[19], "Product 1 (��ȷ��)", "����");
	        result.addValue(character[22], "Product 1 (��ȷ��)", "����");
	        result.addValue(character[25], "Product 1 (��ȷ��)", "����");
	        
	        result.addValue(character[2], "Product 1 (��)", "һ��");
	        result.addValue(character[5], "Product 1 (��)", "����");
	        result.addValue(character[8], "Product 1 (��)", "����");
	        result.addValue(character[11], "Product 1 (��)", "����");
	        result.addValue(character[14], "Product 1 (��)", "����");
	        result.addValue(character[17], "Product 1 (��)", "����");
	        result.addValue(character[20], "Product 1 (��)", "����");
	        result.addValue(character[23], "Product 1 (��)", "����");
	        result.addValue(character[26], "Product 1 (��)", "����");
	
	        
//	        final JFreeChart chart = ChartFactory.createStackedBarChart(
//	                "�˸����",  // chart title
//	                "Category",                  // domain axis label
//	                "����",                     // range axis label
//	                result,                     // data
//	                PlotOrientation.VERTICAL,    // the plot orientation
//	                true,                        // legend
//	                true,                        // tooltips
//	                false	                    // urls
//	            );
	        final JFreeChart chart = ChartFactory.createStackedBarChart(
	                null,  // chart title
	                null,                  // domain axis label
	                null,                     // range axis label
	                result,                     // data
	                PlotOrientation.VERTICAL,    // the plot orientation
	                true,                        // legend
	                true,                        // tooltips
	                false	                    // urls
	            );
	            
	            GroupedStackedBarRenderer renderer = new GroupedStackedBarRenderer();
	            KeyToGroupMap map = new KeyToGroupMap("G1");
	            map.mapKeyToGroup("Product 1 (��)", "G1");
	            map.mapKeyToGroup("Product 1 (��ȷ��)", "G1");
	            map.mapKeyToGroup("Product 1 (��)", "G1");
	            renderer.setSeriesToGroupMap(map); 
	            
	            renderer.setItemMargin(0.0);
	            Paint p1 = new GradientPaint(
	                //0.0f, 0.0f, new Color(0x22, 0x22, 0xFF), 0.0f, 0.0f, new Color(0x88, 0x88, 0xFF)
	            	0.0f, 0.0f, new Color(0xed, 0x7d, 0x71), 0.0f, 0.0f, new Color(0xed, 0x7d, 0x71)
	            );
	            //renderer.setSeriesPaint(0, p1);
	            renderer.setSeriesPaint(0, new Color(0x04, 0xb4, 0x13));
	             
	            Paint p2 = new GradientPaint(
	                0.0f, 0.0f, new Color(0x22, 0xFF, 0x22), 0.0f, 0.0f, new Color(0x88, 0xFF, 0x88)
//	            	0.0f, 0.0f, new Color(0x8b, 0x9b, 0xe5), 0.0f, 0.0f, new Color(0x8b, 0x9b, 0xe5)
	            );
	            //renderer.setSeriesPaint(1, p2);
	            renderer.setSeriesPaint(1, new Color(0xfe, 0xd7, 0x00));
	            
	            Paint p3 = new GradientPaint(
	                0.0f, 0.0f, new Color(0xFF, 0x22, 0x22), 0.0f, 0.0f, new Color(0xFF, 0x88, 0x88)
//	            	0.0f, 0.0f, new Color(0xa5, 0xa5, 0xa5), 0.0f, 0.0f, new Color(0xa5, 0xa5, 0xa5)
	            );
	            //renderer.setSeriesPaint(2, p3);
	            renderer.setSeriesPaint(2, new Color(0xdc, 0x14, 0x3b));
	                
	            renderer.setGradientPaintTransformer(
	                new StandardGradientPaintTransformer(GradientPaintTransformType.HORIZONTAL)
	            );
	            
	            SubCategoryAxis domainAxis = new SubCategoryAxis("�Ը�����");
	            domainAxis.setCategoryMargin(0.3);
	           
	            //domainAxis.addSubCategory("Product 1");
	            
	            CategoryPlot plot = (CategoryPlot) chart.getPlot();
	            plot.setDomainAxis(domainAxis);
	            //plot.setDomainAxisLocation(AxisLocation.TOP_OR_RIGHT);
	            plot.setRenderer(renderer);
	            plot.setFixedLegendItems(new LegendItemCollection());
	            
	            // ����ͼƬ�б߿�
				//chart.setBorderVisible(true);
				//Font kfont = new Font("����", Font.BOLD, 14);    // �ײ�
				//Font titleFont = new Font("����", Font.BOLD, 25); // ͼƬ����
				// ͼƬ����
				//chart.setTitle(new TextTitle(chart.getTitle().getText(), titleFont));
	            //chart.getLegend().setItemFont(new Font("����", Font.PLAIN, 12));
	            // �ײ�
				//chart.getLegend().setItemFont(kfont);
				
				chart.setBackgroundPaint(new Color(255,255,255));
				
				
				// �õ�������������������
				CategoryPlot categoryplot = (CategoryPlot) chart.getPlot();
				NumberAxis numberaxis = (NumberAxis) categoryplot.getRangeAxis();
				numberaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
				/*------����X�������ϵ�����-----------*/
				//domainAxis.setTickLabelFont(new Font("sans-serif", Font.BOLD, 11));
				/*------����X��ı�������------------*/
				//domainAxis.setLabelFont(new Font("����", Font.PLAIN, 12));
				/*------����Y�������ϵ�����-----------*/
				numberaxis.setTickLabelFont(new Font("Consoal", Font.PLAIN, 14));
				/*------����Y��ı�������------------*/
				//numberaxis.setLabelFont(new Font("����", Font.PLAIN, 12));
				//categoryplot.setBackgroundPaint(new Color(238,244,234));
	            
				// ����ͼƬ�����
				ChartUtilities.writeChartAsJPEG(response.getOutputStream(), 1.0f,chart, 600, 400, null);
		}
	}

}
