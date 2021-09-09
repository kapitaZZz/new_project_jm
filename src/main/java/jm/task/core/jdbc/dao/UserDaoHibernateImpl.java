package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            String sql = "CREATE TABLE IF NOT EXISTS users (" +
                    "id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                    "name VARCHAR(50) NOT NULL, lastName VARCHAR(50) NOT NULL, " +
                    "age TINYINT NOT NULL)";

            Query query = session.createSQLQuery(sql).addEntity(User.class);
            query.executeUpdate();
            System.out.println("Table completed!");
            transaction.commit();
        } catch (Exception e) {
            System.out.println("Unable to create table " + e);
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            String sql = "DROP TABLE IF EXISTS users";

            Query query = session.createSQLQuery(sql).addEntity(User.class);
            query.executeUpdate();
            System.out.println("Users dropped!");
            transaction.commit();
        } catch (Exception e) {
            System.out.println("Table unable to drop " + e);
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            User user = new User(name, lastName, age);
            session.save(user);

            transaction.commit();
        } catch (Exception e) {
            System.out.println("Unable to save new user " + e);
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            String sql = "DELETE FROM users WHERE id = :id";

            Query query = session.createSQLQuery(sql).setParameter("id", id);
            query.executeUpdate();

            transaction.commit();
        } catch (Exception e) {
            System.out.println("Unable to remove current user " + e);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<User> getAllUsers() {

        List<User> users = null;
        try (Session session = Util.getSessionFactory().openSession()) {

            Transaction transaction = session.beginTransaction();
            users = session.createQuery("From " + User.class.getSimpleName()).list();

            for (User user : users) {
                System.out.println(user);
            }

            transaction.commit();
        } catch (Exception e) {
            System.out.println("Unable to get any data from table " + e);

        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            String sql = "DELETE FROM users";

            Query query = session.createSQLQuery(sql);
            query.executeUpdate();
            System.out.println("Table is clear!");
            transaction.commit();
        } catch (Exception e) {
            System.out.println("Nothing to delete in table " + e);
        }
    }
}
