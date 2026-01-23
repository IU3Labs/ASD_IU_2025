import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.HashMap;

public class Student {
    long id;
    String name;

    static final int SIZE = 7500000;

    public static void main(String[] args) {
        long startTime;

        long[] arrayTime, linkedTime, setTime, mapTime;
        arrayTime = new long[6]; // Массив временных отметок ВСЕХ операций над экземпляром (6)
        linkedTime = new long[6];
        setTime = new long[2];
        mapTime = new long[6];

        ArrayList<Student> arSt= new ArrayList<Student>();
        LinkedList<Student>lnSt = new LinkedList<Student>();
        HashSet<Student>hsSt = new HashSet<Student>();
        HashMap<Long, Student>hmSt = new HashMap<Long, Student>();
        for (long i = 0L; i < SIZE; i++) {
            arSt.add(new Student());
            lnSt.add(new Student());
            hsSt.add(new Student());
            hmSt.put(i+1, new Student());
        }
        startTime = System.nanoTime();
        arSt.add(null);
        arrayTime[0] = System.nanoTime() - startTime;

        startTime = System.nanoTime();
        arSt.addFirst(null);
        arrayTime[1] = System.nanoTime() - startTime;

        startTime = System.nanoTime();
        arSt.removeLast();
        arrayTime[2] = System.nanoTime() - startTime;

        startTime = System.nanoTime();
        arSt.removeFirst();
        arrayTime[3] = System.nanoTime() - startTime;

        startTime = System.nanoTime();
        arSt.get(arSt.size() / 2);
        arrayTime[4] = System.nanoTime() - startTime;

        startTime = System.nanoTime();
        arSt.getLast();
        arrayTime[5] = System.nanoTime() - startTime;

        System.out.println("Время операций над ArrayList:");
        System.out.printf("Добавление 1 несуществующего элемента в конец: %d\n" +
                "Добавление 1 несуществующего элемента в начало: %d\n" +
                "Удаление последнего элемента: %d\n" +
                "Удаление первого элемента: %d\n" +
                "Взятие центрального элемента: %d\n" +
                "Взятие последнего элемента: %d\n", arrayTime[0], arrayTime[1], arrayTime[2],
                arrayTime[3], arrayTime[4], arrayTime[5]);

        startTime = System.nanoTime();
        lnSt.add(null);
        linkedTime[0] = System.nanoTime() - startTime;

        startTime = System.nanoTime();
        lnSt.addFirst(null);
        linkedTime[1] = System.nanoTime() - startTime;

        startTime = System.nanoTime();
        lnSt.removeLast();
        linkedTime[2] = System.nanoTime() - startTime;

        startTime = System.nanoTime();
        lnSt.removeFirst();
        linkedTime[3] = System.nanoTime() - startTime;

        startTime = System.nanoTime();
        lnSt.get(lnSt.size() / 2);
        linkedTime[4] = System.nanoTime() - startTime;

        startTime = System.nanoTime();
        lnSt.getLast();
        linkedTime[5] = System.nanoTime() - startTime;

        System.out.println("\n-----------------------------------");
        System.out.println("Время операций над LinkedList:");
        System.out.printf("Добавление 1 несуществующего элемента в конец: %d\n" +
                        "Добавление 1 несуществующего элемента в начало: %d\n" +
                        "Удаление последнего элемента: %d\n" +
                        "Удаление первого элемента: %d\n" +
                        "Взятие центрального элемента: %d\n" +
                        "Взятие последнего элемента: %d\n", linkedTime[0], linkedTime[1], linkedTime[2],
                linkedTime[3], linkedTime[4], linkedTime[5]);

        startTime = System.nanoTime();
        hsSt.add(null);
        setTime[0] = System.nanoTime() - startTime;

        startTime = System.nanoTime();
        hsSt.remove(null);
        setTime[1] = System.nanoTime() - startTime;

        System.out.println("\n-----------------------------------");
        System.out.println("Время операций над HashSet:");
        System.out.printf("Добавление 1 несуществующего элемента: %d\n" +
                        "Удаление 1 несуществующего элемента: %d\n",
                setTime[0], setTime[1]);

        startTime = System.nanoTime();
        hmSt.put(SIZE+2L, null);
        mapTime[0] = System.nanoTime() - startTime;

        startTime = System.nanoTime();
        hmSt.put(0L, null);
        mapTime[1] = System.nanoTime() - startTime;

        startTime = System.nanoTime();
        hmSt.remove(SIZE+2L);
        mapTime[2] = System.nanoTime() - startTime;

        startTime = System.nanoTime();
        hmSt.remove(0L);
        mapTime[3] = System.nanoTime() - startTime;

        startTime = System.nanoTime();
        hmSt.get(SIZE/2L+1);
        mapTime[4] = System.nanoTime() - startTime;

        startTime = System.nanoTime();
        hmSt.get(SIZE+2L);
        mapTime[5] = System.nanoTime() - startTime;

        System.out.println("\n-----------------------------------");
        System.out.println("Время операций над HashMap:");
        System.out.printf("Добавление 1 несуществующего элемента в 'конец': %d\n" +
                        "Добавление 1 несуществующего элемента в 'начало': %d\n" +
                        "Удаление 'последнего' элемента: %d\n" +
                        "Удаление 'первого' элемента: %d\n" +
                        "Взятие 'центрального' элемента: %d\n" +
                        "Взятие 'последнего' элемента: %d\n", mapTime[0], mapTime[1], mapTime[2],
                mapTime[3], mapTime[4], mapTime[5]);

    }
}