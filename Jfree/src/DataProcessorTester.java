import java.io.IOException;

public class DataProcessorTester {
    public static void main(String[] args) throws IOException {
        DataProcessor processor = new DataProcessor();
        
        // Test Case 1: Original Data
        System.out.println("Plotting original data...");
        processor.loadData("function_data.csv");
        processor.plotData("Original Quadratic Function");
        
        // Test Case 2: Different Salt Ranges
        System.out.println("Testing different salt ranges...");
        double[] saltRanges = {2.0, 5.0, 8.0};
        for (double range : saltRanges) {
            processor.loadData("function_data.csv");
            processor.saltData(range);
            processor.saveData("salted_range_" + range + ".csv");
            processor.plotData("Salted Data (range=" + range + ")");
        }
        
        // Test Case 3: Smoothing with Different Window Sizes
        System.out.println("Testing different smoothing windows...");
        int[] windowSizes = {3, 5, 7};
        for (int window : windowSizes) {
            processor.loadData("salted_range_5.0.csv"); // Use moderately salted data
            processor.smoothData();
            processor.saveData("smoothed_window_" + window + ".csv");
            processor.plotData("Smoothed Data (window=" + window + ")");
        }
        
        System.out.println("All tests completed!");
    }
} 