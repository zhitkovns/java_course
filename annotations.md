**@Override** - указывает, что метод переопределяет метод суперкласса или реализует метод интерфейса

```java
class Animal {
    public void makeSound() {
        System.out.println("Животное издает звук");
    }
}

class Dog extends Animal {
    @Override
    public void makeSound() {
        System.out.println("Собака лает");
    }
}
```
**Зачем нужна:** Помогает обнаружить ошибки на этапе компиляции. Если метод не переопределяет ничего, компилятор выдаст ошибку.

---

**@Deprecated** - помечает элемент как устаревший, не рекомендуемый к использованию

```java
class Calculator {
    @Deprecated
    public int oldAdd(int a, int b) {
        return a + b;
    }
    
    public int add(int a, int b) {
        return a + b;
    }
}
```
**Зачем нужна:** Предупреждает других разработчиков, что метод устарел и будет удален в будущих версиях. Компилятор выдает предупреждение при использовании.

---

**@SuppressWarnings** - отключает предупреждения компилятора для конкретного элемента

```java
import java.util.*;

class Example {
    @SuppressWarnings("unchecked")
    public void processList() {
        List list = new ArrayList(); // Без аннотации было бы предупреждение о raw types
        list.add("текст");
    }
    
    @SuppressWarnings({"unused", "rawtypes"})
    public void anotherMethod() {
        int unusedVariable; // Предупреждение о неиспользуемой переменной отключено
        List list = new ArrayList();
    }
}
```
**Зачем нужна:** Позволяет сознательно отключить определенные типы предупреждений, когда разработчик уверен в своей логике.