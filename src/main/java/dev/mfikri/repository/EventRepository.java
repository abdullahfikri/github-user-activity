package dev.mfikri.repository;

import dev.mfikri.entity.Event;

import java.util.List;

public interface EventRepository {
    public List<Event> getEvents(String username);
}
