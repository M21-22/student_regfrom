package service;
import domain.Module;
import domain.Operation;
import domain.UsersInfo;

import java.util.Map;

public interface UserService {
    public UsersInfo getUser(Long userId , String userName, String password) throws Exception;
    public Map<Long, Operation> getOperationMap() throws Exception;

    public Map<Long, Module> getModuleMap() throws Exception;

}
