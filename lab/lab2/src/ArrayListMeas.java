import java.util.ArrayList;
import java.util.List;


public class ArrayListMeas {
    private static final int CAPACITY = 10_000_000;

    // Создание
    public static ArrayList<Student> createArrayList(List<Student> students) {
        long start = System.nanoTime();
        ArrayList<Student> list = new ArrayList<Student>(CAPACITY);
        list.addAll(students);
        return list;
    }

    // Добавление в конец
    public static void measureSingleAddTail(ArrayList<Student> students) {
        Student lStudent = new Student(10_000_001L, "Student_10000001");
        GeneralUtil.measureNs("ArrayList single adding element in the end", () -> students.add(lStudent));
    }

    // Добавление в начало
    public static void measureSingleAddHead(ArrayList<Student> students) {
        Student fStudent = new Student(0L, "FirstStudent");
        GeneralUtil.measureNs("ArrayList single adding element in the beginning", () -> students.addFirst(fStudent));
    }

    // Удаление в конце
    public static void measureSingleDeleteTail(ArrayList<Student> students) {
        GeneralUtil.measureNs("ArrayList last element delete: ", students::removeLast);
    }

    // Удаление в начале
    public static void measureSingleDeleteHead(ArrayList<Student> students) {
        GeneralUtil.measureNs("ArrayList first element delete: ", students::removeFirst);
    }

    // Взятие центрального элемента
    public static void measureGetMiddle(ArrayList<Student> students) {
        GeneralUtil.measureNs("ArrayList get id = 5 000 000: ", () -> students.get(5_000_000));
    }

    public static void measureGetLast(ArrayList<Student> students) {
        GeneralUtil.measureNs("ArrayList get last id: ", students::getLast);
    }
}
