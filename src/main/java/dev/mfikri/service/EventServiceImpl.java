package dev.mfikri.service;

import dev.mfikri.entity.Event;
import dev.mfikri.entity.GithubEventType;
import dev.mfikri.exception.UserNotfoundException;
import dev.mfikri.repository.EventRepository;

import java.util.*;

public class EventServiceImpl implements EventService{
    private EventRepository eventRepository;

    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public void getAllEvents(String username) {
        try {
            List<Event> events = eventRepository.getEvents(username);
            Map<String, List<Event>> eventsMap = new HashMap<>();

            events.forEach(event -> {
                String key = event.getType() + ":" + event.getRepo().getName();
                eventsMap.putIfAbsent(key, new ArrayList<>());
                eventsMap.get(key).add(event);
            });

            eventsMap.forEach((key, eventList) -> {
                String[] keys = key.split(":");
                String event = keys[0];
                String repo = keys[1];
                int size = eventList.size();

                if (event.equals(GithubEventType.PushEvent.toString())){
                    System.out.println("Pushed " + size + " commits to " + repo);
                } else if (event.equals(GithubEventType.CreateEvent.toString())) {
                    System.out.println("Create " + size + " Git Branch or tag in " + repo);
                } else if (event.equals(GithubEventType.DeleteEvent.toString())) {
                    System.out.println("Delete " + size + " Git Branch or tag in " + repo);
                } else if (event.equals(GithubEventType.ForkEvent.toString())) {
                    System.out.println("Started Fork from repo " + repo);
                } else if (event.equals(GithubEventType.PullRequestEvent.toString())) {
                    System.out.println("Doing " + size + " pull request to " + repo);
                } else if (event.equals(GithubEventType.PullRequestReviewEvent.toString())) {
                    System.out.println("Review " + size + " pull request in " + repo );
                } else if (event.equals(GithubEventType.PullRequestReviewCommentEvent.toString())) {
                    System.out.println("Type " + size + " comment at pull request review in " + repo );
                } else if (event.equals(GithubEventType.ReleaseEvent.toString())) {
                    System.out.println("Release " + size + " ");
                } else if (event.equals(GithubEventType.WatchEvent.toString())) {
                    System.out.println("Starred " + repo);
                }

            } );



        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }
}
