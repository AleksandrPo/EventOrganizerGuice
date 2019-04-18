package edu.eventorganizer.auth.service;

import com.google.inject.ImplementedBy;
import edu.eventorganizer.auth.model.User;

import java.util.Optional;

@ImplementedBy(UserServiceImpl.class)
public interface UserService {
    Optional getUserById(long id);
    Optional getUserByUsername(String username);
    Optional getUserByEmail(String email);
    Boolean login(String username, String password);
    boolean saveUser(User user);
    void updateUser(User user);
}
