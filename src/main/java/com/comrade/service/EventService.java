package com.comrade.service;

import com.comrade.entity.Event;
import com.comrade.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service("eventService")
@RequiredArgsConstructor
public class EventService implements CommonService<Event> {

    private final EventRepository eventRepository;

    @Override
    @Transactional(readOnly = false)
    public Event save(Event event) {
        return eventRepository.save(event);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Event> fetchAll() {
        return eventRepository.findAll();
    }
}
