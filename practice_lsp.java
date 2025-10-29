// Базовый класс
class Bird {
    public void move() {
        System.out.println("Птица двигается");
    }
}

// Правильный наследник - соблюдает принцип Лисков
class Sparrow extends Bird {
    @Override
    public void move() {
        System.out.println("Воробей летит");
    }
}

// Еще один правильный наследник
class Penguin extends Bird {
    @Override
    public void move() {
        System.out.println("Пингвин идет");
    }
}

// Код, который использует принцип Лисков
class Test {
    public static void makeBirdMove(Bird bird) {
        bird.move();  // Работает с любой птицей
    }
    
    public static void main(String[] args) {
        Bird sparrow = new Sparrow();
        Bird penguin = new Penguin();
        
        makeBirdMove(sparrow);  // Вывод: "Воробей летит"
        makeBirdMove(penguin);  // Вывод: "Пингвин идет"
    }
}