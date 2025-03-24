package dev.mfikri;

import dev.mfikri.repository.EventRepository;
import dev.mfikri.repository.EventRepositoryImpl;
import dev.mfikri.service.EventService;
import dev.mfikri.service.EventServiceImpl;
import dev.mfikri.util.RedisConnectionUtil;
import dev.mfikri.view.EventView;
import redis.clients.jedis.*;

public class App {
    public static void main(String[] args) {
        JedisPooled jedis = RedisConnectionUtil.getJedis();

        EventRepository eventRepository = new EventRepositoryImpl(jedis);
        EventService eventService = new EventServiceImpl(eventRepository);
        EventView eventView = new EventView(eventService);
        eventView.showEventCommand();

        jedis.close();
    }
}
