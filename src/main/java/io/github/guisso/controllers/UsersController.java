package io.github.guisso.controllers;

import io.github.guisso.entities.User;
import java.util.List;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import io.github.guisso.services.DataServiceLocal;
import jakarta.ejb.EJB;

@Named
@RequestScoped
public class UsersController {
    
    @EJB
    private DataServiceLocal dataService;
    
    private List<User> allUsers;
    
    @PostConstruct
    public void initialize(){
        this.allUsers = dataService.getAllUsers();
    }

    public List<User> getAllUsers() {
        return allUsers;
    }
}
