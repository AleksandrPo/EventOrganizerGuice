package edu.eventorganizer.application.model;

import javax.persistence.*;

@Entity
@Table(name = "event_vehicle")
public class Vehicles {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event_id;
    @ManyToOne
    @JoinColumn(name = "vehile_id")
    private Vehicle vehicle_id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Event getEvent_id() {
        return event_id;
    }

    public void setEvent_id(Event event_id) {
        this.event_id = event_id;
    }

    public Vehicle getVehicle_id() {
        return vehicle_id;
    }

    public void setVehicle_id(Vehicle vehicle_id) {
        this.vehicle_id = vehicle_id;
    }
}
