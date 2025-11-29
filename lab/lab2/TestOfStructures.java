/*Основное задание.
        В методе main:
        1.Создать ArrayList, который хранит объекты класса Student
        (ArrayList<Student>).
        2.Создать LinkedList, который хранит объекты класса Student
        (LinkedList <Student>).
        3.Создать Set, который хранит объекты класса Student (HashSet
        <Student>).
        4.Создать HashMap, который хранит объекты класса Student (HashMap
        <Long, Student>).
        В каждую структуру данных добавить 10 000 000 объектов.
        После этого для каждой структуры данных измерить время в нс:
        1.Добавление 1 несуществующего элемента в конец (id = 10 000 001).
        2.Добавление 1 несуществующего элемента в начало.
        3.Удаление последнего элемента
        4.Удаление первого элемента
        5.Взятие (Get) центрального элемента (id = 5 000 000)
        6 Взятие (Get) последнего элемента (id = 9 999 999).
Помимо кода решение должно содержать цифры, полученные при
тестах. При невозможности работать с 10 000 000 записей позволительно
несколько сократить количество объектов.
*/


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public class TestOfStructures {
        private static final int SIZE = 1_000_000;
        private static final Student newEndStudent = new Student((long)SIZE, "New_End_Student");
        private static final Student newStartStudent = new Student((long) -1, "New_Start_Student");
        private static final Student centerStudent = new Student((long) SIZE / 2,"Center");
        private static final Student endStudent = new Student((long) SIZE - 1,"Last_Student");
        private static final Student firstStudent = new Student(0L,"Last_Student");

        private static void testArrayList(ArrayList<Student> arrayList, int size) {

            // 1. Добавление в конец
            long startTime = System.nanoTime();
            arrayList.add(newEndStudent);
            long time1 = System.nanoTime() - startTime;
            System.out.println("1. Добавление в конец: " + time1 + " нс");

            // 2. Добавление в начало
            startTime = System.nanoTime();
            arrayList.add(newStartStudent );
            long time2 = System.nanoTime() - startTime;
            System.out.println("2. Добавление в начало: " + time2 + " нс");

            // 3. Удаление последнего элемента
            startTime = System.nanoTime();
            if (!arrayList.isEmpty()) {
                arrayList.remove(endStudent);
            }
            long time3 = System.nanoTime() - startTime;
            System.out.println("3. Удаление последнего: " + time3 + " нс");

            // 4. Удаление первого элемента
            startTime = System.nanoTime();
            if (!arrayList.isEmpty()) {
                arrayList.remove(firstStudent);
            }
            long time4 = System.nanoTime() - startTime;
            System.out.println("4. Удаление первого: " + time4 + " нс");

            // 5. Получение центрального элемента
            startTime = System.nanoTime();
            Student middleArrayList = arrayList.get(SIZE / 2);
            long time5 = System.nanoTime() - startTime;
            System.out.println("5. Получение центрального: " + time5 + " нс");

            // 6. Получение последнего элемента
            startTime = System.nanoTime();
            Student lastArrayList = arrayList.get(arrayList.size() - 1);
            long time6 = System.nanoTime() - startTime;
            System.out.println("6. Получение последнего: " + time6 + " нс\n");
        }
        private static void testLinkedList(LinkedList<Student> linkedList, int size) {

            // 1. Добавление в конец
            long startTime = System.nanoTime();
            linkedList.add(newEndStudent);
            long time1 = System.nanoTime() - startTime;
            System.out.println("1. Добавление в конец: " + time1 + " нс");

            // 2. Добавление в начало
            startTime = System.nanoTime();
            linkedList.add(newStartStudent );
            long time2 = System.nanoTime() - startTime;
            System.out.println("2. Добавление в начало: " + time2 + " нс");

            // 3. Удаление последнего элемента
            startTime = System.nanoTime();
            if (!linkedList.isEmpty()) {
                linkedList.remove(endStudent);
            }
            long time3 = System.nanoTime() - startTime;
            System.out.println("3. Удаление последнего: " + time3 + " нс");

            // 4. Удаление первого элемента
            startTime = System.nanoTime();
            if (!linkedList.isEmpty()) {
                linkedList.remove(firstStudent);
            }
            long time4 = System.nanoTime() - startTime;
            System.out.println("4. Удаление первого: " + time4 + " нс");

            // 5. Получение центрального элемента
            startTime = System.nanoTime();
            Student middleArrayList = linkedList.get(SIZE / 2);
            long time5 = System.nanoTime() - startTime;
            System.out.println("5. Получение центрального: " + time5 + " нс");

            // 6. Получение последнего элемента
            startTime = System.nanoTime();
            Student lastArrayList = linkedList.get(linkedList.size() - 1);
            long time6 = System.nanoTime() - startTime;
            System.out.println("6. Получение последнего: " + time6 + " нс\n");


        }
        private static void testHashSet(HashSet<Student> hashSet, int size) {

            // 1. Добавление элемента (аналог "в конец" - для Set нет понятия начала/конца)
            long startTime = System.nanoTime();
            hashSet.add(newEndStudent);
            long time1 = System.nanoTime() - startTime;
            System.out.println("1.Добавление элемента: " + time1 + " нс");

            // 2. Добавление в начало - для Set не применимо

            // 3. Удаление последнего элемента - для Set не применимо
            // Вместо этого удалим произвольный элемент
            startTime = System.nanoTime();
            hashSet.remove(newEndStudent);
            long time2 = System.nanoTime() - startTime;
            System.out.println("2.Удаление элемента: " + time2 + " нс");

            // 5. Поиск центрального элемента
            startTime = System.nanoTime();
            boolean contains = hashSet.contains(centerStudent);
            long time3 = System.nanoTime() - startTime;
            System.out.println("3.Наличие центрального элемента: " + time3 + " нс");

            // 6. Поиск последнего элемента
            startTime = System.nanoTime();
            contains = hashSet.contains(endStudent);
            long time4 = System.nanoTime() - startTime;
            System.out.println("4.Наличие последнего элемента: " + time4 + " нс");
        }

        private static void testHashMap(HashMap<Long, Student> hashMap, int size) {

            // 1. Добавление элемента
            long startTime = System.nanoTime();
            hashMap.put(newEndStudent.getId(), newEndStudent);
            long time1 = System.nanoTime() - startTime;
            System.out.println("1.Добавление элемента: " + time1 + " нс");

            // 2. Добавление в начало - для HashMap не применимо

            // 3. Удаление элемента элемента
            startTime = System.nanoTime();
            hashMap.remove(newEndStudent.getId());
            long time2 = System.nanoTime() - startTime;
            System.out.println("2.Удаление элемента: " + time2 + " нс" );

            // 4. Получение центрального элемента
            startTime = System.nanoTime();
            hashMap.get(centerStudent.getId());
            long time3 = System.nanoTime() - startTime;
            System.out.println("3.Получение центрального элемента: " + time3 + " нс");

            // 5. Получение последнего элемента
            startTime = System.nanoTime();
            hashMap.get(endStudent.getId());
            long time4 = System.nanoTime() - startTime;
            System.out.println("4.Получение последнего элемента: " + time4 + " нс");
        }

        public static void main(String[] args) {

            //Создаем структуры данных
            ArrayList<Student> arrayList = new ArrayList<>();
            LinkedList<Student> linkedList = new LinkedList<>();
            HashSet<Student> hashSet = new HashSet<>();
            HashMap<Long, Student> hashMap = new HashMap<>();

            //Время заполнения структур данными
            long startTime = System.nanoTime();
            System.out.println("Заполнение структур данных...");
            for (long i = 0; i < SIZE; i++) {
                Student student = new Student(i, "Student_" + i);
                arrayList.add(student);
                linkedList.add(student);
                hashSet.add(student);
                hashMap.put(i, student);
            }
            long fillTime = System.nanoTime() - startTime;
            System.out.println("Заполнение завершено за: " + fillTime/1000000  + " мс\n");


            // 1. ArrayList операции
            System.out.println("\n ArrayList ");
            testArrayList(arrayList, SIZE);

            // 2. LinkedList операции
            System.out.println("\n LinkedList ");
            testLinkedList(linkedList, SIZE);

            // 3. HashSet операции
            System.out.println("\n HashSet ");
            testHashSet(hashSet, SIZE);

            // 4. HashMap операции
            System.out.println("\n HashMap ");
            testHashMap(hashMap, SIZE);
        }
    }
/*
Вывод программы для 1 000 000 записей:
Заполнение структур данных...
Заполнение завершено за: 791 мс


        ArrayList
1. Добавление в конец: 35042 нс
2. Добавление в начало: 1292 нс
3. Удаление последнего: 6620375 нс
4. Удаление первого: 3612125 нс
5. Получение центрального: 4208 нс
6. Получение последнего: 2583 нс


        LinkedList
1. Добавление в конец: 51708 нс
2. Добавление в начало: 500 нс
3. Удаление последнего: 9544792 нс
4. Удаление первого: 4178500 нс
5. Получение центрального: 2204333 нс
6. Получение последнего: 44750 нс

             HashSet
1.Добавление элемента: 122250 нс
2.Удаление элемента: 28459 нс
3.Наличие центрального элемента: 212250 нс
4.Наличие последнего элемента: 1208 нс

              HashMap
1.Добавление элемента: 4417 нс
2.Удаление элемента: 4500 нс
3.Получение центрального элемента: 533417 нс
4.Получение последнего элемента: 195041 нс
*/
