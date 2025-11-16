package second;

interface FirstInterface {
    default void show() {
        System.out.println("Метод из FirstInterface");
    }
}

interface SecondInterface {
    default void show() {
        System.out.println("Метод из SecondInterface");
    }
}

// Ошибка компиляции - неоднозначность вызова метода
// class MyClass implements FirstInterface, SecondInterface {
// }

// Правильное решение - переопределение конфликтующего метода
class MyClass implements FirstInterface, SecondInterface {
    @Override
    public void show() {
        // 1. Можно вызвать конкретный метод из одного интерфейса
        FirstInterface.super.show();
        
        // 2. Или предоставить свою реализацию
        System.out.println("Собственная реализация в классе");
    }
}

// Другой вариант - вызвать оба метода
class AnotherClass implements FirstInterface, SecondInterface {
    @Override
    public void show() {
        System.out.println("Начало выполнения:");
        FirstInterface.super.show();
        SecondInterface.super.show();
        System.out.println("Завершение выполнения");
    }
}

public class MultipleInterfacesDemo {
    public static void main(String[] args) {
        MyClass obj1 = new MyClass();
        obj1.show();
        
        AnotherClass obj2 = new AnotherClass();
        obj2.show();
    }
}


// Что произойдет:
// - Если класс реализует два интерфейса с одинаковыми default методами, возникнет ошибка компиляции из-за неоднозначности
// - Компилятор не знает, какую реализацию метода использовать

// Как выйти из положения:
// 1. Переопределить конфликтующий метод в классе и:
//    - Вызвать конкретную реализацию через InterfaceName.super.methodName()
//    - Предоставить собственную реализацию
//    - Вызвать обе реализации из разных интерфейсов
// 2. Синтаксис для вызова конкретной реализации: InterfaceName.super.methodName()
