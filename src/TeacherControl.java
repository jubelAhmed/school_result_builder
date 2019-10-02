import java.util.LinkedList;

public class TeacherControl {
    public static LinkedList<Teacher> teacherList;

    public TeacherControl() {

        teacherList = new LinkedList<>();
    }

    public void addTeacher(Teacher teacher) {
        teacherList.add(teacher);

    }

    public boolean login(String email , String password){

        for (Teacher t:teacherList){
            if(t.getEmail().equals(email) && t.getPassword().equals(password)){
                return true;
            }
        }
        return false;
    }

}
