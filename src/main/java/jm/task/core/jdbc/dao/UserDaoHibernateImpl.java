package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private final SessionFactory sessionFactory;

    public UserDaoHibernateImpl() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }


    @Override
    public void createUsersTable() {
        String createUsTab = "CREATE TABLE IF NOT EXISTS USERS(`id` INT NOT NULL AUTO_INCREMENT,`name` VARCHAR(45) NOT NULL,`lastName` VARCHAR(45) NOT NULL,`age` INT(3) NULL,PRIMARY KEY (`id`));";
        Transaction transaction = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();
            session.createSQLQuery(createUsTab).addEntity(User.class).executeUpdate();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction!=null) {
                transaction.rollback();
            }
            System.out.println("Такая таблица уже существует!");
        }
    }

    @Override
    public void dropUsersTable() {
        String dropUsTab ="DROP TABLE IF EXISTS users";
        Transaction transaction = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();
            session.createSQLQuery(dropUsTab).addEntity(User.class).executeUpdate();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction!=null) {
                transaction.rollback();
            }
            System.out.println("Такой таблицы не существует!");
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Transaction transaction = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();
            User user = new User(name,lastName, age);
            session.save(user);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction!=null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void removeUserById(long id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();
            User tempUs = (User) session.load(User.class,id);
            session.delete(tempUs);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction!=null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public List<User> getAllUsers() {
        Transaction transaction = null;
        List<User> list = new ArrayList<>();
        try (Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();
            list = session.createQuery("SELECT a from User a",User.class).getResultList();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction!=null) {
                transaction.rollback();
            }
        }
        System.out.println(list);
        return list;
    }

    @Override
    public void cleanUsersTable() {
        Transaction transaction = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();
            session.createQuery("DELETE FROM User").executeUpdate();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction!=null) {
                transaction.rollback();
            }
        }
    }
}
