package second;
import java.util.StringJoiner;

public class StringJoinerExample {
    public static void main(String[] args) {
        // 1. Простой StringJoiner с разделителем
        StringJoiner joiner1 = new StringJoiner(", ");
        joiner1.add("Яблоко");
        joiner1.add("Банан");
        joiner1.add("Апельсин");
        System.out.println("1. Фрукты: " + joiner1.toString());

        // 2. StringJoiner с разделителем, префиксом и суффиксом
        StringJoiner joiner2 = new StringJoiner(" | ", "[ ", " ]");
        joiner2.add("Понедельник");
        joiner2.add("Вторник");
        joiner2.add("Среда");
        System.out.println("2. Дни недели: " + joiner2.toString());

        // 3. StringJoiner с пустым значением
        StringJoiner joiner3 = new StringJoiner("-", "(", ")");
        joiner3.setEmptyValue("Нет элементов");
        System.out.println("3. Пустой joiner: " + joiner3.toString());
        
        joiner3.add("2023");
        joiner3.add("10");
        joiner3.add("15");
        System.out.println("   После добавления: " + joiner3.toString());

        // 4. Объединение двух StringJoiner
        StringJoiner joiner4 = new StringJoiner(", ", "Предметы: {", "}");
        joiner4.add("Математика");
        joiner4.add("Физика");
        
        StringJoiner joiner5 = new StringJoiner(", ", "Оценки: [", "]");
        joiner5.add("5");
        joiner5.add("4");
        
        StringJoiner merged = joiner4.merge(joiner5);
        System.out.println("4. Объединенный: " + merged.toString());

        // 5. Использование StringJoiner для построения пути
        StringJoiner pathJoiner = new StringJoiner("/");
        pathJoiner.add("home");
        pathJoiner.add("user");
        pathJoiner.add("documents");
        pathJoiner.add("file.txt");
        System.out.println("5. Путь: " + pathJoiner.toString());

        // 6. StringJoiner с разными типами данных
        StringJoiner mixedJoiner = new StringJoiner(" - ", "Информация: ", "");
        mixedJoiner.add("Иван");
        mixedJoiner.add("25 лет");
        mixedJoiner.add("Инженер");
        System.out.println("6. " + mixedJoiner.toString());

        // 7. Длина строки в StringJoiner
        StringJoiner lengthJoiner = new StringJoiner(":");
        lengthJoiner.add("name");
        lengthJoiner.add("email");
        lengthJoiner.add("phone");
        System.out.println("7. Длина строки: " + lengthJoiner.length());
        System.out.println("   Результат: " + lengthJoiner.toString());
    }
}