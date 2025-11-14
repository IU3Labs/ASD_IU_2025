import java.util.LinkedList;
import java.util.List;

public class LinkedListMeas {

    // Создание
    public static LinkedList<Student> createLinkedList(List<Student> students) {
        long start = System.nanoTime();
        LinkedList<Student> linkedlist = new LinkedList<>();
        for (Student student : students) {
            linkedlist.addLast(student);
        }
        //System.out.println("LinkedList: " + (System.nanoTime() - start) + " ns");
        return linkedlist;
    }

    public static void measureSingleAddTail(LinkedList<Student> students) {
        GeneralUtil.measureNs("LinkedListUtil single adding element in the end: ", () -> students.addLast(new Student(10_000_001L, "Student_10000001")));
    }

    public static void measureSingleAddHead(LinkedList<Student> students) {
        GeneralUtil.measureNs("LinkedListUtil single adding element in the beginning: ", () -> students.addFirst(new Student(0L, "FirstStudent")));
    }

    public static void measureSingleDeleteTail(LinkedList<Student> students) {
        GeneralUtil.measureNs("LinkedListUtil last element delete: ", students::removeLast);
    }

    public static void measureSingleDeleteHead(LinkedList<Student> students) {
        GeneralUtil.measureNs("LinkedListUtil first element delete: ", students::removeFirst);
    }

    public static void measureGetMiddle(LinkedList<Student> students) {
        GeneralUtil.measureNs("LinkedListUtil get id = 5 000 000: ", () -> students.listIterator(4999999));
    }

    public static void measureGetLast(LinkedList<Student> students) {
        GeneralUtil.measureNs("LinkedListUtil get last id: ", students::getLast);
    }
}
