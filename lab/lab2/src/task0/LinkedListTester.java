package task0;

import java.util.LinkedList;
import java.util.List;

public class LinkedListTester extends CollectionTester{
    private List<Student> linkedList;
    private long creationTime;

    public LinkedListTester(int size) {
        super("LinkedList", size);
    }

    @Override
    public void createCollection() {
        long startTime = System.nanoTime();
        linkedList = new LinkedList<>();
        for (long i = 0; i < size; i++) {
            linkedList.add(new Student(i, "Student" + i));
        }
        creationTime = System.nanoTime() - startTime;
    }

    @Override
    public void testAddToEnd() {
        long startTime = System.nanoTime();
        linkedList.add(new Student((long) size + 1, "NewStudent"));
        long duration = System.nanoTime() - startTime;
        System.out.println(duration + " нс");
    }

    @Override
    public void testAddToStart() {
        long startTime = System.nanoTime();
        linkedList.add(0, new Student((long) size + 2, "NewStudent2"));
        long duration = System.nanoTime() - startTime;
        System.out.println(duration + " нс");
    }

    @Override
    public void testRemoveLast() {
        long startTime = System.nanoTime();
        if (!linkedList.isEmpty()) {
            linkedList.remove(linkedList.size() - 1);
        }
        long duration = System.nanoTime() - startTime;
        System.out.println(duration + " нс");
    }

    @Override
    public void testRemoveFirst() {
        long startTime = System.nanoTime();
        if (!linkedList.isEmpty()) {
            linkedList.remove(0);
        }
        long duration = System.nanoTime() - startTime;
        System.out.println(duration + " нс");
    }

    @Override
    public void testGetMiddle() {
        long startTime = System.nanoTime();
        Student middle = linkedList.get(size / 2);
        long duration = System.nanoTime() - startTime;
        System.out.println(duration + " нс");
    }

    @Override
    public void testGetLast() {
        long startTime = System.nanoTime();
        Student last = linkedList.get(linkedList.size() - 1);
        long duration = System.nanoTime() - startTime;
        System.out.println(duration + " нс");
    }

    @Override
    public long getCreationTime() {
        return creationTime;
    }

    public static void main(String[] args) {
        LinkedListTester tester = new LinkedListTester(100000);
        tester.createCollection();
        tester.runAllTests();
    }
}
/*
Тестирование LinkedList
Результаты тестов (в наносекундах):
Добавление в конец: 192875 нс
Добавление в начало: 169916 нс
Удаление последнего: 115750 нс
Удаление первого: 97042 нс
Получение центрального: 1660458 нс
Получение последнего: 2958 нс
*/
