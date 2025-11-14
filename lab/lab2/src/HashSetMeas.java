import java.util.HashSet;
import java.util.List;

public class HashSetMeas {
    private static final int CAPACITY = 10_000_000;


    public static HashSet<Student> createHashSet(List<Student> students) {
        long start = System.nanoTime();
        HashSet<Student> set = new HashSet<>(CAPACITY);
        for (Student student : students) {
            set.add(student);
        }
        //System.out.println("HashSet: " + (System.nanoTime() - start) + " ns");
        return set;
    }

    public static void measureSingleAdd(HashSet<Student> students, Student student, String operationName) {
        GeneralUtil.measureNs("HashSet adding element" + operationName, () -> students.add(student));
    }

    public static void measureDelete(HashSet<Student> set, Student studentToDelete, String operationName) {
        GeneralUtil.measureNs("HashSet delete element" + operationName, () -> set.remove(studentToDelete));
    }
}
