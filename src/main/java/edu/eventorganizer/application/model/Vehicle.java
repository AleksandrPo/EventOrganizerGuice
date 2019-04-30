package edu.eventorganizer.application.model;

import edu.eventorganizer.auth.model.User;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "vehicle")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "brand")
    private String brand;
    @Column(name = "color")
    private String color;
    @Column(name = "car_number")
    private String carNumber;
    @Column(name = "seats")
    private int seats;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user_id;

    @OneToMany(mappedBy = "vehicle_id")
    private Set<Vehicles> vehicles;

    @OneToMany(mappedBy = "vehicle_id")
    private Set<Events> events;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public Set<Vehicles> getVehicles() {
        return vehicles;
    }

    public void setVehicles(Set<Vehicles> vehicles) {
        this.vehicles = vehicles;
    }

    public Set<Events> getEvents() {
        return events;
    }

    public void setEvents(Set<Events> events) {
        this.events = events;
    }

    public User getUser_id() {
        return user_id;
    }

    public void setUser_id(User user_id) {
        this.user_id = user_id;
    }
}
