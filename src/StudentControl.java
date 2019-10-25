import java.lang.reflect.Array;
import java.util.*;
import java.util.Map.Entry;

public class StudentControl  {
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

    public boolean addStudent(Student student){
        HashMap<Boolean,String> mp = new HashMap();
        if(checkUniqueRollNumber(student) ){
            if(checkClassEmpty(student.getSchoolClass())){
                studentList.add(student);
                //mp.put(true,"Student added successful");

                return true;
            }else{
               //mp.put(false," "+student.getSchoolClass().getClassName() + " and Section : "+student.getSchoolClass().getSectionName() + " is not empty");
                return false;
            }

        }else{
            // mp.put(false,"Student Role Number "+student.getRoll_number()+" is not unique and roll number must be 0 to 40 (0-40) ");
             return false;
        }

    }

    private boolean checkUniqueRollNumber(Student student){
        if(studentList.isEmpty()){
            return true;
        }

        for(Student sd  : studentList){
            if(sd.getRoll_number() == student.getRoll_number() && sd.getSchoolClass().getClassName().equals(student.getSchoolClass().getClassName()) && sd.getSchoolClass().getSectionName().equals(student.getSchoolClass().getSectionName())){
                System.out.println(student);;
                return false;
            }
        }
        return true;
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

    public void showPersonalResult(Student st){
        StringBuffer sb = new StringBuffer();
        Map marksMap = st.getMarks();
        LinkedList marksList= new LinkedList();

        ArrayList<String> headers = new ArrayList<String>();
        headers.add("Subject");
        headers.add("Marks");
        headers.add("Grade");

        ArrayList<ArrayList<String>> content = new ArrayList<ArrayList<String>>();

        sb.append(ConsoleColors.Magenta+"\n    Student name : "+st.getName() + " || Role Number : "+st.getRoll_number() +ConsoleColors.RESET );

        //sb.append("Subject     ||     marks     ||     grade   \n\n");

        for(Object key : marksMap.keySet()) {
            ArrayList<String> row1 = new ArrayList<String>();
            Object value = marksMap.get(key);
           // sb.append(key + "    ||   " +value + "   ||   "+grade((Double) value) + "\n");
            row1.add(String.valueOf(key));
            row1.add(String.valueOf(value));
            row1.add(grade((Double) value));
            content.add(row1);
            marksList.add(value);
        }

        ConsoleTable ct = new ConsoleTable(headers,content);
        System.out.println(sb.toString());
        ct.printTable();
        System.out.println(ConsoleColors.Magenta+"       Grade : "+ finalGrade(totalGradePoint(marksList))+  "   Total Marks : "+totalMarks(marksList)  +ConsoleColors.RESET+"\n");
//        System.out.println(ConsoleColors.Magenta+"      Total Grade is : "+ finalGrade(totalGradePoint(marksList))+ConsoleColors.RESET+"\n");

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
            sb.append("\nTotal Grade is : "+ grade(totalGradePoint(marksList)) + "\n\n");
        }

        return sb.toString();
    }

    public void showProvidedStudentResult(LinkedList<Student> students){
        //StringBuffer sb = new StringBuffer();
        System.out.println(ConsoleColors.Magenta+"\n                      All Students Marks Sheet"+ConsoleColors.RESET);

        ArrayList<String> headers = new ArrayList<String>();
        headers.add("Class");
        headers.add("Section");
        headers.add("Roll Number");
        headers.add("Student Name");

        Student s = students.get(0);

        for(int i = 0 ; i < s.getSchoolClass().getSubjects().length ; i++){
            headers.add(s.getSchoolClass().getSubjects()[i]);
        }
        headers.add("Total GPA");

        ArrayList<ArrayList<String>> content = new ArrayList<ArrayList<String>>();

        for(Student std : students){
            LinkedList marksList= new LinkedList();
            Map marksMap = std.getMarks();
            ArrayList<String> row1 = new ArrayList<String>();
            row1.add(std.getSchoolClass().getClassName());
            row1.add(std.getSchoolClass().getSectionName());
            row1.add(String.valueOf(std.getRoll_number()));
            row1.add(std.getName());
//            sb.append("\n***** Student Name : "+std.getName() + " *****\n");
//            sb.append("***** Student Roll : "+std.getRoll_number() + " *****\n\n");
//            sb.append("Subject     ||     marks     ||     grade   \n\n");
            for(Object key : marksMap.keySet()) {
                Object value = marksMap.get(key);
                //sb.append(key + "    ||   " +value + "   ||   "+grade((Double) value) + "\n");
                row1.add(String.valueOf(value));
                marksList.add(value);
            }
            row1.add(finalGrade(totalGradePoint(marksList)));
            content.add(row1);
            //sb.append("\nTotal Grade point is : "+ grade(totalGradePoint(marksList)) + "\n\n");
        }

        ConsoleTable ct = new ConsoleTable(headers,content);
        //System.out.println(sb.toString());
        ct.printTable();


    }

    private String grade(double marks){
        if(marks<=100 && marks>=0){
            if( marks>=80){
                return "A";
            }else if(marks >= 60){
                return "B";
            }else if(marks >= 40){
                return "C";
            }else{
                return "F";
            }
        }
        return "Invalid marks";
    }

    private String finalGrade(double marks){
        if(marks<=100 && marks>=0){
            if( marks>=80){
                return "A";
            }else if(marks >= 60){
                return "B";
            }else if(marks >= 40){
                return "C";
            }else{
                return "FAIL";
            }
        }
        return "Invalid marks";
    }

    public double totalMarks(LinkedList marks){
       double sum = 0;
        for(int i = 1 ; i<marks.size() ; i++) {
            double mark = (double) marks.get(i);
            sum += mark;
        }
        return sum;

    }


    private double totalGradePoint(LinkedList marks){
        double sum = 0;
        double count = 0;
        for(int i = 1 ; i<marks.size() ; i++){
            double mark = (double) marks.get(i);
            if(  mark>=40 && mark<=100){
                if(mark>=80){
                    sum += 80;
                }else if(mark>=60){
                    sum += 60;
                }else {
                    sum += 40;
                }
            }else{
                count++;
            }

            if(count == 2){
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
            sb.append("Name : "+student.getName() + "  Roll Number : "+student.getRoll_number() +"  Class : "+student.getSchoolClass().getClassName() + "  Section : "+student.getSchoolClass().getSectionName()+"\n");
        }
        return sb.toString();
    }
}
