import java.util.HashMap;
import java.util.Map;

public class Student {
    private String name;
    private int roll_number;
    private SchoolClass schoolClass;
    private HashMap<String,Double> marks;

    public Student(String name, int roll_number, SchoolClass schoolClass) {
        this.name = name;
        this.roll_number = roll_number;
        this.schoolClass = schoolClass;
        marks = new HashMap();
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

    public Map getMarks() {
        return marks;
    }

    public void setMarks(String subject,double mark) {
        marks.put(subject,mark);
    }



    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", roll_number=" + roll_number +
                '}';
    }
}
