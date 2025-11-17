// Иерархия исключений
class Ex1 extends Exception {
    public Ex1(String message) { super(message); }
}

class Ex2 extends Ex1 {
    public Ex2(String message) { super(message); }
}

class Ex3 extends Ex2 {
    public Ex3(String message) { super(message); }
}

public class MultipleExceptionsExample {
    
    public static void main(String[] args) {
        
        // 1. Несколько исключений обрабатываются идентичным образом
        try {
            int[] array = new int[5];
            String str = null;
            
            // Может выбросить разные исключения
            if (array.length > 0) {
                str.length();
            }
        } catch (NullPointerException e) {
            System.out.println("Ошибка: " + e.getMessage());
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
        
        // 2. Исключения, образующие иерархию Ex1 <|-- Ex2 <|-- Ex3
        try {
            throw new Ex3("Исключение Ex3");
        } catch (Ex3 e) {
            System.out.println("Перехвачено Ex3: " + e.getMessage());
        } catch (Ex2 e) {
            System.out.println("Перехвачено Ex2: " + e.getMessage());
        } catch (Ex1 e) {
            System.out.println("Перехвачено Ex1: " + e.getMessage());
        }
    }
}