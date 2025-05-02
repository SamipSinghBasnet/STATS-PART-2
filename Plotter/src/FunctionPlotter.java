import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FunctionPlotter {

    public static void main(String[] args) {
        //  y = x^2
        ArrayList<Double[]> data = new ArrayList<>();
        for (double x = -10; x <= 10; x += 1.0) {
            double y = Math.pow(x, 2)+2;
            data.add(new Double[]{x, y});
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
    }
}