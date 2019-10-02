import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;

public class StudentControl {
    private static LinkedList<Student> studentList;

    public LinkedList<Student> getStudentList() {
        return studentList;
    }

    public StudentControl(){
        studentList = new LinkedList<>();
    }

    public Student getSingleStudent(int rollNumber,SchoolClass sc){
        for(Student student: studentList){
            if(student.getRoll_number() == rollNumber && student.getSchoolClass().getClassName().equals(sc.getClassName()) && student.getSchoolClass().getSectionName().equals(sc.getSectionName())){
                return student;
            }
        }
        return null;
    }

    public HashMap addStudent(Student student){
        HashMap<Boolean,String> mp = new HashMap();
        if(checkUniqueRollNumber(student) ){
            if(checkClassEmpty(student.getSchoolClass())){
                studentList.add(student);
                mp.put(true,"student added successful");

                return mp;
            }else{
               mp.put(false," "+student.getSchoolClass().getClassName() + " and Section : "+student.getSchoolClass().getSectionName() + " is not empty");
                return mp;
            }

        }else{
             mp.put(false,"Student Role Number "+student.getRoll_number()+" is not unique and role must be less than 40 and up to 0 ");
             return mp;
        }

    }

    private boolean checkUniqueRollNumber(Student student){
        if(studentList.isEmpty()){
            return true;
        }
        for(Student sd  : studentList){
            if(sd.getRoll_number() != student.getRoll_number() && student.getRoll_number()<=40 && student.getRoll_number()>0){
                return true;
            }
        }
        return false;
    }

    private boolean checkClassEmpty(SchoolClass schoolClass){
        int student_count = 1;
        for(Student std:studentList){
            if(std.getSchoolClass().getClassName().equals(schoolClass.getClassName()) && std.getSchoolClass().getSectionName().equals(schoolClass.getSectionName())){
                student_count++;
            }
        }
        if(student_count <= 40){
            return true;
        }
        else return false;
    }

    public void addStudentMarks(Student sd,double score,int position){
        sd.setMarks(sd.getSchoolClass().getSubjects()[position],score);
    }

    public String showPersonalResult(Student st){
        StringBuffer sb = new StringBuffer();
        Map marksMap = st.getMarks();
        LinkedList marksList= new LinkedList();
        sb.append("Student name : "+st.getName() + " and Rool is : "+st.getRoll_number() + "\n\n");

        sb.append("Subject     ||     marks     ||     grade   \n\n");

        for(Object key : marksMap.keySet()) {
            Object value = marksMap.get(key);
            sb.append(key + "    ||   " +value + "   ||   "+grade((Double) value) + "\n");
            marksList.add(value);
        }
        sb.append("\nTotal Grade point is : "+ grade(totalGradePoint(marksList)) + "\n\n");
        return sb.toString();
    }



    public String showAllStudentResult(){
        StringBuffer sb = new StringBuffer();
        for(Student std : this.studentList){
            LinkedList marksList= new LinkedList();
            Map marksMap = std.getMarks();
            sb.append("\n***** Student Name : "+std.getName() + " *****\n");
            sb.append("***** Student Roll : "+std.getRoll_number() + " *****\n\n");
            sb.append("Subject     ||     marks     ||     grade   \n\n");
            for(Object key : marksMap.keySet()) {
                Object value = marksMap.get(key);
                sb.append(key + "    ||   " +value + "   ||   "+grade((Double) value) + "\n");
                marksList.add(value);
            }
            sb.append("\nTotal Grade point is : "+ grade(totalGradePoint(marksList)) + "\n\n");
        }

        return sb.toString();
    }

    public String showProvidedStudentResult(LinkedList<Student> students){
        StringBuffer sb = new StringBuffer();
        System.out.println("\n\nShow All Uploaded Students Mark Sheet\n\n");
        for(Student std : students){
            LinkedList marksList= new LinkedList();
            Map marksMap = std.getMarks();
            sb.append("\n***** Student Name : "+std.getName() + " *****\n");
            sb.append("***** Student Roll : "+std.getRoll_number() + " *****\n\n");
            sb.append("Subject     ||     marks     ||     grade   \n\n");
            for(Object key : marksMap.keySet()) {
                Object value = marksMap.get(key);
                sb.append(key + "    ||   " +value + "   ||   "+grade((Double) value) + "\n");
                marksList.add(value);
            }
            sb.append("\nTotal Grade point is : "+ grade(totalGradePoint(marksList)) + "\n\n");
        }

        return sb.toString();
    }

    private String grade(double marks){
        if(marks<=100 && marks>=0){
            if( marks>=80){
                return "A";
            }else if(marks >=60){
                return "B";
            }else if(marks >= 40){
                return "C";
            }else{
                return "Fail";
            }
        }
        return "Invalid marks";
    }

    private double totalGradePoint(LinkedList marks){
        double sum = 0;
        for(int i = 1 ; i<marks.size() ; i++){
            double mark = (double) marks.get(i);
            if(  mark>=40 && mark<=100){
                sum += mark;
            }else{
                return 0;
            }
        }
        double grade = sum/marks.size();
        return grade;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        for (Student student:studentList){
            sb.append(student.getName() + " "+student.getRoll_number());
        }
        return sb.toString();
    }
}
