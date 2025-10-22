package io.github.guisso.services;

import io.github.guisso.entities.User;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Initialized;
import jakarta.enterprise.event.Observes;

@ApplicationScoped
public class DataInitializer {

    @EJB
    private DataServiceLocal dataService;

    public void execute(@Observes @Initialized(ApplicationScoped.class) Object event) {
        if (dataService.getAllUsers().isEmpty()) {
            User guisso = dataService.createUser("Luis Guisso", "guisso", "asdf", "admin");
            User azacchi = dataService.createUser("Andrea Zacchi", "azacchi", "asdf", "user");

            dataService.createQuality("Wonderful", guisso);
            dataService.createQuality("Team Player", guisso);
            dataService.createQuality("Good Judgement", guisso);
            dataService.createQuality("Good Leader", guisso);

            dataService.createQuality("Dilligent", azacchi);
            dataService.createQuality("Responsible", azacchi);
            dataService.createQuality("Cares for his teammates", azacchi);
        }
    }
}
