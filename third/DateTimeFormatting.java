package third;
import java.util.Formatter;
import java.util.Date;

public class DateTimeFormatting {
    public static void main(String[] args) {
        Date currentDate = new Date();
        
        try (Formatter formatter = new Formatter()) {
            
            // 1. %tH - Час в 24-часовом формате (00-23)
            formatter.format("1. Текущий час: %tH%n", currentDate);
            
            // 2. %tM - Минуты (00-59)
            formatter.format("2. Текущие минуты: %tM%n", currentDate);
            
            // 3. %tY - Год в четырехзначном формате
            formatter.format("3. Текущий год: %tY%n", currentDate);
            
            // 4. %tB - Полное название месяца
            formatter.format("4. Текущий месяц: %tB%n", currentDate);
            
            // 5. %tA - Полное название дня недели
            formatter.format("5. Текущий день недели: %tA%n", currentDate);
            
            // Выводим результат
            System.out.println(formatter.toString());
        }
    }
}
