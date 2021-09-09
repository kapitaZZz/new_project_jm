package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class Main {
    public static void main(String[] args) {

        UserDao user = new UserDaoHibernateImpl();


        user.createUsersTable();
        user.saveUser("John", "Smith", (byte) 50);
        user.saveUser("Jane", "Smith", (byte) 45);
        user.saveUser("Steve", "Malkovich", (byte) 30);
        user.saveUser("Peter", "Pen", (byte) 15);

        user.getAllUsers();

        user.removeUserById(4L);

        user.cleanUsersTable();

        user.getAllUsers();

        user.dropUsersTable();


//        UserService userService = new UserServiceImpl();
//
//        userService.createUsersTable();
//        userService.saveUser("John", "Smith", (byte) 50);
//        userService.saveUser("Jane", "Smith", (byte) 45);
//        userService.saveUser("Steve", "Malkovich", (byte) 30);
//        userService.saveUser("Peter", "Pen", (byte) 15);
//
//        userService.getAllUsers();
//
//        userService.removeUserById(2L);
//
//        userService.cleanUsersTable();
//
//        userService.getAllUsers();
//
//        userService.dropUsersTable();


    }
}
