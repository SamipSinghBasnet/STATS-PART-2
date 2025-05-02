// SamipSimpleHashMap.java
import java.util.LinkedList;                    // Import for linked list implementation

@SuppressWarnings("unchecked")                 // Suppress generic type warnings
public class SamipSimpleHashMap {
    private LinkedList<String>[] list;          // Array of linked lists to store strings
    private String[] fruits = {                 // Test data array of fruit names
            "apple", "banana", "cherry", "date", "elderberry",
            "fig", "grape", "honeydew", "imbe", "jackfruit",
            "kiwi", "lemon", "mango", "nectarine", "orange",
            "papaya", "quince", "raspberry", "strawberry", "tangerine",
            "ugli", "voavanga", "watermelon", "ximenia", "yuzu",
            "ziziphus", "apricot", "blackberry", "coconut", "dragonfruit",
            "guava", "lime", "peach", "plum"
    };

    public SamipSimpleHashMap(int size) {      // Constructor with initial size parameter
        list = new LinkedList[size];            // Initialize array of linked lists
        for (int i = 0; i < list.length; i++) { // Initialize each linked list
            list[i] = new LinkedList<>();
        }
    }

    private int dumbHash(String input){         // Simple hash function using string length
        int length = input.length();            // Get length of input string
        return length;                          // Return length as hash value
    }

    public void add(String key) {               // Method to add string to hash map
        int index = dumbHash(key) % list.length;// Calculate index using hash
        list[index].add(key);                   // Add string to linked list at index
    }

    public boolean contains(String input) {      // Method to check if string exists
        int index = dumbHash(input) % list.length;// Calculate index using hash
        return list[index].contains(input);      // Check if linked list contains string
    }

    public void resize(){                        // Method to resize the hash map
        LinkedList<String>[] oldList = list;     // Store reference to current list
        list = new LinkedList[oldList.length*2]; // Create new array double the size
        for (int i = 0; i < list.length; i++) { // Initialize new linked lists
            list[i] = new LinkedList<>();
        }
        for (int i = 0; i < oldList.length; i++) {  // Copy all elements to new array
            for (String input : oldList[i]) {        // For each string in old lists
                int index = dumbHash(input) % list.length; // Recalculate index
                list[index].add(input);              // Add to new position
            }
        }
    }

    public void runPerformanceTest() {           // Test method for adding performance
        System.out.println("\n=== Performance Test ===");
        long startTime = System.nanoTime();      // Start timing
        for (String fruit : fruits) {            // Add each fruit to map
            add(fruit);                          // Add fruit
            System.out.printf("Added: %s (length: %d, hash: %d)\n", // Print details
                    fruit, fruit.length(), dumbHash(fruit) % list.length);
        }
        long endTime = System.nanoTime();        // End timing
        System.out.printf("Time to add all fruits: %.2f ms\n", // Print time taken
                (endTime - startTime) / 1000000.0);
    }

    public void runSearchTest() {
        System.out.println("\n=== Search Test ===");
        long startTime = System.nanoTime();
        for (String fruit : fruits) {
            boolean found = contains(fruit);
            System.out.printf("Searching for %s: %s\n", fruit, found);
        }
        long endTime = System.nanoTime();
        System.out.printf("Time to search all fruits: %.2f ms\n",
                (endTime - startTime) / 1000000.0);
    }




}