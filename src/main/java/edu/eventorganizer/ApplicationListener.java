package edu.eventorganizer;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.persist.PersistService;
import com.google.inject.persist.jpa.JpaPersistModule;
import com.google.inject.servlet.GuiceServletContextListener;
import com.sun.jersey.guice.JerseyServletModule;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;
import edu.eventorganizer.application.controller.ProfileController;
import edu.eventorganizer.auth.controller.LoginController;
import edu.eventorganizer.application.controller.MainPageController;
import edu.eventorganizer.auth.controller.LogoutController;
import edu.eventorganizer.auth.controller.RegistrationController;
import edu.eventorganizer.auth.module.TestShiroModule;
import org.apache.shiro.guice.web.ShiroWebModule;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.annotation.WebListener;

@WebListener
public class ApplicationListener extends GuiceServletContextListener {

    private ServletContext servletContext;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        this.servletContext = servletContextEvent.getServletContext();
        super.contextInitialized(servletContextEvent);
    }

    @Override
    protected Injector getInjector() {
        Injector injector = Guice.createInjector(new JerseyServletModule() {
            @Override
            protected void configureServlets() {
                bind(LoginController.class);
                bind(RegistrationController.class);
                bind(MainPageController.class);
                bind(ProfileController.class);
                bind(LogoutController.class);
//                bind(LoginServlet.class);
//                filter("/login").through(LoginFilter.class); //TODO: need to understand filters
//                serve("/login").with(LoginServlet.class);
                serve("/*").with(GuiceContainer.class);
            }
        }, new JpaPersistModule("db_manager")
                ,new TestShiroModule(servletContext)
                ,ShiroWebModule.guiceFilterModule());
        injector.getInstance(JpaInitializer.class);
        return injector;
    }
}

class JpaInitializer {
    @Inject
    public JpaInitializer(PersistService persistService) {
        persistService.start();
    }
}
