package DAO;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import utils.DBFunction;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

public abstract class DataDao {
    private DBFunction df = new DBFunction();
    private Log logger = LogFactory.getLog(getClass());

    public abstract StringBuilder jsonBuilder(ResultSet rs) throws Exception;
}
