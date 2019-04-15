package edu.eventorganizer.auth.dao;

import com.google.inject.Inject;
import edu.eventorganizer.auth.model.User;

import javax.persistence.EntityManager;
import java.util.Optional;

public class UserDao {
    @Inject
    EntityManager entityManager;

    public User findById(long id) {
        return entityManager.find(User.class, id);
    }

    public User findByUsername(String username) {
        String query = "FROM event_organizer WHERE username=:" + username;
        return (User) entityManager.createQuery(query);
    }

    public Optional findByEmail(String email) {
        String query = "FROM event_organizer WHERE email=:" + email;
        return Optional.of((User) entityManager.createQuery(query));
    }

    public void save(User user) {
        entityManager.persist(user);
    }

    public void update(User user) {
        entityManager.persist(
                new User.Builder()
                        .setUsername(user.getUsername())
                        .setPassword(user.getPassword())
                        .setEmail(user.getEmail())
                        .setFirstName(user.getFirstName())
                        .setLastName(user.getLastName())
                        .setPhone(user.getPhone())
                        .setStatus(user.getStatus())
                        .build());
    }
}
