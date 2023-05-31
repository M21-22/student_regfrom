package service;

import DAO.UserDao;
import DAO.UserDaoImpl;
import domain.Module;
import domain.Operation;
import domain.UsersInfo;

import java.util.Map;

public class UserServiceImpl implements UserService{
    private UserDao userDao = new UserDaoImpl();
    public UsersInfo getUser(Long userId, String userName, String password) throws Exception {
       return userDao.getUser(userId, userName, password);
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
