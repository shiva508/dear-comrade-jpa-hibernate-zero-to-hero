package com.comrade.controller;

import com.comrade.entity.Event;
import com.comrade.service.EventService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/event")
public class EventController {

    private final EventService eventService;

    public EventController(@Qualifier("eventService") EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping("/save")
    public Event save(@RequestBody Event event) {
        return eventService.save(event);
    }

    @GetMapping("/fetchAll")
    public List<Event> fetchAll() {
        return eventService.fetchAll();
    }
}
