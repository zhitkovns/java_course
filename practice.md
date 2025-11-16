```java
public class MyClass {
    public final int a;
    
    // Вариант 1: Инициализация в конструкторе по умолчанию
    public MyClass() {
        this.a = 10;
    }
    
    // Вариант 2: Инициализация в параметризованном конструкторе
    public MyClass(int value) {
        this.a = value;
    }
}

// Альтернативный вариант с блоком инициализации
class MyClassWithBlock {
    public final int a;
    
    // Вариант 3: Инициализация в блоке инициализации
    {
        a = 20;
    }
    
    public MyClassWithBlock() {
    }
}

// Пример со статическими final полями
class Constants {
    // Вариант 4: Инициализация при объявлении
    public final static int MAX_VALUE = 100;
    
    // Вариант 5: Инициализация в статическом блоке
    public final static int MIN_VALUE;
    static {
        MIN_VALUE = 0;
    }
}
```

**Варианты инициализации final поля `a`:**
1. В конструкторе по умолчанию
2. В параметризованном конструкторе
3. В блоке инициализации экземпляра (в отдельном классе)