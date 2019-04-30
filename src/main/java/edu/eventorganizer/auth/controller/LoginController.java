package edu.eventorganizer.auth.controller;

import com.google.inject.Inject;
import com.sun.jersey.api.view.Viewable;
import edu.eventorganizer.auth.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;

@Path("/")
public class LoginController {

    @Inject
    UserService userService;

    @GET
    public Viewable toLoginPage() {
        return new Viewable("/login.html");
    }

    @POST
    public Viewable toLPage() {
        return new Viewable("/login.html");
    }

    @POST
    @Path("login")
    @Produces(MediaType.TEXT_HTML)
    public Response login(@FormParam("username") String username, @FormParam("password") String password) throws URISyntaxException {
        return userService.login(username, password) ? Response.seeOther(new URI("http://localhost:8080/main")).build()
                : Response.seeOther(new URI("http://localhost:8080/")).build();

    }
}
