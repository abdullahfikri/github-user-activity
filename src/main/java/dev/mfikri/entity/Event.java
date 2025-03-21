package dev.mfikri.entity;

import java.util.Arrays;

public class Event {
    private String id;
    private String type;
    private Actor actor;
    private Repo repo;

    public Event() {
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

    public static Event fromJson(String json) {
        json = json.replace("{", "").replace("}", "").replace("\"", "");

        String[] arrayJson = json.split(",");

        String id = arrayJson[0].split(":")[1].strip();
        String type = arrayJson[1].split(":")[1].strip();
        int actorId = Integer.parseInt(arrayJson[2].split("id:")[1].strip());
        String actorLogin = arrayJson[3].split(":")[1].strip();
        int repoId = Integer.parseInt(arrayJson[8].split("id:")[1].strip());
        String repoName = arrayJson[9].split(":")[1].strip();

        Event event = new Event();
        event.setId(id);
        event.setType(type);
        event.setActor(new Actor(actorId, actorLogin));
        event.setRepo(new Repo(repoId, repoName));

        return event;
    }
}
