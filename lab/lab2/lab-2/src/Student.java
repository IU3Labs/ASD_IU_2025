public class Student {
    private final Long id;
    private final String name;

    public Student(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }
}
