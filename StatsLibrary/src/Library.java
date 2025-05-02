public class Library {

    /**
     * Calculates the factorial of a number
     *
     * @param num The number to calculate factorial for
     * @return The factorial result as a double, or 0 if input is negative
     */
    public double factorial(int num) {
        if (num < 0) return 0; // Return 0 if input is negative
        if (num == 0 || num == 1) return 1; // Return 1 if input is 0 or 1
        double result = 1; // Initialize result to 1
        for (int i = 2; i <= num; i++) { // Loop from 2 to num
            result *= i; // Multiply result by i
        }
        return result; // Return the factorial result
    }

    /**
     * Calculates the Poisson probability
     *
     * @param lambda The average rate of success
     * @param y The number of occurrences
     * @return The Poisson probability
     */
    public double calculatePoisson(double lambda, int y) {
        return (Math.pow(lambda, y) * Math.exp(-lambda)) / factorial(y); // Calculate Poisson probability
    }

    /**
     * Calculates Chebyshev's inequality
     *
     * @param k The number of standard deviations
     * @return The probability according to Chebyshev's inequality
     */
    public double calculateChevyshevs(double k) {
        return (1 - (1 / Math.pow(k, 2))); // Calculate Chebyshev's inequality
    }

    /**
     * Calculates the uniform distribution PDF
     *
     * @param x The value at which to evaluate the PDF
     * @param a The lower bound of the uniform distribution
     * @param b The upper bound of the uniform distribution
     * @return The PDF value at x
     */
    public static double uniformDistribution(double x, double a, double b) {
        if (a >= b) {
            throw new IllegalArgumentException("Lower bound a must be less than upper bound b"); // Validate bounds
        }
        if (x >= a && x <= b) {
            System.out.println("Expected value:"+ (b=a)/2);
            System.out.println("Variance:"+ Math.pow(b-a,2)/2);
            
            return 1.0 / (b - a); // Return PDF value if x is within bounds
            
        } else {
            return 0.0; // Return 0 if x is outside bounds
        }
    }
    

    /**
     * Calculates the normal distribution PDF
     *
     * @param x The value at which to evaluate the PDF
     * @param mu The mean of the normal distribution
     * @param sigma The standard deviation of the normal distribution
     * @return The PDF value at x
     */
    public static double normalDistribution(double x, double mu, double sigma) {
        if (sigma <= 0) {
            throw new IllegalArgumentException("Standard deviation must be positive"); // Validate sigma
        }
        double exponent = -Math.pow(x - mu, 2) / (2 * Math.pow(sigma, 2)); // Calculate exponent
        return (1.0 / (sigma * Math.sqrt(2 * Math.PI))) * Math.exp(exponent); // Return normal PDF value
    }
}