package com.switchsky.config.redis;



public interface RedisService {

    /**
     * redis string类型，获取单个对象
     *
     * @param key   键
     * @param clazz 取出的数据类型
     * @param <T>
     * @return
     */
    public <T> T get(String key, Class<T> clazz);

    /**
     * redis string类型，存储单个对象
     *
     * @param key   键
     * @param value 键值
     * @return
     */
    public <T> boolean set(String key, T value);

    /**
     * redis hash类型，存储单个对象
     *
     * @param key   键
     * @param value 值
     * @param <T>
     * @return
     */
    public <T> boolean hset(String key,String field, T value);


    /**
     * redis hash类型，获取单个对象
     *
     * @param key   键
     * @param clazz 取出的数据类型
     * @param <T>
     * @return
     */
    public <T> T hget(String key, String field, Class<T> clazz);
    /**
     * 为给定得key设置时间，以秒计
     * @param key 键
     * @param seconds 秒
     * @return
     */
    public boolean pexpire(String key,int seconds);
    /**
     * 删除key
     *
     * @param key 键，一个或多个
     * @return
     */
    public boolean del(String... key);

    /**
     * redis list类型，存储单个对象
     *
     * @param key   键
     * @param value 值
     * @param <T>
     * @return
     */
    public <T> boolean lpust(String key, T value);


    /**
     * redis list类型，获取列表元素
     * @param key 键
     * @param <T>
     * @return
     */
    public <T> T lindex(String key, Integer index,Class<T> clazz);

    /**
     * 判断是否存在
     *
     * @param key
     * @param <T>
     * @return
     */
    public <T> boolean exists(String key);


}


