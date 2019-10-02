import java.util.HashMap;
import java.util.Map;

public class SchoolClass {
    private String className;
    private String sectionName;
    private String[] subjects;
    private Student student;
    private Map marks;

    public SchoolClass(String className, String sectionName, String[] subjects) {
        this.className = className.toUpperCase();
        this.sectionName = sectionName.toUpperCase();
        this.subjects = subjects;
        marks = new HashMap();
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public String[] getSubjects() {
        return subjects;
    }

    public void setSubjects(String[] subjects) {
        this.subjects = subjects;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Map getMarks() {
        return marks;
    }

    public void setMarks(String subject,double mark) {
        marks.put(subject,mark);
    }
}
