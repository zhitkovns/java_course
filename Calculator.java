public class Calculator {
    public static int a = 1;
    public static int b;

    public static void printVars() {
        System.out.println(a);
        System.out.println(b);
    }
    
    public static void main(String[] args) {
        // 1. Вызов через имя класса
        Calculator.printVars();
        
        // 2. Вызов через объект класса
        Calculator calculator = new Calculator();
        calculator.printVars();
        
        // 3. Вызов напрямую (в том же классе)
        printVars();
    }
}