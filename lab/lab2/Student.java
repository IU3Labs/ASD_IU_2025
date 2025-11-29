import java.util.*;
//Основное задание. Задание оценивается в 0 баллов.
public class Student {
    Long id;
    String name;

    public Student(Long id, String name) {
        this.id = id;
        this.name = name;
    }


    public static void main(String[] args) {
        int n = 1000000;

        ArrayList<Student> arrayList = new ArrayList<>();
        LinkedList<Student> linkedList = new LinkedList<>();
        HashSet<Student> hashSet = new HashSet<>();
        HashMap<Long, Student> hashMap = new HashMap<>();

        for (long i = 1; i <= n; i++) {
            Student student = new Student(i, "Student " + i);
            arrayList.add(student);
            linkedList.add(student);
            hashSet.add(student);
            hashMap.put(i, student);
        }

        System.out.println("ArrayList:");
        measureOperations(arrayList);

        System.out.println("LinkedList:");
        measureOperations(linkedList);

        System.out.println("HashSet:");
        measureOperations(hashSet);

        System.out.println("HashMap:");
        measureOperations(hashMap);
    }

    public static void measureOperations(List<Student> list) {
        long start, end;

        start = System.nanoTime();
        list.add(new Student(10000001L, "New Student"));
        end = System.nanoTime();
        System.out.println("Добавление в конец: " + (end - start) + " ns");

        start = System.nanoTime();
        list.add(0, new Student(10000002L, "New Student"));
        end = System.nanoTime();
        System.out.println("Добавление в начало: " + (end - start) + " ns");


        start = System.nanoTime();
        list.remove(list.size() - 1);
        end = System.nanoTime();
        System.out.println("Удаление последнего: " + (end - start) + " ns");


        start = System.nanoTime();
        list.remove(0);
        end = System.nanoTime();
        System.out.println("Удаление первого: " + (end - start) + " ns");


        start = System.nanoTime();
        list.get(list.size() / 2);
        end = System.nanoTime();
        System.out.println("Взятие центрального: " + (end - start) + " ns");


        start = System.nanoTime();
        list.get(list.size() - 1);
        end = System.nanoTime();
        System.out.println("Взятие последнего: " + (end - start) + " ns");
    }


    public static void measureOperations(Set<Student> set) {
        long start, end;


        start = System.nanoTime();
        set.add(new Student(10000001L, "New Student"));
        end = System.nanoTime();
        System.out.println("Добавление в set: " + (end - start) + " ns");


        start = System.nanoTime();
        set.remove(new Student(1L, "Student 1"));
        end = System.nanoTime();
        System.out.println("Удаление из set: " + (end - start) + " ns");


        start = System.nanoTime();
        set.contains(new Student(5000000L, "Student 5000000"));
        end = System.nanoTime();
        System.out.println("Поиск элемента: " + (end - start) + " ns");
    }

    public static void measureOperations(Map<Long, Student> map) {
        long start, end;

        start = System.nanoTime();
        map.put(10000001L, new Student(10000001L, "New Student"));
        end = System.nanoTime();
        System.out.println("Добавление в map: " + (end - start) + " ns");


        start = System.nanoTime();
        map.remove(1L);
        end = System.nanoTime();
        System.out.println("Удаление из map: " + (end - start) + " ns");


        start = System.nanoTime();
        map.get(5000000L);
        end = System.nanoTime();
        System.out.println("Поиск элемента в map: " + (end - start) + " ns");
    }
}