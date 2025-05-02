import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FunctionPlotter {
    public static void main(String[] args) {
        // Create data points for y = x^2 + 2
        ArrayList<Double[]> data = new ArrayList<>();
        XYSeries series = new XYSeries("y = x² + 2");
        
        for (double x = -10; x <= 10; x += 1.0) {
            double y = Math.pow(x, 2) + 2;
            data.add(new Double[]{x, y});
            series.add(x, y);
        }

        // Export to CSV
        try (FileWriter writer = new FileWriter("function_data.csv")) {
            writer.append("x,y\n");
            for (Double[] point : data) {
                writer.append(point[0] + "," + point[1] + "\n");
            }
            System.out.println("Data exported to function_data.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Create and display the plot
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series);

        JFreeChart chart = ChartFactory.createXYLineChart(
                "Quadratic Function Plot",
                "X",
                "Y",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        // Create and set up the window
        JFrame frame = new JFrame("Function Plotter");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        
        // Add the chart to the window
        ChartPanel chartPanel = new ChartPanel(chart);
        frame.setContentPane(chartPanel);
        
        // Display the window
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
