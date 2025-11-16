package second;
public enum Operation {
    PLUS("+") {
        public double apply(double x, double y) {
            return x + y;
        }
    },
    MINUS("-") {
        public double apply(double x, double y) {
            return x - y;
        }
    },
    TIMES("*") {
        public double apply(double x, double y) {
            return x * y;
        }
    },
    DIVIDE("/") {
        public double apply(double x, double y) {
            if (y == 0) {
                throw new ArithmeticException("Деление на ноль");
            }
            return x / y;
        }
    };

    private final String symbol;

    Operation(String symbol) {
        this.symbol = symbol;
    }

    // Абстрактный метод - должна быть реализация для каждого элемента
    public abstract double apply(double x, double y);

    // Дополнительный метод
    public String getSymbol() {
        return symbol;
    }

    // Статический метод для поиска по символу
    public static Operation fromSymbol(String symbol) {
        for (Operation op : values()) {
            if (op.symbol.equals(symbol)) {
                return op;
            }
        }
        throw new IllegalArgumentException("Неизвестная операция: " + symbol);
    }

    // Пример использования
    public static void main(String[] args) {
        double result = Operation.PLUS.apply(5, 3);
        System.out.println("5 + 3 = " + result);
        
        Operation op = Operation.fromSymbol("*");
        System.out.println("2 * 4 = " + op.apply(2, 4));
    }
}