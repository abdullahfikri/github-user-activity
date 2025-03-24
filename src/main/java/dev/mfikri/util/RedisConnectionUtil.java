package dev.mfikri.util;

import redis.clients.jedis.DefaultJedisClientConfig;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisClientConfig;
import redis.clients.jedis.JedisPooled;

public class RedisConnectionUtil {
    private static final JedisPooled jedis;


    static {
        String REDIS_ID = System.getenv("REDIS_ID");
        String REDIS_PASSWORD = System.getenv("REDIS_PASSWORD");
        String REDIS_HOST = System.getenv("REDIS_HOST");
        String REDIS_PORT = System.getenv("REDIS_PORT");

        HostAndPort address = new HostAndPort(REDIS_HOST != null ? REDIS_HOST: "127.0.0.1", REDIS_PORT != null ? Integer.parseInt(REDIS_PORT) : 6379);
        JedisClientConfig clientConfig = DefaultJedisClientConfig.builder()
                .user(REDIS_ID)
                .password(REDIS_PASSWORD)
                .build();

        jedis = new JedisPooled(address, clientConfig);
    }

public static JedisPooled getJedis () { return jedis; }
}
