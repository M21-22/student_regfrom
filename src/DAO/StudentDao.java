package DAO;

import domain.StudentInfo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import utils.DBFunction;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.text.SimpleDateFormat;

public class StudentDao implements ObjectDao<StudentInfo> {
    private int action;
    private StudentInfo studentInfo;
    private DBFunction df = new DBFunction();
    private Log logger = LogFactory.getLog(getClass());
    public StudentDao(int action, StudentInfo studentInfo) throws Exception {
        this.action = action;
        this.studentInfo = studentInfo;
        performAction();
    }
    @Override
    public void performAction() throws Exception {
        Connection conn = df.connectDB();
        CallableStatement cs = null;
        String sql="";
        try {
            if (conn != null) {
                logger.info("Connection ");
                try {
                    if(action == 1){
                        sql = "{call NIS_PROJECT.ST_PACKAGE.AddStudent(?,?,?,?,?,?,?,?,?,?,?)}";
                        cs = conn.prepareCall(sql);
                        addOrEditData(studentInfo , cs);
                    }else if(action == 2){
                        sql = "{call NIS_PROJECT.ST_PACKAGE.EditStudent(?,?,?,?,?,?,?,?,?,?,?,?)}";
                        cs = conn.prepareCall(sql);
                        addOrEditData(studentInfo, cs);
                    } else if(action == 3){
                        sql = "{call NIS_PROJECT.ST_PACKAGE.RemoveStudent(?)}";
                        cs = conn.prepareCall(sql);
                        removeData(studentInfo , cs);
                    } else if(action == 4){
                        sql = "{call NIS_PROJECT.ST_PACKAGE.GraduateStudent(?)}";
                        cs = conn.prepareCall(sql);
                        makeGraduate(studentInfo, cs);
                    }
                }
                catch (Exception ex) {
                    logger.info(ex.toString());
                    System.out.println("Error while executing call: " + ex);
                } finally {
                    DBFunction.close(cs);
                }
            }
        } catch (Exception ex) {
            logger.info(ex.toString());
            System.out.println("Error while connecting to database");
        } finally {
            DBFunction.close(conn);
        }
    }
    @Override
    public void addOrEditData(StudentInfo st, CallableStatement cs) throws Exception {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        if(action!=1) cs.setLong("P_ID" ,st.getId());
        cs.setString("P_ST_NAME",st.getName());
        cs.setString("P_ST_SURNAME",st.getSurname());
        cs.setLong("P_COURSE_ID",st.getCourse_id());
        cs.setString("P_EMAIL",st.getEmail());
        cs.setString("P_MOBILE",st.getMobile());
        cs.setDate("P_BIRTH_DATE",new java.sql.Date(dateFormat.parse(st.getBirth_date()).getTime()));
        cs.setInt("P_GENDER_ID",st.getGender_id());
        cs.setString("P_SCHOOL",st.getSchool());
        cs.setInt("P_ENTRANCE_SCORE",st.getEntrance_score());
        cs.setDate("P_COMP_DATE",new java.sql.Date(dateFormat.parse(st.getCompletion_date()).getTime()));
        cs.setString("P_IMAGE_ENCODED", st.getImage());
        cs.execute();
    }
    @Override
    public void removeData(StudentInfo st, CallableStatement cs) throws Exception {
        cs.setLong("P_ID", st.getId());
        cs.execute();
    }

    public void makeGraduate(StudentInfo st, CallableStatement cs) throws Exception {
        cs.setLong("P_ID", st.getId());
        cs.execute();
    }
}

