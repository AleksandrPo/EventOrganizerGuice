package edu.eventorganizer.application.dao;

import com.google.inject.Inject;
import edu.eventorganizer.application.model.Event;

import javax.persistence.EntityManager;
import java.util.List;

public class EventDao {

    @Inject
    EntityManager em;

    public List<Event> getEvents() {
        List list = em.createQuery("select e from Event e").getResultList();
        return list.isEmpty() ? null : (List<Event>) list;
    }
}
