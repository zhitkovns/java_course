/**
 * Демонстрация использования всех операторов с базовыми типами и String
 * 
 * @author Никита
 * @version 1.0
 */
public class OperatorsDemo {
    public static void main(String[] args) {
        // Базовые типы
        int a = 10, b = 3;
        double x = 5.5, y = 2.0;
        boolean bool1 = true, bool2 = false;
        
        // Операторы присваивания
        System.out.println("Операторы присваивания: ");
        int result = a + b;  // =
        System.out.println("= : " + result);
        
        result += 5;  // +=
        System.out.println("+= : " + result);
        
        result -= 2;  // -=
        System.out.println("-= : " + result);
        
        result *= 3;  // *=
        System.out.println("*= : " + result);
        
        result /= 4;  // /=
        System.out.println("/= : " + result);
        
        result %= 5;  // %=
        System.out.println("%= : " + result);

        // Тернарный оператор
        System.out.println("\nТернарный оператор: ");
        int max = (a > b) ? a : b;
        System.out.println("?: max = " + max);

        // Логические операторы
        System.out.println("\nЛогические операторы: ");
        System.out.println("|| : " + (bool1 || bool2));
        System.out.println("&& : " + (bool1 && bool2));
        System.out.println("| : " + (true | false));
        System.out.println("^ : " + (true ^ true));
        System.out.println("& : " + (true & false));

        // Операторы сравнения
        System.out.println("\nОператоры сравнения: ");
        System.out.println("== : " + (a == 10));
        System.out.println("!= : " + (a != b));
        System.out.println("> : " + (a > b));
        System.out.println(">= : " + (a >= 10));
        System.out.println("< : " + (a < b));
        System.out.println("<= : " + (a <= 10));

        // Битовые сдвиги
        System.out.println("\nБитовые сдвиги: ");
        int num = 8;
        System.out.println(">> : " + (num >> 1));   // 4
        System.out.println(">>> : " + (num >>> 1)); // 4
        System.out.println("<< : " + (num << 1));   // 16

        // Арифметические операторы
        System.out.println("\nАрифметические операторы: ");
        System.out.println("+ : " + (a + b));
        System.out.println("- : " + (a - b));
        System.out.println("* : " + (a * b));
        System.out.println("/ : " + (a / b));
        System.out.println("% : " + (a % b));

        // Инкремент и декремент
        System.out.println("\nИнекремент/Декремент: ");
        int i = 5;
        System.out.println("Префиксный ++ : " + (++i));  // 6
        System.out.println("Постфиксный ++ : " + (i++)); // 6 (но i станет 7)
        System.out.println("После постфиксного : " + i); // 7
        
        System.out.println("Префиксный -- : " + (--i));  // 6
        System.out.println("Постфиксный -- : " + (i--)); // 6 (но i станет 5)
        System.out.println("После постфиксного : " + i); // 5

        // Унарные операторы
        System.out.println("\nУнарные операторы:");
        System.out.println("! : " + (!bool1));
        System.out.println("~ : " + (~a));

        // Операторы для String
        System.out.println("\nОператоры для String: ");
        String str1 = "Hello";
        String str2 = "World";
        
        // Конкатенация
        String concat = str1 + " " + str2;
        System.out.println("+ для String: " + concat);
        
        // += для String
        str1 += " Java";
        System.out.println("+= для String: " + str1);
        
        // Сравнение строк
        System.out.println("== для String: " + (str1 == str1));
        System.out.println("!= для String: " + (str1 != str2));

        // Классы-оболочки
        System.out.println("\nКлассы-оболочки: ");
        Integer intObj1 = 10;
        Integer intObj2 = 20;
        System.out.println("== для Integer: " + (intObj1 == intObj2));
        System.out.println("+ для Integer: " + (intObj1 + intObj2));
        
        // Скобки для изменения приоритета
        System.out.println("\nПриоритет операторов: ");
        int withBrackets = (a + b) * 2;
        int withoutBrackets = a + b * 2;
        System.out.println("(a + b) * 2 = " + withBrackets);
        System.out.println("a + b * 2 = " + withoutBrackets);
    }
}