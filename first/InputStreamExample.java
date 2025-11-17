package first;
import java.io.ByteArrayInputStream;
import java.io.IOException;

public class InputStreamExample {
    public static void main(String[] args) {
        // Создаем массив байтов для чтения
        byte[] data = {72, 101, 108, 108, 111, 32, 87, 111, 114, 108, 100};
        
        // Создаем ByteArrayInputStream - подкласс InputStream
        try (ByteArrayInputStream inputStream = new ByteArrayInputStream(data)) {
            
            System.out.println("Чтение данных по одному байту:");
            
            // Метод read() без параметров - читает один байт
            int byteData;
            while ((byteData = inputStream.read()) != -1) {
                // read() возвращает int, преобразуем в char для отображения
                System.out.println("Прочитан байт: " + byteData + " -> символ: '" + (char)byteData + "'");
            }
            
            System.out.println("\nДостигнут конец потока");
            
        } catch (IOException e) {
            System.out.println("Ошибка ввода-вывода: " + e.getMessage());
        }
    }
}