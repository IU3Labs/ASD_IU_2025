import java.util.HashMap;
import java.util.List;

public class HashMapMeas {
    private static final int CAPACITY = 10_000_000;

    // Создание
    public static HashMap<Long, Student> createHashMap(List<Student> students) {
        long start = System.nanoTime();
        HashMap<Long, Student> map = new HashMap<>(CAPACITY);
        for (Student student : students) {
            map.put(student.getId(), student);
        }
        //System.out.println("HashMap: " + (System.nanoTime() - start) + " ns");
        return map;
    }

    // Добавить элемент
    public static void measureSingleAdd(HashMap<Long, Student> map, Student newStudent, String operationName) {
        GeneralUtil.measureNs("HashMap single adding element" + operationName, () -> map.get(newStudent.getId()));
    }

    // Получить центральный
    public static void measureGetMiddle(HashMap<Long, Student> map, Student student) {
        GeneralUtil.measureNs("HashMap get middle time: ", () -> map.get(student.getId()));
    }

    // Получить последний
    public static void measureGetLast(HashMap<Long, Student> map) {
        GeneralUtil.measureNs("HashMap get last time: ", () -> map.get(10_000_000L));
    }

    // Удалить первый
    public static void measureRemoveFirst(HashMap<Long, Student> map) {
        GeneralUtil.measureNs("HashMap remove first time: ", () -> map.get(0L));
    }

    // Удалить последний
    public static void measureRemoveLast(HashMap<Long, Student> map) {
        GeneralUtil.measureNs("HashMap remove last time: ", () -> map.get(10_000_001L));
    }
}
