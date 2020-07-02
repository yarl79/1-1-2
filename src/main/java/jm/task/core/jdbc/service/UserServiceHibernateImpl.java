package jm.task.core.jdbc.service;
import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import java.util.Optional;


public class UserServiceHibernateImpl extends UserServiceImpl{

    protected static final Optional<UserServiceHibernateImpl> userService = Optional.empty();

    private UserServiceHibernateImpl() {
    }

    public static UserServiceImpl getUserServiceImpl() {
        return userService.orElseGet(UserServiceHibernateImpl::new);
    }

    @Override
    protected UserDao getUserDao() {
        return new UserDaoHibernateImpl();
    }
}
