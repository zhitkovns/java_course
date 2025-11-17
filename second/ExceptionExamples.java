package second;
public class ExceptionExamples {
    public static void main(String[] args) {
        
        // 1. ArithmeticException - арифметическая ошибка (деление на ноль)
        try {
            int result = 10 / 0;
        } catch (ArithmeticException e) {
            System.out.println("1. ArithmeticException: " + e.getMessage());
        }
        
        // 2. ArrayIndexOutOfBoundsException - выход за границы массива
        try {
            int[] array = {1, 2, 3};
            int value = array[5];
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("2. ArrayIndexOutOfBoundsException: " + e.getMessage());
        }
        
        // 3. IllegalArgumentException - неверный аргумент метода
        try {
            setAge(-5);
        } catch (IllegalArgumentException e) {
            System.out.println("3. IllegalArgumentException: " + e.getMessage());
        }
        
        // 4. ClassCastException - ошибка приведения типа
        try {
            Object obj = "Hello";
            Integer number = (Integer) obj;
        } catch (ClassCastException e) {
            System.out.println("4. ClassCastException: " + e.getMessage());
        }
        
        // 5. NullPointerException - обращение к null ссылке
        try {
            String text = null;
            int length = text.length();
        } catch (NullPointerException e) {
            System.out.println("5. NullPointerException: " + e.getMessage());
        }
    }
    
    // Метод для демонстрации IllegalArgumentException
    public static void setAge(int age) {
        if (age < 0) {
            throw new IllegalArgumentException("Возраст не может быть отрицательным: " + age);
        }
        System.out.println("Возраст установлен: " + age);
    }
}
