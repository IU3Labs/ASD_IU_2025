import java.util.ArrayList;
import java.util.List;

public class GeneralUtil {

    public static void measureNs(String funcName, Runnable func) {
        long start = System.nanoTime();
        func.run();
        long end = System.nanoTime();
        System.out.println(funcName + ": " + (end - start) + "ns");
    }

    public static List<Student> generateStudents(int count) {
        List<Student> list = new ArrayList<>(count);
        for (long i = 1; i <= count; i++) {
            list.add(new Student(i, "Student_" + i));
        }
        return list;
    }
}
