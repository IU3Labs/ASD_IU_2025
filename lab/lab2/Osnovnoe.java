import java.util.*;
//Основное задание. Задание оценивается в 0 баллов.
public class Osnovnoe {
    Long id;
    String name;

    public Osnovnoe(Long id, String name) {
        this.id = id;
        this.name = name;
    }


    public static void main(String[] args) {
        int n = 1000000;

        ArrayList<Osnovnoe> arrayList = new ArrayList<>();
        LinkedList<Osnovnoe> linkedList = new LinkedList<>();
        HashSet<Osnovnoe> hashSet = new HashSet<>();
        HashMap<Long, Osnovnoe> hashMap = new HashMap<>();

        for (long i = 1; i <= n; i++) {
            Osnovnoe osnovnoe = new Osnovnoe(i, "Student " + i);
            arrayList.add(osnovnoe);
            linkedList.add(osnovnoe);
            hashSet.add(osnovnoe);
            hashMap.put(i, osnovnoe);
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

    public static void measureOperations(List<Osnovnoe> list) {
        long start, end;

        start = System.nanoTime();
        list.add(new Osnovnoe(10000001L, "New Student"));
        end = System.nanoTime();
        System.out.println("Добавление в конец: " + (end - start) + " ns");

        start = System.nanoTime();
        list.add(0, new Osnovnoe(10000002L, "New Student"));
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


    public static void measureOperations(Set<Osnovnoe> set) {
        long start, end;


        start = System.nanoTime();
        set.add(new Osnovnoe(10000001L, "New Student"));
        end = System.nanoTime();
        System.out.println("Добавление в set: " + (end - start) + " ns");


        start = System.nanoTime();
        set.remove(new Osnovnoe(1L, "Student 1"));
        end = System.nanoTime();
        System.out.println("Удаление из set: " + (end - start) + " ns");


        start = System.nanoTime();
        set.contains(new Osnovnoe(5000000L, "Student 5000000"));
        end = System.nanoTime();
        System.out.println("Поиск элемента: " + (end - start) + " ns");
    }

    public static void measureOperations(Map<Long, Osnovnoe> map) {
        long start, end;

        start = System.nanoTime();
        map.put(10000001L, new Osnovnoe(10000001L, "New Student"));
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
