import java.util.Scanner;

public class Marks {

    StudentControl studentControl;
    public Marks(StudentControl studentControl){
        this.studentControl = studentControl;
    }

    public void updateMarks(Student sd){

        System.out.println("Please add the subject marks ...... ");

        for(int i = 0 ; i<sd.getSchoolClass().getSubjects().length;i++){
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter "+sd.getSchoolClass().getSubjects()[i] + " marks : ");
            while (true){
                try {
                    String subjectMarkStr = sc.nextLine();
                    double subjectMark = Double.parseDouble(subjectMarkStr);
                    if(subjectMark>=0 && subjectMark<=100){
                        sd.setMarks(sd.getSchoolClass().getSubjects()[i],subjectMark);
                        break;
                    }else {
                        System.out.println("Please provide valid marks");
                    }
                }catch (Exception e){
                    System.out.println("Provide valid marks");
                }
            }
        }

        System.out.println(studentControl.showPersonalResult(sd));

    }



}
