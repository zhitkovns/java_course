// Собственный класс исключения
class InvalidAgeException extends Exception {
    
    // Конструктор по умолчанию
    public InvalidAgeException() {
        super("Некорректный возраст");
    }
    
    // Конструктор с сообщением
    public InvalidAgeException(String message) {
        super(message);
    }
    
    // Конструктор с сообщением и причиной
    public InvalidAgeException(String message, Throwable cause) {
        super(message, cause);
    }
}

// Пример использования
public class CustomExceptionExample {
    public static void setAge(int age) throws InvalidAgeException {
        if (age < 0 || age > 150) {
            throw new InvalidAgeException("Возраст должен быть от 0 до 150: " + age);
        }
        System.out.println("Возраст установлен: " + age);
    }
    
    public static void main(String[] args) {
        try {
            setAge(25);  // Корректный возраст
            setAge(-5);  // Выбросит InvalidAgeException
        } catch (InvalidAgeException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
}