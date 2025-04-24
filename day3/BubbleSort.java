import java.util.*;
class BubbleSort{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of students");
        int n = sc.nextInt();
        int[] marks = new int[n];
        System.out.println("Before sorting");
        for (int i = 0; i < n; i++) {
            marks[i] = sc.nextInt();
        }
        System.out.println("After sorting");
        int[] result = bubbleSorting(marks);
        System.out.println(Arrays.toString(result));
    }

    private static int[] bubbleSorting(int[] marks){
        int n = marks.length;
        for(int i = 0;i<n -1;i++){
            for(int j = 0;j<n-i-1;j++){
                if(marks[j] > marks[j+1]){
                int temp = marks[j];
                marks[j] = marks[j + 1];
                marks[j + 1] = temp;
                }
            }
        }
        return marks;
    }
}