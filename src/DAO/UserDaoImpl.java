package DAO;

import domain.Module;
import domain.Operation;
import domain.UsersInfo;
import oracle.jdbc.internal.OracleTypes;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import utils.DBFunction;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Map;

public class UserDaoImpl implements UserDao {
    private Log logger = LogFactory.getLog(getClass());
    private DBFunction df = new DBFunction();
    @Override
    public UsersInfo getUser(Long userId, String userName, String password) throws Exception {

        Connection conn = df.connectDB();
        UsersInfo user = new UsersInfo();
        CallableStatement cs = null;
        String sql = "{CALL NIS_SECURITY.SECURITY_FUNCTIONS.GET_USER(?,?,?,?,?)}";
        String encPas = df.encodePass(password);

        try {
            if (conn != null) {
                logger.info("Connection ");

                try {
                    cs = conn.prepareCall(sql);
                    cs.setLong(1 ,userId);
                    cs.setString(2, userName);
                    cs.setString(3, password);
                    cs.registerOutParameter(4, OracleTypes.INTEGER);
                    cs.registerOutParameter(5, OracleTypes.INTEGER);
                    cs.execute();

                    int login = cs.getInt(4);
                    logger.info("Login corresponding to the username and password: " + login);

                    if (login == 1) {
                        try {

                            logger.info("Login corresponding to the username and password: " + login);
                            user.setId(cs.getLong(5));
                            user.setUserName(userName);
                            user.setPsw(password);
                        }
                        catch (Exception ex) {
                            logger.info(ex.toString());
                            System.out.println("Error while fetching data");
                        }
                    } else {
                        user.setId(cs.getLong(5));
                        logger.info("Incorrect username or password");
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
        return user;
    }

    @Override
    public Map<Long, Operation> getOperationMap() throws Exception {
        return null;
    }

    @Override
    public Map<Long, Module> getModuleMap() throws Exception {
        return null;
    }
}

