package edu.eventorganizer.auth.controller;

import com.google.inject.Inject;
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

//    @GET
//    @Produces(MediaType.TEXT_HTML)
//    public Response redirectToLoginPage(@Context HttpServletRequest request, @Context HttpServletResponse response) throws Exception {
//        String contextPath = request.getContextPath();
//        response.sendRedirect(contextPath + "/login.html");
//        return Response.status(Response.Status.OK).build();
////        return Response.status(200).location(URI.create("http://localhost:8080/login.html")).build();
//    }

//    @GET
//    @Produces(MediaType.TEXT_HTML)
//    public Response redirectToLoginPage() {
//        return Response.status(200).location(URI.create("http://localhost:8080/login.html")).build();
//    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    public Response getInteractionsData() {
        return Response.temporaryRedirect(URI.create("http://localhost:8080/login.html")).build();
    }

//    @GET
//    @Produces(MediaType.TEXT_PLAIN)
//    public String someString() {
//        return "ENTERED";
//    }

    @GET
    @Path("/login/{username}/{password}/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(@PathParam("username") String username, @PathParam("password") String password) {
        Boolean isValid = userService.login(username, password);
        return isValid ? Response.status(Response.Status.MOVED_PERMANENTLY).location(URI.create("http://localhost/mainPage")).build()
                : Response.status(Response.Status.MOVED_PERMANENTLY).location(URI.create("http://localhost:8080/login")).build();
    }

    @GET
    @Path("/registration")
    @Produces(MediaType.APPLICATION_JSON)
    public String registration(@PathParam("username") String username, @PathParam("password") String password) {
        Boolean isValid = userService.login(username, password);
        return isValid ? "redirect:main" : "home";
    }

    @POST
    @Path("/registration")
    @Produces(MediaType.APPLICATION_JSON)
    public String register(@PathParam("username") String username, @PathParam("password") String password) {
        Boolean isValid = userService.login(username, password);
        return isValid ? "redirect:main" : "home";
    }
}
