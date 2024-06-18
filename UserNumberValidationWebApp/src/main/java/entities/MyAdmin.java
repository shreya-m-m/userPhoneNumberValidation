package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

// Entity annotation indicates that this class is a JPA entity.
@Entity

// NamedQuery annotation defines a named query for this entity.
@NamedQuery(name="MyAdmin.findAll", query="SELECT m FROM MyAdmin m")
public class MyAdmin {
    // Id annotation specifies the primary key of the entity.
    @Id
    
    // GeneratedValue annotation indicates that the value for the primary key will be automatically generated.
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long admin_id;
    
    // Column annotation specifies the mapping for a persistent property.
    @Column(length=12)
    private String password;
    
    // Column annotation specifies the mapping for a persistent property.
    @Column(length=12)
    private String adminname;
    
    // Default constructor required by JPA.
    public MyAdmin() {
        super();
    }
    
    // Constructor with parameters.
    public MyAdmin(long admin_id, String password, String adminname) {
        super();
        this.admin_id = admin_id;
        this.password = password;
        this.adminname = adminname;
    }
    
    // Getter for admin_id field.
    public long getAdmin_id() {
        return admin_id;
    }
    
    // Setter for admin_id field.
    public void setAdmin_id(long admin_id) {
        this.admin_id = admin_id;
    }
    
    // Getter for password field.
    public String getPassword() {
        return password;
    }
    
    // Setter for password field.
    public void setPassword(String password) {
        this.password = password;
    }
    
    // Getter for adminname field.
    public String getAdminname() {
        return adminname;
    }
    
    // Setter for adminname field.
    public void setAdminname(String adminname) {
        this.adminname = adminname;
    }
}
