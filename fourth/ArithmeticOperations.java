package fourth;

// Объяснение результатов:

// byte + int → int (int больше byte)
// int + long → long (long больше int)
// long + float → float (float больше long, т.к. может хранить дробные числа)
// float + double → double (double больше float)
// short + double → double (double самый большой тип)

// Все операции следуют правилу: результат соответствует большему типу из операндов

public class ArithmeticOperations {
    public static void main(String[] args) {
        // Пример 1: byte + int -> int
        byte b = 10;
        int i = 100;
        var result1 = b + i;
        System.out.println("byte + int = " + result1 + " (тип: " + ((Object)result1).getClass().getSimpleName() + ")");

        // Пример 2: int + long -> long
        int i2 = 50;
        long l = 1000L;
        var result2 = i2 + l;
        System.out.println("int + long = " + result2 + " (тип: " + ((Object)result2).getClass().getSimpleName() + ")");

        // Пример 3: long + float -> float
        long l2 = 100L;
        float f = 3.14f;
        var result3 = l2 + f;
        System.out.println("long + float = " + result3 + " (тип: " + ((Object)result3).getClass().getSimpleName() + ")");

        // Пример 4: float + double -> double
        float f2 = 2.5f;
        double d = 1.5;
        var result4 = f2 + d;
        System.out.println("float + double = " + result4 + " (тип: " + ((Object)result4).getClass().getSimpleName() + ")");

        // Пример 5: short + double -> double
        short s = 5;
        double d2 = 2.2;
        var result5 = s + d2;
        System.out.println("short + double = " + result5 + " (тип: " + ((Object)result5).getClass().getSimpleName() + ")");
    }
}
