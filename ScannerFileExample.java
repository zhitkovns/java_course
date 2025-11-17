import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ScannerFileExample {
    public static void main(String[] args) {
        // Чтение данных из текстового файла с помощью Scanner
        try (Scanner fileScanner = new Scanner(new File("data.txt"))) {
            
            System.out.println("Содержимое файла:");
            
            // Чтение файла построчно
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                System.out.println(line);
            }
            
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден: " + e.getMessage());
        }
    }
}