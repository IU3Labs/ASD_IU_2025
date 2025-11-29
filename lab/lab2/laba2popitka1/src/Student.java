import java.util.Objects;

public class Student {
    private final Long id;
    private final String name;

    public Student(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    // Сравнение объектов только по id
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        Student student = (Student) o;
        return Objects.equals(id, student.id);
    }

    // Хэш-код тоже только от id
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}