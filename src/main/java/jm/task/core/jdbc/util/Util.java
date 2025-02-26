package jm.task.core.jdbc.util;


import jm.task.core.jdbc.model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class Util {
    // реализуйте настройку соеденения с БД

    private static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASSWORD = "upinsmoke";

    public static Connection getConnection() {
        Connection con = null;
        try {
            con = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            if (!con.isClosed()) {
                System.out.println("Соединение с БД установлено");

            }
        } catch (SQLException s) {
            System.err.println("Не удалось загрузить класс драйвера БД");
        }
        return con;
    }

    private static SessionFactory sessionFactory = null;

    static {
        try {
            Properties settings = new Properties();

            settings.setProperty("hibernate.connection.driver_class", "org.postgresql.Driver");
            settings.setProperty("hibernate.connection.url", "jdbc:postgresql://localhost:5432/postgres");
            settings.setProperty("hibernate.connection.username", "postgres");
            settings.setProperty("hibernate.connection.password", "upinsmoke");

            settings.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
            settings.setProperty("hibernate.show_sql", "true");
            settings.setProperty("hibernate.hbm2ddl.auto", "update");

            sessionFactory = new org.hibernate.cfg.Configuration()
                    .addProperties(settings)
                    .addAnnotatedClass(User.class)
                    .buildSessionFactory();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Session getSession() throws HibernateException {
        return sessionFactory.openSession();
    }

    public static void close() throws HibernateException {
        getSession().close();
    }

}
