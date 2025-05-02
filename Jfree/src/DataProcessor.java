import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

@SuppressWarnings("unchecked")
public class DataProcessor {
    private ArrayList<Double[]> data = new ArrayList<>(); // List to hold (x, y) data points
    private Random random = new Random();                 // Random generator for adding noise
    private int windowValue = 5;                          // Window size for smoothing
    // Load data from a CSV file
    public void loadData(String inputFile) throws IOException {
        data.clear(); // Clear old data
        Scanner fileScanner = new Scanner(new File(inputFile)); // Open input file

        if (fileScanner.hasNextLine()) fileScanner.nextLine(); // Skip header line
        while (fileScanner.hasNextLine()) {
            String[] line = fileScanner.nextLine().split(","); // Split line by comma
            data.add(new Double[]{Double.parseDouble(line[0]), Double.parseDouble(line[1])}); // Add (x, y) to list
        }
        fileScanner.close(); // Close file
    }
    // Add random noise to y-values within a given range
    public void saltData(double range) {
        for (Double[] point : data) {
            double salt = (random.nextDouble() * 2 - 1) * range; // Random value between -range and +range
            point[1] += salt; // Add noise to y
        }
    }

    // Apply moving average smoothing to the y-values
    public void smoothData() {
        ArrayList<Double[]> smoothedData = new ArrayList<>(); // New list to store smoothed data
        for (int i = 0; i < data.size(); i++) {
            double sum = 0;
            int count = 0;
            // Calculate average of y-values in window around index i
            for (int j = Math.max(0, i - windowValue); j <= Math.min(data.size() - 1, i + windowValue); j++) {
                sum += data.get(j)[1];
                count++;
            }
            smoothedData.add(new Double[]{data.get(i)[0], sum / count}); // Keep original x and new smoothed y
        }
        data = smoothedData; // Replace original data with smoothed data
    }

    // Save current data (x, y) to a CSV file
    public void saveData(String outputFile) throws IOException {
        FileWriter writer = new FileWriter(outputFile); // Create file writer
        writer.append("x,y\n"); // Write header
        for (Double[] point : data) {
            writer.append(point[0] + "," + point[1] + "\n"); // Write each (x, y) pair
        }
        writer.close(); // Close file
    }
    // Plot the data using JFreeChart
    public void plotData(String title) {
        XYSeriesCollection dataset = new XYSeriesCollection(); // Create dataset for chart
        XYSeries series = new XYSeries(title); // Create a new data series with title
        for (Double[] point : data) {
            series.add(point[0], point[1]); // Add each (x, y) to series
        }
        dataset.addSeries(series); // Add series to dataset
        // Create a line chart with labels
        JFreeChart chart = ChartFactory.createXYLineChart(
                title,              // Chart title
                "X",                // X-axis label
                "Y",                // Y-axis label
                dataset,            // Data
                PlotOrientation.VERTICAL, // Orientation
                true,               // Include legend
                true,               // Tooltips
                false               // No URLs
        );
        // Create a window to display the chart
        JFrame frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600); // Set window size
        ChartPanel chartPanel = new ChartPanel(chart); // Create panel to hold chart
        frame.setContentPane(chartPanel);              // Add chart panel to window
        frame.setLocationRelativeTo(null); // Center window on screen
        frame.setVisible(true);            // Show window
    }
}
