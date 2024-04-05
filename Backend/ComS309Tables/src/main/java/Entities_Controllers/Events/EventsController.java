package Entities_Controllers.Events;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/events")
public class EventsController {

    @Autowired
    private EventsRepository eventsRepository;

    @GetMapping
    public List<Events> getAllEvents() {
        return eventsRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Events> getEventById(@PathVariable Long id) {
        Optional<Events> event = eventsRepository.findById(id);
        return event.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Events> createEvent(@RequestBody Events events) {
        Events savedEvent = eventsRepository.save(events);
        return ResponseEntity.ok(savedEvent);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Events> updateEvent(@PathVariable Long id, @RequestBody Events eventDetails) {
        return eventsRepository.findById(id)
                .map(existingEvent -> {
                    existingEvent.setType(eventDetails.getType());
                    existingEvent.setDescription(eventDetails.getDescription());
                    Events updatedEvent = eventsRepository.save(existingEvent);
                    return ResponseEntity.ok(updatedEvent);
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id) {
        if (!eventsRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        eventsRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
