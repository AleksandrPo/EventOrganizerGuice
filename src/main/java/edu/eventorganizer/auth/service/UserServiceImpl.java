package edu.eventorganizer.auth.service;

import com.google.inject.Inject;
import edu.eventorganizer.application.model.Vehicle;
import edu.eventorganizer.auth.dao.UserDao;
import edu.eventorganizer.auth.model.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {

    @Inject
    UserDao userDao;

    @Override
    public Optional getUserById(long id) {
        return Optional.ofNullable(userDao.findById(id));
    }

    @Override
    public Optional getUserByUsername(String username) {
        return Optional.ofNullable(userDao.findByUsername(username));
    }

    @Override
    public Optional getUserByEmail(String email) {
        return Optional.ofNullable(userDao.findByEmail(email));
    }

    @Override
    public boolean login(String username, String password) {
        String error = null;
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        token.setRememberMe(true);
        Subject currentUser = SecurityUtils.getSubject();
        try {
            currentUser.login(token);
        } catch (Exception e) {
            error = e.getMessage();
        }
        return error == null;

//        return Optional.ofNullable(userDao.findByUsername(username))
//                .map(User::getPassword)
//                .filter(pass -> pass.equals(password))
//                .isPresent();
    }

    @Override
    public boolean saveUser(User user) {
        if(userDao.findByUsername(user.getUsername()) == null) {
            userDao.save(user);
            return true;
        }
        return false;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void updateUser(User user) {
        Optional u = Optional.ofNullable(userDao.findByUsername(user.getUsername()));
        u.ifPresent(i -> userDao.update(user));
    }

    @Override
    public List<Vehicle> getVehicleByNumber(User user) {
        return this.userDao.getVehicleList(user);
    }
}
