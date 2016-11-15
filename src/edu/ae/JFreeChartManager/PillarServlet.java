package edu.ae.JFreeChartManager;

import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.KeyToGroupMap;
import org.jfree.data.category.DefaultCategoryDataset;

import edu.ae.manager.UserManager;

/**
 * ������״ͼ
 * @˵�� 
 * @author cuisuqiang
 * @version 1.0
 * @since
 */
@SuppressWarnings("serial")
public class PillarServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		// ʹ����ͨ���ݼ�
		
		String sid = request.getParameter("id");
		int id = 0;
		if(sid!=null){
			
			id = Integer.parseInt(sid);
			
			UserManager um = new UserManager();
			String analysischaracter = um.analysisCharacterById(id);
			String[] chartdata = analysischaracter.split(",");
			System.out.println(chartdata[0]+"  "+chartdata[26]);
			int[] character = new int[27];
			for(int i=0;i<27;){
				character[i] = Integer.parseInt(chartdata[i]);
				character[i+1] = Integer.parseInt(chartdata[i+1]);
				character[i+2] = Integer.parseInt(chartdata[i+2]);
				i = i+3;
			}
			
			DefaultCategoryDataset chartDate = new DefaultCategoryDataset();
			// ���Ӳ������ݣ���һ�������Ƿ����������һ����ʱ�䣬�м�����ʾ�ò�����
			chartDate.addValue(character[0], "���� 1 (��)", "һ�� 03");
			chartDate.addValue(character[3], "���� 2 (��)", "���� 03");
			chartDate.addValue(character[6], "���� 3 (��)", "���� 03");
			chartDate.addValue(character[9], "���� 4 (��)", "���� 03");
			chartDate.addValue(character[12], "���� 5 (��)", "���� 03");
			chartDate.addValue(character[15], "���� 6 (��)", "���� 03");
			chartDate.addValue(character[18], "���� 7 (��)", "���� 03");
			chartDate.addValue(character[21], "���� 8 (��)", "���� 03");
			chartDate.addValue(character[24], "���� 9 (��)", "���� 03");
			
			chartDate.addValue(character[1], "���� 1 (��)", "һ�� 03");
			chartDate.addValue(character[4], "���� 2 (��)", "���� 03");
			chartDate.addValue(character[7], "���� 3 (��)", "���� 03");
			chartDate.addValue(character[10], "���� 4 (��)", "���� 03");
			chartDate.addValue(character[13], "���� 5 (��)", "���� 03");
			chartDate.addValue(character[16], "���� 6 (��)", "���� 03");
			chartDate.addValue(character[19], "���� 7 (��)", "���� 03");
			chartDate.addValue(character[22], "���� 8 (��)", "���� 03");
			chartDate.addValue(character[25], "���� 9 (��)", "���� 03");
			
			chartDate.addValue(character[2], "���� 1 (��ȷ��)", "һ�� 03");
			chartDate.addValue(character[5], "���� 2 (��ȷ��)", "���� 03");
			chartDate.addValue(character[8], "���� 3 (��ȷ��)", "���� 03");
			chartDate.addValue(character[11], "���� 4 (��ȷ��)", "���� 03");
			chartDate.addValue(character[14], "���� 5 (��ȷ��)", "���� 03");
			chartDate.addValue(character[17], "���� 6 (��ȷ��)", "���� 03");
			chartDate.addValue(character[20], "���� 7 (��ȷ��)", "���� 03");
			chartDate.addValue(character[23], "���� 8 (��ȷ��)", "���� 03");
			chartDate.addValue(character[26], "���� 9 (��ȷ��)", "���� 03");
			
			try {
				// �����ݿ��л�����ݼ�
				DefaultCategoryDataset data = chartDate;
				
				// ʹ��ChartFactory����3D��״ͼ������ʹ��3D��ֱ��ʹ��createBarChart
				JFreeChart chart = ChartFactory.createBarChart(
						"�����˸����", // ͼ�����
						"����", // Ŀ¼�����ʾ��ǩ
						"����", // ��ֵ�����ʾ��ǩ
						data, // ���ݼ�
						PlotOrientation.VERTICAL, // ͼ���򣬴˴�Ϊ��ֱ����
						// PlotOrientation.HORIZONTAL, //ͼ���򣬴˴�Ϊˮƽ����
						true, // �Ƿ���ʾͼ��
						true, // �Ƿ����ɹ���
						false // �Ƿ�����URL����
						);
				
				// ��������ͼƬ�ı���ɫ
				//chart.setBackgroundPaint(Color.LIGHT_GRAY);
				// ����ͼƬ�б߿�
				chart.setBorderVisible(true);
				Font kfont = new Font("����", Font.PLAIN, 12);    // �ײ�
				Font titleFont = new Font("����", Font.BOLD, 25); // ͼƬ����
				// ͼƬ����
				chart.setTitle(new TextTitle(chart.getTitle().getText(), titleFont));
				// �ײ�
				chart.getLegend().setItemFont(kfont);
				// �õ�������������������
				CategoryPlot categoryplot = (CategoryPlot) chart.getPlot();
				
				//����������Χ��ɫ
				categoryplot.setBackgroundPaint(Color.white);
				
				categoryplot.setDomainGridlinesVisible(true);
				categoryplot.setRangeCrosshairVisible(true);
				categoryplot.setRangeCrosshairPaint(Color.blue);
				
				NumberAxis numberaxis = (NumberAxis) categoryplot.getRangeAxis();
				numberaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
				
				BarRenderer barrenderer = (BarRenderer) categoryplot.getRenderer();
				//
				barrenderer.setBaseFillPaint(Color.red);
				barrenderer.setBaseItemLabelFont(new Font("����", Font.PLAIN, 12));
				barrenderer.setSeriesItemLabelFont(1, new Font("����", Font.PLAIN, 12));
				
				CategoryAxis domainAxis = categoryplot.getDomainAxis();			
				/*------����X�������ϵ�����-----------*/
				domainAxis.setTickLabelFont(new Font("sans-serif", Font.PLAIN, 11));
				/*------����X��ı�������------------*/
				domainAxis.setLabelFont(new Font("����", Font.PLAIN, 12));
				/*------����Y�������ϵ�����-----------*/
				numberaxis.setTickLabelFont(new Font("sans-serif", Font.PLAIN, 12));
				/*------����Y��ı�������------------*/
				numberaxis.setLabelFont(new Font("����", Font.PLAIN, 12));
				/*------���������˵ײ��������������-----------*/
				chart.getLegend().setItemFont(new Font("����", Font.PLAIN, 12));
				// ����ͼƬ�����
				ChartUtilities.writeChartAsJPEG(response.getOutputStream(), 1.0f,
						chart, 500, 300, null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}