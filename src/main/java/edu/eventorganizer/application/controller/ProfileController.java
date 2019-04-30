package edu.eventorganizer.application.controller;

import com.google.inject.Inject;
import com.sun.jersey.api.view.Viewable;
import edu.eventorganizer.application.service.EventService;
import edu.eventorganizer.auth.model.User;
import edu.eventorganizer.auth.service.UserService;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.security.Principal;
import java.util.Optional;

@Path("/profile")
public class ProfileController {

    @Inject
    EventService eventService;
    @Inject
    UserService userService;

    @GET
    @Produces(MediaType.TEXT_HTML)
    public Viewable getProfile() {
        return new Viewable("/jsp/profile.jsp");
    }

    @GET
    @Path("/getUserData")
    @Produces(MediaType.APPLICATION_JSON)
    public JSONObject getUserData(@Context HttpServletRequest request) throws JSONException {
        Principal p = request.getUserPrincipal();
        String username = p.getName();
        User user = (User) userService.getUserByUsername(username).orElse(new User());
        JSONObject userJSON = new JSONObject();
        userJSON.put("username", user.getUsername());
        return userJSON;
    }
}
