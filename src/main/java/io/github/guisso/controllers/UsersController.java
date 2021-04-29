package io.github.guisso.controllers;

import io.github.guisso.entities.User;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import io.github.guisso.services.DataServiceBeanLocal;

@Named
@RequestScoped
public class UsersController {
    
    @Inject
    DataServiceBeanLocal dataService;
    
    private List<User> allUsers;
    
    @PostConstruct
    public void initialize(){
        this.allUsers = dataService.getAllUsers();
    }

    public List<User> getAllUsers() {
        return allUsers;
    }
}
