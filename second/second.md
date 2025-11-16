```java
class Animal {
    public void makeSound() {
        System.out.println("Животное издает звук");
    }
    
    public String getName() {
        return "Животное";
    }
}

class Dog extends Animal {
    // Переопределение метода makeSound()
    @Override
    public void makeSound() {
        System.out.println("Собака лает: Гав-гав!");
    }
    
    // Переопределение метода getName()
    @Override
    public String getName() {
        return "Собака";
    }
}

class Cat extends Animal {
    // Переопределение метода makeSound()
    @Override
    public void makeSound() {
        System.out.println("Кошка мяукает: Мяу!");
    }
}

public class AnimalDemo {
    public static void main(String[] args) {
        Animal animal = new Animal();
        Animal dog = new Dog();
        Animal cat = new Cat();
        
        animal.makeSound(); // "Животное издает звук"
        dog.makeSound();    // "Собака лает: Гав-гав!"
        cat.makeSound();    // "Кошка мяукает: Мяу!"
        
        System.out.println(dog.getName()); // "Собака"
    }
}
```

**Что будет если типы возвращаемых значений не совпадают:**

Если у переопределенного метода в подклассе будет другой тип возвращаемого значения, чем у метода в суперклассе, это вызовет **ошибку компиляции**.

```java
class Parent {
    public String getValue() {
        return "Родитель";
    }
}

class Child extends Parent {
    // ошибка компиляции: тип возвращаемого значения не совпадает
    @Override
    public int getValue() {
        return 10;
    }
}
```

**Ошибка:**
```
error: getValue() in Child cannot override getValue() in Parent
return type int is not compatible with String
```

**Исключение:** Ковариантные возвращаемые типы - можно сужать тип возвращаемого значения:

```java
class Animal {
    public Animal getAnimal() {
        return new Animal();
    }
}

class Dog extends Animal {
    @Override
    public Dog getAnimal() { // OK - Dog является подтипом Animal
        return new Dog();
    }
}
```