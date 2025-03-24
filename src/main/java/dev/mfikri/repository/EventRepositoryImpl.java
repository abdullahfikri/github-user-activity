package dev.mfikri.repository;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import dev.mfikri.entity.Actor;
import dev.mfikri.entity.Event;
import dev.mfikri.entity.Repo;
import dev.mfikri.exception.UserNotfoundException;

import com.fasterxml.jackson.databind.ObjectMapper;
import redis.clients.jedis.UnifiedJedis;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class EventRepositoryImpl implements EventRepository {
    UnifiedJedis redis;
    ObjectMapper objectMapper = new ObjectMapper().disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    Kryo kryo = new Kryo();

    public EventRepositoryImpl(UnifiedJedis redis) {
        this.redis = redis;
    }

    @Override
    public List<Event> getEvents(String username) {
        kryo.register(ArrayList.class);
        kryo.register(Event.class);
        kryo.register(Actor.class);
        kryo.register(Repo.class);
        try {

            if (!redis.exists(username)) {
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

                if (response.statusCode() == 404) {
                    throw new UserNotfoundException();
                }
                String responseBody = response.body();

                if (responseBody.length() == 2) {
                    return null;
                }

                List<Event> events = objectMapper.readValue(responseBody, new TypeReference<List<Event>>() {
                });

                saveEventToRedis(username, events);
                httpClient.close();
                return events;
            }
            return getEventsFromRedis(username);

        }  catch (IOException | InterruptedException e) {
            throw new RuntimeException("Failed to catch the api " + e.getMessage());
        }
    }

    private void saveEventToRedis (String key, List<Event> events) throws IOException {
        try (ByteArrayOutputStream stream = new ByteArrayOutputStream();
             Output output = new Output(stream)
        ) {
            kryo.writeObject(output, events);
            output.flush();
            redis.setex(key.getBytes(), 3600L, stream.toByteArray());
        }
    }

    private List<Event> getEventsFromRedis (String key) {
        byte[] bytes = redis.get(key.getBytes());
        if (bytes == null) return null;

        try (Input input = new Input(new ByteArrayInputStream(bytes))){
            return kryo.readObject(input, ArrayList.class);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
