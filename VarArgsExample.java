public class VarArgsExample {
    
    // 1. Метод с varargs для строк
    public static void printAll(String... strings) {
        System.out.print("Строки: ");
        for (String s : strings) {
            System.out.print(s + " ");
        }
        System.out.println();
    }
    
    // 2. Перегрузка - метод с varargs для целых чисел
    public static void printAll(int... numbers) {
        System.out.print("Числа: ");
        for (int num : numbers) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
    
    // 3. Перегрузка - метод с фиксированным первым параметром и varargs
    public static void printAll(String prefix, int... numbers) {
        System.out.print(prefix + ": ");
        for (int num : numbers) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
    
    // 4. Перегрузка - метод с двумя фиксированными параметрами и varargs
    public static void printAll(String prefix, String separator, int... numbers) {
        System.out.print(prefix + ": ");
        for (int i = 0; i < numbers.length; i++) {
            System.out.print(numbers[i]);
            if (i < numbers.length - 1) {
                System.out.print(separator);
            }
        }
        System.out.println();
    }
    
    // 5. Перегрузка - метод без параметров
    public static void printAll() {
        System.out.println("Нет параметров");
    }

    public static void main(String[] args) {
        // Вызов разных перегруженных версий
        printAll();                             // версия 5
        printAll("A", "B", "C");               // версия 1
        printAll(1, 2, 3, 4, 5);               // версия 2
        printAll("Результат", 10, 20, 30);     // версия 3
        printAll("Числа", " - ", 1, 2, 3);     // версия 4
        
        // Вызов с пустым varargs
        printAll(new String[0]);               // версия 1
        printAll(new int[0]);                  // версия 2
    }
}

// Вывод:
// Нет параметров
// Строки: A B C 
// Числа: 1 2 3 4 5 
// Результат: 10 20 30 
// Числа: 1 - 2 - 3 
// Строки: 
// Числа: 