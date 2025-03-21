package dev.mfikri.repository;

import dev.mfikri.entity.Event;
import dev.mfikri.exception.UserNotfoundException;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class EventRepositoryImpl implements EventRepository {

    @Override
    public Event getEvents(String username) {
        try {
            HttpClient httpClient = HttpClient.newBuilder()
                    .version(HttpClient.Version.HTTP_2)
                    .followRedirects(HttpClient.Redirect.NORMAL)
                    .connectTimeout(Duration.ofSeconds(20))
                    .build();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://api.github.com/users/" + username + "/events"))
                    .timeout(Duration.ofMinutes(2))
                    .header("Content-Type", "application/json")
                    .header("Accept", "application/vnd.github+json")
                    .header("X-GitHub-Api-Version", "2022-11-28")
                    .GET()
                    .build();


            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.statusCode());

            if (response.statusCode() == 404) {
                throw new UserNotfoundException();
            }
            String responseBody = response.body();

            if (responseBody.length() == 2) {
                return null;
            }

            List<String> events = new ArrayList<>();

            String[] eventsJson = responseBody.replace("[", "").replace("]", "").split("},\\{");
            for (String eventJson : eventsJson) {
                if (!eventJson.endsWith("}") && !eventJson.startsWith("{")){
                    eventJson = "{" + eventJson + "}";
                    events.add(eventJson);
                } else if (!eventJson.endsWith("}")) {
                    eventJson = eventJson + "}";
                    events.add(eventJson);
                } else if (!eventJson.startsWith("{")) {
                    eventJson = "{" + eventJson;
                    events.add(eventJson);
                } else  {
                    events.add(eventJson);
                }
            }

            httpClient.close();
            return Event.fromJson(events.getFirst());
        }  catch (IOException | InterruptedException e) {
            throw new RuntimeException("Failed to catch the api " + e.getMessage());
        }
    }
}
