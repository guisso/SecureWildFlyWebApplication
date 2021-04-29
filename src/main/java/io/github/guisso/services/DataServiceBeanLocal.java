package io.github.guisso.services;

import io.github.guisso.entities.Quality;
import io.github.guisso.entities.User;
import java.util.List;
import java.util.Optional;
import javax.ejb.Local;

/**
 *
 * @author Luis Guisso <luis dot guisso at ifnmg dot edu dot br>
 */
@Local
public interface DataServiceBeanLocal {
    
    public User createUser(String name, String username, String password, String group);
    
    public Quality createQuality(String description, User user);
    
    public List<User> getAllUsers();
    
    public Optional<User> getUser(String username);
    
    public List<Quality> getQualities(User user);
}
