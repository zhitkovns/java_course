package second;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class OutputStreamExample {
    public static void main(String[] args) {
        // Создаем ByteArrayOutputStream - подкласс OutputStream
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            
            System.out.println("Запись данных по одному байту:");
            
            // Метод write(int) - записывает один байт
            outputStream.write(72);   // H
            outputStream.write(101);  // e
            outputStream.write(108);  // l
            outputStream.write(108);  // l
            outputStream.write(111);  // o
            outputStream.write(32);   // пробел
            outputStream.write(87);   // W
            outputStream.write(111);  // o
            outputStream.write(114);  // r
            outputStream.write(108);  // l
            outputStream.write(100);  // d
            
            // Получаем записанные данные в виде массива байтов
            byte[] result = outputStream.toByteArray();
            
            // Выводим результат
            System.out.println("Записано байтов: " + result.length);
            System.out.println("Текст: " + new String(result));
            
            // Выводим каждый байт отдельно
            System.out.println("\nЗаписанные байты:");
            for (byte b : result) {
                System.out.println("Байт: " + b + " -> символ: '" + (char)b + "'");
            }
            
        } catch (IOException e) {
            System.out.println("Ошибка ввода-вывода: " + e.getMessage());
        }
    }
}
