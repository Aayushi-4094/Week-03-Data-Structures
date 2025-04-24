import java.util.Arrays;
import java.util.Scanner;

public class QuickSort {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of products");
        int n = sc.nextInt();
        System.out.println("Enter the product prices");
        int[] productPrices = new int[n];
        for (int i = 0; i < n; i++) {
            productPrices[i] = sc.nextInt();
        }

        quickSort(productPrices, 0, n - 1);
        System.out.println("Sorted prices: " + Arrays.toString(productPrices));
    }

    private static void quickSort(int[] productPrices, int start, int end) {
        if (start < end) {
            int pivotIndex = partition(productPrices, start, end);
            quickSort(productPrices, start, pivotIndex - 1);
            quickSort(productPrices, pivotIndex + 1, end);
        }
    }

    private static int partition(int[] productPrices, int start, int end) {
        int pivot = productPrices[end];
        int pivotIndex = start;  
        for (int i = start; i < end; i++) {  
            if (productPrices[i] <= pivot) {  
                int temp = productPrices[pivotIndex];
                productPrices[pivotIndex] = productPrices[i];
                productPrices[i] = temp;
                pivotIndex++;
            }
        }
        int temp = productPrices[pivotIndex];
        productPrices[pivotIndex] = productPrices[end];
        productPrices[end] = temp;
        
        return pivotIndex;  
    }
}