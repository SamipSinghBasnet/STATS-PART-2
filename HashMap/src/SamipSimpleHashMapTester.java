// SamipSimpleHashMapTester.java
public class SamipSimpleHashMapTester {
    public static void main(String[] args) {     // Main method
        // Original basic test
        System.out.println("=== Original Basic Test ===");
        SamipSimpleHashMap originalMap = new SamipSimpleHashMap(10); // Create map size 10
        originalMap.add("apple");                // Add test strings
        originalMap.add("kiwi");
        originalMap.add("cherry");
        System.out.println("Contains 'apple': " + // Test contains method
                originalMap.contains("apple"));
        System.out.println("Contains 'grape': " + // Test non-existent string
                originalMap.contains("grape"));
        originalMap.resize();                     // Test resize operation
        System.out.println("Contains 'apple' after resize: " + // Verify after resize
                originalMap.contains("apple"));
        System.out.println("Contains 'grape' after resize: " +
                originalMap.contains("grape"));
        // Extended performance tests
        System.out.println("\n=== Extended Performance Tests ===");
        SamipSimpleHashMap testMap = new SamipSimpleHashMap(10); // New map for tests
        testMap.runPerformanceTest();            // Run performance test
        testMap.runSearchTest();                 // Run search test
    }
}