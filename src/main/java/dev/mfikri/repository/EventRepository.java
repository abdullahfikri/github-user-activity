package dev.mfikri.repository;

import dev.mfikri.entity.Event;

public interface EventRepository {
    public Event getEvents(String username);
}
