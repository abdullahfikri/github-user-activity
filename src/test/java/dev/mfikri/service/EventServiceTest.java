package dev.mfikri.service;

import dev.mfikri.entity.Actor;
import dev.mfikri.entity.Event;
import dev.mfikri.entity.Repo;
import dev.mfikri.repository.EventRepository;
import dev.mfikri.repository.EventRepositoryImpl;
import dev.mfikri.util.EventData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class EventServiceTest {
    @Mock
    private EventRepository eventRepository;
    private EventService eventService;

    @BeforeEach
    void setUp() {
//        eventRepository = new EventRepositoryImpl();
        eventService = new EventServiceImpl(eventRepository);
    }

    @Test
    void testGetAllEvent() {
        String username = "abdullahfikri";

        List<Event> dummy = EventData.getDummy(username);

        Mockito.when(eventRepository.getEvents(username)).thenReturn(dummy);

        eventService.getAllEvents(username);

        Mockito.verify(eventRepository).getEvents(username);
    }
}
