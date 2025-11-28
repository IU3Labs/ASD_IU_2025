package lab2;

import java.util.Objects;

public class Student {
    private final long id;
    private final String name;

    public Student(long id, String name){
        this.id = id;
        this.name = name;
    }

    public long getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        Student other = (Student) o;
        return this.id == other.id;
    }

    @Override
    public int hashCode(){
        return Long.hashCode(id);
    }
}
