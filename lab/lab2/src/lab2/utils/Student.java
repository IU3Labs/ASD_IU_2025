package lab2.utils;

public class Student implements Comparable<Student>{
    long id;
    String name;

    public Student(long t_id, String t_name) {
        id = t_id;
        name = t_name;
    }

    public long getID(){
        return id;
    }
    public String getNAME(){
        return name;
    }

    @Override
    public int compareTo(Student student) {
        return Long.compare(id, student.id);
    }
}
