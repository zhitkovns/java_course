package first;
import java.util.Formatter;

public class FormatterExample {
    public static void main(String[] args) {
        // Создаем объект Formatter с try для автоматического закрытия
        try (Formatter formatter = new Formatter()) {
            
            // 1. %s - строковое представление аргумента
            formatter.format("1. Строка: %s%n", "Hello World");
            
            // 2. %d - десятичное целое значение
            formatter.format("2. Число: %d%n", 255);
            
            // 3. %f - десятичное значение с плавающей точкой
            formatter.format("3. Дробное число: %.2f%n", 3.14159);
            
            // 4. %b - логическое значение аргумента
            formatter.format("4. Логическое значение: %b%n", true);
            
            // 5. %c - символьное представление аргумента
            formatter.format("5. Символ: %c%n", 'A');
            
            // Выводим отформатированный результат
            System.out.println(formatter.toString());
        } // formatter автоматически закрывается здесь
    }
}