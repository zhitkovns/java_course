## Типы переменных в switch (Java)

### Допустимые типы:

1. Целочисленные типы: byte, short, int
```java
int day = 3;
switch(day) {
    case 1: System.out.println("Пн"); break;
    case 2: System.out.println("Вт"); break;
    case 3: System.out.println("Ср"); break;
}
```

2. char
```java
char grade = 'A';
switch(grade) {
    case 'A': System.out.println("Отлично"); break;
    case 'B': System.out.println("Хорошо"); break;
}
```

3. String (начиная с Java 7)
```java
String color = "RED";
switch(color) {
    case "RED": System.out.println("Красный"); break;
    case "BLUE": System.out.println("Синий"); break;
}
```

4. Enum
```java
enum Size { SMALL, MEDIUM, LARGE }
Size size = Size.MEDIUM;
switch(size) {
    case SMALL: System.out.println("Маленький"); break;
    case MEDIUM: System.out.println("Средний"); break;
}
```

5. Byte, Short, Character, Integer (обертки)
```java
Integer number = 10;
switch(number) {
    case 10: System.out.println("Десять"); break;
    case 20: System.out.println("Двадцать"); break;
}
```

### Switch expressions (Java 14+):
```java
String result = switch(day) {
    case 1, 2, 3, 4, 5 -> "Будни";
    case 6, 7 -> "Выходные";
    default -> "Неизвестно";
};
```