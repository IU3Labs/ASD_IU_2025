package task0;

import java.util.ArrayList;
import java.util.List;

public class ArrayListTester extends CollectionTester{
    public ArrayListTester(int size) {
        super("ArrayList", size);
    }

    private List<Student> arrayList;
    private long creationTime;

    @Override
    public void createCollection() {
        long startTime = System.nanoTime();
        arrayList = new ArrayList<>();
        for (long i = 0; i < size; i++) {
            arrayList.add(new Student(i, "Student" + i));
        }
        creationTime = System.nanoTime() - startTime;
    }

    @Override
    public void testAddToEnd() {
        long startTime = System.nanoTime();
        arrayList.add(new Student((long) size + 1, "NewStudent"));
        long duration = System.nanoTime() - startTime;
        System.out.println(duration + " нс");
    }

    @Override
    public void testAddToStart() {
        long startTime = System.nanoTime();
        arrayList.add(0, new Student((long) size + 2, "NewStudent2"));
        long duration = System.nanoTime() - startTime;
        System.out.println(duration + " нс");
    }

    @Override
    public void testRemoveLast() {
        long startTime = System.nanoTime();
        if (!arrayList.isEmpty()) {
            arrayList.remove(arrayList.size() - 1);
        }
        long duration = System.nanoTime() - startTime;
        System.out.println(duration + " нс");
    }

    @Override
    public void testRemoveFirst() {
        long startTime = System.nanoTime();
        if (!arrayList.isEmpty()) {
            arrayList.remove(0);
        }
        long duration = System.nanoTime() - startTime;
        System.out.println(duration + " нс");
    }

    @Override
    public void testGetMiddle() {
        long startTime = System.nanoTime();
        Student middle = arrayList.get(size / 2);
        long duration = System.nanoTime() - startTime;
        System.out.println(duration + " нс");
    }

    @Override
    public void testGetLast() {
        long startTime = System.nanoTime();
        Student last = arrayList.get(arrayList.size() - 1);
        long duration = System.nanoTime() - startTime;
        System.out.println(duration + " нс");
    }

    @Override
    public long getCreationTime() {
        return creationTime;
    }

    public static void main(String[] args) {
        ArrayListTester tester = new ArrayListTester(100000);
        tester.createCollection();
        tester.runAllTests();
    }
}

/*
Тестирование ArrayList
Результаты тестов (в наносекундах):
Добавление в конец: 76666 нс
Добавление в начало: 49042 нс
Удаление последнего: 37542 нс
Удаление первого: 22875 нс
Получение центрального: 7833 нс
Получение последнего: 1333 нс
*/
