```java
class Vehicle {
    public void startEngine() {
        System.out.println("Двигатель запущен");
    }
    
    public int getMaxSpeed() {
        return 120;
    }
}

class Car extends Vehicle {
    // Опечатка в имени метода - мы хотели переопределить startEngine
    @Override
    public void startEngin() {  // ошибка: метод startEngin не существует в суперклассе
        System.out.println("Автомобиль: двигатель запущен");
    }
    
    // Правильное переопределение
    @Override
    public int getMaxSpeed() {
        return 180;
    }
}

class Truck extends Vehicle {
    // Ошибка в сигнатуре метода - другой тип параметра
    @Override
    public void startEngine(String key) {  // ошибка: метод с таким параметром не существует
        System.out.println("Грузовик: двигатель запущен с ключом");
    }
}
```

**Объяснение:**

Аннотация `@Override` помогает обнаружить ошибки на этапе компиляции:

1. **Опечатки в имени метода** - компилятор сообщит, что метод `startEngin` не переопределяет никакой метод из суперкласса
2. **Несовпадение сигнатур** - если параметры или возвращаемый тип не совпадают с методом суперкласса
3. **Попытка переопределения несуществующего метода** - если в суперклассе нет метода с таким именем

**Без `@Override`** эти ошибки остались бы незамеченными:
- `startEngin()` стал бы просто новым методом класса `Car`
- Компиляция прошла бы успешно, но полиморфизм не работал бы правильно

**Ошибки компиляции:**
```
error: method does not override or implement a method from a supertype
```