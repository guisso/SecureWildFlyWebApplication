package io.github.guisso.services;

import io.github.guisso.entities.Quality;
import io.github.guisso.entities.User;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.security.enterprise.identitystore.Pbkdf2PasswordHash;

@Stateless
public class DataServiceBean
        implements DataServiceBeanLocal {

    @PersistenceContext(unitName = "secureApp")
    EntityManager em;

    @Inject
    Pbkdf2PasswordHash passwordHasher;

    @Override
    public User createUser(String name, String username, String password, String group) {

        // @see ApplicationConfig
        Map<String, String> parameters = new HashMap<>();
        parameters.put("Pbkdf2PasswordHash.Iterations", "3071");
        parameters.put("Pbkdf2PasswordHash.Algorithm", "PBKDF2WithHmacSHA512");
        parameters.put("Pbkdf2PasswordHash.SaltSizeBytes", "64");
        passwordHasher.initialize(parameters);

        User newUser = new User(
                name,
                username,
                passwordHasher.generate(
                        password.toCharArray()),
                group);
        em.persist(newUser);
//        em.flush();
        return newUser;
    }

    @Override
    public Quality createQuality(String description, User user) {
        Quality newQuality = new Quality(description, user);
        em.persist(newQuality);
//        em.flush();
        return newQuality;
    }

    @Override
    public List<User> getAllUsers() {
        return em.createNamedQuery("User.all", User.class).getResultList();
    }

    @Override
    public Optional<User> getUser(String username) {
        return em.createNamedQuery("User.byUsername", User.class)
                .setParameter("username", username)
                .getResultList()
                .stream()
                .findFirst(); // Can be null (Optional)...
    }

    @Override
    public List<Quality> getQualities(User user) {
        return em.createNamedQuery("Quality.byUser", Quality.class)
                .setParameter("userId", user.getId())
                .getResultList();
    }
}
