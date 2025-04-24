
import java.util.Scanner;

public class StringBuilderReverse {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a string");
        String input = sc.nextLine();
        StringBuilder sb = new StringBuilder(input);
        StringBuilder reversed = sb.reverse();
        System.out.println(reversed.toString());
    }
}
