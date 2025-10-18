import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public class Student {
    private static final long LENGTH = 10000000L;
    private long id;
    private String name;

    public Student(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static void main(String[] args) {
        ArrayList<Student> arrayList = new ArrayList();

        for(long i = 1L; i <= 10000000L; ++i) {
            arrayList.add(new Student(i, "Student " + i));
        }

        CollectionTests.testArrayList(arrayList);
        LinkedList<Student> linkedList = new LinkedList();

        for(long i = 1L; i <= 10000000L; ++i) {
            linkedList.add(new Student(i, "Student " + i));
        }

        CollectionTests.testLinkedList(linkedList);
        HashSet<Student> hashSet = new HashSet();

        for(long i = 1L; i <= 10000000L; ++i) {
            hashSet.add(new Student(i, "Student " + i));
        }

        CollectionTests.testHashSet(hashSet);
        HashMap<Long, Student> hashMap = new HashMap();

        for(long i = 1L; i <= 10000000L; ++i) {
            hashMap.put(i, new Student(i, "Student " + i));
        }

        CollectionTests.testHashMap(hashMap);
    }
}
