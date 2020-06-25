package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

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
}
