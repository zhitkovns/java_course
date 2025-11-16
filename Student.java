// Соглашения для метода equals():
// 1. Рефлексивность: x.equals(x) всегда true
// 2. Симметричность: если x.equals(y) true, то y.equals(x) тоже true
// 3. Транзитивность: если x.equals(y) true и y.equals(z) true, то x.equals(z) true
// 4. Непротиворечивость: многократные вызовы x.equals(y) возвращают одно значение
// 5. Сравнение с null: x.equals(null) всегда false
// 6. Связь с hashCode: если x.equals(y) true, то x.hashCode() == y.hashCode()

// Объяснение реализации:
// - Сравниваем студентов только по studentId, так как это уникальный идентификатор
// - Сначала проверяем ссылочное равенство для оптимизации
// - Проверяем что объект не null и того же класса
// - Приводим тип только после проверки класса
// - Всегда переопределяем hashCode() вместе с equals()


public class Student {
    private String name;
    private int age;
    private String studentId;
    
    public Student(String name, int age, String studentId) {
        this.name = name;
        this.age = age;
        this.studentId = studentId;
    }
    
    @Override
    public boolean equals(Object obj) {
        // 1. Проверка рефлексивности (сравнение с самим собой)
        if (this == obj) return true;
        
        // 2. Проверка на null и совместимость типов
        if (obj == null || getClass() != obj.getClass()) return false;
        
        // 3. Приведение типа
        Student student = (Student) obj;
        
        // 4. Сравнение по значимым полям
        // studentId - уникальный идентификатор студента
        return studentId.equals(student.studentId);
    }
    
    @Override
    public int hashCode() {
        // Согласованность с equals - используем те же поля
        return studentId.hashCode();
    }
}