package DAO;

import oracle.jdbc.internal.OracleTypes;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.json.JSONObject;
import utils.DBFunction;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

public class GetDataDao {
    private DBFunction df = new DBFunction();
    private Log logger = LogFactory.getLog(getClass());

    public JSONArray jsonBuilder(ResultSet rs) throws Exception{
        JSONArray jsonArray=new JSONArray();
        while(rs.next()){
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();
            JSONObject jsonObject=new JSONObject();
            for(int i=1;i<=columnCount;i++){
                jsonObject.put(rsmd.getColumnName(i),rs.getString(rsmd.getColumnName(i)));
            }
            jsonArray.put(jsonObject);
        }
        return jsonArray;
    };

    public JSONArray getData(long tableId) throws Exception{
        Connection conn = df.connectDB();
        CallableStatement cs = null;
        ResultSet rs = null;
        String sql = "{ ?= call NIS_PROJECT.ST_PACKAGE.LOAD_DATA(?)}";

        JSONArray res = null;
        try {
            if (conn != null) {
                logger.info("Connection ");

                try {
                    cs = conn.prepareCall(sql);
                    cs.registerOutParameter(1, OracleTypes.CURSOR);
                    cs.setLong(2 ,tableId);
                    cs.execute();

                    rs =(ResultSet) cs.getObject(1);
                    res = jsonBuilder(rs);
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
        return res;
    };
}
