package edu.eventorganizer.auth.controller;

import com.google.inject.Inject;
import com.sun.jersey.api.view.Viewable;
import edu.eventorganizer.auth.model.User;
import edu.eventorganizer.auth.service.UserService;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
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
    @Path("register")
    @Produces(MediaType.TEXT_HTML)
    public Response getRegPage(@FormParam("username") String username,
                               @FormParam("password") String password,
                               @FormParam("re_password") String re_password,
                               @FormParam("email") String email,
                               @FormParam("firstName") String firstName,
                               @FormParam("lastName") String lastName,
                               @FormParam("phone") int phone
                               ) throws URISyntaxException {
        if(password.equals(re_password)) {
            User user = new User.Builder()
                    .setUsername(username)
                    .setPassword(password)
                    .setEmail(email)
                    .setFirstName(firstName)
                    .setLastName(lastName)
                    .setPhone(phone)
                    .build();
            return userService.saveUser(user) ? Response.seeOther(new URI("http://localhost:8080")).build()
                    : Response.seeOther(new URI("http://localhost:8080/reg")).build();
        }
        return Response.seeOther(new URI("http://localhost:8080/reg")).build();
    }
}
