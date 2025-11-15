import service.PerformanceTester;
import model.Student;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Тестирования производительности коллекций");
        ArrayList<Student> arrayList = new ArrayList<>();
        PerformanceTester.addStudentsToArrayList(arrayList);
        PerformanceTester.measureArrayListOperations(arrayList);


        LinkedList<Student> linkedList = new LinkedList<>();
        PerformanceTester.addStudentsToLinkedList(linkedList);
        PerformanceTester.measureLinkedListOperations(linkedList);


        HashSet<Student> hashSet = new HashSet<>();
        PerformanceTester.addStudentsToHashSet(hashSet);
        PerformanceTester.measureHashSetOperations(hashSet);


        HashMap<Long, Student> hashMap = new HashMap<>();
        PerformanceTester.addStudentsToHashMap(hashMap);
        PerformanceTester.measureHashMapOperations(hashMap);

        System.out.println("Тестирование завершено!");
    }
}
