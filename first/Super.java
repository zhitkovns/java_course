package first;

class Animal {
    protected String name;
    protected int age;
    
    // Конструктор суперкласса
    public Animal(String name, int age) {
        this.name = name;
        this.age = age;
    }
    
    // Метод суперкласса
    public void makeSound() {
        System.out.println("Животное издает звук");
    }
    
    public void displayInfo() {
        System.out.println("Имя: " + name + ", Возраст: " + age);
    }
}

class Dog extends Animal {
    private String breed;
    
    // 1. Использование super для вызова конструктора суперкласса
    public Dog(String name, int age, String breed) {
        super(name, age); // вызов конструктора Animal
        this.breed = breed;
    }
    
    @Override
    public void makeSound() {
        // 2. Использование super для вызова метода суперкласса
        super.makeSound(); // вызов метода makeSound() из Animal
        System.out.println("Собака лает: Гав-гав!");
    }
    
    public void displayFullInfo() {
        // 3. Использование super для доступа к полю суперкласса
        System.out.println("Порода: " + breed + ", Имя: " + super.name);
        
        // Вызов метода суперкласса
        super.displayInfo();
    }
}

public class Super {
    public static void main(String[] args) {
        Dog dog = new Dog("Бобик", 3, "Овчарка");
        dog.makeSound();
        dog.displayFullInfo();
    }
}
