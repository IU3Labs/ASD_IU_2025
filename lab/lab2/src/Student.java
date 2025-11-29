import java.util.*;

public class Student {
    private long id;
    private String name;

    public static void main(String[] args) {
        Student student = new Student();

        ArrayList<Student> student_array = new ArrayList<>();
        LinkedList<Student> student_linked = new LinkedList<>();
        HashSet<Student> student_set = new HashSet<>();
        HashMap<Long, Student> student_map = new HashMap<>();

        arraysAdd(student_array, student_linked, student_set, student_map);

        timeOfAddingElemToTheEnd(student_array, student_linked, student_set, student_map);

        timeOfAddingElemToTheEnd(student_array, student_linked, student_set, student_map);

        timeOfDeletingLastElement(student_array, student_linked, student_set, student_map);

        timeOfDeletingFirstElement(student_array, student_linked, student_set, student_map);

        timeOfGettingMiddleElem(student_array, student_linked, student_map);

        timeOfGettingFirstElem(student_array, student_linked, student_map);

    }

    public static void arraysAdd(ArrayList<Student> student_array, LinkedList<Student> student_linked,
                                 HashSet<Student> student_set, HashMap<Long, Student> student_map) {
        for (int i = 0; i <= 1_000_000; i++) {
            Student student = new Student();
            student.id = i;
            student.name = "Студент №" + i;

            student_array.add(student);
            student_linked.add(student);
            student_set.add(student);
            student_map.put((long) i, student);
        }
    }

    public static void timeOfAddingElemToTheEnd(ArrayList<Student> student_array, LinkedList<Student> student_linked,
                                                HashSet<Student> student_set, HashMap<Long, Student> student_map) {
        System.out.println("Время добавления элемента в конец массива для: ");

        long start = System.nanoTime();
        student_array.add(null);
        long timearray = System.nanoTime();
        student_linked.add(null);
        long timelinked = System.nanoTime();
        student_set.add(null);
        long timeset = System.nanoTime();
        student_map.put(null, null);
        long end = System.nanoTime();

        System.out.println(" ArrayList: " + (timearray - start));
        System.out.println(" LinkedList: " + (timelinked - timearray));
        System.out.println(" HashSet: " + (timeset - timelinked));
        System.out.println(" HashMap: " + (end - timeset));
    }

    public static void timeOfAddingElemAtTheStart(ArrayList<Student> studentarray, LinkedList<Student> studentlinked,
                                                  HashSet<Student> studentset, HashMap<Long, Student> studentmap) {
        System.out.println("Время добавления элемента в начало массива для: ");

        long start = System.nanoTime();
        studentarray.add(0, null);
        long timearray = System.nanoTime();
        studentlinked.add(0, null);
        long timelinked = System.nanoTime();
        studentset.add(null);  //для set и map нет понятия "добавить в начало, конец или середину.
        long timeset = System.nanoTime();
        studentmap.put(null, null);
        long end = System.nanoTime();

        System.out.println(" ArrayList: " + (timearray - start));
        System.out.println(" LinkedList: " + (timelinked - timearray));
        System.out.println(" HashSet: " + (timeset - timelinked));
        System.out.println(" HashMap: " + (end - timeset));
    }

    public static void timeOfDeletingLastElement(ArrayList<Student> studentarray, LinkedList<Student> studentlinked,
                                                 HashSet<Student> studentset, HashMap<Long, Student> studentmap) {
        System.out.println("Время удаления последнего элемента массива для: ");

        long start = System.nanoTime();
        studentarray.remove(studentarray.size() - 1);
        long timearray = System.nanoTime();
        studentlinked.remove(studentlinked.size() - 1);
        long timelinked = System.nanoTime();
        studentset.remove("Студуент №" + (studentset.size() - 1));
        long timeset = System.nanoTime();
        studentmap.remove(studentmap.size() - 1);
        long end = System.nanoTime();

        System.out.println(" ArrayList: " + (timearray - start));
        System.out.println(" LinkedList: " + (timelinked - timearray));
        System.out.println(" HashSet: " + (timeset - timelinked));
        System.out.println(" HashMap: " + (end - timeset));
    }

    public static void timeOfDeletingFirstElement(ArrayList<Student> studentarray, LinkedList<Student> studentlinked,
                                                  HashSet<Student> studentset, HashMap<Long, Student> studentmap) {
        System.out.println("Время удаления первого элемента массива для: ");

        long start = System.nanoTime();
        studentarray.remove(0);
        long timearray = System.nanoTime();
        studentlinked.remove(0);
        long timelinked = System.nanoTime();
        studentset.remove("Студуент №" + 0);
        long timeset = System.nanoTime();
        studentmap.remove(0);
        long end = System.nanoTime();

        System.out.println(" ArrayList: " + (timearray - start));
        System.out.println(" LinkedList: " + (timelinked - timearray));
        System.out.println(" HashSet: " + (timeset - timelinked));
        System.out.println(" HashMap: " + (end - timeset));
    }

    public static void timeOfGettingMiddleElem(ArrayList<Student> studentarray, LinkedList<Student> studentlinked,
                                               HashMap<Long, Student> studentmap) {
        System.out.println("Время взятия среднего элемента массива для: ");

        long start = System.nanoTime();
        Student starr = studentarray.get(500_000);
        long time_array = System.nanoTime();
        Student stlink = studentlinked.get(500_000);
        long time_linked = System.nanoTime();
        Student stmap = studentmap.get(500_000);
        long end = System.nanoTime();
        // Для HashSet невозможно

        System.out.println(" ArrayList: " + (time_array - start) + " Значение: " + starr);
        System.out.println(" LinkedList: " + (time_linked - time_array) + " Значение: " + stlink);
        System.out.println(" HashMap: " + (end - time_linked) + " Значение: " + stmap);
    }

    public static void timeOfGettingFirstElem(ArrayList<Student> studentarray, LinkedList<Student> studentlinked,
                                              HashMap<Long, Student> studentmap) {
        System.out.println("Время взятия первого элемента массива для: ");

        long start = System.nanoTime();
        Student starr = studentarray.get(0);
        long time_array = System.nanoTime();
        Student stlink = studentlinked.get(0);
        long time_linked = System.nanoTime();
        Student stmap = studentmap.get(0);
        long end = System.nanoTime();
        // Для HashSet невозможно

        System.out.println(" ArrayList: " + (time_array - start) + " Значение: " + starr);
        System.out.println(" LinkedList: " + (time_linked - time_array) + " Значение: " + stlink);
        System.out.println(" HashMap: " + (end - time_linked) + " Значение: " + stmap);
    }
}
