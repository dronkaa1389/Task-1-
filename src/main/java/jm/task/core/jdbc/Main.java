package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static UserService userService = new UserServiceImpl();

    public static void main(String[] args) throws SQLException {

        userService.createUsersTable();
        System.out.println("Таблица users создана.");

        userService.saveUser("Песик", "Дворовый", (byte) 5);
        System.out.println("User с именем – Песик добавлен в базу данных.");
        userService.saveUser("Котик", "Домашний", (byte) 3);
        System.out.println("User с именем – Котик добавлен в базу данных.");
        userService.saveUser("Лисичка", "Лесная", (byte) 12);
        System.out.println("User с именем – Лисичка добавлен в базу данных.");
        userService.saveUser("Хрен", "Моржовый", (byte) 99);
        System.out.println("User с именем – Хрен добавлен в базу данных.");

        List<User> users = userService.getAllUsers();
        System.out.println("Список всех пользователей:");
        users.forEach(System.out::println);

        userService.cleanUsersTable();
        System.out.println("Таблица users очищена.");

        userService.dropUsersTable();
        System.out.println("Таблица users удалена.");

    }
}



