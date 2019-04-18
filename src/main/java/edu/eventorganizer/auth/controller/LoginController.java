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

@Path("/")
public class LoginController {

    @Inject
    UserService userService;

    @GET
    public Viewable toLoginPage() {
        return new Viewable("/login.html");
    }

//    @GET
//    @Produces(MediaType.TEXT_HTML)
//    public Response redirectToLoginPage(@Context HttpServletRequest request, @Context HttpServletResponse response) throws Exception {
//        String contextPath = request.getContextPath();
//        response.sendRedirect(contextPath + "login.html");
//        return Response.status(Response.Status.OK).build();
//    }

    @POST
    @Path("login")
    public Response login(@FormParam("username") String username, @FormParam("password") String password) {
//        Boolean isValid = userService.login(username, password);
        Boolean isValid = username.equals("user1") && password.equals("pass");
        return isValid ? Response.status(Response.Status.OK).build()
                : Response.status(Response.Status.BAD_REQUEST).build();

    }
}
