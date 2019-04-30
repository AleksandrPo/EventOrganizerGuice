package edu.eventorganizer.application.service;

import com.google.inject.Inject;
import edu.eventorganizer.application.dao.EventDao;
import edu.eventorganizer.application.model.Event;

import java.util.List;

public class EventServiceImpl implements EventService {

    @Inject
    EventDao eventDao;

    @Override
    public List<Event> findEvents() {
        return eventDao.getEvents();
    }
}
