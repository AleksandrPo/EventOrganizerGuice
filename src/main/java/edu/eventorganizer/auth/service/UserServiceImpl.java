package edu.eventorganizer.auth.service;

import com.google.inject.Inject;
import edu.eventorganizer.auth.dao.UserDao;
import edu.eventorganizer.auth.model.User;

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
    public Boolean login(String username, String password) {
//        User u = userDao.findByUsername(username);
//        return u != null && u.getPassword().equals(password);
        return Optional.ofNullable(userDao.findByUsername(username))
                .map(User::getPassword)
                .filter(pass -> pass.equals(password))
                .isPresent();
    }

    @Override
    public boolean saveUser(User user) {
        User u = userDao.findByUsername(user.getUsername());
        if(u == null) {
            userDao.save(user);
        }
        return u != null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void updateUser(User user) {
        Optional u = Optional.ofNullable(userDao.findByUsername(user.getUsername()));
        u.ifPresent(i -> userDao.update(user));
    }
}
