```java
public class A {
    {
        System.out.println("logic (1) id= " + this.id);
    }

    static {
        System.out.println("static logic");
    }

    private int id = 1;

    public A(int id) {
        this.id = id;
        System.out.println("ctor id= " + id);
    }

    {
        System.out.println("logic (2) id= " + id);
    }
}

public class Main {
    public static void main(String[] args) {
        new A(100);
    }
}
```

**Вывод программы:**
```
static logic
logic (1) id= 0
logic (2) id= 1
ctor id= 100
```

**Почему такой вывод:**

1. **`static logic`** - статический блок выполняется первым, при загрузке класса
2. **`logic (1) id= 0`** - первый логический блок:
   - Выполняется после статического блока
   - Поле `id` еще не инициализировано (значение по умолчанию 0)
3. **`logic (2) id= 1`** - второй логический блок:
   - Выполняется после первого блока
   - Поле `id` уже инициализировано значением 1
4. **`ctor id= 100`** - конструктор:
   - Выполняется последним
   - Меняет значение `id` на 100

**Порядок выполнения:**
1. Статический блок (при загрузке класса)
2. Логические блоки в порядке объявления
3. Конструктор