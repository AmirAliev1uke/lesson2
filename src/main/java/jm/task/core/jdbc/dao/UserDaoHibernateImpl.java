package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.engine.query.spi.HQLQueryPlan;

import javax.persistence.Query;
import javax.transaction.Transaction;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private final SessionFactory sessionFactory;

    public UserDaoHibernateImpl() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }


    @Override
    public void createUsersTable() {
        String createUsTab = "CREATE TABLE `preprojectbase`.`users` (`id` INT NOT NULL AUTO_INCREMENT,`name` VARCHAR(45) NOT NULL,`lastName` VARCHAR(45) NOT NULL,`age` INT(3) NULL,PRIMARY KEY (`id`));";
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            int query = session.createSQLQuery(createUsTab).addEntity(User.class).executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Такая таблица уже существует!");
        }
    }

    @Override
    public void dropUsersTable() {
        String dropUsTab ="DROP TABLE IF EXISTS users";
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            int query = session.createSQLQuery(dropUsTab).addEntity(User.class).executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Такой таблицы не существует!");
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = sessionFactory.getCurrentSession()) {
            User user = new User(name,lastName, age);
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            User tempUs = (User) session.load(User.class,id);
            session.delete(tempUs);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            list = session.createCriteria(User.class).list();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(list);
        return list;
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            session.createQuery("DELETE FROM User").executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
