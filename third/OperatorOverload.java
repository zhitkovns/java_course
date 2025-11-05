package third;

public class OperatorOverload {
    public static void main(String[] args) {
        // String + базовые типы
        String name = "Никита";
        int age = 20;
        double height = 1.85;
        boolean isStudent = true;
        char grade = 'A';
        
        // String + объекты
        Integer score = 95;
        Double average = 85.5;
        
        // Конкатенация разных типов
        String info = "Имя: " + name + 
                     ", возраст: " + age + 
                     ", рост: " + height + " м" +
                     ", студент: " + isStudent +
                     ", оценка: " + grade +
                     ", баллы: " + score +
                     ", средний: " + average;
        
        System.out.println(info);
    }
}
