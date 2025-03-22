package dev.mfikri.util;

import dev.mfikri.entity.Actor;
import dev.mfikri.entity.Event;
import dev.mfikri.entity.Repo;

import java.util.ArrayList;
import java.util.List;

public class EventData {
    public static List<Event> getDummy (String username) {

        Actor actor = new Actor(123, username);

        List<Event> events = new ArrayList<>();
        events.add(new Event("111", "CreateEvent", actor, new Repo(951799145, "abdullahfikri/github-user-activity")));
        events.add(new Event("112", "CreateEvent", actor, new Repo(951799145, "abdullahfikri/github-user-activity")));
        events.add(new Event("114", "CreateEvent", actor, new Repo(951595369, "abdullahfikri/task-tracker")));
        events.add(new Event("115", "CreateEvent", actor, new Repo(951595369, "abdullahfikri/task-tracker")));
        events.add(new Event("113", "WatchEvent", actor, new Repo(949355062, "thuanDaoSE/tasktracker")));
        events.add(new Event("116", "WatchEvent", actor, new Repo(949355062, "FasterXML/jackson-databind")));
        events.add(new Event("117", "WatchEvent", actor, new Repo(949355062, "24silver/webservice")));
        events.add(new Event("120", "WatchEvent", actor, new Repo(2, "eduardolat/kokoro-web")));
        events.add(new Event("118", "PushEvent", actor, new Repo(1, "kamranahmedse/developer-roadmap")));
        events.add(new Event("119", "IssueCommentEvent", actor, new Repo(1, "kamranahmedse/developer-roadmap")));
        events.add(new Event("121", "PullRequestEvent", actor, new Repo(1, "kamranahmedse/developer-roadmap")));
        events.add(new Event("122", "PushEvent", actor, new Repo(1, "kamranahmedse/developer-roadmap")));
        events.add(new Event("123", "PushEvent", actor, new Repo(1, "kamranahmedse/developer-roadmap")));

        return events;
    }
}
