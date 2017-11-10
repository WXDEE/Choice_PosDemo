package org.pos.dao.cache;

import redis.clients.jedis.JedisPool;

public class RedisDao {
    private final JedisPool jedisPool;

    public RedisDao(String ip, int port) {
        jedisPool = new JedisPool(ip, port);
    }
}
