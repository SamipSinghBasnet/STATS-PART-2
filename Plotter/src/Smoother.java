import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
@https://www.geeksforgeeks.org/java-math-min-method-examples/

public class Smoother {

    private ArrayList<Double[]> data = new ArrayList<>();
    private int windowValue = 5; // Window size for smoothing

    public void loadData(String inputFile) throws IOException {
        Scanner fileScanner = new Scanner(new File(inputFile));
        if (fileScanner.hasNextLine()) fileScanner.nextLine(); // Skip header

        while (fileScanner.hasNextLine()) {
            String[] line = fileScanner.nextLine().split(",");
            data.add(new Double[]{Double.parseDouble(line[0]), Double.parseDouble(line[1])});
        }
        fileScanner.close();
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
}