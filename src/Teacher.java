public class Teacher {
    private String teacherName;
    private String email;
    private String password;

    public Teacher(String teacherName, String email, String password) {
        this.teacherName = teacherName;
        this.email = email;
        this.password = password;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getEmail() {
        return email;
    }



    public String getPassword() {
        return password;
    }


}
