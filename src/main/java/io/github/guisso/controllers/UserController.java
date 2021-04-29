package io.github.guisso.controllers;

import io.github.guisso.entities.Quality;
import io.github.guisso.entities.User;
import java.util.List;
import java.util.Optional;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.security.enterprise.SecurityContext;
import javax.servlet.ServletException;
import io.github.guisso.services.DataServiceBeanLocal;

@Named
@RequestScoped
public class UserController {

    @Inject
    DataServiceBeanLocal dataService;

    @Inject
    SecurityContext securityContext;

    @Inject
    FacesContext facesContext;

    private Optional<User> currentUser;
    private List<Quality> currentQualities;

    @PostConstruct
    public void initialize() {
        String username = securityContext.getCallerPrincipal().getName();
        this.currentUser = dataService.getUser(username);
        this.currentUser.ifPresent(user -> {
            this.currentQualities = dataService.getQualities(user);
        });
    }

    public User getCurrentUser() {
        return currentUser.orElse(null);
    }

    public List<Quality> getCurrentQualities() {
        return currentQualities;
    }

    public boolean isAuthenticated() {
        return securityContext.getCallerPrincipal() != null;
    }

    public boolean isAllowedToSeeUsers() {
        return securityContext.isCallerInRole("admin");
    }

    public String logout() throws ServletException {
        facesContext.getExternalContext()
                .invalidateSession();
        return "/login?faces-redirect=true";
    }
}
