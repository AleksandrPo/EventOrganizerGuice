package edu.eventorganizer.auth.model;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private short phone;
    private Status status;

    public User() {}
    public User(String username, String password, String email, String firstName, String lastName, short phone, Status status) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
    }

    public long getId() {
        return id;
    }

//    public void setId(long id) {
//        this.id = id;
//    }

    @Column(name = "username")
    public String getUsername() {
        return username;
    }

//    public void setUsername(String username) {
//        this.username = username;
//    }

    @Column(name = "password")
    public String getPassword() {
        return password;
    }

//    public void setPassword(String password) {
//        this.password = password;
//    }

    @Column(name = "email")
    public String getEmail() {
        return email;
    }

//    public void setEmail(String email) {
//        this.email = email;
//    }

    @Column(name = "firstName")
    public String getFirstName() {
        return firstName;
    }

//    public void setFirstName(String firstName) {
//        this.firstName = firstName;
//    }

    @Column(name = "lastName")
    public String getLastName() {
        return lastName;
    }

//    public void setLastName(String lastName) {
//        this.lastName = lastName;
//    }

    @Column(name = "phone")
    public short getPhone() {
        return phone;
    }

//    public void setLastName(Status status) {
//        this.status = status;
//    }

    @Column(name = "status")
    public Status getStatus() {
        return status;
    }

//    public void setPhone(short phone) {
//        this.phone = phone;
//    }

    public static class Builder {

        private String username;
        private String password;
        private String email;
        private String firstName;
        private String lastName;
        private short phone;
        private Status status;

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
        public Builder setStatus(Status status) {
            this.status = status;
            return this;
        }
        public User build() {
            return new User(username, password, email, firstName, lastName, phone, status);
        }
    }
}
