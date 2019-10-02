import java.util.LinkedList;

public class SchoolClassControl {
    private static LinkedList<SchoolClass> schoolClassList;

    private String[] classNames = {"One","Two","Three","Four","Five","Six","Seven","Eight","Nine","Ten"} ;
    private String[] sections = {"A","B"};

    public SchoolClassControl(){
        schoolClassList = new LinkedList<>();
    }

    public String addClass(SchoolClass schoolClass){
        if(checkSchoolClassSize(schoolClass)){
            if(checkClassNameAndSection(schoolClass)){
                schoolClassList.add(schoolClass);
                return "Successfully class added";
            }else{
                return "class or section name is wrong";
            }

        }else{
            return "already class is full";
        }

    }

    private boolean checkSchoolClassSize(SchoolClass schoolClass){

        if(schoolClassList.size()<=10){
            return true;
        }
        return false;
    }

    private boolean checkClassNameAndSection(SchoolClass schoolClass){
        int check = 0;
        if(schoolClass.getSectionName().equals("A") || schoolClass.getSectionName().equals("B")){
            for(String cn:classNames){
                if(cn.toUpperCase().equals(schoolClass.getClassName().toUpperCase())){
                    check +=1;
                }
            }
            if(check == 0){
                return false;
            }else {
                if(schoolClassList.isEmpty()){
                    return true;
                }
                for (SchoolClass sc : schoolClassList) {

                    if (sc.getClassName().equals(schoolClass.getClassName()) && sc.getSectionName().equals(schoolClass.getSectionName())) {
                        return false;
                    }

                }
            }
        }
        else{
            return false;
        }


        return true;
    }



}
