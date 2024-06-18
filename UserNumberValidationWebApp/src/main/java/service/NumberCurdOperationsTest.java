package service;

import static org.junit.Assert.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import entities.MyAdmin;
import entities.MyUser;

public class NumberCurdOperationsTest  {

    // Mocking the required dependencies
    @Mock
    private SessionFactory sessionFactory;

    @Mock
    private Session session;

    @Mock
    private Transaction transaction;
    
    @Mock
    private Criteria criteria;

    @Mock
    private Query<MyUser> query;

    // Injecting mocks into NumberCurdOperations
    @InjectMocks
    private NumberCurdOperations service;

    // Setting up the mocks and initializing the objects
    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
        when(sessionFactory.openSession()).thenReturn(session);
        when(session.beginTransaction()).thenReturn(transaction);
        // Mocking the getTransaction method to return the mocked transaction
        when(session.getTransaction()).thenReturn(transaction);
    }

    // Test for adding a user
    @Test
    public void testAddUser() {
        MyUser user = new MyUser();
        user.setUser_id(1L);

        // Mocking session.get(MyUser.class, 1L) to return the user
        when(session.get(MyUser.class, 1L)).thenReturn(user);

        MyUser result = service.addUser(user);

        // Assertions to verify the result
        assertNotNull(result);
        assertEquals(1L, result.getUser_id());
        // Verifying interactions with the session and transaction
        verify(session).beginTransaction();
        verify(session).save(user);
        verify(session).get(MyUser.class, 1L);
        verify(transaction).commit(); // Verify that transaction.commit() is called
        verify(session).close(); // Verify that session.close() is called
    }

    // Test for getting all users
    @Test
    public void testGetUsers() {
        Long userId = 1L;
        List<MyUser> userList = new ArrayList<>();
        Query<MyUser> query = mock(Query.class);
        
        // Mocking the session.createQuery to return a query that returns a user list
        when(session.createQuery("FROM MyUser m", MyUser.class)).thenReturn(query);
        when(query.getResultList()).thenReturn(userList);
        
        List<MyUser> result = service.getUsers();
        // Assertions to verify the result
        assertEquals(userList, result);
    }

    // Test for getting a user by ID
    @Test
    public void testGetRowDataById() {
        long userId = 1L;
        MyUser expectedUser = new MyUser();
        expectedUser.setUser_id(userId);

        // Mocking the session.get() method to return the expected user
        when(session.get(MyUser.class, userId)).thenReturn(expectedUser);

        MyUser result = service.getRowDataById(userId);

        // Assertions to verify the result
        assertNotNull(result);
        assertEquals(userId, result.getUser_id());
        verify(session).get(MyUser.class, userId);
    }

    // Test for updating a user's number
    @Test
    public void testUpdateNumber() {
        long userId = 1L;
        long newNumber = 9999999L;

        MyUser mockUser = new MyUser();
        mockUser.setUser_id(userId);

        // Mock the session.get() method to return the mock user
        when(session.get(MyUser.class, userId)).thenReturn(mockUser);

        // Call the method to be tested
        service.updateNumber(userId, newNumber);

        // Verify that session.get() and session.getTransaction().commit() were called
        verify(session).get(MyUser.class, userId);
        verify(session).getTransaction();
        verify(transaction).commit();
    }

    // Test for deleting a user
    @Test
    public void testDeleteUser() {
        MyUser user = new MyUser();
        user.setUser_id(1L);

        // Call the method to be tested
        MyUser result = service.deleteUser(user);

        // Verify that session.delete() and transaction.commit() were called
        verify(session).delete(user);
        verify(transaction).commit();

        // Ensure that the returned user is the same as the input user
        assertSame(user, result);
    }

    // Test for handling deletion with a null user (expected to throw an exception)
    @Test(expected = IllegalArgumentException.class)
    public void testDeleteUserWithNullUser() {
        // Call the method with a null user, expecting an IllegalArgumentException
        service.deleteUser(null);
    }

    // Test for validating a user
    @Test
    public void testValidateUser() {
        MyUser user = new MyUser();
        user.setUsername("user");
        user.setPassword("password");

        // Mock the Criteria object
        Criteria criteria = mock(Criteria.class);
        when(session.createCriteria(MyUser.class)).thenReturn(criteria);
        when(criteria.add(any())).thenReturn(criteria);
        when(criteria.uniqueResult()).thenReturn(user);

        MyUser result = service.validateUser("user", "password");

        // Assertions to verify the result
        assertNotNull(result);
        assertEquals("user", result.getUsername());
        // Verifying interactions with session and criteria
        verify(session).createCriteria(MyUser.class);
        verify(criteria, times(2)).add(any());
        verify(criteria).uniqueResult();
    }

    // Test for validating an admin
    @Test
    public void testValidateAdmin() {
        MyAdmin admin = new MyAdmin();
        admin.setAdminname("admin");
        admin.setPassword("password");

        // Mock the Criteria object
        Criteria criteria = mock(Criteria.class);
        when(session.createCriteria(MyAdmin.class)).thenReturn(criteria);
        when(criteria.add(any())).thenReturn(criteria);
        when(criteria.uniqueResult()).thenReturn(admin);

        MyAdmin result = service.validateAdmin("admin", "password");

        // Assertions to verify the result
        assertNotNull(result);
        assertEquals("admin", result.getAdminname());
        // Verifying interactions with session and criteria
        verify(session).createCriteria(MyAdmin.class);
        verify(criteria, times(2)).add(any());
        verify(criteria).uniqueResult();
    }
}
