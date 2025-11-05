package first;

/**
 * Демонстрация использования метода decode() в классах-оболочках
 * 
 * @author Никита
 * @version 1.0
 */
public class DecodeExample {
    public static void main(String[] args) {
        System.out.println("Метод decode() для разных систем счисления:");
        
        // Десятичные числа
        Integer dec1 = Integer.decode("123");
        System.out.println("decode(\"123\") = " + dec1);
        
        // Восьмеричные числа (начинаются с 0)
        Integer oct1 = Integer.decode("0123");
        System.out.println("decode(\"0123\") = " + oct1 + " (восьмеричное)");
        
        // Шестнадцатеричные числа (начинаются с 0x или 0X)
        Integer hex1 = Integer.decode("0x1A");
        System.out.println("decode(\"0x1A\") = " + hex1 + " (шестнадцатеричное)");
        
        Integer hex2 = Integer.decode("0XFF");
        System.out.println("decode(\"0XFF\") = " + hex2 + " (шестнадцатеричное)");
        
        // Шестнадцатеричные числа (начинаются с #)
        Integer hex3 = Integer.decode("#FF00");
        System.out.println("decode(\"#FF00\") = " + hex3 + " (шестнадцатеричное)");
        
        // Отрицательные числа
        Integer neg1 = Integer.decode("-123");
        System.out.println("decode(\"-123\") = " + neg1);
        
        Integer negHex = Integer.decode("-0x1A");
        System.out.println("decode(\"-0x1A\") = " + negHex);
        
        System.out.println("\nМетод decode() для других классов-оболочек:");
        
        // Long
        Long longDec = Long.decode("123456789");
        System.out.println("Long.decode(\"123456789\") = " + longDec);
        
        Long longHex = Long.decode("0xFFFFFFFF");
        System.out.println("Long.decode(\"0xFFFFFFFF\") = " + longHex);
        
        // Short
        Short shortDec = Short.decode("1234");
        System.out.println("Short.decode(\"1234\") = " + shortDec);
        
        Short shortHex = Short.decode("0x7F");
        System.out.println("Short.decode(\"0x7F\") = " + shortHex);
        
        // Byte
        Byte byteDec = Byte.decode("127");
        System.out.println("Byte.decode(\"127\") = " + byteDec);
        
        Byte byteHex = Byte.decode("0x7F");
        System.out.println("Byte.decode(\"0x7F\") = " + byteHex);
        
        System.out.println("\nСравнение с другими методами:");
        
        // Сравнение decode() с valueOf() и parseType()
        Integer v1 = Integer.decode("123");
        Integer v2 = Integer.valueOf(123);
        Integer v3 = Integer.valueOf("123");
        Integer v4 = Integer.parseInt("123");
        
        System.out.println("decode(\"123\") = " + v1);
        System.out.println("valueOf(123) = " + v2);
        System.out.println("valueOf(\"123\") = " + v3);
        System.out.println("parseInt(\"123\") = " + v4);
        
        System.out.println("\nОбработка ошибок:");
        
        try {
            Integer error1 = Integer.decode("ABC");
            System.out.println("Это не напечатается");
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: decode(\"ABC\") - " + e.getMessage());
        }
        
        try {
            Integer error2 = Integer.decode("");
            System.out.println("Это не напечатается");
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: decode(\"\") - " + e.getMessage());
        }
        
        try {
            Integer error3 = Integer.decode("018");
            System.out.println("Это не напечатается");
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: decode(\"018\") - " + e.getMessage());
        }
        
        System.out.println("\nПрактическое применение:");
        
        // Чтение чисел из конфигурационных файлов
        String[] configValues = {"255", "0xFF", "0777", "#FF8800", "-42"};
        
        for (String value : configValues) {
            try {
                int number = Integer.decode(value);
                System.out.println("Конфиг значение \"" + value + "\" = " + number);
            } catch (NumberFormatException e) {
                System.out.println("Некорректный формат: " + value);
            }
        }
    }
}