package edu.eventorganizer.auth.model;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "email")
    private String email;
    @Column(name = "firstName")
    private String firstName;
    @Column(name = "lastName")
    private String lastName;
    @Column(name = "phone")
    private short phone;
    @Column(name = "u_status")
    private String status;//TODO: Statuses: ACTIVE, FROZEN, DELETED
    @Column(name = "permission")
    private String permission;//TODO: Permissions: USER, ADMIN

    public User() {}
    private User(String username, String password, String email, String firstName, String lastName, short phone, String status, String permission) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.status = status;
        this.permission = permission;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public short getPhone() {
        return phone;
    }

    public String getStatus() {
        return status;
    }

    public String getPermission() {
        return permission;
    }

    public static class Builder {

        private String username;
        private String password;
        private String email;
        private String firstName;
        private String lastName;
        private short phone;
        private String status;
        private String permission;

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
        public Builder setPhone(short phone) {
            this.phone = phone;
            return this;
        }
        public Builder setStatus(String status) {
            this.status = status;
            return this;
        }
        public Builder setPermission(String permission) {
            this.permission = permission;
            return this;
        }
        public User build() {
            return new User(username, password, email, firstName, lastName, phone, status, permission);
        }
    }
}
