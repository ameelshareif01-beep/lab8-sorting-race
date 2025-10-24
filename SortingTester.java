import java.util.Arrays;
import java.util.Random;

public class SortingTester {

    public static void main(String[] args) {
        int[] sizes = {1000, 5000, 10000, 25000, 50000, 100000};
        System.out.println("--- The Sorting Race ---");

        for (int n : sizes) {
            System.out.println("\n--- Testing for array size n = " + n + " ---");

            testScenario("Average Case", generateRandomArray(n));
            testScenario("Best Case (Sorted Ascending)", generateSortedArray(n));
            testScenario("Worst Case (Sorted Descending)", generateReverseSortedArray(n));
        }
    }

    private static void testScenario(String label, int[] baseArray) {
        System.out.println("\n" + label + ":");

        runAndTimeAllSorts(baseArray, "Selection Sort", SortingAlgorithms::selectionSort);
        runAndTimeAllSorts(baseArray, "Insertion Sort", SortingAlgorithms::insertionSort);
        runAndTimeAllSorts(baseArray, "Merge Sort", SortingAlgorithms::mergeSort);
    }

    private static void runAndTimeAllSorts(int[] baseArray, String sortName, java.util.function.Consumer<int[]> sortMethod) {
        int[] arr = Arrays.copyOf(baseArray, baseArray.length);

        long start = System.nanoTime();
        sortMethod.accept(arr);
        long end = System.nanoTime();

        double elapsedMs = (end - start) / 1_000_000.0;
        System.out.printf("%-20s : %.3f ms%n", sortName, elapsedMs);
    }

    public static int[] generateRandomArray(int size) {
        Random rand = new Random();
        int[] arr = new int[size];
        for (int i = 0; i < size; i++)
            arr[i] = rand.nextInt(size * 10);
        return arr;
    }

    public static int[] generateSortedArray(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++)
            arr[i] = i;
        return arr;
    }

    public static int[] generateReverseSortedArray(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++)
            arr[i] = size - i;
        return arr;
    }
}
