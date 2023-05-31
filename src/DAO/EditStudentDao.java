package DAO;

import domain.StudentInfo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import utils.DBFunction;

import java.sql.CallableStatement;
import java.sql.Connection;

public class EditStudentDao {
    private DBFunction df = new DBFunction();
    private Log logger = LogFactory.getLog(getClass());

    public void editData(StudentInfo st) throws Exception{
        Connection conn = df.connectDB();
        CallableStatement cs = null;
        String sql = "{call NIS_PROJECT.ST_PACKAGE.EditStudent(?,?,?,?,?,?,?,?,?,?,?)}";

        try {
            if (conn != null) {
                logger.info("Connection ");

                try {
                    System.out.println(st.toString());
                    cs = conn.prepareCall(sql);
                    cs.setLong("P_ID" ,st.getId());
                    cs.setString("P_ST_NAME",st.getName());
                    cs.setString("P_ST_SURNAME",st.getSurname());
                    cs.setLong("P_COURSE_ID",st.getCourse_id());
                    cs.setString("P_EMAIL",st.getEmail());
                    cs.setString("P_MOBILE",st.getMobile());
                    cs.setString("P_BIRTH_DATE",st.getBirth_date());
                    cs.setInt("P_GENDER_ID",st.getGender_id());
                    cs.setString("P_SCHOOL",st.getSchool());
                    cs.setInt("P_ENTRANCE_SCORE",st.getEntrance_score());
                    cs.setString("P_COMP_DATE",st.getCompletion_date());
                    System.out.println("dfgdfg");
                    cs.execute();

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
    };
}

