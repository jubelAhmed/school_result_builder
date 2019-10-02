public class Student {
    private String name;
    private int roll_number;
    private SchoolClass schoolClass;

    public Student(String name, int roll_number, SchoolClass schoolClass) {
        this.name = name;
        this.roll_number = roll_number;
        this.schoolClass = schoolClass;
    }

    public String getName() {
        return name;
    }


    public int getRoll_number() {
        return roll_number;
    }

    public SchoolClass getSchoolClass() {
        return schoolClass;
    }

    public void setSchoolClass(SchoolClass schoolClass) {
        this.schoolClass = schoolClass;
    }
}
