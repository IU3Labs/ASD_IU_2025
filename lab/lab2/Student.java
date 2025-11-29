package lab2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public class Student {
    private long id;
    private String name;

    public Student(long id, String name) {
        this.id = id;
        this.name = name;
    }
}

class StudentDemo {
    private static final long LENGTH = 10000000L;

    public static void main(String[] args) {
        ArrayList<Student> arrayList = new ArrayList();

        for(long i = 1L; i <= LENGTH; ++i) {
            arrayList.add(new Student(i, "Student " + i));
        }

        CollectionTest.testArrayList(arrayList);
        LinkedList<Student> linkedList = new LinkedList();

        for(long i = 1L; i <= LENGTH; ++i) {
            linkedList.add(new Student(i, "Student " + i));
        }

        CollectionTest.testLinkedList(linkedList);
        HashSet<Student> hashSet = new HashSet();

        for(long i = 1L; i <= LENGTH; ++i) {
            hashSet.add(new Student(i, "Student " + i));
        }

        CollectionTest.testHashSet(hashSet);
        HashMap<Long, Student> hashMap = new HashMap();

        for(long i = 1L; i <= LENGTH; ++i) {
            hashMap.put(i, new Student(i, "Student " + i));
        }

        CollectionTest.testHashMap(hashMap);
    }
}