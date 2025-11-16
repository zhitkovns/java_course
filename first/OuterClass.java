package first;
public class OuterClass {
    private String outerField = "Поле внешнего класса";
    
    // 1. public внутренний класс - доступен везде
    public class PublicInnerClass {
        public void accessOuter() {
            System.out.println(outerField); // имеет доступ к полям внешнего класса
        }
    }
    
    // 2. protected внутренний класс - доступен в пакете и наследникам
    protected class ProtectedInnerClass {
        protected void show() {
            System.out.println("Protected inner class");
        }
    }
    
    // 3. внутренний класс по умолчанию - доступен только в пакете
    class PackagePrivateInnerClass {
        void display() {
            System.out.println("Package private inner class");
        }
    }
    
    // 4. private внутренний класс - доступен только во внешнем классе
    private class PrivateInnerClass {
        private void secret() {
            System.out.println("Private inner class");
        }
    }
    
    // Метод для демонстрации доступа к private внутреннему классу
    public void usePrivateInner() {
        PrivateInnerClass pic = new PrivateInnerClass();
        pic.secret(); // можно использовать внутри внешнего класса
    }
}

// Другой класс в том же пакете
class SamePackageClass {
    public void testAccess() {
        OuterClass outer = new OuterClass();
        
        // public - доступен везде
        OuterClass.PublicInnerClass publicInner = outer.new PublicInnerClass();
        
        // protected - доступен в том же пакете
        OuterClass.ProtectedInnerClass protectedInner = outer.new ProtectedInnerClass();
        
        // по умолчанию - доступен в том же пакете
        OuterClass.PackagePrivateInnerClass packageInner = outer.new PackagePrivateInnerClass();
        
        // private - НЕДОСТУПЕН
        // OuterClass.PrivateInnerClass privateInner = outer.new PrivateInnerClass(); // ошибка
    }
}

// Класс в другом пакете
/*
package other;
import OuterClass;

class DifferentPackageClass {
    public void testAccess() {
        OuterClass outer = new OuterClass();
        
        // public - доступен везде
        OuterClass.PublicInnerClass publicInner = outer.new PublicInnerClass();
        
        // protected - НЕДОСТУПЕН (не наследник)
        // OuterClass.ProtectedInnerClass protectedInner = outer.new ProtectedInnerClass(); // ошибка
        
        // по умолчанию - НЕДОСТУПЕН (другой пакет)
        // OuterClass.PackagePrivateInnerClass packageInner = outer.new PackagePrivateInnerClass(); // ошибка
        
        // private - НЕДОСТУПЕН
        // OuterClass.PrivateInnerClass privateInner = outer.new PrivateInnerClass(); // ошибка
    }
}
*/

// Наследник OuterClass (даже в другом пакете)
class SubClass extends OuterClass {
    public void testInheritance() {
        // protected - доступен наследнику
        ProtectedInnerClass protectedInner = new ProtectedInnerClass();
        
        // public - доступен
        PublicInnerClass publicInner = new PublicInnerClass();
    }
}

// Поведение внутренних классов:

// 1. Все внутренние классы имеют неявную ссылку на внешний класс и доступ к его полям
// 2. Спецификаторы доступа работают так же как для обычных классов:
//    - public - доступен везде
//    - protected - доступен в пакете + наследникам
//    - по умолчанию - доступен только в пакете
//    - private - доступен только во внешнем классе
// 3. Создание объекта внутреннего класса требует экземпляра внешнего класса