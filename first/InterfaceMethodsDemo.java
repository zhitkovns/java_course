package first;
interface StringProcessor {
    // Статический метод интерфейса
    static String reverseString(String input) {
        return new StringBuilder(input).reverse().toString();
    }
    
    // Дефолтный метод (неабстрактный)
    default String process(String input) {
        return "Обработано: " + input.toUpperCase();
    }
    
    // Абстрактный метод (должен быть реализован в классе)
    int getProcessingTime();
}

class TextProcessor implements StringProcessor {
    // Реализация абстрактного метода
    @Override
    public int getProcessingTime() {
        return 100;
    }
    
    // Дефолтный метод можно переопределить (не обязательно)
    @Override
    public String process(String input) {
        return "Текст обработан: " + input.toLowerCase();
    }
}

class SimpleProcessor implements StringProcessor {
    // Только реализация абстрактного метода
    // Дефолтный метод используется без изменений
    @Override
    public int getProcessingTime() {
        return 50;
    }
}

public class InterfaceMethodsDemo {
    public static void main(String[] args) {
        // Способы вызова методов:
        
        // 1. Вызов статического метода через имя интерфейса
        String reversed = StringProcessor.reverseString("Hello");
        System.out.println("Реверс: " + reversed);
        
        // 2. Создание объектов классов
        TextProcessor textProc = new TextProcessor();
        SimpleProcessor simpleProc = new SimpleProcessor();
        
        // 3. Вызов дефолтного метода (переопределенного)
        System.out.println(textProc.process("JAVA"));
        
        // 4. Вызов дефолтного метода (из интерфейса)
        System.out.println(simpleProc.process("java"));
        
        // 5. Вызов абстрактных методов (реализованных в классах)
        System.out.println("Время обработки TextProcessor: " + textProc.getProcessingTime());
        System.out.println("Время обработки SimpleProcessor: " + simpleProc.getProcessingTime());
        
        // 6. Использование через ссылку на интерфейс
        StringProcessor processor = new TextProcessor();
        System.out.println(processor.process("Interface"));
    }
}