package edu.eventorganizer.auth.model;

import edu.eventorganizer.application.model.Events;
import edu.eventorganizer.application.model.Vehicle;
import lombok.Getter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "salt")
    private String salt;
    @Column(name = "email")
    private String email;
    @Column(name = "firstName")
    private String firstName;
    @Column(name = "lastName")
    private String lastName;
    @Column(name = "phone")
    private int phone;

    @OneToMany(mappedBy = "user_id")
    private Set<Vehicle> vehicle;
    @OneToMany(mappedBy = "event_id")
    private Set<Events> events;

    @Enumerated(EnumType.STRING)
    private Status status;
    @Enumerated(EnumType.STRING)
    private Roles role;


    public User() {}
    private User(String username, String password, String email, String firstName, String lastName, int phone, Status status, Roles role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.status = status;
        this.role = role;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setStatus (Status status) {
        this.status = status;
    }

    public void setRole(Roles role) {
        this.role = role;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public static class Builder {

        private String username;
        private String password;
        private String email;
        private String firstName;
        private String lastName;
        private int phone;
        private Status status;
        private Roles role;

        public Builder setUsername(String username) {
            this.username = username;
            return this;
        }
        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }
        public Builder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }
        public Builder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }
        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }
        public Builder setPhone(int phone) {
            this.phone = phone;
            return this;
        }
        public Builder setStatus(Status status) {
            this.status = status;
            return this;
        }
        public Builder setRole(Roles role) {
            this.role = role;
            return this;
        }
        public User build() {
            return new User(username, password, email, firstName, lastName, phone,
                    status == null ? Status.ACTIVE : status,
                    role == null ? Roles.USER : role);
        }
    }
}
