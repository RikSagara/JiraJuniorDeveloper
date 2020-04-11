package ru.ngu.JiraJuniorDeveloper.Web;

import ru.ngu.JiraJuniorDeveloper.DataBase.UserDao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.persistence.Persistence;
import javax.servlet.annotation.WebListener;
import ru.ngu.JiraJuniorDeveloper.DataBase.UserDao;
import ru.ngu.JiraJuniorDeveloper.Model.User;
import ru.ngu.JiraJuniorDeveloper.Model.UserRole;

@WebListener
public class ApplicationListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("ProdPersistenceUnit");
        sce.getServletContext().setAttribute("factory", factory);
        EntityManager manager=factory.createEntityManager();
        UserDao user=new UserDao(manager);
        User foundUser=user.findUserByName("scott");
        if(foundUser==null){
            user.createUser("scott","tiger", UserRole.Admin);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        EntityManagerFactory factory = (EntityManagerFactory) sce.getServletContext()
                .getAttribute("factory");

        if (factory != null) {
            factory.close();
        }
    }
}
