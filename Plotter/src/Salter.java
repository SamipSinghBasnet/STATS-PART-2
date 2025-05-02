import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Salter {

    private ArrayList<Double[]> data = new ArrayList<>();
    private Random random = new Random();



    public void loadData(String inputFile) throws IOException {
        data.clear();
        Scanner fileScanner = new Scanner(new File(inputFile));

        // Skip header
        if (fileScanner.hasNextLine()) {
            fileScanner.nextLine();
        }

        // Read x, y values
        while (fileScanner.hasNextLine()) {
            String[] line = fileScanner.nextLine().split(",");
            double x = Double.parseDouble(line[0]);
            double y = Double.parseDouble(line[1]);
            data.add(new Double[]{x, y});
        }
        fileScanner.close();

    }

    public void saltData(double range) {


        for (Double[] point : data) {
            double salt = (random.nextDouble() * 2 - 1) * range; // Random number between -range and +range
            point[1] += salt;
        }
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