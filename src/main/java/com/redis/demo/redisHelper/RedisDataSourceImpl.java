package com.redis.demo.redisHelper;

import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

/**
 * @author vens
 * @date 2018-07-09 23:08
 **/
public class RedisDataSourceImpl implements RedisDataSource {
    private ShardedJedisPool shardedJedisPool;
    public ShardedJedis getRedisClient() {
        return shardedJedisPool.getResource();
    }

    public void returnResource(ShardedJedis shardedJedis) {
        shardedJedis.close();
    }

    public void returnResource(ShardedJedis shardedJedis, boolean broken) {
        returnResource(shardedJedis);
    }
    public void setShardedJedisPool(ShardedJedisPool shardedJedisPool) {
        this.shardedJedisPool = shardedJedisPool;
    }

}
