package service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import entities.MyAdmin;
import entities.MyUser;

@Service
public class NumberCurdOperations {
    
    // Injecting the Hibernate SessionFactory to manage sessions
    @Autowired
    SessionFactory factory;

    // Method to add a user to the database
    public MyUser addUser(MyUser user) {
        MyUser localUser = null;
        try (Session session = factory.openSession()) {
            // Begin transaction
            session.beginTransaction();
            // Save the user object
            session.save(user);
            // Fetch the saved user object to verify it was saved correctly
            localUser = session.get(MyUser.class, user.getUser_id());
            System.out.println(localUser);
            // Commit the transaction
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return localUser;
    }

    // Method to retrieve a list of all users from the database
    public List<MyUser> getUsers() {
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        List<MyUser> users = session.createQuery("FROM MyUser m", MyUser.class).getResultList();
        transaction.commit();
        session.close();
        return users;
    }


    // Method to retrieve a user by their ID
    public MyUser getRowDataById(long user_id) {
        try (Session session = factory.openSession()) {
            // Get the user object by ID
            return session.get(MyUser.class, user_id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void updateNumber(long userId, long newNumber) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();

            MyUser rowData = session.get(MyUser.class, userId);
            if (rowData != null) {
                rowData.setNumber(newNumber);
                session.getTransaction().commit();
            } else {
                // Throw an exception if the user with the given user_id doesn't exist
                throw new IllegalArgumentException("User with user_id " + userId + " not found");
            }
        } catch (Exception e) {
            e.printStackTrace(); // Handle or log the exception as needed
        }
    }

    // Method to delete a user from the database
    public MyUser deleteUser(MyUser user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }

        try (Session session = factory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(user);
            transaction.commit();
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    // Method to validate admin credentials
    public MyAdmin validateAdmin(String adminname, String password) {
        try (Session session = factory.openSession()) {
            Criteria criteria = session.createCriteria(MyAdmin.class);
            criteria.add(Restrictions.eq("adminname", adminname));
            criteria.add(Restrictions.eq("password", password));
            return (MyAdmin) criteria.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Method to validate user credentials
    public MyUser validateUser(String username, String password) {
        try (Session session = factory.openSession()) {
            Criteria criteria = session.createCriteria(MyUser.class);
            criteria.add(Restrictions.eq("username", username));
            criteria.add(Restrictions.eq("password", password));
            return (MyUser) criteria.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
