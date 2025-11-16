// Результат выполнения:
// toString: [5, 2, 8, 1, 9]
// after sort: [1, 2, 5, 8, 9]
// binarySearch for 8: 3
// equals: true
// compare: 0
// compare different: -1

// Пояснения:
// - toString(): преобразует массив в читаемую строку
// - binarySearch(): работает только на отсортированных массивах, возвращает индекс элемента
// - equals(): возвращает true если массивы одинаковой длины и содержат одинаковые элементы
// - compare(): возвращает 0 если равны, отрицательное если первый меньше, положительное если первый больше
// - sort(): сортирует массив по возрастанию

import java.util.Arrays;

public class ArraysExample {
    public static void main(String[] args) {
        int[] arr = {5, 2, 8, 1, 9};
        int[] arr2 = {1, 2, 5, 8, 9};
        int[] arr3 = {1, 2, 5, 8, 9};
        
        // toString - представление массива в виде строки
        System.out.println("toString: " + Arrays.toString(arr));
        
        // sort - сортировка массива
        Arrays.sort(arr);
        System.out.println("after sort: " + Arrays.toString(arr));
        
        // binarySearch - поиск в отсортированном массиве
        int index = Arrays.binarySearch(arr, 8);
        System.out.println("binarySearch for 8: " + index);
        
        // equals - сравнение массивов
        boolean isEqual = Arrays.equals(arr2, arr3);
        System.out.println("equals: " + isEqual);
        
        // compare - сравнение массивов
        int comparison = Arrays.compare(arr2, arr3);
        System.out.println("compare: " + comparison);
        
        // compare с разными массивами
        int[] arr4 = {1, 2, 3};
        int[] arr5 = {1, 2, 4};
        int comparison2 = Arrays.compare(arr4, arr5);
        System.out.println("compare different: " + comparison2);
    }
}