import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Salter {

    private ArrayList<Double[]> data = new ArrayList<>(); // List to store (x, y) pairs
    private Random random = new Random();                 // Random number generator

    // Load data from a CSV file
    public void loadData(String inputFile) throws IOException {
        data.clear(); // Clear any previous data
        Scanner fileScanner = new Scanner(new File(inputFile)); // Open the input file

        // Skip the first line (header)
        if (fileScanner.hasNextLine()) {
            fileScanner.nextLine();
        }

        // Read each line and extract x and y values
        while (fileScanner.hasNextLine()) {
            String[] line = fileScanner.nextLine().split(","); // Split by comma
            double x = Double.parseDouble(line[0]); // Convert x value
            double y = Double.parseDouble(line[1]); // Convert y value
            data.add(new Double[]{x, y}); // Store the pair in the list
        }

        fileScanner.close(); // Close the file
    }

    // Add random noise (salt) to y values
    public void saltData(double range) {
        for (Double[] point : data) {
            double salt = (random.nextDouble() * 2 - 1) * range; // Generate random value in [-range, +range]
            point[1] += salt; // Add noise to y value
        }
    }

    // Save modified data to a new CSV file
    public void saveData(String outputFile) throws IOException {
        FileWriter writer = new FileWriter(outputFile); // Create writer for output file
        writer.append("x,y\n"); // Write header

        for (Double[] point : data) { // Go through all (x, y) points
            writer.append(point[0] + "," + point[1] + "\n"); // Write each point to file
        }

        writer.close(); // Close the file
    }
}
