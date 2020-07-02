package jm.task.core.jdbc.util;
import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Util {

    private static SessionFactory sessionFactory;

    public static Connection getMysqlConnection() {
        try {
            StringBuilder url = new StringBuilder();
            url.append("jdbc:mysql://").            //db type
                    append("localhost:").           //host name
                    append("3306/").                //port
                    append("pre_proj_112?").       //db name
                    append("user=java&").           //login
                    append("password=java&").        //password
                    append("useUnicode=true&useJDBCCompliantTimezoneShift=true&").
                    append("useLegacyDatetimeCode=false&serverTimezone=UTC");

            DriverManager.registerDriver((Driver) Class.forName("com.mysql.cj.jdbc.Driver").newInstance());
            Connection connection = DriverManager.getConnection(url.toString());
            return connection;
        } catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new IllegalStateException();
        }
    }


    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            sessionFactory = createSessionFactory();
        }
        return sessionFactory;
    }

    private static Configuration getMySqlConfiguration() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(User.class);

        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        configuration.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
        configuration.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/pre_proj_112?" +
                "useLegacyDatetimeCode=false&serverTimezone=UTC");
        configuration.setProperty("hibernate.connection.username", "java");
        configuration.setProperty("hibernate.connection.password", "java");
        configuration.setProperty("hibernate.show_sql", "true");
        configuration.setProperty("hibernate.hbm2ddl.auto", "create");
        configuration.setProperty("serverTimezone", "UTC");
        //configuration.setProperty("useJDBCCompliantTimezoneShift", "true");
        configuration.setProperty("useLegacyDatetimeCode", "false");
        return configuration;
    }

    private static SessionFactory createSessionFactory() {
        Configuration configuration = getMySqlConfiguration();
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }
}
