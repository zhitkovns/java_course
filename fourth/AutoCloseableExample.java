package fourth;
import java.io.FileInputStream;
import java.io.IOException;

public class AutoCloseableExample {
    public static void main(String[] args) {
        
        // Пример с try-with-resources и AutoCloseable
        try (FileInputStream input = new FileInputStream("test.txt")) {
            // Чтение данных из файла
            int data;
            while ((data = input.read()) != -1) {
                System.out.print((char) data);
            }
        } catch (IOException e) {
            System.out.println("Ошибка при работе с файлом: " + e.getMessage());
        }
        // Файл автоматически закрывается здесь, даже если произошло исключение
        
        // Пример с собственным классом, реализующим AutoCloseable
        try (MyResource resource = new MyResource()) {
            resource.doSomething();
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
        // Ресурс автоматически закрывается здесь
    }
}

// Собственный класс, реализующий AutoCloseable
class MyResource implements AutoCloseable {
    public void doSomething() {
        System.out.println("Выполняется работа с ресурсом");
    }
    
    @Override
    public void close() throws Exception {
        System.out.println("Ресурс закрыт автоматически");
        // Здесь освобождаются ресурсы (закрытие файлов, сетевых соединений и т.д.)
    }
}

/* 
Интерфейс AutoCloseable позволяет использовать автоматическое управление ресурсами через конструкцию try-with-resources. 
Когда объект реализует этот интерфейс, он гарантированно будет закрыт при выходе из блока try, даже если произошло исключение.
Без AutoCloseable пришлось бы вручную закрывать ресурсы в блоке finally, что увеличивало бы объем кода и могло приводить к ошибкам.
*/