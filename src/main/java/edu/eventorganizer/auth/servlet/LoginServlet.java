package edu.eventorganizer.auth.servlet;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import edu.eventorganizer.auth.dao.UserDao;
import edu.eventorganizer.auth.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

//@Singleton
//public class LoginServlet extends HttpServlet {
//
//    @Inject
//    UserDao userDao;
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        User user = new User.Builder()
//                .setUsername(req.getParameter("username"))
//                .setPassword(req.getParameter("password"))
//                .build();
//        User u = userDao.findByUsername(user.getUsername());
//        u.setActive(u != null);
//        if(u.isActive() && u.getPassword().equals(user.getPassword())) {
//            HttpSession session = req.getSession(true);
//            session.setAttribute("currentSessionUser", user);
//            resp.sendRedirect("/main");
//        } else {
//            System.err.print("Wrong username or password");
//            resp.sendRedirect("/");
//        }
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//    }
//}
