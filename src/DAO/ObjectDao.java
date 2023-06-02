package DAO;

import domain.CourseInfo;
import domain.ObjectInfo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import utils.DBFunction;

import java.sql.CallableStatement;
import java.sql.Connection;

public interface ObjectDao<T extends ObjectInfo> {

    public void performAction() throws Exception;

    public void addOrEditData(T obj, CallableStatement cs) throws Exception;

    public void removeData(T obj, CallableStatement cs) throws Exception;
}
