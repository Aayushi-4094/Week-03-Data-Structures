import java.util.*;
public class InsertionSort {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of employees");
        int n = sc.nextInt();
        System.out.println("Before Sorting");
        int[] empID = new int[n];
        for(int i = 0;i<n;i++){
            empID[i] = sc.nextInt();
        }

        int result[] = insertionSort(empID);
        System.out.println(Arrays.toString(result));
    }

    private static int[] insertionSort(int[] empID){
        for (int i = 1; i < empID.length-1; i++) {
            int key = empID[i];
            int j = i - 1;
            while (j >= 0 && empID[j] > key) {
                empID[j + 1] = empID[j];
                j = j - 1;
            }
            empID[j + 1] = key;
        }
        return empID;
    }
}
