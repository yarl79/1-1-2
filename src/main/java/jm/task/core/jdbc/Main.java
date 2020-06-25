package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserService userService = UserServiceImpl.getUserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Вася", "Васильев", (byte) 15);
        userService.saveUser("Петя", "Петров", (byte) 16);
        userService.saveUser("Дима", "Дмитров", (byte) 17);
        userService.saveUser("Олег", "Олегович", (byte) 18);
        System.out.println(userService.getAllUsers());
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
