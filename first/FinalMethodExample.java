package first;
class SuperClass {
    // final метод - нельзя переопределять в подклассах
    public final void finalMethod() {
        System.out.println("Этот метод нельзя переопределить");
    }
    
    // обычный метод - можно переопределять
    public void normalMethod() {
        System.out.println("Этот метод можно переопределить");
    }
}

class SubClass extends SuperClass {
    // ОШИБКА КОМПИЛЯЦИИ - нельзя переопределить final метод
    // public void finalMethod() {
    //     System.out.println("Попытка переопределения");
    // }
    
    // OK - переопределение обычного метода
    @Override
    public void normalMethod() {
        System.out.println("Метод переопределен в подклассе");
    }
}

public class FinalMethodExample {
    public static void main(String[] args) {
        SubClass obj = new SubClass();
        obj.finalMethod();   // вызов final метода из SuperClass
        obj.normalMethod();  // вызов переопределенного метода
    }
}