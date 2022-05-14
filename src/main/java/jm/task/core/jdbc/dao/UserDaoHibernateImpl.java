package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private SessionFactory sessionFactory = Util.getSessionFactory();
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS mydbtest.users (id BIGINT NOT NULL auto_increment,"+
                "name VARCHAR(30) NOT NULL,lastName VARCHAR(30) NOT NULL,"+
                "age TINYINT,PRIMARY KEY(id));";
        Transaction transaction = null;
        try(Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();
            session.createSQLQuery(CREATE_TABLE);
            transaction.commit();
        } catch (Exception e){
            e.printStackTrace();
            transaction.rollback();

        }
    }

    @Override
    public void dropUsersTable() {
        final String DROP_TABLE = "DROP TABLE IF EXISTS mydbtest.users";
        Transaction transaction = null;
        try(Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();
            session.createSQLQuery(DROP_TABLE);
            transaction.commit();
        }catch (Exception e){
            e.printStackTrace();
            transaction.rollback();
        }

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Transaction transaction = null;
        try(Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();
            session.save(new User(name,lastName,age));
            transaction.commit();
        }
        catch(Exception e){
            e.printStackTrace();
            if(transaction != null){
                transaction.rollback();
            }
        }

    }

    @Override
    public void removeUserById(long id) {
        Transaction transaction = null;

    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public void cleanUsersTable() {

    }
}
