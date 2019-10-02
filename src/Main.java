import javax.swing.*;
import java.io.IOException;
import java.sql.Array;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args)
        {

            TeacherControl teacherControl = new TeacherControl();
            StudentControl studentControl = new StudentControl();
            SchoolClassControl schoolClassControl = new SchoolClassControl();

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

            System.out.println(schoolClassControl.addClass(eight_a));
            System.out.println(schoolClassControl.addClass(eight_b));

    // create students
            Student student1 = new Student("joy",1,eight_a);
            Student student2 = new Student("ridoy",2,eight_a);
            Student student3 = new Student("rafi",3,eight_a);
            Student student4 = new Student("mahbub",4,eight_a);
            Student student5 = new Student("kolim",5,eight_a);
            Student student6 = new Student("bk",6,eight_a);

            HashMap mp =  studentControl.addStudent(student2);

            System.out.println(mp.keySet().toArray()[0]);
            System.out.println(mp.values().toArray()[0]);
            studentControl.addStudent(student3);
            studentControl.addStudent(student4);



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

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
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

