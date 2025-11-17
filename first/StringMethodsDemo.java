package first;
public class StringMethodsDemo {
    public static void main(String[] args) {
        String text = "Hello World";
        String emptyText = "";
        String spacedText = "   Java Programming   ";
        
        // 1. length() - возвращает длину строки
        System.out.println("1. Длина строки: " + text.length());
        
        // 2. charAt() - возвращает символ по указанному индексу
        System.out.println("2. Символ по индексу 4: " + text.charAt(4));
        
        // 3. substring() - возвращает подстроку
        System.out.println("3. Подстрока с 6 индекса: " + text.substring(6));
        
        // 4. toUpperCase() - преобразование в верхний регистр
        System.out.println("4. В верхнем регистре: " + text.toUpperCase());
        
        // 5. trim() - удаляет пробелы в начале и конце строки
        System.out.println("5. trim(): '" + spacedText.trim() + "'");
        
        // 6. replace() - заменяет символы или подстроки
        System.out.println("6. replace 'l' на 'L': " + text.replace('l', 'L'));
        
        // 7. contains() - проверяет содержит ли строка подстроку
        System.out.println("7. Содержит 'World': " + text.contains("World"));
        
        // 8. startsWith() - проверка начала строки
        System.out.println("8. Начинается с 'Hello': " + text.startsWith("Hello"));
        
        // 9. split() - разделяет строку на массив подстрок
        String[] words = text.split(" ");
        System.out.println("9. split() - первое слово: " + words[0]);
        
        // 10. isEmpty() - проверка пустой строки
        System.out.println("10. isEmpty(): " + emptyText.isEmpty());
    }
}