package com.switchsky.config.redis.impl;



import com.alibaba.fastjson.JSON;
import com.switchsky.config.redis.RedisService;
import com.switchsky.config.redis.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

/**
 * redis操作封装类
 */
@Service
public class RedisServiceImpl implements RedisService {

    /*redis工具类，用于获取jedis连接，进行redis数据库的操作*/
    @Autowired
    private RedisUtil redisUtil;

    /**
     * redis string类型，获取单个对象
     *
     * @param key   键
     * @param clazz 取出的数据类型
     * @param <T>
     * @return
     */
    public <T> T get(String key, Class<T> clazz) {
        Jedis jedis = null;
        try {
            jedis = redisUtil.getJedis();
            String str = jedis.get(key);
            return stringToBean(str, clazz);
        } finally {
            returnToPool(jedis);
        }

    }

    /**
     * redis string类型，存储对象
     *
     * @param key   键
     * @param value 键值
     * @return
     */
    public <T> boolean set(String key, T value) {
        Jedis jedis = null;
        try {
            jedis = redisUtil.getJedis();
            String str = beanToString(value);
            if (str == null || str.length() <= 0)
                return false;
            jedis.set(key, str);
            return true;
        } finally {
            returnToPool(jedis);
        }

    }

    /**
     * 删除key
     *
     * @param key 键，一个或多个
     * @return
     */
    public boolean del(String... key) {
        Jedis jedis = null;
        try {
            jedis = redisUtil.getJedis();
            jedis.del(key);
            if (jedis.exists(key) == 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        } finally {
            returnToPool(jedis);
        }
    }


    /**
     * 为给定得key设置时间，以秒计
     * @param key 键
     * @param seconds 秒
     * @return
     */
    public boolean pexpire(String key,int seconds) {
        Jedis jedis=null;
        try {
            jedis = redisUtil.getJedis();
            jedis.expire(key, seconds);
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            returnToPool(jedis);
        }
    }


    /**
     * redis hash类型，存储单个对象
     *
     * @param key   键
     * @param value 值
     * @return
     */
    @Override
    public <T> boolean hset(String key, String field, T value) {
        Jedis jedis = null;
        try {
            jedis = redisUtil.getJedis();
            String str = beanToString(value);
            if (str == null || str.length() <= 0)
                return false;
            jedis.hset(key, field, str);
            return true;
        } finally {
            returnToPool(jedis);
        }

    }

    /**
     * redis hash类型，获取单个对象
     *
     * @param key   键
     * @param clazz 取出的数据类型
     * @return
     */
    @Override
    public <T> T hget(String key, String field, Class<T> clazz) {
        Jedis jedis = null;
        try {
            jedis = redisUtil.getJedis();
            String str = jedis.hget(key, field);
            return stringToBean(str, clazz);
        } finally {
            returnToPool(jedis);
        }
    }

    /**
     * redis list类型，存储单个对象
     *
     * @param key   键
     * @param value 值
     * @return
     */
    @Override
    public <T> boolean lpust(String key, T value) {
        Jedis jedis = null;
        try {
            jedis = redisUtil.getJedis();
            String str = beanToString(value);
            if (str == null || str.length() <= 0)
                return false;
            jedis.lpush(key, str);
            return true;
        } finally {
            returnToPool(jedis);
        }
    }

    /**
     * redis list类型，获取指定下标元素
     *
     * @param key 键
     * @return
     */
    @Override
    public <T> T lindex(String key, Integer index, Class<T> clazz) {
        Jedis jedis = null;
        try {
            jedis = redisUtil.getJedis();
            String str = jedis.lindex(key, index);
            return stringToBean(str, clazz);
        } finally {
            returnToPool(jedis);
        }
    }

    /**
     * 判断是否存在
     *
     * @param key
     * @param <T>
     * @return
     */
    public <T> boolean exists(String key) {
        Jedis jedis = null;
        try {
            jedis = redisUtil.getJedis();
            return jedis.exists(key);
        } finally {
            returnToPool(jedis);
        }
    }

    /**
     * 把bean转换成json
     *
     * @param value
     * @param <T>
     * @return
     */
    private <T> String beanToString(T value) {
        if (value == null)
            return null;
        Class<?> aClass = value.getClass();
        if (aClass == Integer.class) {
            return "" + value;
        } else if (aClass == String.class) {
            return (String) value;
        } else if (aClass == Long.class) {
            return "" + value;
        } else {
            return JSON.toJSONString(value);
        }
    }

    /**
     * 把json转换成bean
     */
    private <T> T stringToBean(String str, Class<T> aClass) {
        if (str == null || str.length() <= 0 || aClass == null)
            return null;
        if (aClass == int.class || aClass == Integer.class) {
            return (T) Integer.valueOf(str);
        } else if (aClass == String.class) {
            return (T) str;
        } else if (aClass == long.class || aClass == Long.class) {
            return (T) Long.valueOf(str);
        } else {
            return JSON.parseObject(str, aClass);
        }
    }

    /**
     * 关闭jedis
     *
     * @param jedis
     */
    private void returnToPool(Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }
    }

}


