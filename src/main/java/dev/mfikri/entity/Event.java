package dev.mfikri.entity;

import java.util.Arrays;

public class Event {
    private String id;
    private String type;
    private Actor actor;
    private Repo repo;

    public Event() {
    }

    public Event(String id, String type, Actor actor, Repo repo) {
        this.id = id;
        this.type = type;
        this.actor = actor;
        this.repo = repo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Actor getActor() {
        return actor;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }

    public Repo getRepo() {
        return repo;
    }

    public void setRepo(Repo repo) {
        this.repo = repo;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", actor=" + actor +
                ", repo=" + repo +
                '}';
    }
}
