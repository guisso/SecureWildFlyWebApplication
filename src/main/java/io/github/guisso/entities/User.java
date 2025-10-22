package io.github.guisso.entities;

import java.io.Serializable;
import java.util.Objects;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
@NamedQueries({
    @NamedQuery(
            name = "User.all", 
            query = "select us from User us "
                    + "order by us.id"),
    @NamedQuery(
            name = "User.byUsername", 
            query = "select us from User us "
                    + "where us.username = :username")
})
public class User 
        implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    
    @Column(name = "name", nullable = false)
    private String name;
    
    @Column(name = "username", nullable = false, unique = true)
    private String username;
    
    @Column(name = "user_password", nullable = false)
    private String password;
    
    @Column(name = "user_group", nullable = false)
    private String group;

    //<editor-fold defaultstate="collapsed" desc="Constructors">
    public User() {
    }

    public User(String name, String username, String password, String group) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.group = group;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Getters/Setters">
    public Integer getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="hashCode/equals">
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        return Objects.equals(this.id, other.id);
    }
    //</editor-fold>
}
