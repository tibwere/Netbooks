package logic.util;

import java.util.Map;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.Chart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import logic.bean.BookBean;
import logic.util.enumeration.DiagramTypes;

public class DiagramFactory{
	   	 
	public Chart createChart(DiagramTypes type , Map<BookBean, Integer> books ){		
		if (type.equals(DiagramTypes.PIE_CHART))
			return createPieChart(books);
		else
			return createBarChart(books);		
	}
	
	
	public Chart createPieChart(Map<BookBean, Integer> books) {
			
		int index = 0;
		ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
		
		for (Map.Entry<BookBean, Integer> entry : books.entrySet()) {
			if(index == 5) break;
			index++;
			pieChartData.add(new PieChart.Data(entry.getKey().getTitle(), entry.getValue()));
		}
		
		return new PieChart(pieChartData);
		
	}
	
	public Chart createBarChart(Map<BookBean, Integer> books) {
		
		int index = 0;
		
		NumberAxis xAxis = new NumberAxis();
		CategoryAxis yAxis = new CategoryAxis();
		BarChart barChart = new BarChart(xAxis,yAxis);
		barChart.setTitle("Top 5 books");
		xAxis.setLabel("Numero copie vendute");
		xAxis.setTickLabelRotation(90);
		yAxis.setLabel("Book title");		
		barChart.setLegendVisible(false);
		XYChart.Series series = new XYChart.Series();
		
		for (Map.Entry<BookBean, Integer> entry : books.entrySet()) {
				if(index == 5) break;
				index++;
				series.getData().add(new XYChart.Data(entry.getValue(), entry.getKey().getTitle()));
		}
		series.setName("serie");
		barChart.getData().add(series);
		
		
		return barChart;
	}
	
}
