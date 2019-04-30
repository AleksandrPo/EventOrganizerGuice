package edu.eventorganizer.application.service;

import com.google.inject.ImplementedBy;
import edu.eventorganizer.application.model.Event;
import edu.eventorganizer.auth.service.UserServiceImpl;

import java.util.List;

@ImplementedBy(EventServiceImpl.class)
public interface EventService {
    List<Event> findEvents();
}
