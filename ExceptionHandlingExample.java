public class ExceptionHandlingExample {
    
    public static void main(String[] args) {
        System.out.println("Программа запущена");
        
        // Исключение, которое будет перехвачено
        try {
            int result = 10 / 0; // ArithmeticException
        } catch (ArithmeticException e) {
            System.out.println("Перехвачено ArithmeticException: " + e.getMessage());
        }
        
        System.out.println("Программа продолжает работу после перехваченного исключения");
        
        // Исключение, которое приведет к аварийной остановке
        String text = null;
        int length = text.length(); // NullPointerException - Не перехвачено
        
        // Этот код не выполнится из-за аварийной остановки
        System.out.println("Этот текст никогда не будет выведен");
    }
}