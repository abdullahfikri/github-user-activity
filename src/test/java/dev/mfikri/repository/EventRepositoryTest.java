package dev.mfikri.repository;

import dev.mfikri.entity.Event;
import dev.mfikri.exception.UserNotfoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Year;

public class EventRepositoryTest {
    private EventRepository eventRepository;

    @BeforeEach
    void setUp() {
        eventRepository = new EventRepositoryImpl();
    }

    @Test
    void testGetEventsSuccess() {
        Event events = eventRepository.getEvents("abdullahfikri");

        Assertions.assertNotNull(events);
    }

    @Test
    void testGetEventsNotFoundEvents() {
        // test is doing for user who not use GitHub within 90days per 21 March 2025.
        Event events = eventRepository.getEvents("lephuocloc1729");
        Assertions.assertNull(events);
    }

    @Test
    void testGetEventsNotFoundUser() {
        Assertions.assertThrowsExactly(UserNotfoundException.class, () -> eventRepository.getEvents("usernotvalid3210932"));
    }
}
