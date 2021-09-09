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
    private static Connection connection;
    private static SessionFactory sessionFactory;

    private final static String DRIVER = "com.mysql.cj.jdbc.Driver";
    private final static String USER = "root";
    private final static String URL = "jdbc:mysql://localhost:3306/users";
    private final static String PASSWORD = "wJ687Eb3h6Q9g4";


    public Util() {

    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration()
                        .setProperty("hibernate.connection.driver_class", DRIVER)
                        .setProperty("hibernate.connection.url", URL)
                        .setProperty("hibernate.connection.username", USER)
                        .setProperty("hibernate.connection.password", PASSWORD)
                        .setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");

                configuration.addAnnotatedClass(User.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);


            } catch (Exception e) {
                System.out.println("Connection HIBERNATE lost" + e);
            }
        }
        return sessionFactory;
    }


    public static Connection getConnection() {
        try {
            if (connection == null) {
                Driver driver = new com.mysql.cj.jdbc.Driver();
                DriverManager.registerDriver(driver);

                Class.forName("com.mysql.cj.jdbc.Driver");
                String USER = "root";
                String URL = "jdbc:mysql://localhost:3306/users";
                String PASSWORD = "wJ687Eb3h6Q9g4";
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("Connection lost! " + e);
        }
        return connection;
    }
}
