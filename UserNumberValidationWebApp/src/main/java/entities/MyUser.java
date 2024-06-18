package entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

// Entity annotation indicates that this class is a JPA entity.
@Entity

// NamedQuery annotation defines a named query for this entity.
@NamedQuery(name="MyUser.findAll", query="SELECT m FROM MyUser m")
public class MyUser {
    // Id annotation specifies the primary key of the entity.
    @Id
    
    // GeneratedValue annotation indicates that the value for the primary key will be automatically generated.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long user_id;
    
    // Column annotation specifies the mapping for a persistent property.
    @Column(length=12)
    private long number;
    
    // Fields for username and password.
    private String username;
    private String password;
    
    // Constructor with parameters.
    public MyUser(long user_id, long number) {
        super();
        this.user_id = user_id;
        this.number = number;
    }
    
    // Default constructor required by JPA.
    public MyUser() {
        super();
    }
    
    // Getter for user_id field.
    public long getUser_id() {
        return user_id;
    }
    
    // Setter for user_id field.
    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }
    
    // Getter for number field.
    public long getNumber() {
        return number;
    }
    
    // Setter for number field.
    public void setNumber(long number) {
        this.number = number;
    }
    
    // Getter for username field.
    public String getUsername() {
        return username;
    }
    
    // Setter for username field.
    public void setUsername(String username) {
        this.username = username;
    }
    
    // Getter for password field.
    public String getPassword() {
        return password;
    }
    
    // Setter for password field.
    public void setPassword(String password) {
        this.password = password;
    }

}
