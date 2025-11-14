package lab2;

import java.util.Objects;

public class Student {
    private final Long id;
    private final String name;

    public Student(Long id, String name){
        this.id = id;
        this.name = name;
    }

    public Long getId(){
        return id;
    }

    public String getName(){
        return name;
    }
    @Override
    public int hashCode(){
        return Objects.hash(id);
    }
}
