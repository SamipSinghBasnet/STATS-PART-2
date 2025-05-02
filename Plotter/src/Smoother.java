import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
@https://www.geeksforgeeks.org/java-math-min-method-examples/

public class Smoother {

    private ArrayList<Double[]> data = new ArrayList<>(); // List to hold (x, y) values
    private int windowValue = 5; // Number of neighbors to include on each side for smoothing
    // Load data from CSV file
    public void loadData(String inputFile) throws IOException {
        Scanner fileScanner = new Scanner(new File(inputFile)); // Open input file
        if (fileScanner.hasNextLine()) fileScanner.nextLine(); // Skip header line

        while (fileScanner.hasNextLine()) {
            String[] line = fileScanner.nextLine().split(","); // Split by comma
            data.add(new Double[]{Double.parseDouble(line[0]), Double.parseDouble(line[1])}); // Parse x and y, add to list
        }
        fileScanner.close(); // Close file
    }
    // Smooth data using moving average
    public void smoothData() {
        ArrayList<Double[]> smoothedData = new ArrayList<>(); // New list for smoothed values
        for (int i = 0; i < data.size(); i++) {
            double sum = 0; // Sum of y values in the window
            int count = 0;  // Number of points in the window

            // Loop from (i - windowValue) to (i + windowValue), stay within bounds
            for (int j = Math.max(0, i - windowValue); j <= Math.min(data.size() - 1, i + windowValue); j++) {
                sum += data.get(j)[1]; // Add y value
                count++; // Increase count
            }
            smoothedData.add(new Double[]{data.get(i)[0], sum / count}); // Store original x and averaged y
        }
        data = smoothedData; // Replace original data with smoothed data
    }
    // Save smoothed data to a new CSV file
    public void saveData(String outputFile) throws IOException {
        FileWriter writer = new FileWriter(outputFile); // Create file writer
        writer.append("x,y\n"); // Write CSV header
        for (Double[] point : data) { // Loop through data points
            writer.append(point[0] + "," + point[1] + "\n"); // Write x and y
        }
        writer.close(); // Close file
    }
}
