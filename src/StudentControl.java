import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class StudentControl {
    private static LinkedList<Student> studentList;

    public StudentControl(){
        studentList = new LinkedList<>();
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

    public boolean checkUniqueRollNumber(Student student){
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

    public boolean checkClassEmpty(SchoolClass schoolClass){
        int count = 1;
        for(Student std:studentList){
            if(std.getSchoolClass().getClassName().equals(schoolClass.getClassName()) && std.getSchoolClass().getSectionName().equals(schoolClass.getSectionName())){
                count++;
            }
        }
        System.out.println(count);
        if(count <=3){
            return true;
        }
        else return false;
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
