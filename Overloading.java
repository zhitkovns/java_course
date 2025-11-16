class Calculator {
    // Перегрузка метода add с разными параметрами
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
}

class Animal {
    public void makeSound() {
        System.out.println("Животное издает звук");
    }
    
    public void eat() {
        System.out.println("Животное ест");
    }
}

class Dog extends Animal {
    // Переопределение метода
    @Override
    public void makeSound() {
        System.out.println("Собака лает: Гав-гав!");
    }
}

class Cat extends Animal {
    // Переопределение метода
    @Override
    public void makeSound() {
        System.out.println("Кошка мяукает: Мяу!");
    }
}

public class Overloading {
    public static void main(String[] args) {
        // Перегрузка
        Calculator calc = new Calculator();
        System.out.println(calc.add(2, 3));        // 5
        System.out.println(calc.add(2.5, 3.5));    // 6.0
        System.out.println(calc.add(1, 2, 3));     // 6
        System.out.println(calc.add("Hello", "World")); // "HelloWorld"
        
        // Переопределение
        Animal animal = new Animal();
        Animal dog = new Dog();
        Animal cat = new Cat();
        
        animal.makeSound(); // "Животное издает звук"
        dog.makeSound();    // "Собака лает: Гав-гав!"
        cat.makeSound();    // "Кошка мяукает: Мяу!"
    }
}
