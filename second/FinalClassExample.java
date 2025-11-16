package second;
// Final класс - нельзя наследовать
final class FinalClass {
    public void display() {
        System.out.println("Это final класс");
    }
}

// ОШИБКА КОМПИЛЯЦИИ - нельзя наследовать от final класса
// class SubClass extends FinalClass {
//     public void show() {
//         System.out.println("Попытка наследования");
//     }
// }

// Обычный класс - можно наследовать
class NormalClass {
    public void display() {
        System.out.println("Это обычный класс");
    }
}

// OK - наследование от обычного класса
class SubClass extends NormalClass {
    @Override
    public void display() {
        System.out.println("Метод переопределен в подклассе");
    }
}

public class FinalClassExample {
    public static void main(String[] args) {
        FinalClass finalObj = new FinalClass();
        finalObj.display();
        
        SubClass subObj = new SubClass();
        subObj.display();
    }
}
