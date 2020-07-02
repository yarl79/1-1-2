package jm.task.core.jdbc.dao;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class UserDaoHibernateImpl implements UserDao {

    private Session session;

    public UserDaoHibernateImpl() {
        session = Util.getSessionFactory().openSession();
    }

    @Override
    public void createUsersTable() {
        Optional<Transaction> trx = Optional.empty();
        try {
            trx.orElseGet(session::beginTransaction);
            SQLQuery query = session.createSQLQuery("CREATE TABLE IF NOT EXISTS users" +
                    "(id BIGINT AUTO_INCREMENT, name VARCHAR(256), lastname VARCHAR(256)," +
                    "age TINYINT, PRIMARY KEY (id))");
            query.executeUpdate();
            trx.ifPresent(Transaction::commit);
        } catch (HibernateException e) {
            trx.ifPresent(Transaction::rollback);
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public void dropUsersTable() {
        Optional<Transaction> trx = Optional.empty();
        try {
            trx.orElseGet(session::beginTransaction);
            SQLQuery query = session.createSQLQuery("DROP TABLE IF EXISTS users");
            query.executeUpdate();
            trx.ifPresent(Transaction::commit);
        } catch (HibernateException e) {
            trx.ifPresent(Transaction::rollback);
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Optional<Transaction> tx = Optional.empty();
        try {
            tx.orElseGet(session::beginTransaction);
            User user = new User(name, lastName, age);
            session.save(user);
            tx.ifPresent(Transaction::commit);
        } catch (HibernateException e) {
            tx.ifPresent(Transaction::rollback);
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public void removeUserById(long id) {
        Optional<Transaction> tx = Optional.empty();
        try {
            tx.orElseGet(session::beginTransaction);
            Optional<Object> persistInstance = Optional.of(session.load(User.class, id));
            persistInstance.ifPresent(session::delete);
            tx.ifPresent(Transaction::commit);
        } catch (HibernateException e) {
            tx.ifPresent(Transaction::rollback);
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public List<User> getAllUsers() {
        Optional<Transaction> tx = Optional.empty();
        List<User> users = new ArrayList<>();
        try {
            tx.orElseGet(session::beginTransaction);
            Query qry = session.createQuery("FROM User");
            users = qry.list();
            tx.ifPresent(Transaction::commit);
        } catch (HibernateException e) {
            tx.ifPresent(Transaction::rollback);
            e.printStackTrace();
        } finally {
            session.close();
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        Optional<Transaction> tx = Optional.empty();
        try {
            tx.orElseGet(session::beginTransaction);
            Query qry = session.createQuery("DELETE FROM User");
            qry.executeUpdate();
            tx.ifPresent(Transaction::commit);
        } catch (HibernateException e) {
            tx.ifPresent(Transaction::rollback);
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
