package sixth;

public class TypeInferenceExample {
    public static void main(String[] args) {
        // Type inference с var - компилятор сам определяет тип
        var name = "Никита";                   // String
        var age = 25;                          // int
        var height = 1.75;                     // double
        var isStudent = true;                  // boolean
        var scores = new int[]{95, 87, 92};    // int[]
        
        System.out.println("Тип name: " + name.getClass().getSimpleName());
        System.out.println("Тип age: " + ((Object)age).getClass().getSimpleName());
        System.out.println("Тип height: " + ((Object)height).getClass().getSimpleName());
        System.out.println("Тип isStudent: " + ((Object)isStudent).getClass().getSimpleName());
        System.out.println("Тип scores: " + scores.getClass().getSimpleName());
        
        // Использование в циклах
        for (var score : scores) {
            System.out.println("Оценка: " + score + " (тип: " + ((Object)score).getClass().getSimpleName() + ")");
        }
        
        // var с методами
        var result = calculate(10, 3.5);
        System.out.println("Результат: " + result + " (тип: " + ((Object)result).getClass().getSimpleName() + ")");
    }
    
    public static double calculate(int a, double b) {
        return a * b;
    }
}