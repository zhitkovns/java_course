```java
class A {
    int a1;              // по умолчанию
    public int a2;       // public
    protected int a3;    // protected  
    private int a4;      // private

    void method1() { }        // по умолчанию
    public void method2() { } // public
    protected void method3() { } // protected
    private void method4() { }   // private
}

class B extends A {
    public void testB() {
        a1 = 10;     // Доступно - тот же пакет
        a2 = 20;     // Доступно - public
        a3 = 30;     // Доступно - protected + наследник
        // a4 = 40;  // Недоступно - private
        
        method1();   // Доступно - тот же пакет  
        method2();   // Доступно - public
        method3();   // Доступно - protected + наследник
        // method4(); // Недоступно - private
    }
}

class C extends B {
    public void testC() {
        a1 = 100;    // Доступно - тот же пакет
        a2 = 200;    // Доступно - public  
        a3 = 300;    // Доступно - protected + наследник (через B)
        // a4 = 400; // Недоступно - private
        
        method1();   // Доступно - тот же пакет
        method2();   // Доступно - public
        method3();   // Доступно - protected + наследник
        // method4(); // Недоступно - private
    }
}
```