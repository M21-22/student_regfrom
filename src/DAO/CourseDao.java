package DAO;

import domain.CourseInfo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import utils.DBFunction;

import java.sql.CallableStatement;
import java.sql.Connection;

public class CourseDao implements ObjectDao<CourseInfo> {
    private int action;
    private CourseInfo courseInfo;
    private DBFunction df = new DBFunction();
    private Log logger = LogFactory.getLog(getClass());

    public CourseDao(int action, CourseInfo courseInfo) throws Exception {
        this.action = action;
        this.courseInfo = courseInfo;
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
                        sql = "{call NIS_PROJECT.ST_PACKAGE.AddCourse(?,?,?)}";
                        cs = conn.prepareCall(sql);
                        addOrEditData(courseInfo, cs);
                    }else if(action == 2){
                        sql = "{call NIS_PROJECT.ST_PACKAGE.EditCourse(?,?,?,?)}";
                        cs = conn.prepareCall(sql);
                        addOrEditData(courseInfo, cs);
                    } else if(action == 3){
                        sql = "{call NIS_PROJECT.ST_PACKAGE.RemoveCourse(?)}";
                        cs = conn.prepareCall(sql);
                        removeData(courseInfo, cs);
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
    public void addOrEditData(CourseInfo crs, CallableStatement cs) throws Exception {
        if(action!=1) cs.setLong("P_ID" ,crs.getId());
        cs.setString("P_COURSE_NAME", crs.getCrsname());
        cs.setString("P_COURSE_CODE", crs.getCrscode());
        cs.setLong("P_LECTURER_ID", crs.getLecturer_id());
        cs.execute();
    }
    @Override
    public void removeData(CourseInfo crs, CallableStatement cs) throws Exception {
        cs.setLong("P_ID", crs.getId());
        cs.execute();
    }
}

