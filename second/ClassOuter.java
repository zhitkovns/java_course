package second;
public class ClassOuter {
    private String privateField = "private поле";
    String packageField = "package поле"; 
    protected String protectedField = "protected поле";
    public String publicField = "public поле";
    
    private void privateMethod() {
        System.out.println("private метод");
    }
    
    void packageMethod() {
        System.out.println("package метод");
    }
    
    protected void protectedMethod() {
        System.out.println("protected метод");
    }
    
    public void publicMethod() {
        System.out.println("public метод");
    }
    
    // Внутренний класс
    public class InnerClass {
        public void accessAllFieldsAndMethods() {
            // Доступ ко всем полям внешнего класса независимо от спецификаторов
            System.out.println(privateField);    // OK - private
            System.out.println(packageField);    // OK - package
            System.out.println(protectedField);  // OK - protected  
            System.out.println(publicField);     // OK - public
            
            // Доступ ко всем методам внешнего класса независимо от спецификаторов
            privateMethod();    // OK - private
            packageMethod();    // OK - package
            protectedMethod();  // OK - protected
            publicMethod();     // OK - public
        }
    }
    
    public void demonstrateInnerClass() {
        InnerClass inner = new InnerClass();
        inner.accessAllFieldsAndMethods();
    }
    
    // Статический вложенный класс
    public static class StaticNestedClass {
        public void tryAccess(ClassOuter outer) {
            // Статический класс НЕ имеет автоматического доступа к полям внешнего класса
            // System.out.println(privateField);  // ОШИБКА - нет прямого доступа
            
            // Доступ только через переданный экземпляр и только к доступным членам
            // System.out.println(outer.privateField);  // ОШИБКА - private
            // System.out.println(outer.packageField);  // ОШИБКА - package (другой пакет)
            System.out.println(outer.publicField);     // OK - public
            
            // Аналогично с методами
            // outer.privateMethod();  // ОШИБКА - private
            outer.publicMethod();     // OK - public
        }
        
        public static void staticNestedMethod() {
            System.out.println("Статический метод вложенного класса");
        }
    }
}

// Использование в другом классе
class TestClasses {
    public static void main(String[] args) {
        ClassOuter outer = new ClassOuter();
        
        // Создание внутреннего класса
        ClassOuter.InnerClass inner = outer.new InnerClass();
        inner.accessAllFieldsAndMethods();
        
        // Создание статического вложенного класса
        ClassOuter.StaticNestedClass nested = new ClassOuter.StaticNestedClass();
        nested.tryAccess(outer);
        ClassOuter.StaticNestedClass.staticNestedMethod();
    }
}

// Ответ:
// Да, внутренний класс имеет доступ ко всем полям и методам внешнего класса, включая private-поля и private-методы. 
// Особенности доступа:
// 1. Внутренний класс (inner class) - имеет полный доступ ко всем членам внешнего класса независимо от спецификаторов доступа
// 2. Статический вложенный класс (static nested class) - не имеет автоматического доступа, подчиняется обычным правилам доступа как отдельный класс

// Причина: Внутренний класс неявно содержит ссылку на экземпляр внешнего класса, поэтому он может обращаться к его приватным членам как "своим собственным". 
// Это нарушение инкапсуляции оправдано тесной связью между внешним и внутренним классами.