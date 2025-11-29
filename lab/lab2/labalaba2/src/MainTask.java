import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public class MainTask {
    public static void main(String[] args) {

        // Создание коллекций
        ArrayList<Student> arrayListOfStudents = new ArrayList<>();
        LinkedList<Student> linkedListOfStudents = new LinkedList<>();
        HashSet<Student> hashSetOfStudents = new HashSet<>();
        HashMap<Long, Student> hashMapOfStudents = new HashMap<>();

        // Заполнение коллекций одним миллионом студентов
        for (long i = 1; i <= 1000000; i++) {
            Student student = new Student(i, i + " student's name");
            arrayListOfStudents.add(student);
            linkedListOfStudents.add(student);
            hashSetOfStudents.add(student);
            hashMapOfStudents.put(i, student);
        }


        long startOfProcess;
        long endOfProcess;


        // Удаление первого элемента
        System.out.println("Удаление первого: ");
        startOfProcess = System.nanoTime();
        arrayListOfStudents.remove(0);
        endOfProcess = System.nanoTime();
        System.out.printf("%s %d %c", "Массив: ", endOfProcess - startOfProcess, '\n');

        startOfProcess = System.nanoTime();
        linkedListOfStudents.removeFirst();
        endOfProcess = System.nanoTime();
        System.out.printf("%s %d %c", "Связанный список: ", endOfProcess - startOfProcess, '\n');

        startOfProcess = System.nanoTime();
        hashSetOfStudents.remove(hashSetOfStudents.iterator().next());
        endOfProcess = System.nanoTime();
        System.out.printf("%s %d %c", "Хэшсет: ", endOfProcess - startOfProcess, '\n');

        startOfProcess = System.nanoTime();
        hashMapOfStudents.remove(1L);
        endOfProcess = System.nanoTime();
        System.out.printf("%s %d %c", "Хэшмап: ", endOfProcess - startOfProcess, '\n');

        // Удаление последнего элемента
        System.out.println("Удаление последнего: ");
        startOfProcess = System.nanoTime();
        arrayListOfStudents.remove(arrayListOfStudents.size() - 1);
        endOfProcess = System.nanoTime();
        System.out.printf("%s %d %c", "Массив: ", endOfProcess - startOfProcess, '\n');

        startOfProcess = System.nanoTime();
        linkedListOfStudents.removeLast();
        endOfProcess = System.nanoTime();
        System.out.printf("%s %d %c", "Связанный список: ", endOfProcess - startOfProcess, '\n');

        startOfProcess = System.nanoTime();
        endOfProcess = System.nanoTime();
        System.out.printf("%s %d %c", "Хэшсет: ", endOfProcess - startOfProcess, '\n');

        startOfProcess = System.nanoTime();
        hashMapOfStudents.remove(1000000L);
        endOfProcess = System.nanoTime();
        System.out.printf("%s %d %c", "Хэшмап: ", endOfProcess - startOfProcess, '\n');

        // Добавление элемента в начало
        Student newStudent = new Student(1000001L, 1000001L + " student's name");
        System.out.println("Добавление элемента в начало: ");
        startOfProcess = System.nanoTime();
        arrayListOfStudents.add(0, newStudent);
        endOfProcess = System.nanoTime();
        System.out.printf("%s %d %c", "Массив: ", endOfProcess - startOfProcess, '\n');

        startOfProcess = System.nanoTime();
        linkedListOfStudents.addFirst(newStudent);
        endOfProcess = System.nanoTime();
        System.out.printf("%s %d %c", "Связанный список: ", endOfProcess - startOfProcess, '\n');

        startOfProcess = System.nanoTime();
        hashSetOfStudents.add(newStudent);
        endOfProcess = System.nanoTime();
        System.out.printf("%s %d %c", "Хэшсет: ", endOfProcess - startOfProcess, '\n');

        startOfProcess = System.nanoTime();
        hashMapOfStudents.put(newStudent.getID(), newStudent);
        endOfProcess = System.nanoTime();
        System.out.printf("%s %d %c", "Хэшмап: ", endOfProcess - startOfProcess, '\n');

        // Добавление элемента в конец
        Student newEndStudent = new Student(1000002L, 1000002L + " student's name");
        System.out.println("Добавление элемента в конец: ");
        startOfProcess = System.nanoTime();
        arrayListOfStudents.add(newEndStudent);
        endOfProcess = System.nanoTime();
        System.out.printf("%s %d %c", "Массив: ", endOfProcess - startOfProcess, '\n');

        startOfProcess = System.nanoTime();
        linkedListOfStudents.addLast(newEndStudent);
        endOfProcess = System.nanoTime();
        System.out.printf("%s %d %c", "Связанный список: ", endOfProcess - startOfProcess, '\n');

        startOfProcess = System.nanoTime();
        hashSetOfStudents.add(newEndStudent);
        endOfProcess = System.nanoTime();
        System.out.printf("%s %d %c", "Хэшсет: ", endOfProcess - startOfProcess, '\n');

        startOfProcess = System.nanoTime();
        hashMapOfStudents.put(newEndStudent.getID(), newEndStudent);
        endOfProcess = System.nanoTime();
        System.out.printf("%s %d %c", "Хэшмап: ", endOfProcess - startOfProcess, '\n');

        // Вывод центрального элемента
        System.out.println("Вывод центрального элемента: ");
        startOfProcess = System.nanoTime();
        Student centerStudent = (Student) arrayListOfStudents.get(500000);
        endOfProcess = System.nanoTime();
        System.out.printf("%s %d %c", "Массив: ", endOfProcess - startOfProcess, '\n');

        startOfProcess = System.nanoTime();
        centerStudent = (Student) linkedListOfStudents.get(500000);
        endOfProcess = System.nanoTime();
        System.out.printf("%s %d %c", "Связанный список: ", endOfProcess - startOfProcess, '\n');

        startOfProcess = System.nanoTime();
        hashSetOfStudents.contains(new Student(500000L, "500000"));
        endOfProcess = System.nanoTime();
        System.out.printf("%s %d %c", "Хэшсет: ", endOfProcess - startOfProcess, '\n');

        startOfProcess = System.nanoTime();
        centerStudent = (Student) hashMapOfStudents.get(500000L);
        endOfProcess = System.nanoTime();
        System.out.printf("%s %d %c", "Хэшмап: ", endOfProcess - startOfProcess, '\n');

        // Вывод посдеднего элемента
        System.out.println("Вывод посдеднего элемента: ");
        startOfProcess = System.nanoTime();
        Student lastStudent = (Student) arrayListOfStudents.get(arrayListOfStudents.size() - 1);
        endOfProcess = System.nanoTime();
        System.out.printf("%s %d %c", "Массив: ", endOfProcess - startOfProcess, '\n');

        startOfProcess = System.nanoTime();
        lastStudent = (Student) linkedListOfStudents.getLast();
        endOfProcess = System.nanoTime();
        System.out.printf("%s %d %c", "Связанный список: ", endOfProcess - startOfProcess, '\n');

        startOfProcess = System.nanoTime();
        lastStudent = (Student) hashSetOfStudents.toArray()[hashSetOfStudents.size() - 1];
        endOfProcess = System.nanoTime();
        System.out.printf("%s %d %c", "Хэшсет: ", endOfProcess - startOfProcess, '\n');

        startOfProcess = System.nanoTime();
        hashMapOfStudents.get(lastStudent.getID());
        endOfProcess = System.nanoTime();
        System.out.printf("%s %d %c", "Хэшмап: ", endOfProcess - startOfProcess, '\n');
    }
}