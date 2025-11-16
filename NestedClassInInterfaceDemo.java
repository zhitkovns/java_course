// Интерфейс с вложенным классом
interface MathOperations {
    // Статический метод интерфейса
    static double multiply(double a, double b) {
        return a * b;
    }
    
    // Вложенный статический класс внутри интерфейса
    class Calculator {
        private String operationName;
        
        public Calculator(String operationName) {
            this.operationName = operationName;
        }
        
        public double add(double a, double b) {
            return a + b;
        }
        
        public double subtract(double a, double b) {
            return a - b;
        }
        
        public double multiplyUsingInterface(double a, double b) {
            // Доступ к статическому методу интерфейса
            return MathOperations.multiply(a, b);
        }
        
        public void displayOperation() {
            System.out.println("Текущая операция: " + operationName);
        }
    }
    
    // Другой вложенный класс - утилитарный
    class Constants {
        public static final double PI = 3.14159;
        public static final double E = 2.71828;
        
        public static double circleArea(double radius) {
            return PI * radius * radius;
        }
    }
}

// Еще один пример интерфейса с вложенным классом
interface Vehicle {
    void start();
    void stop();
    
    // Вложенный класс для создания стандартных реализаций
    class DefaultVehicle implements Vehicle {
        private String name;
        
        public DefaultVehicle(String name) {
            this.name = name;
        }
        
        @Override
        public void start() {
            System.out.println(name + " запущен");
        }
        
        @Override
        public void stop() {
            System.out.println(name + " остановлен");
        }
        
        public String getName() {
            return name;
        }
    }
}

// Класс для демонстрации использования
public class NestedClassInInterfaceDemo {
    public static void main(String[] args) {
        // Способы вызова методов вложенного класса из интерфейса
        
        // 1. Создание экземпляра вложенного класса Calculator
        MathOperations.Calculator calc = new MathOperations.Calculator("Сложение");
        calc.displayOperation();
        System.out.println("5 + 3 = " + calc.add(5, 3));
        System.out.println("5 - 3 = " + calc.subtract(5, 3));
        System.out.println("5 * 3 = " + calc.multiplyUsingInterface(5, 3));
        
        // 2. Использование статических методов и констант из вложенного класса Constants
        System.out.println("Число PI: " + MathOperations.Constants.PI);
        System.out.println("Площадь круга радиусом 5: " + MathOperations.Constants.circleArea(5));
        
        // 3. Использование вложенного класса DefaultVehicle из интерфейса Vehicle
        Vehicle.DefaultVehicle car = new Vehicle.DefaultVehicle("Автомобиль");
        car.start();
        car.stop();
        System.out.println("Название транспортного средства: " + car.getName());
        
        // 4. Можно использовать полиморфизм
        Vehicle vehicle = new Vehicle.DefaultVehicle("Мотоцикл");
        vehicle.start();
        vehicle.stop();
    }
}