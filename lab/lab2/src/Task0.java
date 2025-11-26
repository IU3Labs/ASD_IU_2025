import java.util.*;

public class Task0 {
    private static final int CAPACITY = 10_000_000;

    public static void main(String[] args) {
        List<Student> students = GeneralUtil.generateStudents(CAPACITY);

        // ArrayList

        ArrayList<Student> arrayListTest = ArrayListMeas.createArrayList(students);
        ArrayListMeas.measureSingleAddHead(arrayListTest);
        ArrayListMeas.measureSingleAddTail(arrayListTest);
        ArrayListMeas.measureSingleDeleteHead(arrayListTest);
        ArrayListMeas.measureSingleDeleteTail(arrayListTest);
        ArrayListMeas.measureGetMiddle(arrayListTest);
        ArrayListMeas.measureGetLast(arrayListTest);


        // Linked List

        LinkedList<Student> linkedListTest = LinkedListMeas.createLinkedList(arrayListTest);
        LinkedListMeas.measureSingleAddHead(linkedListTest);
        LinkedListMeas.measureSingleAddTail(linkedListTest);
        LinkedListMeas.measureSingleDeleteHead(linkedListTest);
        LinkedListMeas.measureSingleDeleteTail(linkedListTest);
        LinkedListMeas.measureGetMiddle(linkedListTest);
        LinkedListMeas.measureGetLast(linkedListTest);


        // HashSet

        HashSet<Student> hashSetTest = HashSetMeas.createHashSet(arrayListTest);
        HashSetMeas.measureSingleAdd(hashSetTest, new Student(10_000_001L, "newStudent"), "new student");
        HashSetMeas.measureDelete(hashSetTest, new Student(0L, "newStudent"), "new student");


        // HashMap

        HashMap<Long, Student> hashMapTest = HashMapMeas.createHashMap(arrayListTest);
        HashMapMeas.measureSingleAdd(hashMapTest, new Student(10_000_001L, "LastStudent"), "last student");
        HashMapMeas.measureSingleAdd(hashMapTest, new Student(0L, "FirstStudent"), "first student");
        HashMapMeas.measureRemoveFirst(hashMapTest);
        HashMapMeas.measureRemoveLast(hashMapTest);
        HashMapMeas.measureGetMiddle(hashMapTest, new Student(5_000_000L, "Student_5000000"));
        HashMapMeas.measureGetLast(hashMapTest);


        // XORLinkedList

        XORLinkedList<Student> XORLinkedListTest = XORLinkedListMeas.createXORLinkedList(arrayListTest);
        XORLinkedListMeas.measureSingleAddHead(XORLinkedListTest);
        XORLinkedListMeas.measureSingleAddTail(XORLinkedListTest);
        XORLinkedListMeas.measureSingleDeleteHead(XORLinkedListTest);
        XORLinkedListMeas.measureSingleDeleteTail(XORLinkedListTest);
        XORLinkedListMeas.measureGetMiddle(XORLinkedListTest);
        XORLinkedListMeas.measureGetLast(XORLinkedListTest);


        // LRUCache
        LRUCache<Long, Student> lruCache = LRUCacheMeas.createLRUCache(arrayListTest, 10_000_000);
        LRUCacheMeas.measureSinglePut(lruCache, new Student(10_000_001L, "LastStudent"), "first student");
        LRUCacheMeas.measureGetMiddle(lruCache, new Student(5_000_000L, "Student_5000000"));
        LRUCacheMeas.measureGetLast(lruCache);

    }


}
