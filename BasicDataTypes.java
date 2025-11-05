class PrimitiveTypes {
    public static void main(String[] args) {
        // Целочисленные типы
        byte byteValue = 100;                    // от -128 до 127
        short shortValue = 30000;               // от -32768 до 32767
        int intValue = 2000000000;              // от -2^31 до 2^31-1
        long longValue = 9000000000000000000L;  // от -2^63 до 2^63-1
        
        // Типы с плавающей точкой
        float floatValue = 3.14f;               // 32-битное число
        double doubleValue = 3.14159265359;     // 64-битное число
        
        // Символьный тип
        char charValue = 'A';
        
        // Булевский тип
        boolean booleanValue = true;            // true или false
        
        // Нулевые значения для примитивов (значения по умолчанию)
        byte defaultByte = 0;                   // нулевое значение для byte
        short defaultShort = 0;                 // нулевое значение для short
        int defaultInt = 0;                     // нулевое значение для int
        long defaultLong = 0L;                  // нулевое значение для long
        float defaultFloat = 0.0f;              // нулевое значение для float
        double defaultDouble = 0.0;             // нулевое значение для double
        char defaultChar = '\u0000';            // нулевое значение для char (null-символ)
        boolean defaultBoolean = false;         // нулевое значение для boolean
    }
}