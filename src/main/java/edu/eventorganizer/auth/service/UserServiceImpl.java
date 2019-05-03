package edu.eventorganizer.auth.service;

import com.google.inject.Inject;
import edu.eventorganizer.application.model.Vehicle;
import edu.eventorganizer.auth.dao.UserDao;
import edu.eventorganizer.auth.model.User;
import edu.eventorganizer.auth.realm.MyRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.env.Environment;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;

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
//        IniRealm iniRealm = new IniRealm("C:/Users/aleks/IdeaProjects/EventOrganizerGuice/src/main/webapp/WEB-INF/shiro.ini");
        Realm myRealm = new MyRealm();
        SecurityManager securityManager = new DefaultSecurityManager(myRealm);
        SecurityUtils.setSecurityManager(securityManager);

        Subject currentUser = SecurityUtils.getSubject();
        if(!currentUser.isAuthenticated()) {
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            try {
                currentUser.login(token);
                return true;
            } catch (UnknownAccountException iae) {
                System.out.println("There is no user with username: " + token.getPrincipal());
            } catch (IncorrectCredentialsException ice) {
                System.out.println("Password is wrong!");
            }
        } else {
            return true; //already logged in
        }
        return false;
    }

    @Override
    public void logout() {
        Subject currentUser = SecurityUtils.getSubject();
        currentUser.logout();
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
