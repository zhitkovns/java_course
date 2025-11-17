package second;

public class StringConversionDemo {
    public static void main(String[] args) {
        String str = "Hello";
        StringBuffer stringBuffer = new StringBuffer("World");
        StringBuilder stringBuilder = new StringBuilder("Java");
        
        // 1. String -> StringBuffer
        StringBuffer sbFromString = new StringBuffer(str);
        System.out.println("1. String -> StringBuffer: " + sbFromString.toString());
        
        // 2. String -> StringBuilder
        StringBuilder sbdFromString = new StringBuilder(str);
        System.out.println("2. String -> StringBuilder: " + sbdFromString.toString());
        
        // 3. StringBuffer -> String
        String strFromBuffer = stringBuffer.toString();
        System.out.println("3. StringBuffer -> String: " + strFromBuffer);
        
        // 4. StringBuilder -> String
        String strFromBuilder = stringBuilder.toString();
        System.out.println("4. StringBuilder -> String: " + strFromBuilder);
        
        // 5. StringBuffer -> StringBuilder (через String)
        StringBuilder sbdFromBuffer = new StringBuilder(stringBuffer.toString());
        System.out.println("5. StringBuffer -> StringBuilder: " + sbdFromBuffer.toString());
        
        // 6. StringBuilder -> StringBuffer (через String)
        StringBuffer sbFromBuilder = new StringBuffer(stringBuilder.toString());
        System.out.println("6. StringBuilder -> StringBuffer: " + sbFromBuilder.toString());
        
        // 7. StringBuffer в String через valueOf (альтернативный способ)
        String strFromBuffer2 = String.valueOf(stringBuffer);
        System.out.println("7. StringBuffer -> String через valueOf: " + strFromBuffer2);
        
        // 8. StringBuilder в String через valueOf (альтернативный способ)
        String strFromBuilder2 = String.valueOf(stringBuilder);
        System.out.println("8. StringBuilder -> String через valueOf: " + strFromBuilder2);
        
        // Демонстрация изменений
        stringBuffer.append("!");
        stringBuilder.append("!");
        
        System.out.println("\nПосле изменений:");
        System.out.println("StringBuffer: " + stringBuffer.toString());
        System.out.println("StringBuilder: " + stringBuilder.toString());
        System.out.println("String остался прежним: " + str);
    }
}


// Методы преобразования:
// 1. String → StringBuffer: конструктор StringBuffer(String str)
// 2. String → StringBuilder: конструктор StringBuilder(String str)  
// 3. StringBuffer → String: метод toString()
// 4. StringBuilder → String: метод toString()
// 5. StringBuffer → StringBuilder: через String: new StringBuilder(stringBuffer.toString())
// 6. StringBuilder → StringBuffer: через String: new StringBuffer(stringBuilder.toString())
// 7. StringBuffer → String: альтернативно String.valueOf(stringBuffer)
// 8. StringBuilder → String: альтернативно String.valueOf(stringBuilder)