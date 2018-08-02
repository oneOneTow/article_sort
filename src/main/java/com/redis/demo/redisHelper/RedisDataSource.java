package com.redis.demo.redisHelper;

import org.springframework.stereotype.Component;
import redis.clients.jedis.ShardedJedis;

/**
 * @author vens
 * @date 2018-07-09 23:06
 **/
public interface RedisDataSource {
    public abstract ShardedJedis getRedisClient();
    public void returnResource(ShardedJedis shardedJedis);
    public void returnResource(ShardedJedis shardedJedis, boolean broken);

}
