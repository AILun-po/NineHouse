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
 * 生产柱状图
 * @说明 
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
		// 使用普通数据集
		
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
			// 增加测试数据，第一个参数是访问量，最后一个是时间，中间是显示用不考虑
			chartDate.addValue(character[0], "分数 1 (是)", "一型 03");
			chartDate.addValue(character[3], "分数 2 (是)", "二型 03");
			chartDate.addValue(character[6], "分数 3 (是)", "三型 03");
			chartDate.addValue(character[9], "分数 4 (是)", "四型 03");
			chartDate.addValue(character[12], "分数 5 (是)", "五型 03");
			chartDate.addValue(character[15], "分数 6 (是)", "六型 03");
			chartDate.addValue(character[18], "分数 7 (是)", "七型 03");
			chartDate.addValue(character[21], "分数 8 (是)", "八型 03");
			chartDate.addValue(character[24], "分数 9 (是)", "九型 03");
			
			chartDate.addValue(character[1], "分数 1 (否)", "一型 03");
			chartDate.addValue(character[4], "分数 2 (否)", "二型 03");
			chartDate.addValue(character[7], "分数 3 (否)", "三型 03");
			chartDate.addValue(character[10], "分数 4 (否)", "四型 03");
			chartDate.addValue(character[13], "分数 5 (否)", "五型 03");
			chartDate.addValue(character[16], "分数 6 (否)", "六型 03");
			chartDate.addValue(character[19], "分数 7 (否)", "七型 03");
			chartDate.addValue(character[22], "分数 8 (否)", "八型 03");
			chartDate.addValue(character[25], "分数 9 (否)", "九型 03");
			
			chartDate.addValue(character[2], "分数 1 (不确定)", "一型 03");
			chartDate.addValue(character[5], "分数 2 (不确定)", "二型 03");
			chartDate.addValue(character[8], "分数 3 (不确定)", "三型 03");
			chartDate.addValue(character[11], "分数 4 (不确定)", "四型 03");
			chartDate.addValue(character[14], "分数 5 (不确定)", "五型 03");
			chartDate.addValue(character[17], "分数 6 (不确定)", "六型 03");
			chartDate.addValue(character[20], "分数 7 (不确定)", "七型 03");
			chartDate.addValue(character[23], "分数 8 (不确定)", "八型 03");
			chartDate.addValue(character[26], "分数 9 (不确定)", "九型 03");
			
			try {
				// 从数据库中获得数据集
				DefaultCategoryDataset data = chartDate;
				
				// 使用ChartFactory创建3D柱状图，不想使用3D，直接使用createBarChart
				JFreeChart chart = ChartFactory.createBarChart(
						"九型人格分析", // 图表标题
						"类型", // 目录轴的显示标签
						"分数", // 数值轴的显示标签
						data, // 数据集
						PlotOrientation.VERTICAL, // 图表方向，此处为垂直方向
						// PlotOrientation.HORIZONTAL, //图表方向，此处为水平方向
						true, // 是否显示图例
						true, // 是否生成工具
						false // 是否生成URL链接
						);
				
				// 设置整个图片的背景色
				//chart.setBackgroundPaint(Color.LIGHT_GRAY);
				// 设置图片有边框
				chart.setBorderVisible(true);
				Font kfont = new Font("宋体", Font.PLAIN, 12);    // 底部
				Font titleFont = new Font("宋体", Font.BOLD, 25); // 图片标题
				// 图片标题
				chart.setTitle(new TextTitle(chart.getTitle().getText(), titleFont));
				// 底部
				chart.getLegend().setItemFont(kfont);
				// 得到坐标设置字体解决乱码
				CategoryPlot categoryplot = (CategoryPlot) chart.getPlot();
				
				//设置柱子周围颜色
				categoryplot.setBackgroundPaint(Color.white);
				
				categoryplot.setDomainGridlinesVisible(true);
				categoryplot.setRangeCrosshairVisible(true);
				categoryplot.setRangeCrosshairPaint(Color.blue);
				
				NumberAxis numberaxis = (NumberAxis) categoryplot.getRangeAxis();
				numberaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
				
				BarRenderer barrenderer = (BarRenderer) categoryplot.getRenderer();
				//
				barrenderer.setBaseFillPaint(Color.red);
				barrenderer.setBaseItemLabelFont(new Font("宋体", Font.PLAIN, 12));
				barrenderer.setSeriesItemLabelFont(1, new Font("宋体", Font.PLAIN, 12));
				
				CategoryAxis domainAxis = categoryplot.getDomainAxis();			
				/*------设置X轴坐标上的文字-----------*/
				domainAxis.setTickLabelFont(new Font("sans-serif", Font.PLAIN, 11));
				/*------设置X轴的标题文字------------*/
				domainAxis.setLabelFont(new Font("宋体", Font.PLAIN, 12));
				/*------设置Y轴坐标上的文字-----------*/
				numberaxis.setTickLabelFont(new Font("sans-serif", Font.PLAIN, 12));
				/*------设置Y轴的标题文字------------*/
				numberaxis.setLabelFont(new Font("宋体", Font.PLAIN, 12));
				/*------这句代码解决了底部汉字乱码的问题-----------*/
				chart.getLegend().setItemFont(new Font("宋体", Font.PLAIN, 12));
				// 生成图片并输出
				ChartUtilities.writeChartAsJPEG(response.getOutputStream(), 1.0f,
						chart, 500, 300, null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}