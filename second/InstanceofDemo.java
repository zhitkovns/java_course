package second;

/**
 * Демонстрация использования оператора instanceof
 * 
 * @author Никита
 * @version 1.0
 */
class Animal {}
class Dog extends Animal {}
class Cat extends Animal {}
class Bird extends Animal {}

public class InstanceofDemo {
    public static void main(String[] args) {
        // Создаем объекты разных типов
        Animal animal = new Animal();
        Dog dog = new Dog();
        Cat cat = new Cat();
        Object obj = new Dog();
        
        // Null объект
        Animal nullAnimal = null;
        String nullString = null;
        
        System.out.println("Примеры instanceof: ");
        
        // Проверка на точное соответствие типа
        System.out.println("dog instanceof Dog: " + (dog instanceof Dog));        // true
        System.out.println("dog instanceof Animal: " + (dog instanceof Animal));  // true
        System.out.println("animal instanceof Dog: " + (animal instanceof Dog));  // false
        
        // Проверка через родительский класс Object
        System.out.println("dog instanceof Object: " + (dog instanceof Object));  // true
        System.out.println("obj instanceof Dog: " + (obj instanceof Dog));        // true
        System.out.println("obj instanceof Animal: " + (obj instanceof Animal));  // true
        
        // Проверка с классами из иерархии
        System.out.println("cat instanceof Animal: " + (cat instanceof Animal));  // true
        
        System.out.println("\ninstanceof с null: ");
        
        // Оператор instanceof с null всегда возвращает false
        System.out.println("nullAnimal instanceof Animal: " + (nullAnimal instanceof Animal));  // false
        System.out.println("nullString instanceof String: " + (nullString instanceof String));  // false
        System.out.println("null instanceof Object: " + (null instanceof Object));              // false
        
        System.out.println("\nПрактическое применение: ");
        
        // Использование в условиях
        processAnimal(animal);
        processAnimal(dog);
        processAnimal(cat);
        processAnimal(nullAnimal);
        
        // Проверка массива
        Object[] objects = { "Hello", 42, 3.14, dog, null };
        checkArrayTypes(objects);
    }
    
    /**
     * Метод демонстрирует использование instanceof для обработки разных типов
     * 
     * @param animal объект животного для обработки
     */
    public static void processAnimal(Animal animal) {
        System.out.print("Обработка животного: ");
        
        if (animal instanceof Dog) {
            System.out.println("Это собака - гав-гав!");
        } else if (animal instanceof Cat) {
            System.out.println("Это кот - мяу!");
        } else if (animal instanceof Animal) {
            System.out.println("Это неизвестное животное");
        } else if (animal == null) {
            System.out.println("Передан null объект");
        }
    }
    
    /**
     * Метод проверяет типы объектов в массиве
     * 
     * @param objects массив объектов для проверки
     */
    public static void checkArrayTypes(Object[] objects) {
        System.out.println("\nПроверка типов в массиве: ");
        
        for (int i = 0; i < objects.length; i++) {
            Object obj = objects[i];
            
            System.out.print("Элемент " + i + ": ");
            
            if (obj instanceof String) {
                System.out.println("String - '" + obj + "'");
            } else if (obj instanceof Integer) {
                System.out.println("Integer - " + obj);
            } else if (obj instanceof Double) {
                System.out.println("Double - " + obj);
            } else if (obj instanceof Dog) {
                System.out.println("Dog");
            } else if (obj instanceof Cat) {
                System.out.println("Cat");
            } else if (obj == null) {
                System.out.println("null");
            } else {
                System.out.println("Неизвестный тип: " + obj.getClass().getSimpleName());
            }
        }
    }
}