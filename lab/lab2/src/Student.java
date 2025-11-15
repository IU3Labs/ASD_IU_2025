import java.util.*;

public class Student {
    private long id;
    private String name;

    public static void main(String[] args){
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
                                 HashSet<Student> student_set, HashMap<Long, Student> student_map){
        for (int i = 0; i <= 1_000_000; i++){
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
                                     HashSet<Student> student_set, HashMap<Long, Student> student_map){
        System.out.println("Время добавления элемента в конец массива для: ");

        long start = System.nanoTime();
        student_array.add(null);
        long time_array = System.nanoTime();
        student_linked.add(null);
        long time_linked = System.nanoTime();
        student_set.add(null);
        long time_set = System.nanoTime();
        student_map.put(null, null);
        long end = System.nanoTime();

        System.out.println(" ArrayList: " + (time_array - start));
        System.out.println(" LinkedList: " + (time_linked - time_array));
        System.out.println(" HashSet: " + (time_set - time_linked));
        System.out.println(" HashMap: " + (end - time_set));
    }

    public static void timeOfAddingElemAtTheStart(ArrayList<Student> student_array, LinkedList<Student> student_linked,
                                                  HashSet<Student> student_set, HashMap<Long, Student> student_map){
        System.out.println("Время добавления элемента в начало массива для: ");

        long start = System.nanoTime();
        student_array.add(0, null);
        long time_array = System.nanoTime();
        student_linked.add(0, null);
        long time_linked = System.nanoTime();
        student_set.add(null);  //для set и map нет понятия "добавить в начало, конец или середину.
        long time_set = System.nanoTime();
        student_map.put(null, null);
        long end = System.nanoTime();

        System.out.println(" ArrayList: " + (time_array - start));
        System.out.println(" LinkedList: " + (time_linked - time_array));
        System.out.println(" HashSet: " + (time_set - time_linked));
        System.out.println(" HashMap: " + (end - time_set));
    }

    public static void timeOfDeletingLastElement(ArrayList<Student> student_array, LinkedList<Student> student_linked,
                                                   HashSet<Student> student_set, HashMap<Long, Student> student_map){
        System.out.println("Время удаления последнего элемента массива для: ");

        long start = System.nanoTime();
        student_array.remove(student_array.size() - 1);
        long time_array = System.nanoTime();
        student_linked.remove(student_linked.size() - 1);
        long time_linked = System.nanoTime();
        student_set.remove("Студуент №" + (student_set.size() - 1));
        long time_set = System.nanoTime();
        student_map.remove(student_map.size() - 1);
        long end = System.nanoTime();

        System.out.println(" ArrayList: " + (time_array - start));
        System.out.println(" LinkedList: " + (time_linked - time_array));
        System.out.println(" HashSet: " + (time_set - time_linked));
        System.out.println(" HashMap: " + (end - time_set));
    }

    public static void timeOfDeletingFirstElement(ArrayList<Student> student_array, LinkedList<Student> student_linked,
                                                   HashSet<Student> student_set, HashMap<Long, Student> student_map){
        System.out.println("Время удаления первого элемента массива для: ");

        long start = System.nanoTime();
        student_array.remove(0);
        long time_array = System.nanoTime();
        student_linked.remove(0);
        long time_linked = System.nanoTime();
        student_set.remove("Студуент №" + 0);
        long time_set = System.nanoTime();
        student_map.remove(0);
        long end = System.nanoTime();

        System.out.println(" ArrayList: " + (time_array - start));
        System.out.println(" LinkedList: " + (time_linked - time_array));
        System.out.println(" HashSet: " + (time_set - time_linked));
        System.out.println(" HashMap: " + (end - time_set));
    }

    public static void timeOfGettingMiddleElem(ArrayList<Student> student_array, LinkedList<Student> student_linked,
                                               HashMap<Long, Student> student_map){
        System.out.println("Время взятия среднего элемента массива для: ");

        long start = System.nanoTime();
        Student st_arr = student_array.get(500_000);
        long time_array = System.nanoTime();
        Student st_link = student_linked.get(500_000);
        long time_linked = System.nanoTime();
        Student st_map = student_map.get(500_000);
        long end = System.nanoTime();
        // Для HashSet невозможно

        System.out.println(" ArrayList: " + (time_array - start) + " Значение: " + st_arr);
        System.out.println(" LinkedList: " + (time_linked - time_array) + " Значение: " + st_link);
        System.out.println(" HashMap: " + (end - time_linked) + " Значение: " + st_map);
    }

    public static void timeOfGettingFirstElem(ArrayList<Student> student_array, LinkedList<Student> student_linked,
                                              HashMap<Long, Student> student_map){
        System.out.println("Время взятия первого элемента массива для: ");

        long start = System.nanoTime();
        Student st_arr = student_array.get(0);
        long time_array = System.nanoTime();
        Student st_link = student_linked.get(0);
        long time_linked = System.nanoTime();
        Student st_map = student_map.get(0);
        long end = System.nanoTime();
        // Для HashSet невозможно

        System.out.println(" ArrayList: " + (time_array - start) + " Значение: " + st_arr);
        System.out.println(" LinkedList: " + (time_linked - time_array) + " Значение: " + st_link);
        System.out.println(" HashMap: " + (end - time_linked) + " Значение: " + st_map);
    }
}
