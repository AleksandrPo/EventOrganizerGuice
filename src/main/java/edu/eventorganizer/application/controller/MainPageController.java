package edu.eventorganizer.application.controller;

import com.google.inject.Inject;
import com.sun.jersey.api.view.Viewable;
import edu.eventorganizer.application.model.Event;
import edu.eventorganizer.application.model.Events;
import edu.eventorganizer.application.service.EventService;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/main")
public class MainPageController {

    @Inject
    EventService eventService;

    @GET
    @Produces(MediaType.TEXT_HTML)
    public Viewable toMainPage() {
        return new Viewable("/jsp/mainPage.jsp");
    }

    @GET
    @Path("/table")
    @Produces(MediaType.APPLICATION_JSON)
    public JSONArray getTable() throws JSONException {
        List<Event> events = eventService.findEvents();
        JSONArray jsonArray = new JSONArray();
        if(events != null) {
            for (Event event : events) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("eventName", event.getEventName());
                jsonObject.put("startDate", event.getStartDate());
                jsonObject.put("endDate", event.getEndDate());
                jsonArray.put(jsonObject);
            }
        }
        return jsonArray;
    }
}
