package controller;

// Import statements for JUnit, Mockito, Spring MVC Test, and other necessary classes
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import entities.MyAdmin;
import entities.MyUser;
import service.NumberCurdOperations;

public class WebPageControllerTest {

    // Mocked NumberCurdOperations service
    @Mock
    private NumberCurdOperations service;

    // Injecting mocks into WebPageController
    @InjectMocks
    private WebPageController controller;

    // MockMvc instance for testing MVC endpoints
    private MockMvc mockMvc;
    
    @Mock
    private HttpSession session;
    
    @Mock
    private Model model;
    
    @Mock
    private RedirectAttributes redirectAttributes;

    // Setup method to initialize mocks and mockMvc before each test
    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    // Test for home page request mapping
    @Test
    public void testHome() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("home"));
    }

    // Test for registration page request mapping
    @Test
    public void testRegistration() throws Exception {
        mockMvc.perform(get("/registration"))
                .andExpect(status().isOk())
                .andExpect(view().name("registerPage"));
    }

    // Test for user registration
    @Test
    public void testRegister() throws Exception {
        MyUser user = new MyUser();
        when(service.addUser(any(MyUser.class))).thenReturn(user);
        mockMvc.perform(post("/register")
                .param("username", "testuser")
                .param("password", "password"))
                .andExpect(status().isOk())
                .andExpect(view().name("home"));
    }
    
    // Test for User Login GetMapping Handling
    @Test
    public void testUserLoginGet() throws Exception {
        mockMvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("userLogin"));
    }
    
    // Test for User Login PostMapping with valid credentials
    @Test
    public void testUserLogin_ValidCredentials() {
        String username = "valid_username";
        String password = "valid_password";
        MyUser localUser = new MyUser();
        localUser.setUsername(username);
        localUser.setPassword(password);
        when(service.validateUser(username, password)).thenReturn(localUser);
        String viewName = controller.userLogin(model, session, username, password);
        assertEquals("redirect:/update", viewName);
        verify(session).setAttribute("validuser", localUser);
        verify(model, never()).addAttribute(eq("error"), anyString());
    }

    // Test for User Login PostMapping with invalid credentials
    @Test
    public void testUserLogin_InvalidCredentials() {
        String username = "invalid_username";
        String password = "invalid_password";
        when(service.validateUser(username, password)).thenReturn(null);
        String viewName = controller.userLogin(model, session, username, password);
        assertEquals("userLogin", viewName);
        verify(model).addAttribute("error", "Invalid username or password");
        verify(session, never()).setAttribute(eq("validuser"), any(MyUser.class));
    }

    // Test for Admin Login GetMapping Handling
    @Test
    public void testAdminLoginGet() throws Exception {
        mockMvc.perform(get("/admin"))
                .andExpect(status().isOk())
                .andExpect(view().name("adminPage"));
    }
    
    // Test for Admin Login PostMapping with valid credentials
    @Test
    public void testAdminLogin_ValidCredentials() {
        String adminname = "valid_adminname";
        String password = "valid_password";
        MyAdmin localAdmin = new MyAdmin();
        localAdmin.setAdminname(adminname);
        localAdmin.setPassword(password);
        when(service.validateAdmin(adminname, password)).thenReturn(localAdmin);
        String viewName = controller.adminLogin(model, session, adminname, password);
        assertEquals("redirect:/display1", viewName);
        verify(model).addAttribute("validAdmin", localAdmin);
        verify(model, never()).addAttribute(eq("error"), anyString());
    }

    // Test for Admin Login PostMapping with invalid credentials
    @Test
    public void testAdminLogin_InvalidCredentials() {
        String adminname = "invalid_adminname";
        String password = "invalid_password";
        when(service.validateAdmin(adminname, password)).thenReturn(null);
        String viewName = controller.adminLogin(model, session, adminname, password);
        assertEquals("adminPage", viewName);
        verify(model).addAttribute("error", "Invalid adminname or password");
        verify(model, never()).addAttribute(eq("validAdmin"), any(MyAdmin.class));
    }
    
    // Test for displaying UserNumber List
    @Test
    public void testListUsers() {
        List<MyUser> users = Arrays.asList(new MyUser(), new MyUser());
        when(service.getUsers()).thenReturn(users);
        String viewName = controller.listUsers(model);
        assertEquals("userList", viewName);
        verify(model, times(1)).addAttribute("users", users);
    }
    
    // Test for displaying update page with a valid user
    @Test
    public void testShowUpdatePage_ValidUser() {
        MyUser validUser = new MyUser();
        when(session.getAttribute("validuser")).thenReturn(validUser);
        String viewName = controller.showUpdatePage(session, model);
        assertEquals("updatePage", viewName);
        verify(model).addAttribute("user", validUser);
    }

    // Test for displaying update page with an invalid user
    @Test
    public void testShowUpdatePage_InvalidUser() {
        when(session.getAttribute("validuser")).thenReturn(null);
        String viewName = controller.showUpdatePage(session, model);
        assertEquals("redirect:/userLogin", viewName);
        verify(model, never()).addAttribute(anyString(), any());
    }
    
    // Test for updating user number
    @Test
    public void testUpdateUserNumber() {
        long userId = 123L;
        long newNumber = 9876543210L;
        MyUser updatedUser = new MyUser();
        updatedUser.setUser_id(userId);
        updatedUser.setNumber(newNumber);
        when(session.getAttribute("validuser")).thenReturn(updatedUser);
        doNothing().when(service).updateNumber(userId, newNumber);
        // Mock the behavior of addFlashAttribute to return the RedirectAttributes itself (chaining support)
        when(redirectAttributes.addFlashAttribute(anyString(), anyString())).thenReturn(redirectAttributes);


        String viewName = controller.updateUserNumber(userId, newNumber, session, redirectAttributes);

        assertEquals("redirect:/update", viewName);
        verify(service).updateNumber(userId, newNumber);
        verify(session).setAttribute("validuser", updatedUser);
        verify(redirectAttributes, times(1)).addFlashAttribute("successMessage", "Number updated successfully!");
        assertEquals(newNumber, updatedUser.getNumber());
    }
    
    // Test for deleting a user
    @Test
    public void testDeleteUser() {
        long userId = 123L;
        MyUser userToDelete = new MyUser();
        userToDelete.setUser_id(userId);
        String viewName = controller.deleteUser(userId);
        assertEquals("redirect:/display1", viewName);
        verify(service).deleteUser(argThat(argument -> argument.getUser_id() == userId));
    }
}
