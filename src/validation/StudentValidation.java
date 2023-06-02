package validation;

import domain.StudentInfo;

public class StudentValidation {
    public StudentInfo st;
    public StudentValidation(StudentInfo st){
        this.st = st;
    }
    public int[] checkValid(){
        int[] arr = new int[st.getColumnCount()];
        if(!st.getName().matches("\\b([A-Za-z-']+\\b)")) arr[2]=1;//return 2;
        if(!st.getSurname().matches("\\b([A-Za-z-']+\\b)"))arr[3]=1; //return 3;
        if(!st.getMobile().matches("\\+994(50|51|55|70|77)(\\d{7})")) arr[5]=1;//return 5;
        if(!st.getEmail().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}")) arr[6]=1;//return 6;
        if(!st.getBirth_date().matches("\\b\\d{4}-\\d{2}-\\d{2}\\b")) arr[7] = 1;
        if(!st.getCompletion_date().matches("\\b\\d{4}-\\d{2}-\\d{2}\\b")) arr[11] = 1;
        return arr;
    }
}
