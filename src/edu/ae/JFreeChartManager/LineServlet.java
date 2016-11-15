package edu.ae.JFreeChartManager;

import java.awt.Color;
import java.awt.Font;
import java.io.*;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jfree.chart.*;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
/**
 * ������������ͼ
 * @˵�� 
 * @author cuisuqiang
 * @version 1.0
 * @since
 */
@SuppressWarnings("serial")
public class LineServlet extends HttpServlet {
	@SuppressWarnings("deprecation")
	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		String id = request.getParameter("id");
		System.out.println("chart id:"+id);
		// ��Mysql��ʹ�� select
		// year(accessdate),month(accessdate),day(accessdate),count(*)
		// ����accessdate ��һ��date���͵�ʱ��
		// ʱ�����ж��󼯺�
		TimeSeriesCollection chartTime = new TimeSeriesCollection();
		// ʱ�����ж��󣬵�1��������ʾʱ�����е����֣���2��������ʱ�����ͣ�����Ϊ��
		// �ö������ڱ���ǰcount��ÿ��ķ��ʴ���
		TimeSeries timeSeries = new TimeSeries("�շ���", Day.class);
		// Ϊ����ʾ��ֱ��ƴװ����
		// Day����װ��ʽ��day-month-year ���ʴ���
		timeSeries.add(new Day(1, 1, 2010), 50);
		timeSeries.add(new Day(2, 1, 2010), 47);
		timeSeries.add(new Day(3, 1, 2010), 82);
		timeSeries.add(new Day(4, 1, 2010), 95);
		timeSeries.add(new Day(5, 1, 2010), 104);
		timeSeries.add(new Day(6, 1, 2010), 425);
		chartTime.addSeries(timeSeries);
		XYDataset date = chartTime;
		try {
			// ʹ��ChartFactory������ʱ�����е�ͼ�����
			JFreeChart chart = ChartFactory.createTimeSeriesChart(
					"��վÿ�����ͳ��", // ͼ�α���
					"����", // X��˵��
					"������", // Y��˵��
					date, // ����
					true, // �Ƿ񴴽�ͼ��
					true, // �Ƿ�����Tooltips
					false // �Ƿ�����URL����
			);
			// ��������ͼƬ�ı���ɫ
			chart.setBackgroundPaint(Color.PINK);
			// ����ͼƬ�б߿�
			chart.setBorderVisible(true);
			// ���ͼ���������
			XYPlot xyPlot = (XYPlot) chart.getPlot();
			// ���ñ�������ı���ɫ
			xyPlot.setBackgroundPaint(Color.lightGray);
			// ���ú� ������������ɫ
			xyPlot.setDomainGridlinePaint(Color.GREEN);
			xyPlot.setRangeGridlinePaint(Color.GREEN);
			// ���úᡢ�����꽻�����Ƿ���ʾ
			xyPlot.setDomainCrosshairVisible(true);
			xyPlot.setRangeCrosshairVisible(true);
			// ������ݵ㣨X,Y����render������������ݵ�
			XYItemRenderer xyItemRenderer = xyPlot.getRenderer();
			if (xyItemRenderer instanceof XYLineAndShapeRenderer) {
				XYLineAndShapeRenderer xyLineAndShapeRenderer = (XYLineAndShapeRenderer) xyItemRenderer;
				xyLineAndShapeRenderer.setShapesVisible(true); // ���ݵ�ɼ�
				xyLineAndShapeRenderer.setShapesFilled(true); // ���ݵ���ʵ�ĵ�
				xyLineAndShapeRenderer.setSeriesFillPaint(0, Color.RED); // ���ݵ����Ϊ��ɫ
				xyLineAndShapeRenderer.setUseFillPaint(true);// �����úõ�����Ӧ�õ�render��
			}
			// �����������ݷ��ɽ����������
			// ��������
			Font xfont = new Font("����", Font.PLAIN, 12);    // X��
			Font yfont = new Font("����", Font.PLAIN, 12);    // Y��
			Font kfont = new Font("����", Font.PLAIN, 12);    // �ײ�
			Font titleFont = new Font("����", Font.BOLD, 25); // ͼƬ����
			// ͼƬ����
			chart.setTitle(new TextTitle(chart.getTitle().getText(), titleFont));
			// �ײ�
			chart.getLegend().setItemFont(kfont);			
			// X ��
			ValueAxis domainAxis = xyPlot.getDomainAxis();
			domainAxis.setLabelFont(xfont);// �����
			domainAxis.setTickLabelFont(xfont);// ����ֵ
			domainAxis.setTickLabelPaint(Color.BLUE); // ������ɫ
			// Y ��
			ValueAxis rangeAxis = xyPlot.getRangeAxis();
			rangeAxis.setLabelFont(yfont);
			rangeAxis.setLabelPaint(Color.BLUE); // ������ɫ
			rangeAxis.setTickLabelFont(yfont);
			// ������������������ʾ�ĸ�ʽ
			DateAxis dateAxis = (DateAxis) xyPlot.getDomainAxis();
			// �������ڸ�ʽ
			dateAxis.setDateFormatOverride(new SimpleDateFormat("yyyy-MM-dd"));
			// ��ͻ���������ɵ�ͼƬ
			ChartUtilities.writeChartAsJPEG(response.getOutputStream(), 1.0f,
					chart, 500, 300, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
