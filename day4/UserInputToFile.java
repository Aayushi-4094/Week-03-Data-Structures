import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class UserInputToFile {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             FileWriter writer = new FileWriter("output.txt", true)) { 
            String input;
            System.out.println("Enter text (type 'exit' to stop):");
                        while (true) {
                input = reader.readLine();
                
                if ("exit".equalsIgnoreCase(input)) {
                    break;
                }
                writer.write(input + System.lineSeparator()); 
            }
            
            System.out.println("Input has been written to output.txt.");
            
        } catch (IOException e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }
}
