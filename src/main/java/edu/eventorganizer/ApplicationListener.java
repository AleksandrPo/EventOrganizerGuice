package edu.eventorganizer.auth.listener;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.persist.PersistService;
import com.google.inject.persist.jpa.JpaPersistModule;
import com.google.inject.servlet.GuiceServletContextListener;
import com.sun.jersey.guice.JerseyServletModule;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;
import edu.eventorganizer.auth.controller.LoginController;
import edu.eventorganizer.application.controller.MainPageController;
import edu.eventorganizer.auth.controller.RegistrationController;

import javax.servlet.annotation.WebListener;

@WebListener
public class ApplicationListener extends GuiceServletContextListener {
    @Override
    protected Injector getInjector() {
        Injector injector = Guice.createInjector(new JerseyServletModule() {
            @Override
            protected void configureServlets() {
                bind(LoginController.class);
                bind(RegistrationController.class);
                bind(MainPageController.class);
                serve("/*").with(GuiceContainer.class);
            }
        }, new JpaPersistModule("db_manager"));
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
