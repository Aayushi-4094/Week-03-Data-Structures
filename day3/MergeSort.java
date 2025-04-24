import java.util.*;

public class MergeSort {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of books");
        int n = sc.nextInt();
        System.out.println("Enter the book prices");
        int[] bookPrices = new int[n];
        for (int i = 0; i < n; i++) {
            bookPrices[i] = sc.nextInt();
        }

        mergeSort(bookPrices, 0, n - 1);
        System.out.println("Sorted prices: " + Arrays.toString(bookPrices));
    }

    private static void mergeSort(int[] bookPrices, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(bookPrices, left, mid);
            mergeSort(bookPrices, mid + 1, right); 
            merge(bookPrices, left, mid, right);  
        }
    }

    private static void merge(int[] bookPrices, int left, int mid, int right) {  
        int n1 = mid - left + 1;
        int n2 = right - mid;
        int[] leftArr = new int[n1];
        int[] rightArr = new int[n2];

        for (int i = 0; i < n1; i++) {
            leftArr[i] = bookPrices[left + i];
        }
        for (int j = 0; j < n2; j++) {
            rightArr[j] = bookPrices[mid + 1 + j];
        }

        int i = 0, j = 0, k = left;  
        while (i < n1 && j < n2) {
            if (leftArr[i] <= rightArr[j]) {
                bookPrices[k] = leftArr[i];
                i++;
            } else {
                bookPrices[k] = rightArr[j];  
                j++;
            }
            k++;
        }
        while (i < n1) {
            bookPrices[k] = leftArr[i];
            i++;
            k++;
        }
        while (j < n2) {
            bookPrices[k] = rightArr[j];
            j++;
            k++;
        }
    }
}