import java.util.List;


public class LRUCacheMeas {

    private static final int CAPACITY = 10_000_000;

    public static LRUCache<Long, Student> createLRUCache(List<Student> students, long capacity) {
        LRUCache<Long, Student> cache = new LRUCache<>(capacity);
        for (Student student : students) {
            cache.put(student.getId(), student);
        }
        return cache;
    }

    public static void measureSinglePut(LRUCache<Long, Student> lruCache, Student newStudent, String operationName){
        GeneralUtil.measureNs("LRUCache single adding element " + operationName, () -> lruCache.get(newStudent.getId()));
    }

    // Получить центральный
    public static void measureGetMiddle(LRUCache<Long, Student> lruCache, Student student) {
        GeneralUtil.measureNs("LRUCache get middle time: ", () -> lruCache.get(student.getId()));
    }

    // Получить последний
    public static void measureGetLast(LRUCache<Long, Student> lruCache) {
        GeneralUtil.measureNs("LRUCache get last time: ", () -> lruCache.get(10_000_000L));
    }
}