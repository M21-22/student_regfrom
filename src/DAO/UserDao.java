package DAO;

import domain.Module;
import domain.Operation;
import domain.UsersInfo;

import java.util.Map;

public interface UserDao {
    public UsersInfo getUser(Long userId , String userName, String password) throws Exception;

    public Map<Long, Operation> getOperationMap() throws Exception;

    public Map<Long, Module> getModuleMap() throws Exception;
}

