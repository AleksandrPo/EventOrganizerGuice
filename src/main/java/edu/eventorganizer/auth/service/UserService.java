package edu.eventorganizer.auth.service;

import com.google.inject.ImplementedBy;
import edu.eventorganizer.application.model.Vehicle;
import edu.eventorganizer.auth.model.User;

import java.util.List;
import java.util.Optional;

@ImplementedBy(UserServiceImpl.class)
public interface UserService {
    Optional getUserById(long id);
    Optional getUserByUsername(String username);
    Optional getUserByEmail(String email);
    boolean login(String username, String password);
    boolean saveUser(User user);
    void updateUser(User user);
    List<Vehicle> getVehicleByNumber(User user);
}
