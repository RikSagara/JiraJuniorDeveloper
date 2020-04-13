package ru.ngu.JiraJuniorDeveloper.Web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.ngu.JiraJuniorDeveloper.DataBase.UserDao;
import ru.ngu.JiraJuniorDeveloper.Model.UserRole;

@Component
public class StartupListener {
    @Autowired
    private UserDao users;

    @EventListener
    @Transactional
    public void applicationStarted(ContextRefreshedEvent event) {
        if (users.findUserByName("scott") == null) {
            users.createUser("scott","tiger", UserRole.Admin);
        }
    }
}
