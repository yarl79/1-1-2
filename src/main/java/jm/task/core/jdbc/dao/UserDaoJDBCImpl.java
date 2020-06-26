package jm.task.core.jdbc.dao;


import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class UserDaoJDBCImpl implements UserDao {

    private final Connection mysqlConnection = Util.getMysqlConnection();

    public UserDaoJDBCImpl() {
    }

    @Override
    public void createUsersTable() {
        try {
            Statement stmt = mysqlConnection.createStatement();
            stmt.execute("CREATE TABLE IF NOT EXISTS users (id BIGINT AUTO_INCREMENT, " +
                    "name VARCHAR(256), lastname VARCHAR(256), age TINYINT, PRIMARY KEY (id))");
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        try {
            Statement stmt = mysqlConnection.createStatement();
            stmt.executeUpdate("DROP TABLE IF EXISTS users");
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try {
            Statement stmt = mysqlConnection.createStatement();
            stmt.executeUpdate(String.format("INSERT INTO users (name, lastname , age)" +
                            "VALUES ('%s', '%s', %d)",
                    name,
                    lastName,
                    age));
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try {
            Statement stmt = mysqlConnection.createStatement();
            stmt.executeUpdate(String.format("DELETE FROM users WHERE id=%d", id));
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        ArrayList<User> users = new ArrayList<>();
        try {
            Statement stmt = mysqlConnection.createStatement();
            stmt.executeQuery("SELECT * FROM users");
            ResultSet result = stmt.getResultSet();
            while (result.next()) {
                users.add(new User(result.getLong(1),
                        result.getString(2),
                        result.getString(3),
                        result.getByte(4)));
            }

            result.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public void cleanUsersTable() {
        try {
            Statement stmt = mysqlConnection.createStatement();
            stmt.executeUpdate("DELETE FROM users");
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
