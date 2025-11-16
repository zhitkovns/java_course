## Пример использования break и continue

### Пример с while
```java
int count = 0;
while (count < 10) {
    count++;
    
    if (count == 3) {
        continue; // пропускаем число 3
    }
    
    if (count == 8) {
        break; // выходим при достижении 8
    }
    
    System.out.println("Обрабатываем: " + count);
}
// Вывод: 1, 2, 4, 5, 6, 7
```