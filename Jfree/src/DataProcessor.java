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
    private ArrayList<Double[]> data = new ArrayList<>();
    private Random random = new Random();
    private int windowValue = 5;

    public void loadData(String inputFile) throws IOException {
        data.clear();
        Scanner fileScanner = new Scanner(new File(inputFile));
        if (fileScanner.hasNextLine()) fileScanner.nextLine(); // Skip header

        while (fileScanner.hasNextLine()) {
            String[] line = fileScanner.nextLine().split(",");
            data.add(new Double[]{Double.parseDouble(line[0]), Double.parseDouble(line[1])});
        }
        fileScanner.close();
    }

    public void saltData(double range) {
        for (Double[] point : data) {
            double salt = (random.nextDouble() * 2 - 1) * range;
            point[1] += salt;
        }
    }

    public void smoothData() {
        ArrayList<Double[]> smoothedData = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            double sum = 0;
            int count = 0;
            for (int j = Math.max(0, i - windowValue); j <= Math.min(data.size() - 1, i + windowValue); j++) {
                sum += data.get(j)[1];
                count++;
            }
            smoothedData.add(new Double[]{data.get(i)[0], sum / count});
        }
        data = smoothedData;
    }

    public void saveData(String outputFile) throws IOException {
        FileWriter writer = new FileWriter(outputFile);
        writer.append("x,y\n");
        for (Double[] point : data) {
            writer.append(point[0] + "," + point[1] + "\n");
        }
        writer.close();
    }

    public void plotData(String title) {
        XYSeriesCollection dataset = new XYSeriesCollection();
        XYSeries series = new XYSeries(title);
        
        for (Double[] point : data) {
            series.add(point[0], point[1]);
        }
        dataset.addSeries(series);

        JFreeChart chart = ChartFactory.createXYLineChart(
                title,
                "X",
                "Y",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        JFrame frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        
        ChartPanel chartPanel = new ChartPanel(chart);
        frame.setContentPane(chartPanel);
        
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

} 