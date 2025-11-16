package first;
import java.util.*;

public class InstanceofExample {
    public static void main(String[] args) {
        List<String> stringList = new ArrayList<>();
        List<Integer> integerList = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        
        // 1. Проверка на тип параметризованной коллекции (стирание типов)
        System.out.println(stringList instanceof List); // true
        System.out.println(stringList instanceof ArrayList); // true
        
        // 2. Проверка содержимого после получения элемента
        List<Object> mixedList = Arrays.asList("text", 123, 45.67);
        for (Object item : mixedList) {
            if (item instanceof String) {
                System.out.println("String: " + item);
            } else if (item instanceof Integer) {
                System.out.println("Integer: " + item);
            } else if (item instanceof Double) {
                System.out.println("Double: " + item);
            }
        }
        
        // 3. Проверка перед приведением типа
        Object obj = stringList;
        if (obj instanceof List) {
            List<?> list = (List<?>) obj;
            System.out.println("Это List с " + list.size() + " элементами");
        }
        
        // 4. Pattern matching с instanceof
        Object value = "Hello";
        if (value instanceof String s) {
            System.out.println("Длина строки: " + s.length());
        }
    }
}

// Вывод:
// true
// true
// String: text
// Integer: 123
// Double: 45.67
// Это List с 0 элементами
// Длина строки: 5