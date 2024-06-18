package controller;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import entities.MyAdmin;
import entities.MyUser;

import service.NumberCurdOperations;

@Controller
public class WebPageController {
    
    @Autowired
    private NumberCurdOperations service;


    // Home page handler
    @RequestMapping(value="/", method=RequestMethod.GET)
    public String home(Model model) {
        return "home";
    }

    // Registration page handler
    @RequestMapping(value="/registration", method=RequestMethod.GET)
    public String registeration() {
        return "registerPage";
    }

    // Handler for registering a new user
    @RequestMapping(value="/register", method=RequestMethod.POST)
    public String register(Model model, MyUser user) {
        MyUser local = service.addUser(user);
        model.addAttribute("user", local);
     //   model.addAttribute("registrationSuccess", true);
        return "home";
    }

    // User Login page handler
    @RequestMapping(value="/login", method=RequestMethod.GET)
    public String userlogin() {
        return "userLogin";
    }
 // Handler for validating user login
    @PostMapping("/userlogin")
    public String userLogin(Model model, HttpSession session, @RequestParam("username") String username, @RequestParam("password") String password) {
    	MyUser localUser = service.validateUser(username, password);
    	  if (localUser != null) {
    	      //  session.setAttribute("userId", localUser.getUser_id());
    		  session.setAttribute("validuser", localUser);
    	
    	        return "redirect:/update";
    	    } else {
    	        model.addAttribute("error", "Invalid username or password");
    	        return "userLogin"; // Return the login page to display the error
    	    }
        
    }
    // admin Login page handler
    @RequestMapping(value="/admin", method=RequestMethod.GET)
    public String login() {
        return "adminPage";
    }

    // Handler for validating admin login
    @PostMapping("/adminlogin")
    public String adminLogin(Model model, HttpSession session, @RequestParam("adminname") String adminname, @RequestParam("password") String password) {
    	MyAdmin localAdmin = service.validateAdmin(adminname, password);
    	  if (localAdmin != null) {
    	   //     session.setAttribute("userId", localUser.getUser_id());
    	        model.addAttribute("validAdmin", localAdmin);
    	        return "redirect:/display1";
    	    } else {
    	        model.addAttribute("error", "Invalid adminname or password");
    	        return "adminPage"; // Return the login page to display the error
    	    }
        
    }
    // Handler for displaying the list of users
    @RequestMapping(value="/display1", method=RequestMethod.GET)
    public String listUsers(Model model) {
        List<MyUser> users = service.getUsers();
       // System.out.println(users);
        model.addAttribute("users", users);
      //  System.out.println("this is the 1st user " + users);
      //  model.addAttribute("users", users);
      //  System.out.println("this is the 2nd user " + users);
        return "userList";
    }

 // Handler for showing the edit form for a user
    @GetMapping("/update")
    public String showUpdatePage(HttpSession session, Model model) {
        MyUser localUser = (MyUser) session.getAttribute("validuser");
        if (localUser != null) {
            model.addAttribute("user", localUser); 
            return "updatePage";
        } else {
            return "redirect:/userLogin"; // Redirect to login if no valid user in session
        }
    }

    @PostMapping("/updateNumber")
    public String updateUserNumber(@RequestParam("user_id") long userId, @RequestParam("number") long newNumber, HttpSession session, RedirectAttributes redirectAttributes) {
        System.out.println("Received user_id: " + userId);
        System.out.println("Received new number: " + newNumber);
        
        // Update the user's phone number in the database
        service.updateNumber(userId, newNumber);
        
        // Update the user object in the session
        MyUser updatedUser = (MyUser) session.getAttribute("validuser");
        if (updatedUser != null) {
            updatedUser.setNumber(newNumber);
            session.setAttribute("validuser", updatedUser);
        }
        redirectAttributes.addFlashAttribute("successMessage", "Number updated successfully!");
        return "redirect:/update";
    }


    // Handler for deleting a user
    @GetMapping("/deleteUser")
    public String deleteUser(@RequestParam("user_id") long id) {
    	MyUser userToDelete = new MyUser(); 
        userToDelete.setUser_id(id);
        service.deleteUser(userToDelete);
 
        return "redirect:/display1";
    }
}
