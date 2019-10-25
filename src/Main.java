import javax.swing.*;
import java.io.IOException;
import java.sql.Array;
import java.sql.Connection;
import java.util.*;
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


            String[] class_subjects = {"Physics","Chemistry","Biology","Math","English","Bangla","Social Science","ICT"};

            if(class_subjects.length > 8){
                System.out.println("Class EIGHT subject must be 8");
                return;
            }

    // create classes
            SchoolClass eight_a = new SchoolClass("Eight","A",class_subjects);
            SchoolClass eight_b= new SchoolClass("Eight","B",class_subjects);
            SchoolClass nine_a= new SchoolClass("Nine","A",class_subjects);
            SchoolClass nine_b= new SchoolClass("Nine","B",class_subjects);
            SchoolClass ten_a= new SchoolClass("Ten","A",class_subjects);
            SchoolClass ten_b= new SchoolClass("Ten","B",class_subjects);

            schoolClassControl.addClass(eight_a);
            schoolClassControl.addClass(eight_b);
            schoolClassControl.addClass(nine_a);
            schoolClassControl.addClass(nine_b);
            schoolClassControl.addClass(ten_a);
            schoolClassControl.addClass(ten_b);

    // create students

            // some predefined students for testing

            Student student1 = new Student("joy",1,eight_a);
            Student student2 = new Student("ridoy",2,eight_a);
            Student student3 = new Student("rafi",3,eight_a);
            Student student4 = new Student("mahbub",4,eight_b);
            Student student5 = new Student("kolim",5,eight_b);
            Student student6 = new Student("bk",1,ten_a);
            Student student7 = new Student("rafi",2,ten_a);
            Student student8 = new Student("nayem",3,ten_b);
            Student student9 = new Student("Wr",3,nine_a);
            Student student10 = new Student("hs",3,nine_b);

            studentControl.addStudent(student1);

            //System.out.println(mp.keySet().toArray()[0]);
            //System.out.println(mp.values().toArray()[0]);
            studentControl.addStudent(student2);
            studentControl.addStudent(student3);
            studentControl.addStudent(student4);
            studentControl.addStudent(student5);
            studentControl.addStudent(student6);
            studentControl.addStudent(student7);
            studentControl.addStudent(student8);
            studentControl.addStudent(student9);
            studentControl.addStudent(student10);



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
                                System.out.println("1 : Add Students Marks");
                                System.out.println("2 : Show Added Students Result");
                                System.out.println("3 : Add Students");
                                System.out.println("4 : Show Students List");
                                System.out.println("0 : Exit");
                                try{
                                    System.out.println("\nChoose the Option : ");
                                    String option_str = sc.nextLine();
                                    int option = Integer.parseInt(option_str);
                                    if(option >=0 && option < 5){
                                        if(option == 0){
                                            return;
                                        }
                                        else if(option == 1){
                                            while (true){
                                                int i = 1;
                                                System.out.println("Select the Class name with Section: ");
                                                for(SchoolClass scl: schoolClassControl.getSchoolClassList()){

                                                    System.out.println(i + " : Class "+ConsoleColors.Blue+scl.getClassName() +ConsoleColors.RESET +" && Section "+ConsoleColors.Blue+scl.getSectionName()+ConsoleColors.RESET);
                                                    i++;
                                                }
                                                System.out.println(i+ " : Back" );
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


                                                                        System.out.println("You have " + (addStudentNumbr - k) + " student left");
                                                                        System.out.println("Continue to add students marks press=> any key or exit press => e");
                                                                        System.out.print("cmd>> ");
                                                                        String target = sc.nextLine();
                                                                        if(target.toLowerCase().equals("e")){
                                                                            break;
                                                                        }else{

                                                                            System.out.println("\nProvide Student roll number : ");
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
                                        else if(option == 3){
                                            System.out.println("\n  **   Add Students   ** ");
                                            System.out.println("Provide Student All Information");
                                            System.out.println("Student Name : ");
                                            try {
                                                String name = sc.nextLine();
                                                System.out.println("Student Roll Number(1-40) : ");
                                                while (true){
                                                    String roll_numberStr = sc.nextLine();
                                                    int roll_number = Integer.parseInt(roll_numberStr);
                                                    if(roll_number>0 && roll_number<=40){
                                                        System.out.println("Choose Class Name and Section : ");
                                                        for (int i = 0; i < schoolClassControl.getSchoolClassList().size(); i++) {
                                                            System.out.println("  "+(i + 1) + " : Class " + schoolClassControl.getSchoolClassList().get(i).getClassName() + " && Section " + schoolClassControl.getSchoolClassList().get(i).getSectionName());
                                                        }
                                                        while (true) {

                                                            String classNameSectionStr = sc.nextLine();

                                                            int classNameSection = (int) Integer.parseInt(classNameSectionStr);

                                                            if (classNameSection > 0 && classNameSection <= schoolClassControl.getSchoolClassList().size()) {
                                                                Student std = new Student(name,roll_number,schoolClassControl.getSchoolClassList().get(classNameSection-1));
                                                                boolean stdControl = studentControl.addStudent(std);

                                                               if(stdControl) {
                                                                   System.out.println("\n"+std);
                                                                   System.out.println("Student Added Successful\n");
                                                                   break;
                                                               }
                                                                else{
                                                                    System.out.println("This Roll number already added\nStudent Added Failed");
                                                                    break;
                                                                }


                                                             } else {
                                                                System.out.println("Please Provide Valid Input");
                                                             }

                                                        }

                                                    }else{
                                                        System.out.println("Please Provide valid Roll number");
                                                    }
                                                    break;

                                                }

                                            }catch (Exception e){
                                                System.out.println("Please Provide valid Input");
                                            }

                                        }
                                        else if(option == 4){

                                            ArrayList<String> headers = new ArrayList<String>();
                                            headers.add("Class");
                                            headers.add("Section");
                                            headers.add("Roll Number");
                                            headers.add("Name");


                                            ArrayList<ArrayList<String>> content = new ArrayList<ArrayList<String>>();



                                            for(Student std : studentControl.getStudentList()) {
                                                ArrayList<String> row1 = new ArrayList<String>();
                                                row1.add(std.getSchoolClass().getClassName());
                                                row1.add(std.getSchoolClass().getSectionName());
                                                row1.add(""+std.getRoll_number());
                                                row1.add(std.getName());

                                                content.add(row1);
                                            }

                                            ConsoleTable ct = new ConsoleTable(headers,content);

                                            ct.printTable();

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

