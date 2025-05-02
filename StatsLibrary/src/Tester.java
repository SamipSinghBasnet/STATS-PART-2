import java.util.Scanner;

public class Tester {
    public static void main(String[] args) {
        Library lib = new Library(); // Create an instance of Library
        Scanner sc = new Scanner(System.in); // Create a Scanner for user input

        // Prompt for Poisson distribution parameters
        System.out.print("Enter the average rate of success (lambda): ");
        double lambda = sc.nextDouble(); // Read lambda from user

        System.out.print("Enter the number of occurrences (y): ");
        int y = sc.nextInt(); // Read y from user
        double probability = lib.calculatePoisson(lambda, y); // Calculate Poisson probability
        System.out.println("Poisson probability: " + probability); // Print the result

        // Prompt for Chebyshev's inequality parameter
        System.out.println("Enter the value for K");
        double k = sc.nextDouble(); // Read k from user
        while (k < 1) { // Validate that k is greater than 1
            System.out.println("K should be greater than 1. Please re-enter:");
            k = sc.nextDouble(); // Re-read k if invalid
        }
        double Chebyshev = lib.calculateChevyshevs(k); // Calculate Chebyshev's inequality
        System.out.println(Chebyshev); // Print the result

        // Prompt for uniform distribution parameters
        System.out.print("Enter value for a (lower bound): ");
        double a = sc.nextDouble(); // Read a from user
        System.out.print("Enter value for b (upper bound): ");
        double b = sc.nextDouble(); // Read b from user
        System.out.print("Enter value for x: ");
        double x = sc.nextDouble(); // Read x from user

        // Call the uniform distribution method and print the result
        double uniform = Library.uniformDistribution(x, a, b); // Calculate uniform PDF
        System.out.println("Uniform PDF: " + uniform); // Print the result

        // Calculate and print the normal distribution PDF at x=0, mu=0, sigma=1
        double normal = Library.normalDistribution(0, 0, 1); // Standard normal at x=0
        System.out.println("Normal PDF: " + normal); // Print the result
    }
}
