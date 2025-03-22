package dev.mfikri;

import dev.mfikri.repository.EventRepository;
import dev.mfikri.repository.EventRepositoryImpl;
import dev.mfikri.service.EventService;
import dev.mfikri.service.EventServiceImpl;
import dev.mfikri.view.EventView;

public class App {
    public static void main(String[] args) {
        EventRepository eventRepository = new EventRepositoryImpl();
        EventService eventService = new EventServiceImpl(eventRepository);
        EventView eventView = new EventView(eventService);
        eventView.showEventCommand();
    }
}
