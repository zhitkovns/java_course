## <? extends T> - Upper Bounded Wildcard (ковариантность)

```java
import java.util.*;

public class WildcardExample {
    
    // <? extends Number> - принимает любые списки Number или его наследников
    public static double sum(List<? extends Number> numbers) {
        double total = 0;
        for (Number num : numbers) {
            total += num.doubleValue();
        }
        return total;
    }
    
    public static void main(String[] args) {
        List<Integer> integers = Arrays.asList(1, 2, 3);
        List<Double> doubles = Arrays.asList(1.1, 2.2, 3.3);
        
        System.out.println(sum(integers)); // OK - Integer extends Number
        System.out.println(sum(doubles));  // OK - Double extends Number
    }
}
```

**Зачем нужно:** Можно читать из коллекции, но нельзя добавлять (кроме null)

## <? super T> - Lower Bounded Wildcard (контрвариантность)

```java
import java.util.*;

public class WildcardExample {
    
    // <? super Integer> - принимает списки Integer или его суперклассы
    public static void addNumbers(List<? super Integer> list) {
        for (int i = 1; i <= 3; i++) {
            list.add(i); // Можно добавлять Integer
        }
    }
    
    public static void main(String[] args) {
        List<Number> numbers = new ArrayList<>();
        List<Object> objects = new ArrayList<>();
        
        addNumbers(numbers); // OK - Number super Integer
        addNumbers(objects); // OK - Object super Integer
        
        System.out.println(numbers); // [1, 2, 3]
        System.out.println(objects); // [1, 2, 3]
    }
}
```

**Зачем нужно:** Можно добавлять в коллекцию, но нельзя читать (только как Object)

## PECS принцип (Producer Extends, Consumer Super)

```java
// Producer - читаем данные, используем <? extends T>
public static void processNumbers(List<? extends Number> producer) {
    for (Number num : producer) {
        System.out.println(num.doubleValue());
    }
}

// Consumer - записываем данные, используем <? super T>
public static void fillList(List<? super Integer> consumer) {
    consumer.add(1);
    consumer.add(2);
    consumer.add(3);
}
```

**Объяснение:**
- `<? extends T>` - для источников данных (читаем)
- `<? super T>` - для приемников данных (записываем)