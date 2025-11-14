import java.util.LinkedList;
import java.util.List;


public class XORLinkedListMeas {
    public static XORLinkedList<Student> createXORLinkedList(List<Student> students) { // Инициализация
        long start = System.nanoTime();
        XORLinkedList<Student> XORlinkedlist = new XORLinkedList<>();
        for (Student student : students) {
            XORlinkedlist.add(student);
        }
        return XORlinkedlist;
    }

    public static void measureSingleAddTail(XORLinkedList<Student> students) {
        GeneralUtil.measureNs("XORLinkedListUtil single adding element in the end: ", () -> students.add(new Student(10_000_001L, "Student_10000001")));
    }

    public static void measureSingleAddHead(XORLinkedList<Student> students) {
        GeneralUtil.measureNs("XORLinkedListUtil single adding element in the beginning: ", () -> students.addFirst(new Student(0L, "FirstStudent")));
    }

    public static void measureSingleDeleteTail(XORLinkedList<Student> students) {
        GeneralUtil.measureNs("XORLinkedListUtil last element delete: ", students::removeLast);
    }

    public static void measureSingleDeleteHead(XORLinkedList<Student> students) {
        GeneralUtil.measureNs("XORLinkedListUtil first element delete: ", students::removeFirst);
    }

    public static void measureGetMiddle(XORLinkedList<Student> students) {
        GeneralUtil.measureNs("XORLinkedListUtil get id = 5 000 000: ", () -> students.get(4999));

    }

    public static void measureGetLast(XORLinkedList<Student> students) {
        GeneralUtil.measureNs("XORLinkedListUtil get last id: ", students::getLast);
    }
}

