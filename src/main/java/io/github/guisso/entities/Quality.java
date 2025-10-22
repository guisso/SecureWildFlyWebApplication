package io.github.guisso.entities;

import java.io.Serializable;
import java.util.Objects;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@Entity
@Table(name = "qualities")
@NamedQuery(
        name = "Quality.byUser", 
        query = "select qual from Quality qual "
                + "where qual.user.id = :userId")
public class Quality 
        implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    
    @Column(name = "description", nullable = false)
    private String description;
    
    @ManyToOne
    @JoinColumn(
            name = "user_id", 
            referencedColumnName = "id", 
            nullable = false)
    private User user;

    //<editor-fold defaultstate="collapsed" desc="Constructors">
    public Quality() {
    }

    public Quality(String description, User user) {
        this.description = description;
        this.user = user;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Getters/Setters">
    public Integer getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public User getUser() {
        return user;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="hashCode/equals">
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.id);
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
        final Quality other = (Quality) obj;
        return Objects.equals(this.id, other.id);
    }
    //</editor-fold>
    
}
