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
 * 生产线型趋势图
 * @说明 
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
		// 在Mysql中使用 select
		// year(accessdate),month(accessdate),day(accessdate),count(*)
		// 其中accessdate 是一个date类型的时间
		// 时间序列对象集合
		TimeSeriesCollection chartTime = new TimeSeriesCollection();
		// 时间序列对象，第1个参数表示时间序列的名字，第2个参数是时间类型，这里为天
		// 该对象用于保存前count天每天的访问次数
		TimeSeries timeSeries = new TimeSeries("日访问", Day.class);
		// 为了演示，直接拼装数据
		// Day的组装格式是day-month-year 访问次数
		timeSeries.add(new Day(1, 1, 2010), 50);
		timeSeries.add(new Day(2, 1, 2010), 47);
		timeSeries.add(new Day(3, 1, 2010), 82);
		timeSeries.add(new Day(4, 1, 2010), 95);
		timeSeries.add(new Day(5, 1, 2010), 104);
		timeSeries.add(new Day(6, 1, 2010), 425);
		chartTime.addSeries(timeSeries);
		XYDataset date = chartTime;
		try {
			// 使用ChartFactory来创建时间序列的图表对象
			JFreeChart chart = ChartFactory.createTimeSeriesChart(
					"网站每天访问统计", // 图形标题
					"日期", // X轴说明
					"访问量", // Y轴说明
					date, // 数据
					true, // 是否创建图例
					true, // 是否生成Tooltips
					false // 是否生产URL链接
			);
			// 设置整个图片的背景色
			chart.setBackgroundPaint(Color.PINK);
			// 设置图片有边框
			chart.setBorderVisible(true);
			// 获得图表区域对象
			XYPlot xyPlot = (XYPlot) chart.getPlot();
			// 设置报表区域的背景色
			xyPlot.setBackgroundPaint(Color.lightGray);
			// 设置横 纵坐标网格颜色
			xyPlot.setDomainGridlinePaint(Color.GREEN);
			xyPlot.setRangeGridlinePaint(Color.GREEN);
			// 设置横、纵坐标交叉线是否显示
			xyPlot.setDomainCrosshairVisible(true);
			xyPlot.setRangeCrosshairVisible(true);
			// 获得数据点（X,Y）的render，负责描绘数据点
			XYItemRenderer xyItemRenderer = xyPlot.getRenderer();
			if (xyItemRenderer instanceof XYLineAndShapeRenderer) {
				XYLineAndShapeRenderer xyLineAndShapeRenderer = (XYLineAndShapeRenderer) xyItemRenderer;
				xyLineAndShapeRenderer.setShapesVisible(true); // 数据点可见
				xyLineAndShapeRenderer.setShapesFilled(true); // 数据点是实心点
				xyLineAndShapeRenderer.setSeriesFillPaint(0, Color.RED); // 数据点填充为蓝色
				xyLineAndShapeRenderer.setUseFillPaint(true);// 将设置好的属性应用到render上
			}
			// 配置以下内容方可解决乱码问题
			// 配置字体
			Font xfont = new Font("宋体", Font.PLAIN, 12);    // X轴
			Font yfont = new Font("宋体", Font.PLAIN, 12);    // Y轴
			Font kfont = new Font("宋体", Font.PLAIN, 12);    // 底部
			Font titleFont = new Font("宋体", Font.BOLD, 25); // 图片标题
			// 图片标题
			chart.setTitle(new TextTitle(chart.getTitle().getText(), titleFont));
			// 底部
			chart.getLegend().setItemFont(kfont);			
			// X 轴
			ValueAxis domainAxis = xyPlot.getDomainAxis();
			domainAxis.setLabelFont(xfont);// 轴标题
			domainAxis.setTickLabelFont(xfont);// 轴数值
			domainAxis.setTickLabelPaint(Color.BLUE); // 字体颜色
			// Y 轴
			ValueAxis rangeAxis = xyPlot.getRangeAxis();
			rangeAxis.setLabelFont(yfont);
			rangeAxis.setLabelPaint(Color.BLUE); // 字体颜色
			rangeAxis.setTickLabelFont(yfont);
			// 定义坐标轴上日期显示的格式
			DateAxis dateAxis = (DateAxis) xyPlot.getDomainAxis();
			// 设置日期格式
			dateAxis.setDateFormatOverride(new SimpleDateFormat("yyyy-MM-dd"));
			// 向客户端输出生成的图片
			ChartUtilities.writeChartAsJPEG(response.getOutputStream(), 1.0f,
					chart, 500, 300, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
