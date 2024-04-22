package Entities_Controllers.Events;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class EventController {
    @Autowired
    private EventRepository eventRepository;

    private final String success = "{\"message\":\"success\"}";
    private final String failure = "{\"message\":\"failure\"}";
    @GetMapping(path = "/events")
    List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    @GetMapping(path = "/events/{id}")
    Event getEventById(@PathVariable int id) {
        return eventRepository.findById(id);
    }

    @PostMapping(path = "/events")
    <T> T createEvent(@RequestBody Event event) {
        if (event == null) {
            return (T) failure;
        }
        eventRepository.save(event);
        return (T) event;
    }

    @PutMapping("events/{id}")
    Event updateEvent(@PathVariable int id, @RequestBody Event request) {
        Event event = eventRepository.findById(id);

        if(event == null) {
            throw new RuntimeException("event id does not exist");
        }

        if (request.getId() == 0) {
            request.setId(event.getId());
        }
        if (request.getTitle() == null) {
            request.setTitle(event.getTitle());
        }
        if (request.getDescription() == null) {
            request.setDescription(event.getDescription());
        }
        if (request.getCondition1() == null) {
            request.setCondition1(event.getCondition1());
        }
        if (request.getCondition2() == null) {
            request.setCondition2(event.getCondition1());
        }
        if (request.getHpChange() == null) {
            request.setHpChange(event.getHpChange());
        }

        eventRepository.save(request);
        return eventRepository.findById(request.getId());
    }

    @DeleteMapping("events/{id}")
    String deleteEvent(@PathVariable int id) {
        if (eventRepository.findById(id) == null) {
            return failure;
        } else {
            eventRepository.deleteById(id);
            return success;
        }
    }
}
