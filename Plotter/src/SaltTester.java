import java.io.IOException;

public class SaltTester {

    public static void main(String[] args) throws IOException {
        Salter salter = new Salter();
        salter.loadData("function_data.csv");

        // Salt with different ranges
        salter.saltData(3.0);
        salter.saveData("salted_data_1.csv");

        salter.loadData("function_data.csv"); // Reload original data
        salter.saltData(5.0);
        salter.saveData("salted_data_2.csv");

        salter.loadData("function_data.csv"); // Reload original data
        salter.saltData(5.0);
        salter.saveData("salted_data_3.csv");

        System.out.println("Data salted and saved to CSV files.");
        Smoother smoother = new Smoother();
        smoother.loadData("salted_data_1.csv");
        smoother.smoothData();
        smoother.saveData("smoothed_data_1.csv");

        smoother.loadData("salted_data_2.csv");
        smoother.smoothData();
        smoother.saveData("smoothed_data_2.csv");

        smoother.loadData("salted_data_3.csv");
        smoother.smoothData();
        smoother.saveData("smoothed_data_3.csv");

        System.out.println("Data  smoothed, saved to CSV files.");
    }
}
