package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import java.util.Optional;


public class UserServiceJDBCImpl extends UserServiceImpl {

    protected static final Optional<UserServiceJDBCImpl> userService = Optional.empty();

    private UserServiceJDBCImpl() {
    }

    public static UserServiceImpl getUserServiceImpl() {
        return userService.orElseGet(UserServiceJDBCImpl::new);
    }

    @Override
    protected UserDao getUserDao() {
        return new UserDaoJDBCImpl();
    }
}
