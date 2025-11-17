package first;

public class StringBufferBuilderDemo {
    public static void main(String[] args) {
        // 1. append() - добавляет строку в конец
        StringBuilder sb1 = new StringBuilder("Hello");
        sb1.append(" World");
        System.out.println("1. append(): " + sb1.toString());

        // 2. insert() - вставляет строку в указанную позицию
        StringBuffer sb2 = new StringBuffer("Hello World");
        sb2.insert(6, "Beautiful ");
        System.out.println("2. insert(): " + sb2.toString());

        // 3. delete() - удаляет символы между указанными индексами
        StringBuilder sb3 = new StringBuilder("Hello World");
        sb3.delete(5, 11);
        System.out.println("3. delete(): " + sb3.toString());

        // 4. reverse() - переворачивает строку
        StringBuffer sb4 = new StringBuffer("Hello");
        sb4.reverse();
        System.out.println("4. reverse(): " + sb4.toString());

        // 5. replace() - заменяет символы между индексами на новую строку
        StringBuilder sb5 = new StringBuilder("Hello World");
        sb5.replace(6, 11, "Java");
        System.out.println("5. replace(): " + sb5.toString());

        // 6. capacity() - возвращает текущую емкость буфера
        StringBuffer sb6 = new StringBuffer();
        System.out.println("6. capacity(): " + sb6.capacity());
        sb6.append("This is a long string to demonstrate capacity growth");
        System.out.println("   capacity после добавления: " + sb6.capacity());

        // 7. length() - возвращает длину строки в буфере
        StringBuilder sb7 = new StringBuilder("Java Programming");
        System.out.println("7. length(): " + sb7.length());

        // 8. charAt() - возвращает символ по указанному индексу
        StringBuffer sb8 = new StringBuffer("Hello");
        System.out.println("8. charAt(1): " + sb8.charAt(1));

        // 9. setCharAt() - устанавливает символ по указанному индексу
        StringBuilder sb9 = new StringBuilder("Hello");
        sb9.setCharAt(1, 'a');
        System.out.println("9. setCharAt(): " + sb9.toString());

        // 10. substring() - возвращает подстроку
        StringBuffer sb10 = new StringBuffer("Hello World");
        System.out.println("10. substring(0,5): " + sb10.substring(0, 5));
        System.out.println("    substring(6): " + sb10.substring(6));
    }
}