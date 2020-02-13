package logic.util;

import java.util.List;

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
	   	 
	public Chart createChart(DiagramTypes type , List<BookBean> books ){
		
		if (type.equals(DiagramTypes.PIE_CHART))
			return createPieChart(books);
		else
			return createBarChart(books);		
	}
	
	
	public Chart createPieChart(List<BookBean> books) {
			
		ObservableList<PieChart.Data> pieChartData =
        FXCollections.observableArrayList(
               new PieChart.Data(books.get(0).getTitle() , 300),//fare con numero vero di copie
			   new PieChart.Data(books.get(1).getTitle() , 120)
			   );
        PieChart pieChart = new PieChart(pieChartData);
        
       	return pieChart;
	}
	
	public Chart createBarChart(List<BookBean> books) {
		
		NumberAxis xAxis = new NumberAxis();
		CategoryAxis yAxis = new CategoryAxis();
		BarChart<Number,String> barChart = new BarChart<Number,String>(xAxis,yAxis);
		barChart.setTitle("Top 5 books");
		xAxis.setLabel("Numero copie vendute");
		xAxis.setTickLabelRotation(90);
		yAxis.setLabel("Book title");		
		barChart.setLegendVisible(false);
		XYChart.Series<Number, String> series1 = new XYChart.Series<>(); 
		series1.getData().add(new XYChart.Data<Number, String>(300, books.get(0).getTitle()));//fare con numero copie
		series1.getData().add(new XYChart.Data<Number, String>(120, books.get(1).getTitle()));
		barChart.getData().add(series1);
		
		return barChart;
	}
	
}
