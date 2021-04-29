package io.github.guisso.controllers;

import io.github.guisso.entities.User;
import io.github.guisso.services.DataServiceBeanLocal;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 *
 * @author Luis Guisso <luis dot guisso at ifnmg dot edu dot br>
 */
@Named
@RequestScoped
public class NewUserController {
    
    @Inject
    DataServiceBeanLocal dataService;
    
    private User user;
    private List<String> qualities;

    /**
     * Creates a new instance of NewUserController
     */
    public NewUserController() {
        user = new User();
    }

    //<editor-fold defaultstate="collapsed" desc="Getters/Setters">
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<String> getQualities() {
        return qualities;
    }

    public void setQualities(List<String> qualities) {
        this.qualities = qualities;
        
    }
    //</editor-fold>
    
    public String save() {
        user = dataService.createUser(
                user.getName(), 
                user.getUsername(), 
                user.getPassword(), 
                user.getGroup());
        
        for(String q : qualities) {
            dataService.createQuality(q, user);
        }
        
        return "/app/users?faces-redirect=true";
    }
    
}
