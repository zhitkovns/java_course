## Ключевое слово `this` в подклассах:

```java
class Parent {
    protected String name = "Parent";
}

class Child extends Parent {
    private String name = "Child";
    
    public void printNames() {
        System.out.println(this.name);      // "Child" - поле текущего класса
        System.out.println(super.name);     // "Parent" - поле родителя
    }
}
```

**Использование `this`:**
- Обращение к полям и методам текущего класса
- Вызов других конструкторов того же класса: `this(параметры)`
- Передача текущего объекта как аргумента

## Ключевое слово `super` в подклассах:

```java
class Animal {
    protected String sound = "Звук животного";
    
    public void makeSound() {
        System.out.println(sound);
    }
}

class Dog extends Animal {
    private String sound = "Гав-гав!";
    
    public void printSounds() {
        System.out.println(this.sound);     // "Гав-гав!" - поле текущего класса
        System.out.println(super.sound);    // "Звук животного" - поле родителя
    }
    
    @Override
    public void makeSound() {
        super.makeSound();  // вызов метода родителя
        System.out.println("А также: " + this.sound);
    }
}
```

**Использование `super`:**
- Обращение к полям и методам родительского класса
- Вызов конструктора родителя: `super(параметры)` (должен быть первой строкой)
- Использование когда подкласс переопределяет поле/метод родителя