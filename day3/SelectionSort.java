import java.util.*;
public class SelectionSort {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number the exam scores");
        int n = sc.nextInt();
        System.out.println("Before sorting");
        int[] scores = new int[n];
        for(int i = 0;i<n;i++){
            scores[i] = sc.nextInt();
        }
        int[] result = selectionSorting(scores);
        System.out.println(Arrays.toString(result));
    }

    private static int[] selectionSorting(int[] scores){
        int n = scores.length;
        for (int i = 0; i < n-1; i++) {
            int min_index = i;
            for(int j = i+1;j<n;j++){
                if(scores[j] < scores[min_index]){
                    min_index = j;
                }
            } 
            int temp = scores[min_index];
            scores[min_index] = scores[i];
            scores[i] = temp;
        }
        return scores;
}
}

