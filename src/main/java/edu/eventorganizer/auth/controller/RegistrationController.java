package edu.eventorganizer.auth.controller;

import com.google.inject.Inject;
import com.sun.jersey.api.view.Viewable;
import edu.eventorganizer.auth.model.User;
import edu.eventorganizer.auth.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@Path("/reg")
public class RegistrationController {

    @Inject
    UserService userService;

    @GET
    @Produces(MediaType.TEXT_HTML)
    public Viewable toRegistration() {
        return new Viewable("/registration.html");
    }

    @POST
    @Path("/register")
    @Produces(MediaType.TEXT_HTML)//????
    public Response getRegPage(@FormParam("username") String username,
                               @FormParam("password") String password,
                               @FormParam("re_password") String re_password,
                               @FormParam("email") String email,
                               @FormParam("firstName") String firstName,
                               @FormParam("lastName") String lastName,
                               @FormParam("phone") short phone
                               ) throws URISyntaxException {
        User user;
        if(password.equals(re_password)) {
            user = new User.Builder()
                    .setUsername(username)
                    .setPassword(password)
                    .setEmail(email)
                    .setFirstName(firstName)
                    .setLastName(lastName)
                    .setPhone(phone)
                    .setStatus("ACTIVE")
                    .setPermission("USER")
                    .build();
            return userService.saveUser(user) ? Response.temporaryRedirect(new URI("/mainPage")).build()
                    : Response.temporaryRedirect(new URI("/")).build();
        }
        return Response.temporaryRedirect(new URI("/")).build();
    }
}
