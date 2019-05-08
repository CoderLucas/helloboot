package com.helloboot.util.cache;

import org.springframework.lang.NonNull;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author lujianhao
 * @date 2019/5/7
 */
public class CacheMemoryUtils {

    private static final int DEFAULT_MAX_COUNT = 1024;

    private static final Map<String, CacheValue> CACHE_MAP = new ConcurrentHashMap<>();

    /**
     * Put in cache
     *
     * @param key   the key of cache
     * @param value the value of cache
     */
    public static void put(@NonNull final String key, final Object value) {
        put(key, value, -1);
    }

    /**
     * Put in cache
     *
     * @param key      the key of cache
     * @param value    the value of cache
     * @param saveTime the save time of cache, in seconds
     */
    public static void put(@NonNull final String key, final Object value, int saveTime) {
        if (value == null) {
            return;
        }
        if (CACHE_MAP.size() >= DEFAULT_MAX_COUNT) {
            clearExpired();
        }
        if (CACHE_MAP.size() >= DEFAULT_MAX_COUNT) {
            return;
        }
        long dueTime = saveTime < 0 ? -1 : System.currentTimeMillis() + saveTime * 1000;
        CACHE_MAP.put(key, new CacheValue(dueTime, value));
    }

    /**
     * Return the value of cache
     *
     * @param key the key of cache
     * @param <T> the type of cache
     * @return the value of cache
     */
    public <T> T get(@NonNull final String key) {
        return get(key, null);
    }

    /**
     * Return the value of cache
     *
     * @param key          the key of cache
     * @param defaultValue the default value if the cache doesn't exist
     * @param <T>          the type of cache
     * @return the value of cache
     */
    public <T> T get(@NonNull final String key, final T defaultValue) {
        CacheValue val = CACHE_MAP.get(key);
        if (val == null) {
            return defaultValue;
        }
        if (val.dueTime == -1 || val.dueTime >= System.currentTimeMillis()) {
            //noinspection unchecked
            return (T) val.value;
        }
        CACHE_MAP.remove(key);
        return defaultValue;
    }

    /**
     * Remove the cache by key
     *
     * @param key the key of cache
     */
    public static void remove(@NonNull final String key) {
        CACHE_MAP.remove(key);
    }

    /**
     * Determines whether the cache is full
     *
     * @return
     */
    public static boolean isMax() {
        if (CACHE_MAP.size() >= DEFAULT_MAX_COUNT) {
            return true;
        }
        return false;
    }

    /**
     * Clear expired caches
     */
    public static void clearExpired() {
        for (Map.Entry<String, CacheValue> entry : CACHE_MAP.entrySet()) {
            String key = entry.getKey();
            if (entry.getValue().dueTime < System.currentTimeMillis()) {
                CACHE_MAP.remove(key);
            }
        }
    }

    /**
     * Clear all caches
     */
    public static void clear() {
        CACHE_MAP.clear();
    }

    /**
     * Return the count of cache.
     *
     * @return the count of cache
     */
    public static int getCacheCount() {
        return CACHE_MAP.size();
    }

    private static final class CacheValue {
        long dueTime;
        Object value;

        CacheValue(long dueTime, Object value) {
            this.dueTime = dueTime;
            this.value = value;
        }
    }
}
