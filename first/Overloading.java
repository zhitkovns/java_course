package first;
class Calculator {
    
    // Перегруженные методы сложения
    public int add(int a, int b) {
        return a + b;
    }
    
    public double add(double a, double b) {
        return a + b;
    }
    
    public int add(int a, int b, int c) {
        return a + b + c;
    }
    
    public String add(String a, String b) {
        return a + b;
    }
    
    // Перегруженные методы вывода информации
    public void printInfo(String name) {
        System.out.println("Имя: " + name);
    }
    
    public void printInfo(String name, int age) {
        System.out.println("Имя: " + name + ", Возраст: " + age);
    }
    
    public void printInfo(String name, int age, String city) {
        System.out.println("Имя: " + name + ", Возраст: " + age + ", Город: " + city);
    }
}

public class Overloading {
    public static void main(String[] args) {
        Calculator calc = new Calculator();
        
        // Вызов разных перегруженных методов
        System.out.println(calc.add(5, 3));           // 8
        System.out.println(calc.add(2.5, 3.7));       // 6.2
        System.out.println(calc.add(1, 2, 3));        // 6
        System.out.println(calc.add("Hello", "World")); // "HelloWorld"
        
        calc.printInfo("Анна");
        calc.printInfo("Анна", 25);
        calc.printInfo("Анна", 25, "Москва");
    }
}