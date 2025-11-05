/**
 * Класс для демонстрации работы с утилитой javadoc
 * Содержит методы для математических операций и работы со строками
 * 
 * @author Никита
 * @since 2025
 */
public class JavadocExample {
    
    /**
     * Константа класса - значение числа Пи
     */
    public static final double PI = 3.14159;
    
    private String name;
    private int value;
    
    /**
     * Конструктор по умолчанию
     * Инициализирует поля значениями по умолчанию
     */
    public JavadocExample() {
        this.name = "Неизвестно";
        this.value = 0;
    }
    
    /**
     * Параметризованный конструктор
     * 
     * @param name имя для инициализации
     * @param value числовое значение для инициализации
     * @throws IllegalArgumentException если name равен null или value отрицательное
     */
    public JavadocExample(String name, int value) {
        if (name == null) {
            throw new IllegalArgumentException("Имя не может быть null");
        }
        if (value < 0) {
            throw new IllegalArgumentException("Значение не может быть отрицательным");
        }
        this.name = name;
        this.value = value;
    }
    
    /**
     * Вычисляет площадь круга по заданному радиусу
     * 
     * @param radius радиус круга
     * @return площадь круга
     * @throws IllegalArgumentException если радиус отрицательный
     * @see #PI
     * @see Math#pow(double, double)
     */
    public double calculateCircleArea(double radius) {
        if (radius < 0) {
            throw new IllegalArgumentException("Радиус не может быть отрицательным");
        }
        return PI * Math.pow(radius, 2);
    }
    
    /**
     * Проверяет, является ли число четным
     * 
     * @param number число для проверки
     * @return true если число четное, false в противном случае
     */
    public boolean isEven(int number) {
        return number % 2 == 0;
    }
    
    /**
     * Возвращает приветственное сообщение с именем
     * 
     * @return строка с приветствием
     * @see #getName()
     */
    public String getGreeting() {
        return "Привет, " + name + "!";
    }
    
    /**
     * Геттер для поля name
     * 
     * @return текущее значение имени
     */
    public String getName() {
        return name;
    }
    
    /**
     * Сеттер для поля name
     * 
     * @param name новое значение имени
     * @throws IllegalArgumentException если name равен null
     */
    public void setName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Имя не может быть null");
        }
        this.name = name;
    }
    
    /**
     * Геттер для поля value
     * 
     * @return текущее числовое значение
     */
    public int getValue() {
        return value;
    }
    
    /**
     * Сеттер для поля value
     * 
     * @param value новое числовое значение
     * @throws IllegalArgumentException если value отрицательное
     */
    public void setValue(int value) {
        if (value < 0) {
            throw new IllegalArgumentException("Значение не может быть отрицательным");
        }
        this.value = value;
    }
    
    /**
     * Устаревший метод, рекомендуется использовать {@link #calculateCircleArea(double)}
     * 
     * @deprecated Этот метод устарел, используйте calculateCircleArea вместо него
     * @param radius радиус круга
     * @return площадь круга
     */
    @Deprecated
    public double oldCalculateArea(double radius) {
        return 3.14 * radius * radius;
    }
    
    /**
     * Основной метод программы для демонстрации работы класса
     * 
     * @param args аргументы командной строки (не используются)
     */
    public static void main(String[] args) {
        JavadocExample example = new JavadocExample("Никита", 20);
        
        System.out.println(example.getGreeting());
        System.out.println("Площадь круга с радиусом 5: " + example.calculateCircleArea(5));
        System.out.println("Число 10 четное: " + example.isEven(10));
    }
}