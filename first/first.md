## Примеры бесконечных циклов

### 1. Бесконечный цикл while
```java
while (true) {
    System.out.println("Этот цикл выполняется вечно");
}
```

### 2. Бесконечный цикл while с условием, которое всегда true
```java
int i = 0;
while (i >= 0) {
    System.out.println("i = " + i);
    i++; // i всегда будет >= 0
}
```

### 3. Бесконечный цикл do-while
```java
do {
    System.out.println("Этот цикл тоже выполняется вечно");
} while (true);
```

### 4. Бесконечный цикл do-while с постоянным условием
```java
boolean flag = true;
do {
    System.out.println("Цикл не остановится");
} while (flag); // flag никогда не меняется
```

### 5. Бесконечный цикл for
```java
for (;;) {
    System.out.println("И этот цикл бесконечный");
}
```