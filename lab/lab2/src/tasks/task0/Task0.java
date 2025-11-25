package tasks.task0;

import java.util.*;

public class Task0 {
    private static final int studentsAmount = 10000000;

    public static void task0() {
        List<Student> contingentArrayList= new ArrayList<Student>();

        for( int i = 0; i < studentsAmount; i++){
            contingentArrayList.add(new Student((long) i, "Vitalik"+i));
        }

        LinkedList<Student> contingentLinkedList = new LinkedList<Student>(contingentArrayList);
        HashSet<Student> contingentHashSet = new HashSet<Student>(contingentArrayList);
        HashMap<Long, Student> contingentHashMap = new HashMap<>();
        for (Student vitalik: contingentArrayList){
            contingentHashMap.put(vitalik.getId(), vitalik);
        }

        System.out.println("---------------------------------------------");
        System.out.println("ArrayList time test start...");
        System.out.println("---------------------------------------------");
        long startTime = System.nanoTime();
        contingentArrayList.addLast(new Student((long)studentsAmount+1, "Vitalik"+studentsAmount+1));
        long resultTime = System.nanoTime() - startTime;
        System.out.println("Add to end position time: " + resultTime + " ns");

        startTime = System.nanoTime();
        contingentArrayList.addFirst(new Student((long)studentsAmount+2, "Vitalik"+studentsAmount+2));
        resultTime = System.nanoTime() - startTime;
        System.out.println("Add to start position time: " + resultTime + " ns");

        startTime = System.nanoTime();
        contingentArrayList.remove(contingentArrayList.getLast());
        resultTime = System.nanoTime() - startTime;
        System.out.println("Remove last element time: " + resultTime + " ns");

        startTime = System.nanoTime();
        contingentArrayList.remove(contingentArrayList.getFirst());
        resultTime = System.nanoTime() - startTime;
        System.out.println("Remove first element time: " + resultTime + " ns");

        startTime = System.nanoTime();
        Student centralVitalik = contingentArrayList.get(studentsAmount/2);
        resultTime = System.nanoTime() - startTime;
        System.out.println("Get middle element time: " + resultTime + " ns");

        startTime = System.nanoTime();
        Student lastVitalik = contingentArrayList.get(studentsAmount - 1);
        resultTime = System.nanoTime() - startTime;
        System.out.println("Get last element time: " + resultTime + " ns");

        System.out.println("---------------------------------------------");
        System.out.println("ArrayList time test END.");
        System.out.println("---------------------------------------------\n");

        System.out.println("---------------------------------------------");
        System.out.println("LinkedList time test start...");
        System.out.println("---------------------------------------------");
        startTime = System.nanoTime();
        contingentLinkedList.addLast(new Student((long)studentsAmount+1, "Vitalik"+studentsAmount+1));
        resultTime = System.nanoTime() - startTime;
        System.out.println("Add to end position time: " + resultTime + " ns");

        startTime = System.nanoTime();
        contingentLinkedList.addFirst(new Student((long)studentsAmount+2, "Vitalik"+studentsAmount+2));
        resultTime = System.nanoTime() - startTime;
        System.out.println("Add to start position time: " + resultTime + " ns");

        startTime = System.nanoTime();
        contingentLinkedList.remove(contingentArrayList.getLast());
        resultTime = System.nanoTime() - startTime;
        System.out.println("Remove last element time: " + resultTime + " ns");

        startTime = System.nanoTime();
        contingentLinkedList.remove(contingentArrayList.getFirst());
        resultTime = System.nanoTime() - startTime;
        System.out.println("Remove first element time: " + resultTime + " ns");

        startTime = System.nanoTime();
        centralVitalik = contingentLinkedList.get(studentsAmount/2);
        resultTime = System.nanoTime() - startTime;
        System.out.println("Get middle element time: " + resultTime + " ns");

        startTime = System.nanoTime();
        lastVitalik = contingentLinkedList.get(studentsAmount - 1);
        resultTime = System.nanoTime() - startTime;
        System.out.println("Get last element time: " + resultTime + " ns");

        System.out.println("---------------------------------------------");
        System.out.println("LinkedList time test END.");
        System.out.println("---------------------------------------------\n");

        System.out.println("HashSet time test start...");
        System.out.println("---------------------------------------------");
        startTime = System.nanoTime();
        contingentHashSet.add(new Student((long)studentsAmount+1, "Vitalik"+studentsAmount+1));
        resultTime = System.nanoTime() - startTime;
        System.out.println("Add to end position time: " + resultTime + " ns");

        startTime = System.nanoTime();
        contingentHashSet.remove(contingentArrayList.getLast());
        resultTime = System.nanoTime() - startTime;
        System.out.println("Remove last element time: " + resultTime + " ns");

        startTime = System.nanoTime();
        contingentHashSet.remove(contingentArrayList.getFirst());
        resultTime = System.nanoTime() - startTime;
        System.out.println("Remove first element time: " + resultTime + " ns");

        startTime = System.nanoTime();
        boolean centralVitalikHash = contingentHashSet.contains(contingentArrayList.get(studentsAmount/2));
        resultTime = System.nanoTime() - startTime;
        System.out.println("Get middle element time: " + resultTime + " ns");

        startTime = System.nanoTime();
        lastVitalik = contingentLinkedList.get(studentsAmount - 1);
        resultTime = System.nanoTime() - startTime;
        System.out.println("Get last element time: " + resultTime + " ns");

        System.out.println("---------------------------------------------");
        System.out.println("HashSet time test END.");
        System.out.println("---------------------------------------------\n");
    }
}
