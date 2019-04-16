package edu.eventorganizer.auth.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;

@Path("/registration")
public class RegistrationController {

    @GET
    @Produces(MediaType.TEXT_HTML)
    public Response getRegPage() {
        return Response.temporaryRedirect(URI.create("http://localhost:8080/login.html")).build();
    }
}
