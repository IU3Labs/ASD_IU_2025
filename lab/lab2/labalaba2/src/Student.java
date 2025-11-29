public class Student {
    private Long id;
    private String name;

    public Student(Long gotID, String gotName) {
        this.id = gotID;
        this.name = gotName;
    }

    public Student() {
        this(0L, "none");
    }

    public Long getID() {
        return id;
    }

    public void setID(Long myID) {
        this.id = myID;
    }

    public String getName() {
        return name;
    }

    public void setName(String myName) {
        this.name = myName;
    }
}
