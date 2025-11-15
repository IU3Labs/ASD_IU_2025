package student;/*Создать класс Student с полями:
1 Long id
2 String name
В методе main:
1 Создать ArrayList, который хранит объекты класса Student
(ArrayList<Student>).
2 Создать LinkedList, который хранит объекты класса Student
(LinkedList <Student>).
3 Создать Set, который хранит объекты класса Student (HashSet
<Student>).
4 Создать HashMap, который хранит объекты класса Student (HashMap
<Long, Student>).
В каждую структуру данных добавить 10 000 000 объектов.
После этого для каждой структуры данных измерить время в нс:
1 Добавление 1 несуществующего элемента в конец (id = 10 000 001).
2 Добавление 1 несуществующего элемента в начало.
3 Удаление последнего элемента
4 Удаление первого элемента
5 Взятие (Get) центрального элемента (id = 5 000 000)
6 Взятие (Get) последнего элемента (id = 9 999 999).
Помимо кода решение должно содержать цифры, полученные при
тестах. При невозможности работать с 10 000 000 записей позволительно
несколько сократить количество объектов.*/


import java.util.*;

public class TaskStudent {

    protected static void measureListPerformance(List<DataTypeStudent> list, DataTypeStudent newDataTypeStudent) {
        int middle = list.size() / 2;
        long start, end;

        start = System.nanoTime();
        list.add(newDataTypeStudent);
        end = System.nanoTime();
        System.out.println(" Добавление в конец: " + (end - start));
        start = System.nanoTime();
        list.add(0, newDataTypeStudent);
        end = System.nanoTime();
        System.out.println("Добавление в начало: " + (end - start));
        start = System.nanoTime();
        list.remove(list.size() - 1);
        end = System.nanoTime();
        System.out.println("Удаление последнего: " + (end - start));
        start = System.nanoTime();
        list.remove(0);
        end = System.nanoTime();
        System.out.println("Удаление первого: " + (end - start));
        start = System.nanoTime();
        list.get(middle);
        end = System.nanoTime();
        System.out.println("Get центрального: " + (end - start));
        start = System.nanoTime();
        list.get(list.size() - 1);
        end = System.nanoTime();
        System.out.println("Get последнего: " + (end - start));
    }

    protected static void measureSetPerformance(Set<DataTypeStudent> set, DataTypeStudent newDataTypeStudent) {
        long start, end;

        start = System.nanoTime();
        set.add(newDataTypeStudent);
        end = System.nanoTime();
        System.out.println("Добавление (HashSet): " + (end - start));
        start = System.nanoTime();
        set.remove(newDataTypeStudent);
        end = System.nanoTime();
        System.out.println("Удаление (HashSet): " + (end - start));
    }

    protected static void measureMapPerformance(Map<Long, DataTypeStudent> map, DataTypeStudent newDataTypeStudent) {
        long start, end;
        int middle = map.size() / 2;
        start = System.nanoTime();
        map.put(newDataTypeStudent.getId(), newDataTypeStudent);
        end = System.nanoTime();
        System.out.println("Добавление (HashMap): " + (end - start));
        start = System.nanoTime();
        map.remove(newDataTypeStudent.getId());
        end = System.nanoTime();
        System.out.println("Удаление (HashMap): " + (end - start));
        start = System.nanoTime();
        map.get(middle);
        end = System.nanoTime();
        System.out.println("Get центрального (HashMap): " + (end - start));
        start = System.nanoTime();
        map.get(map.size());
        end = System.nanoTime();
        System.out.println("Get последнего (HashMap): " + (end - start));
    }

    public static void main(String[] args) {
            int size =10_000_000;
            ArrayList<DataTypeStudent> arrayList = new ArrayList<>();
            List<DataTypeStudent> linkedList = new LinkedList<>();
            Set<DataTypeStudent> hashSet = new HashSet<>();
            Map<Long, DataTypeStudent> hashMap = new HashMap<>();
            for (long i = 0; i < size; i++) {
                DataTypeStudent s = new DataTypeStudent(i, "Student" + i);
                arrayList.add(s);
                linkedList.add(s);
                hashSet.add(s);
                hashMap.put(i, s);
            }
            DataTypeStudent newDataTypeStudent = new DataTypeStudent(10_000_001L, "NewStudent");
            System.out.println("Количество элементов: " + size);
            System.out.println("Измерение времени (мс):\n");
            System.out.println("==ArrayList==");
            measureListPerformance(arrayList, newDataTypeStudent);
            System.out.println("==LinkedList++");
            measureListPerformance(linkedList, newDataTypeStudent);
            System.out.println("==HashSet");
            measureSetPerformance(hashSet, newDataTypeStudent);
            System.out.println("==HashMap==");
            measureMapPerformance(hashMap, newDataTypeStudent);

    }
}

/*==ArrayList==
 Добавление в конец: 600
Добавление в начало: 3994400
Удаление последнего: 8400
Удаление первого: 3738700
Get центрального: 6700
Get последнего: 900
==LinkedList++
 Добавление в конец: 3200
Добавление в начало: 11200
Удаление последнего: 4300
Удаление первого: 1600
Get центрального: 26319700
Get последнего: 4700
==HashSet
Добавление (HashSet): 19500
Удаление (HashSet): 13100
==HashMap==
Добавление (HashMap): 5400
Удаление (HashMap): 9300
Get центрального (HashMap): 25800
Get последнего (HashMap): 2100 */