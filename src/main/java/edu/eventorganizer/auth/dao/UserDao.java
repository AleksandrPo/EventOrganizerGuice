package edu.eventorganizer.auth.dao;

import com.google.inject.Inject;
import com.google.inject.persist.Transactional;
import edu.eventorganizer.application.model.Vehicle;
import edu.eventorganizer.auth.model.User;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class UserDao {
    @Inject
    EntityManager entityManager;

    public User findById(long id) {
        return entityManager.find(User.class, id);
    }

    public User findByUsername(String username) {
        List list = entityManager.createQuery(
                "select u from User u where u.username like :username")
                .setParameter("username", username)
                .getResultList();
        return list.isEmpty() ? null : (User) list.get(0);
    }

    public Optional findByEmail(String email) {
        List list = entityManager.createQuery(
                "select u from User u where u.email like :email")
                .setParameter("email", email)
                .getResultList();
        return list.isEmpty() ? null : (Optional) list.get(0);
    }

    public Optional getUserInfo() {
        return Optional.empty();
    }

    @Transactional
    public void save(User user) {
        entityManager.persist(user);
    }

    @Transactional
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
                        .setRole(user.getRole())
                        .build());
    }

    public List<Vehicle> getVehicleList(User user) {
        List list = entityManager.createQuery(
                "select v from Vehicle v join v.user_id u where u.id=:user_id")
                .setParameter("user_id", user.getId())
                .getResultList();
        return list.isEmpty() ? null : (List<Vehicle>) list;//TODO: make correctly
    }
}
