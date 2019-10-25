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
                    subjectMark = Math.round(subjectMark);

                    if(subjectMark>=0 && subjectMark<=100){
                        System.out.println("If you want to update marks press b (cmd=>b) or continue to add marks press any key(cmd=> enter) ");
                        System.out.print("cmd>> ");
                        String target = sc.nextLine();
                        if(target.toLowerCase().equals("b")){
                            System.out.println("Please provide updated "+sd.getSchoolClass().getSubjects()[i] +"  marks");
                            continue;
                        }else {
                            sd.setMarks(sd.getSchoolClass().getSubjects()[i],subjectMark);
                            break;
                        }
                    }else {
                        System.out.println("Please provide valid marks");
                    }
                }catch (Exception e){
                    System.out.println("Provide valid marks");
                }
            }
        }

        studentControl.showPersonalResult(sd);

    }



}
