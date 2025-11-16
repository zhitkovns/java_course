package third;
public class OuterClassThird {
    // Внешний класс создает экземпляр внутреннего класса
    private InnerClass inner = new InnerClass();
    
    public void accessInnerClass() {
        // Доступ к public полям и методам внутреннего класса
        inner.publicField = "доступ из внешнего класса";
        inner.publicMethod();
        
        // Доступ к protected полям и методам внутреннего класса
        inner.protectedField = "доступ к protected";
        inner.protectedMethod();
        
        // Доступ к полям и методам по умолчанию внутреннего класса
        inner.packageField = "доступ к package";
        inner.packageMethod();
        
        // Доступ к private полям и методам внутреннего класса - ОШИБКА
        // inner.privateField = "нет доступа"; // Ошибка компиляции
        // inner.privateMethod(); // Ошибка компиляции
    }
    
    // Внутренний класс
    public class InnerClass {
        private String privateField = "private поле";
        String packageField = "package поле";
        protected String protectedField = "protected поле";
        public String publicField = "public поле";
        
        private void privateMethod() {
            System.out.println("private метод внутреннего класса");
        }
        
        void packageMethod() {
            System.out.println("package метод внутреннего класса");
        }
        
        protected void protectedMethod() {
            System.out.println("protected метод внутреннего класса");
        }
        
        public void publicMethod() {
            System.out.println("public метод внутреннего класса");
        }
    }
    
    // Другой метод внешнего класса
    public void createAndAccessInner() {
        // Создание внутреннего класса
        InnerClass newInner = new InnerClass();
        
        // Доступ только к public, protected и package членам
        newInner.publicMethod();
        newInner.protectedMethod();
        newInner.packageMethod();
        
        // private методы недоступны
        // newInner.privateMethod(); // Ошибка компиляции
    }
}

// Класс в том же пакете
class SamePackageClass {
    public void testAccess() {
        OuterClassThird outer = new OuterClassThird();
        
        // Создание внутреннего класса из другого класса
        OuterClassThird.InnerClass inner = outer.new InnerClass();
        
        // Доступ только к public членам из другого класса
        inner.publicField = "доступ из другого класса";
        inner.publicMethod();
        
        // protected, package и private недоступны из другого класса
        // inner.protectedField = "нет доступа"; // Ошибка
        // inner.packageField = "нет доступа"; // Ошибка
        // inner.privateField = "нет доступа"; // Ошибка
    }
}

// Ответ:
// Да, внешний класс имеет доступ к полям и методам внутреннего класса, но доступ зависит от спецификаторов доступа:
// 1. Public - полный доступ из внешнего класса
// 2. Protected - доступен из внешнего класса  
// 3. По умолчанию (package) - доступен из внешнего класса (если в том же пакете)
// 4. Private - НЕ доступен из внешнего класса

// Причина: Внешний класс и внутренний класс - это разные классы, поэтому между ними действуют обычные правила видимости Java. 
// Внешний класс не имеет специальных привилегий для доступа к private-членам внутреннего класса.