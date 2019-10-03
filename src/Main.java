import javax.swing.*;
import java.io.IOException;
import java.sql.Array;
import java.sql.Connection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args)
        {

            TeacherControl teacherControl = new TeacherControl();
            StudentControl studentControl = new StudentControl();
            SchoolClassControl schoolClassControl = new SchoolClassControl();

            LinkedList<Student> providedStudents = new LinkedList<>();

    // create teacher account
            Teacher t = new Teacher("jubel","jubel8180@gmail.com","1234");
            teacherControl.addTeacher(t);

    // create classes
            String[] class_eight_subjects = {"Physics","Chemistry","Biology","Math","English","Bangla","Social Science","ICT"};

            if(class_eight_subjects.length > 8){
                System.out.println("Class EIGHT subject must be 8");
                return;
            }


            SchoolClass eight_a = new SchoolClass("eight","A",class_eight_subjects);
            SchoolClass eight_b= new SchoolClass("eight","B",class_eight_subjects);
            SchoolClass eight_c= new SchoolClass("nine","A",class_eight_subjects);

            schoolClassControl.addClass(eight_a);
            schoolClassControl.addClass(eight_b);
            schoolClassControl.addClass(eight_c);

    // create students
            Student student1 = new Student("joy",1,eight_a);
            Student student2 = new Student("ridoy",2,eight_a);
            Student student3 = new Student("rafi",3,eight_a);
            Student student4 = new Student("mahbub",4,eight_a);
            Student student5 = new Student("kolim",5,eight_a);
            Student student6 = new Student("bk",6,eight_a);

            HashMap mp =  studentControl.addStudent(student1);

            //System.out.println(mp.keySet().toArray()[0]);
            //System.out.println(mp.values().toArray()[0]);
            studentControl.addStudent(student2);
            studentControl.addStudent(student3);
            studentControl.addStudent(student4);
            studentControl.addStudent(student5);
            studentControl.addStudent(student6);



    // program running from there

            System.out.println("*** Teacher Portal ***");
            System.out.println("*** Welcome To Connect With us ***");
            System.out.println("Please login the system....");
            while (true){
                Scanner sc = new Scanner(System.in);
                System.out.print("email : ");
                String t_email = sc.nextLine();

                System.out.print("Password : ");

                String t_password = sc.nextLine();

                if(!t_email.isEmpty() && !t_password.isEmpty()){
                    if(isValid(t_email)){

                        boolean login = teacherControl.login(t_email,t_password);
                        if(login){

                            System.out.println("you are logged in");
                            while (true){
                                System.out.println("1 : Enter Marks");
                                System.out.println("2 : Show Updated Student Result");
                                System.out.println("0 : Exit");
                                try{
                                    String option_str = sc.nextLine();
                                    int option = Integer.parseInt(option_str);
                                    if(option >=0 && option < 3){
                                        if(option == 0){
                                            return;
                                        }
                                        else if(option == 1){
                                            while (true){
                                                int i = 1;
                                                System.out.println("Select the class name with section: ");
                                                for(SchoolClass scl: schoolClassControl.getSchoolClassList()){

                                                    System.out.println(i + " : "+scl.getClassName() + " and section : "+scl.getSectionName());
                                                    i++;
                                                }
                                                System.out.println(i+ " : Beak" );
                                                System.out.println("0 : Exit" );
                                                try{
                                                    String classOptionStr = sc.nextLine();
                                                    int classOption = Integer.parseInt(classOptionStr);
                                                    if(classOption >=0 && classOption<=i){
                                                        if(classOption == 0){
                                                            return;
                                                        }else if(classOption == i){
                                                            break;
                                                        }else{
                                                            SchoolClass selectedSchoolClass = schoolClassControl.getSchoolClassList().get(classOption-1);
                                                            //System.out.println(selectedSchoolClass.getClassName() + " "+selectedSchoolClass.getSectionName());
                                                            try{
                                                                System.out.println("How many student marks do you want to add ? ");
                                                                String addStudentNmbrStr = sc.nextLine();
                                                                int addStudentNumbr = Integer.parseInt(addStudentNmbrStr);
                                                                if(addStudentNumbr>0 && addStudentNumbr<=40){

                                                                    for(int k = 0 ; k<addStudentNumbr ; ){
                                                                        System.out.println("\nPlease provide student roll number : ");
                                                                        try {

                                                                            String rollNumberStr = sc.nextLine();
                                                                            int rollNumber = Integer.parseInt(rollNumberStr);

                                                                            if(rollNumber>0 && rollNumber<=40) {
                                                                                Student std = studentControl.getSingleStudent(rollNumber,selectedSchoolClass);

                                                                                if(std==null){
                                                                                    System.out.println("Roll number "+rollNumber+" is not found in the class");

                                                                                }
                                                                                else if(!std.getMarks().isEmpty()){
                                                                                    System.out.println(ConsoleColors.RED+"Already this roll number is updated"+ConsoleColors.RESET);

                                                                                }
                                                                                else{
                                                                                    Marks m = new Marks(studentControl);
                                                                                    m.updateMarks(std);
                                                                                    providedStudents.add(std);
                                                                                    k++;
                                                                                }
                                                                            }else{
                                                                                System.out.println(ConsoleColors.RED+"This roll number is not valid"+ConsoleColors.RESET);
                                                                            }

                                                                        }catch (Exception e){
                                                                            System.out.println(ConsoleColors.RED+"Please Provide valid input"+ConsoleColors.RESET);
                                                                        }
                                                                    }


                                                                }
                                                                else{
                                                                    System.out.println("Please provide valid length of input");
                                                                }
                                                            }catch (Exception e){
                                                                System.out.println("Provide valid input");
                                                            }



                                                        }
                                                    }else{
                                                        System.out.println("Please provide valid input");
                                                    }

                                                }catch (Exception e){
                                                    System.out.println("Please provide valid input");
                                                }

                                            }

                                        }
                                        else if(option == 2){
                                            if(providedStudents.size()>0){
                                                studentControl.showProvidedStudentResult(providedStudents);
                                            }else{
                                                System.out.println(ConsoleColors.RED+"Marks is not uploaded\n"+ConsoleColors.RESET);
                                            }

                                        }
                                    }else{
                                        System.out.println("Please Provide valid input");
                                    }

                                }catch (Exception e){
                                    System.out.println("Please Provide valid input");
                                }



                            }

                        }else {
                            System.out.println(ConsoleColors.RED + "Email and password does not match" +
                                    ConsoleColors.RESET );
                        }
                    }else{
                        System.out.println(ConsoleColors.RED + "Provide valid email address" +
                                ConsoleColors.RESET );
                    }

                }else{
                    System.out.println(ConsoleColors.RED + "email or passowrd does not empty" +
                            ConsoleColors.RESET );
                }

            }

    }

    public static boolean isValid(String email)
    {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }

}



//        try {
//            Connection conn = ConnectDatabase.getConnection();
//            if (conn != null){
//                System.out.println("connected");
//            }
//        }catch (Exception e){
//            System.out.println(e);
//        }

