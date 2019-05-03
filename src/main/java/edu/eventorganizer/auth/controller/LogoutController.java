package edu.eventorganizer.auth.controller;

import com.google.inject.Inject;
import com.sun.jersey.api.view.Viewable;
import edu.eventorganizer.auth.service.UserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/logout")
public class LogoutController {
//    @Inject
//    HttpServletResponse response;
//    @Inject
//    HttpServletRequest request;
    @Inject
    UserService userService;

    @GET
    public Viewable logout() {
//        Cookie[] cookies = request.getCookies();
//        if(cookies != null) {
//            for(Cookie cookie : cookies) {
//                cookie.setValue("");
//                cookie.setPath("/");
//                cookie.setMaxAge(0);
//                response.addCookie(cookie);
//            }
//        }
//        String uName = request.getUserPrincipal().getName();
//        HttpSession session = request.getSession(false);
//        if(session != null) {
//            session.invalidate();
//        }
        userService.logout();
        return new Viewable("/login.html");
    }
}
