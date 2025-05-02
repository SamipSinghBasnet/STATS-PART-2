import java.io.FileWriter; // Import class to write to files
import java.io.IOException; // Import class to handle file writing errors
import java.util.ArrayList; // Import ArrayList class to store data points

public class FunctionPlotter {

    public static void main(String[] args) {
        // y = x^2 + 2 (we'll store x and y values)
        ArrayList<Double[]> data = new ArrayList<>(); // Create a list to hold (x, y) pairs

        for (double x = -10; x <= 10; x += 1.0) { // Loop from x = -10 to x = 10
            double y = Math.pow(x, 2) + 2; // Calculate y = x^2 + 2
            data.add(new Double[]{x, y}); // Store x and y as a pair in the list
        }

        // Export the data to a CSV file
        try (FileWriter writer = new FileWriter("function_data.csv")) { // Create file writer for output file
            writer.append("x,y\n"); // Write CSV header

            for (Double[] point : data) { // Loop through each point in the list
                writer.append(point[0] + "," + point[1] + "\n"); // Write x and y to file, separated by comma
            }

            System.out.println("Data exported to function_data.csv"); // Print confirmation
        } catch (IOException e) { // If there's an error writing the file
            e.printStackTrace(); // Print error details
        }
    }
}
