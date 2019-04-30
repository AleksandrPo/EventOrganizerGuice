package edu.eventorganizer.auth.controller;

import com.google.inject.Inject;
import com.sun.jersey.api.view.Viewable;
import edu.eventorganizer.auth.service.UserService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/main")
public class MainPageController {

    @Inject
    UserService userService;

    @GET
    public Viewable toMainPage() {
        return new Viewable("/mainPage.html");
    }
}
